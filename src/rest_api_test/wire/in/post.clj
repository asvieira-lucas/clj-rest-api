(ns rest-api-test.wire.in.post
  (:require [schema.core :as s]))

(s/defschema PostRequest
  {:title   (s/constrained s/Str (fn [title] (> (count title) 5)))
   :content (s/constrained s/Str #(> (count %) 50))})
