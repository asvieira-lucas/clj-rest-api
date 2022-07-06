(defproject rest-api-test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.stuartsierra/component "1.1.0"]
                 [com.h2database/h2 "1.4.200"]
                 [com.github.seancorfield/next.jdbc "1.2.780"]
                 [io.pedestal/pedestal.service "0.5.10"]
                 [io.pedestal/pedestal.service-tools "0.5.10"] ;; Only needed for ns-watching; WAR tooling
                 [io.pedestal/pedestal.jetty "0.5.10"]
                 [io.pedestal/pedestal.immutant "0.5.10"]
                 [hikari-cp "2.14.0"]
                 [prismatic/schema "1.3.0"]]
  :repl-options {:init-ns user})
