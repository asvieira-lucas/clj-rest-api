(ns rest-api-test.interceptors.now
  (:require [io.pedestal.interceptor :as interceptor])
  (:import [java.time Instant]))

(def interceptor
  (interceptor/interceptor
   {:name  :now-interceptor
    :enter (fn [context]
             (update context :request assoc :now (Instant/now)))}))
