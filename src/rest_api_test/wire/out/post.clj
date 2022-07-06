(ns rest-api-test.wire.out.post
  (:require [schema.core :as s]))

(s/defschema PostResponse
  {:id         s/Uuid
   :title      s/Str
   :content    s/Str
   :created-at s/Inst})
