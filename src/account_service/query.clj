(ns account-service.query
  (:require [account_service.database]
            [korma.core :refer :all]))

(defentity clients)

(defentity users
  (has-one clients {:fk :user_id}))

(defn get-users []
  (select users
          (fields :id :type :email :status :address :phone)
          (with clients)))