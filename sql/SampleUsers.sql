INSERT INTO users(username,first_name,last_name,user_password) 
VALUES('csanders1','Charles','Sanders','asdf');

insert into user_address(username_id,address,city,country,state,zipcode) 
values ('csanders1','111 EEE ST.','Char','USA','Hawaii','33344');

INSERT INTO users(username,first_name,last_name,address,city,country,zipcode,state,user_password) 
VALUES('reg242','Ashley','Raulson','555 werte Rd.','Qert','USA','33346','Hawaii','qwerty');

INSERT INTO users(username,first_name,last_name,user_password) 
VALUES('flack15','Charles','Sanders','hjkl');

insert into accounts(username_id,account_number,account_name,balance)
values('reg242','6400760044','Checking',250.00);

insert into accounts(username_id,account_number,account_name,balance)
values('csanders1','2500367034','Checking',550.00);

SELECT * FROM accounts WHERE current_username IN 
(SELECT username FROM users);

select * from users;
select * from user_address;
select * from accounts;

SELECT balance FROM accounts WHERE username IN 
(SELECT username FROM users WHERE username = 'reg242');