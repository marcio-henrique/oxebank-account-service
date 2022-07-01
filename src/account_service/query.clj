(ns account-service.query
  (:require [account_service.database]
            [korma.core :refer :all]))

(defentity clients)

(defentity users
  (has-one clients {:fk :user_id}))

(defn get-users []
  (select users
          (fields :id :type :email :status :address :phone)
          (with clients (fields [:id :client_id] :mounthly_income :official_document [:type :client_type]))))

(defentity banking_accounts
  (belongs-to clients {:fk :client_id}))

(defn get-banking-accounts []
  (select banking_accounts
          (fields :id :client_id :type :status :balance)))