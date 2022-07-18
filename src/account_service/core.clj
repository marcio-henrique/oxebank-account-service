(ns account-service.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.util.request :refer :all]
            [account-service.query :refer :all]
            [cheshire.core :refer :all])
  (:gen-class))

(defn parse-int [s]
  (Integer. (re-find  #"\d+" s)))

;users
(defn users-index [req]
  {:status  200
   :headers {"Content-Type" "application-json"}
   :body    (->>
             (generate-string (get-users)))})

(defn users-show
  [req]
  (let [id (:id (:params req))
        data (get-user id)]
    {:status  200
     :headers {"Content-Type" "application-json"}
     :body    (->>
               (generate-string data))}))


;banking-accounts
(defn banking-accounts-index [req]
  {:status  200
   :headers {"Content-Type" "application-json"}
   :body    (->>
             (generate-string (get-banking-accounts)))})

(defn banking-accounts-show
  [req]
  (let [id (:id (:params req))
        data (get-banking-account id)]
    {:status  200
     :headers {"Content-Type" "application-json"}
     :body    (->>
               (generate-string data))}))

(defn banking-accounts-create
  [params]
  (let [{:keys [client_id type]} params]
    {:status  200
     :headers {"Content-Type" "application-json"}
     :body    (->>
               (generate-string (add-banking-account client_id type)))}))


;movimentations

(defn deposit-account
  [params]
  (let [{:keys [id value]} params]
    {:status  200
     :headers {"Content-Type" "application-json"}
     :body    (->>
               (change-account-balance id
                  (+ (get-in (get-account-balance id) [0 :balance]) (parse-int value))) (str "Depósito realizado."))}))

(defn withdrawal-account-body
  [id value]
  (if (<= (parse-int value) (get-in (get-account-balance id) [0 :balance]))
    (change-account-balance id (- (get-in (get-account-balance id) [0 :balance]) (parse-int value)))
    (str "Não foi possível realizar o saque.")))

(defn withdrawal-account
  [params]
  (let [{:keys [id value]} params]
    {:status  200
     :headers {"Content-Type" "application-json"}
     :body    (->>
               (withdrawal-account-body id value))}))



(defroutes app-routes

  (GET "/users" [] users-index)
  (GET "/users/:id" [] users-show)

  (GET "/banking-accounts" [] banking-accounts-index)
  (GET "/banking-accounts/:id" [] banking-accounts-show)

  (POST "/banking-accounts" {:keys [params]}
    (banking-accounts-create params))

  (POST "/deposit-account" {:keys [params]}
    (deposit-account params))


  (POST "/withdrawal-account" {:keys [params]}
    (withdrawal-account params))

  (route/not-found "Error, page not found!"))

(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)) {:port port})
    ; Run the server without ring defaults
    ;(server/run-server #'app-routes {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))