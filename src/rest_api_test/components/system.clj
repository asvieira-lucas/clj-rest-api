(ns rest-api-test.components.system
  (:require [com.stuartsierra.component :as component]
            [rest-api-test.components.db :as components.db]
            [rest-api-test.components.http :as http]
            [rest-api-test.routes :as routes]))

(defn system-map [config]
  (component/system-map
   :db (components.db/database)
   :http-server (component/using (http/server routes/routes) [:db])))
