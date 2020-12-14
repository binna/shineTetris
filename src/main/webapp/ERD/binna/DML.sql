SELECT * FROM shineTetris.tcm_member;
DROP TABLE shineTetris.tcm_member;


ALTER TABLE shineTetris.tcm_member CHANGE password user_pw varchar(1000);
ALTER TABLE shineTetris.tcm_member CHANGE username user_id varchar(45);
ALTER TABLE shineTetris.tcm_member CHANGE USER_NM user_name varchar(20);
ALTER TABLE shineTetris.tcm_member ADD authority varchar(100);

SELECT USER_ID username, USER_PW password FROM shineTetris.tcm_member;

UPDATE shineTetris.tcm_member set authority = 'ROLE_USER';

CREATE TABLE shineTetris.tcm_member(
	user_seq INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id VARCHAR(45),
	user_pw VARCHAR(1000),
	user_name VARCHAR(20),
	user_email VARCHAR(50),
	user_zipcode VARCHAR(50),
	user_address1 VARCHAR(100),
	user_address2 VARCHAR(100),
	enabled VARCHAR(1) DEFAULT '1',
	authority VARCHAR(10) DEFAULT 'ROLE_USER'
);

SELECT * FROM shineTetris.tcm_member;