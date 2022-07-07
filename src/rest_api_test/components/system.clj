(ns rest-api-test.components.system
  (:require [cheshire.generate]
            [com.stuartsierra.component :as component]
            [rest-api-test.components.db :as components.db]
            [rest-api-test.components.http :as http]
            [rest-api-test.routes :as routes])
  (:import [java.time Instant]))

(defn extend-protocols
  []
  (extend-protocol cheshire.generate/JSONable
    Instant
    (to-json [dt gen]
      (cheshire.generate/write-string gen (str dt)))))

(defn system-map
  [config]
  (extend-protocols)

  (component/system-map
   :db (components.db/database)
   :http-server (component/using (http/server routes/routes) [:db])))
