DROP DATABASE IF EXISTS jpw;
CREATE DATABASE jpw DEFAULT CHARACTER SET utf8;

use jpdb;

##new user
GRANT ALL ON jpdb.* TO ganjp@'115.66.145.145' IDENTIFIED BY 'ganjp';

##修改用户信息
#update user set password=password('ganjp') where user='root';


##删除用户
#DELETE FROM user WHERE User="ganjp";
DELETE FROM user WHERE User="cossadmin";

COMMIT;
flush privileges;

