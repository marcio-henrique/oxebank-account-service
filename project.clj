(defproject account-service "0.1.0-SNAPSHOT"
  :description "Demo Clojure web app"
  :url "http://clojure-getting-started.herokuapp.com"
  :min-lein-version "2.0.0"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [mysql/mysql-connector-java "5.1.44"]
                 [http-kit "2.3.0"]
                 [ring/ring-defaults "0.3.2"]
                 [compojure "1.6.1"]
                 [org.clojure/data.json "0.2.6"]
                 [korma "0.3.0-RC5"]
                 [cheshire "5.11.0"]
                 ]
  :main ^:skip-aot account-service.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})