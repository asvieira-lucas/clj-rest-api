(ns rest-api-test.db.config
  (:require [clojure.java.io :as io]
            [next.jdbc :as jdbc]))

(defn- tables-file
  []
  (-> (io/resource "tables.sql")
      io/file
      slurp))

(defn load-tables!
  [db]
  (let [tables-stmt (tables-file)]
    (jdbc/execute! (db) [tables-stmt])))
