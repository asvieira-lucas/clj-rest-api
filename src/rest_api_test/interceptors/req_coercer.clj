(ns rest-api-test.interceptors.req-coercer
  (:require [io.pedestal.interceptor :as interceptor]
            [schema.core :as s]))

(defn interceptor
  [schema]
  (interceptor/interceptor
    {:name  :req-coercer
     :enter (fn [{{:keys [json-params]} :request :as context}]
              (s/validate schema json-params)
              context)}))
