(ns rest-api-test.routes
  (:require [rest-api-test.interceptors.log :as interceptors.log]))

(defn echo
  [{:keys [json-params]}]
  {:status 200 :body json-params})

(def routes
  {:echo {:path         "/echo"
          :method       :post
          :handler      echo
          :interceptors [interceptors.log/interceptor]}})
