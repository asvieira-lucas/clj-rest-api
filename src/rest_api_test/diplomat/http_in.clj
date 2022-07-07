(ns rest-api-test.diplomat.http-in
  (:require [rest-api-test.adapters.post :as adapters.post]
            [rest-api-test.controllers.post :as controllers.post]))

(defn create-post!
  [{:keys [now db] req :json-params}]
  {:status 201
   :body   (-> req
               (adapters.post/request->model now)
               (controllers.post/create! db)
               rest-api-test.adapters.post/model->response)})
