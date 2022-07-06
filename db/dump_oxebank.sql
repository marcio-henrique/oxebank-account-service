INSERT INTO oxebank_account_service.users (`type`,email,status,address,password,phone,api_token,name) VALUES
	 (1,'email@email.com',1,'teste_address','123456','8299999999',NULL,'Marcio'),
	 (1,'teste@email.com',1,'endereco','789456','9999999999',NULL,'Eduardo');

INSERT INTO oxebank_account_service.clients (user_id,`type`,mounthly_income,official_document) VALUES
	 (1,1,520025,222222222),
	 (2,1,12005,11111111);

INSERT INTO oxebank_account_service.banking_accounts (client_id,`type`,status,balance,password,api_token) VALUES
	 (1,1,1,255,'123456',NULL),
	 (1,1,1,20,'123456',NULL);