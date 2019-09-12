--CREATE DATABASE BankATM;

CREATE TABLE users (

	username VARCHAR(12) UNIQUE PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	state VARCHAR(15) NULL,
	zipcode VARCHAR(5) NULL
	
);

CREATE TABLE account_numbers (

	account_number INTEGER,
	balance NUMERIC CHECK(balance >= 0.0),
	current_username VARCHAR(12) UNIQUE,
	FOREIGN KEY (current_username) REFERENCES users(username)
	
);

drop schema account_numbers;

--create table BankATM.trnsactn_history (

	--constraint a

--);

