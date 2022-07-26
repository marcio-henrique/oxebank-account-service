(ns account-service.database 
  (:require [korma.db :as korma]))

(def db-connection-info
  (korma/mysql
   {:classname "com.mysql.jdbc.Driver"
    :subprotocol "mysql"
    :user "root"
    :password "senha123"
    :db "oxebank_account_service"
    :subname "//localhost:3306/oxebank_account_service?autoReconnect=true&useSSL=false"}))

; set up korma
(korma/defdb db db-connection-info)