create database oxebank_account_service;
use oxebank_account_service;

CREATE table `users` (
`id` bigint PRIMARY KEY AUTO_INCREMENT,
`type` int,
`name` varchar(80),
`email` varchar(80),
`status` int,
`address` varchar(255),
`password` varchar(80),
`phone` varchar(50),
`api_token` varchar(80)
);

CREATE TABLE `clients` (
`id` int PRIMARY KEY AUTO_INCREMENT,
`user_id` bigint,
`type` int,
`mounthly_income` int,
`official_document` int
);

CREATE TABLE `banking_accounts` (
`id` int PRIMARY KEY AUTO_INCREMENT,
`client_id` int,
`type` int,
`status` int,
`balance` decimal(10, 0),
`password` varchar(80),
`api_token` varchar(80)
);

ALTER TABLE `clients` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `banking_accounts` ADD FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`);