(ns rest-api-test.model.post
  (:require [schema.core :as s]))

(defn- str-long-enough?
  [length str]
  (> (count str) length))

(s/defschema Post
  {:id         s/Uuid
   :title      (s/constrained s/Str (partial str-long-enough? 5))
   :content    (s/constrained s/Str (partial str-long-enough? 50))
   :created-at s/Inst})
