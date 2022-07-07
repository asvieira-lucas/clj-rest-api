(ns rest-api-test.routes
  (:require [rest-api-test.diplomat.http-in :as http-in]
            [rest-api-test.interceptors.log :as interceptors.log]
            [rest-api-test.wire.in.post :as in.post]
            [rest-api-test.wire.out.post :as out.post]))

(defn echo
  [{:keys [json-params]}]
  {:status 200 :body json-params})

(def routes
  {:echo        {:path         "/echo"
                 :method       :post
                 :handler      echo
                 :interceptors [interceptors.log/interceptor]}
   :create-post {:path       "/posts"
                 :method     :post
                 :req-schema in.post/PostRequest
                 :res-schema {201 out.post/PostResponse}
                 :handler    http-in/create-post!}})
