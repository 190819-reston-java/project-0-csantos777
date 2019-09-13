INSERT INTO users(username,first_name,last_name,address,city,country,zipcode,state,user_password) 
VALUES('csanders1','Charles','Sanders','111 EEE ST.','Char','USA','33344','Hawaii','asdf');

INSERT INTO users(username,first_name,last_name,address,city,country,zipcode,state,user_password) 
VALUES('reg242','Ashley','Raulson','555 werte Rd.','Qert','USA','33346','Hawaii','qwerty');

INSERT INTO users(username,first_name,last_name,address,city,country,zipcode,state,user_password) 
VALUES('flack15','Charles','Sanders','6745 Cofe Ln.','Alf','Canada','33344','','hjkl');

insert into accounts(current_username,account_name,account_number,balance)
values('reg242','Checking',34109534,250.00);

insert into accounts(current_username,account_name,account_number,balance)
values('flack15','Checking',52659581,550.00);

SELECT * FROM accounts WHERE current_username IN 
(SELECT username FROM users WHERE username = 'reg242');