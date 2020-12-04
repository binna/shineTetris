SELECT * FROM shineTetris.tcm_member;

ALTER TABLE shineTetris.tcm_member CHANGE user_pw USER_PW varchar(1000);

SELECT USER_ID username, USER_PW password FROM shineTetris.tcm_member;

UPDATE shineTetris.tcm_member set enabled = '1';