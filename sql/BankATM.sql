CREATE DATABASE BankATM;

CREATE TABLE BankATM.users (

	id INT UNIQUE NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	address VARCHAR(30) NOT NULL,
	city VARCHAR(20) NOT NULL,
	country VARCHAR(20) NOT NULL,
	zipcode VARCHAR(5) null
	
);

CREATE TABLE BankATM.account_numbers (

	account_number INT,
	balance REAL,
	CONSTRAINT id_mark FOREIGN KEY (id),
	CONSTRAINT no_negative_balance CHECK (balance >= 0.00) 
	
);

--create table BankATM.trnsactn_history (

	--constraint a

--);

