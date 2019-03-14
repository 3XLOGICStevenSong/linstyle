/*
 Navicat MySQL Data Transfer

 Source Server         : 210.76.163.54
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : 210.76.163.54:3306
 Source Schema         : train

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 20/12/2018 16:07:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record`;
CREATE TABLE `t_chat_record`  (
  `no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '連番 消息编号',
  `from_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送用户编号',
  `to_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收用户编号',
  `sign_flag` tinyint(1) NOT NULL COMMENT '消息是否已签收 0：未签收  1：已签收',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'チャット記録',
  `insert_date` date NOT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作成人',
  `update_date` date NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`no`, `from_user_id`, `to_user_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
