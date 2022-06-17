(defproject account-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [compojure "1.7.0"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-json "0.5.1"]
                 [korma "0.4.3"]
                 [mysql/mysql-connector-java "8.0.29"]
                 [ring/ring-defaults "0.3.3"]]
  :main ^:skip-aot account-service.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
