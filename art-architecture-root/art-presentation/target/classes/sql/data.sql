INSERT INTO `yltappdb`.`sys_back_user`(
  `BACK_USER_ID`,`LOGIN_CODE`,`USER_NAME`,`PASSWORD`,`SALT`,
  `STATUS`,`CREATE_TIME`,`UPDATE_TIME`,`LAST_LOGIN_IP`
)VALUES (
 1, 'admin', '管理员', '2e54299e2e3d84481888a89a098dd1d4c3b8cc9048e848f8ab267edf08d6475ac89312380187f467a9a9776e4874e27b9786bb70d7404850e2d1db2917fe1667', 
 'A1DAF854D0F8AB3FE5E932EA41E02EBB0D23DEE3E219B5A8F47F805B02D7865F', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null
),(
 2, 'user', '普通用户', '2e54299e2e3d84481888a89a098dd1d4c3b8cc9048e848f8ab267edf08d6475ac89312380187f467a9a9776e4874e27b9786bb70d7404850e2d1db2917fe1667', 
 'A1DAF854D0F8AB3FE5E932EA41E02EBB0D23DEE3E219B5A8F47F805B02D7865F', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null
);

INSERT INTO `yltappdb`.`sys_back_role`
(
  `ROLE_ID`,`ROLE_NAME`,`STATUS`,`CREATE_TIME`,`UPDATE_TIME`
)VALUES(
  1,'系统管理员',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
),(
  2,'PG',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
),(
  3,'TL',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
),(
  4,'SE',1,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
);