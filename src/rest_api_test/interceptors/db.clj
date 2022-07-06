(ns rest-api-test.interceptors.db
  (:require [io.pedestal.interceptor :as interceptor]))

(defn interceptor
  [db]
  (interceptor/interceptor
   {:name  :db-interceptor
    :enter (fn [context]
             (update context :request assoc :db db))}))
