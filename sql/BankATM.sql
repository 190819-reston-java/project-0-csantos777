--CREATE DATABASE BankATM;

CREATE TABLE users (

	id SERIAL PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	zipcode VARCHAR(5) NULL
	
);

CREATE TABLE account_numbers (

	account_number INTEGER,
	balance NUMERIC CHECK(balance >= 0.0),
	user_id SERIAL,
	FOREIGN KEY (user_id) REFERENCES users(id)
	
);

--create table BankATM.trnsactn_history (

	--constraint a

--);

