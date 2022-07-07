(ns rest-api-test.components.http
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as interceptors.body-params]
            [io.pedestal.http.route :as route]
            [rest-api-test.db.config :as db.config]
            [rest-api-test.interceptors.db :as interceptors.db]
            [rest-api-test.interceptors.error :as interceptors.error]
            [rest-api-test.interceptors.now :as interceptors.now]
            [rest-api-test.interceptors.req-coercer :as interceptors.req-coercer]))

(defn- route
  [[route-name
    {:keys [path method interceptors handler req-schema res-schema] :or {interceptors []}}]]
  (let [interceptors (cond-> interceptors
                             #_#_res-schema (conj (res-coercer res-schema))
                             req-schema (conj (interceptors.req-coercer/interceptor req-schema))
                             (constantly true) (conj handler))]
    [path method interceptors :route-name route-name]))

(defn- expanded-routes
  [routes]
  (->> routes
       (map route)
       set
       route/expand-routes))

(defn- base-server
  [routes]
  {::http/routes (expanded-routes routes)
   ::http/type   :immutant
   ::http/host   "0.0.0.0"
   ::http/join?  false
   ::http/port   5000})

(defn- service-map
  [db routes]
  (-> (base-server routes)
      http/default-interceptors
      (update ::http/interceptors conj interceptors.error/req-coercer-error)
      (update ::http/interceptors conj http/json-body)
      (update ::http/interceptors conj (interceptors.body-params/body-params))
      (update ::http/interceptors conj interceptors.now/interceptor)
      (update ::http/interceptors conj (interceptors.db/interceptor db))))

(defrecord HttpServer [db routes]
  component/Lifecycle

  (start [component]
    (db.config/load-tables! db)

    (->> (service-map db routes)
         http/create-server
         http/start
         (assoc component :server)))

  (stop [{:keys [server]}]
    (http/stop server)))

(defn server
  [routes]
  (map->HttpServer {:routes routes}))
