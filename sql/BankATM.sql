CREATE DATABASE BankATM;

CREATE TABLE users (

	username VARCHAR(15) UNIQUE PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	user_password varchar(50) NOT NULL
	
);

CREATE TABLE user_address (

	username_id VARCHAR(15) UNIQUE PRIMARY KEY,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	state VARCHAR(15) NULL,
	zipcode VARCHAR(5) NULL,
	FOREIGN KEY (username_id) REFERENCES users(username)
	
);

CREATE TABLE accounts (

	username_id VARCHAR(15) UNIQUE PRIMARY KEY,
	account_number VARCHAR(10) UNIQUE NOT NULL,
	account_name VARCHAR(15),
	balance NUMERIC CHECK(balance >= 0.0),
	FOREIGN KEY (username_id) REFERENCES users(username)
	
);

CREATE TABLE transaction_history (

	username_id VARCHAR(15) UNIQUE,
	account_number VARCHAR(10) UNIQUE,
	balance NUMERIC CHECK(balance >= 0.0),
	date_of_transaction date_part,
	FOREIGN KEY (username_id) REFERENCES users(username),
	FOREIGN KEY (account_number) REFERENCES accounts(account_number)
	PRIMARY KEY (username_id,account_number);
	
);
