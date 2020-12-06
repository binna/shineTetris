SELECT * FROM shineTetris.tcm_member;

ALTER TABLE shineTetris.tcm_member CHANGE password user_pw varchar(1000);
ALTER TABLE shineTetris.tcm_member CHANGE username user_id varchar(45);
ALTER TABLE shineTetris.tcm_member CHANGE USER_NM user_name varchar(20);
ALTER TABLE shineTetris.tcm_member ADD authority varchar(100);

SELECT USER_ID username, USER_PW password FROM shineTetris.tcm_member;

UPDATE shineTetris.tcm_member set authority = 'ROLE_USER';