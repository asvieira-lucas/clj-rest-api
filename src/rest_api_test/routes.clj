(ns rest-api-test.routes
  (:require [io.pedestal.interceptor :as interceptor]))

(defn echo
  [{:keys [json-params]}]
  {:status 200 :body json-params})

(defn hello-world
  [{:keys [now db]}]
  {:status 200 :body {:data "Hello, world!"}})

(def logger
  (interceptor/interceptor
    {:name  ::logger
     :enter (fn [context]
              (println (str "context: " context)))}))

(def routes
  {:hello-world {:path    "/hello"
                 :method  :get
                 :handler hello-world}
   :echo        {:path         "/echo"
                 :method       :post
                 :handler      echo
                 :interceptors [logger]}})
