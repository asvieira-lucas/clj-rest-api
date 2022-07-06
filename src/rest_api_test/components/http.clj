(ns rest-api-test.components.http
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [rest-api-test.db.config :as db.config]
            [rest-api-test.interceptors.db :as interceptors.db]
            [rest-api-test.interceptors.now :as interceptors.now]))

(defn- base-server
  [routes]
  {::http/routes (route/expand-routes routes)
   ::http/type   :immutant
   ::http/host   "0.0.0.0"
   ::http/join?  false
   ::http/port   5000})

(defn- service-map
  [db routes]
  (-> (base-server routes)
      http/default-interceptors
      (update ::http/interceptors conj http/json-body)
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
