(ns rest-api-test.interceptors.log
  (:require [io.pedestal.interceptor :as interceptor]))

(def interceptor
  (interceptor/interceptor
    {:name  ::logger
     :enter (fn [context]
              (println (str "context: " context)))}))
