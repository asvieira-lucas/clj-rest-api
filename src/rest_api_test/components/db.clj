(ns rest-api-test.components.db
  (:require [next.jdbc.connection :as connection])
  (:import [com.zaxxer.hikari HikariDataSource]))

(def ^:private db-config
  {:dbtype "h2:mem" :dbname "testing"})

(defn database [] (connection/component HikariDataSource db-config))
