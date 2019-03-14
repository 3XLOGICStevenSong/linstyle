CREATE TABLE `chat_msg` (
  `id` varchar(64) NOT NULL,
  `send_user_id` varchar(64) NOT NULL,
  `accept_user_id` varchar(64) NOT NULL,
  `msg` varchar(255) NOT NULL,
  `sign_flag` int(1) NOT NULL COMMENT '消息是否签收状态\r\n1：签收\r\n0：未签收\r\n',
  `create_time` datetime NOT NULL COMMENT '发送请求的事件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(64) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '用户名，账号，微信号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `face_image` varchar(255) NOT NULL COMMENT '我的头像，如果没有默认给一张',
  `face_image_big` varchar(255) NOT NULL,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
  `qrcode` varchar(255) NOT NULL COMMENT '新用户注册后默认后台生成二维码，并且上传到fastdfs',
  `cid` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

