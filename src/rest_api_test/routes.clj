(ns rest-api-test.routes)

(defn hello-world
  [{:keys [now db]}]
  {:status 200 :body {:data "Hello, world!"}})

(def routes
  #{["/hello"
     :get hello-world
     :route-name :hello-world]})
