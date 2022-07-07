(ns rest-api-test.adapters.post
  (:require [rest-api-test.model.post :as model.post]
            [rest-api-test.wire.in.post :as in.post]
            [rest-api-test.wire.out.post :as out.post]
            [schema.core :as s]))

(s/defn request->model :- model.post/Post
  [{:keys [title content]} :- in.post/PostRequest
   now :- s/Inst]
  #:post{:id         (random-uuid)
         :title      title
         :content    content
         :created-at now})

(s/defn model->response :- out.post/PostResponse
  [{:post/keys [id title content created-at]}]
  {:id         id
   :title      title
   :content    content
   :created-at created-at})
