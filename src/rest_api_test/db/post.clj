(ns rest-api-test.db.post
  (:require [next.jdbc]
            [next.jdbc.sql]
            [rest-api-test.logic.db :as logic.db]))

(defn insert!
  [post db]
  (->> (logic.db/model->db post)
       (next.jdbc.sql/insert! (db) :posts)))

(defn find-all
  [db]
  (->> (next.jdbc/execute! (db) ["SELECT * FROM posts"])
       (map (partial logic.db/db->model :post))))
