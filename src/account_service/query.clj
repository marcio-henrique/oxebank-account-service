(ns account-service.query
  (:require [account_service.database]
            [korma.core :refer :all]))

(defentity clients)

(defentity users
  (has-one clients {:fk :user_id}))

(defn get-users []
  (select users
          (fields :id :type :name :email :status :address :phone)
          (with clients (fields [:id :client_id] :mounthly_income :official_document [:type :client_type]))))

(defn get-user [id]
  (select users
          (fields :id :type :name :email :status :address :phone)
          (with clients (fields [:id :client_id] :mounthly_income :official_document [:type :client_type]))
          (where {:id id})))


(defentity banking_accounts
  (belongs-to clients {:fk :client_id}))

(defn get-banking-accounts []
  (select banking_accounts
          (fields :id :client_id :type :status :balance)))

(defn get-banking-account [id]
  (select banking_accounts
          (fields :id :client_id :type :status :balance)
          (where {:id id})))

(defn add-banking-account [client_id type]
  (insert banking_accounts
          (values {:client_id client_id :type type :status 1 :balance 0})))

(defn get-account-balance [id]
  (select banking_accounts 
          (fields :balance)
          (where {:id id})))
        
(defn change-account-balance [id new-value]
  (update banking_accounts 
          (set-fields {:balance new-value})
          (where {:id id})))
