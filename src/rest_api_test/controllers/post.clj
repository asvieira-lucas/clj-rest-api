(ns rest-api-test.controllers.post
  (:require [rest-api-test.db.post :as db.post]))

(defn create!
  [post db]
  (doto post
    (db.post/insert! db)))

(defn find-all
  [db]
  (db.post/find-all db))
