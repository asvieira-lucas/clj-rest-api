(ns rest-api-test.interceptors.error
  (:require [io.pedestal.interceptor.error :as interceptor.error]))

(def req-coercer-error
  (interceptor.error/error-dispatch
    [ctx ex]
    [{:type :schema.core/error :interceptor :req-coercer}]
    (assoc ctx :response {:status 400
                          :body   {:errors (:error (ex-data ex))}})))
