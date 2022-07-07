(ns rest-api-test.logic.db
  (:require [camel-snake-kebab.core :refer [->kebab-case-keyword ->snake_case_keyword]]))

(defn model->db
  [m]
  (update-keys m ->snake_case_keyword))

(defn db->model
  [ns m]
  (update-keys m (fn [k]
                   (->> (name (->kebab-case-keyword k))
                        (keyword (name ns))))))
