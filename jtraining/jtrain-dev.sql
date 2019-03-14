/*
 Navicat MySQL Data Transfer

 Source Server         : 210.76.163.54
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : 210.76.163.54:3306
 Source Schema         : jtrain-dev

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 14/01/2019 15:52:25
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
  `insert_date` datetime(0) NOT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  PRIMARY KEY (`no`, `from_user_id`, `to_user_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_learn_record
-- ----------------------------
DROP TABLE IF EXISTS `t_learn_record`;
CREATE TABLE `t_learn_record`  (
  `student_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生番号',
  `train_id` int(11) NOT NULL COMMENT 'トレーニングID',
  `train_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'トレーニングタイトル',
  `source_type` int(11) NOT NULL COMMENT 'トレーニング資料種別',
  `source_no` int(11) NOT NULL COMMENT 'トレーニング資料番号',
  `source_length` int(11) NULL DEFAULT NULL COMMENT 'トレーニング資料規模(ビデオ:秒 テキスト:頁)',
  `source_learn_length` int(11) NULL DEFAULT NULL COMMENT 'トレーニング資料勉強規模(ビデオ:秒 テキスト:頁)',
  `learn_percent` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成パーセント',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '有効フラグ(0:有効 1:無効)',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`student_id`, `train_id`, `train_title`, `source_type`, `source_no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_source_category
-- ----------------------------
DROP TABLE IF EXISTS `t_source_category`;
CREATE TABLE `t_source_category`  (
  `t_source_category` int(11) NOT NULL AUTO_INCREMENT COMMENT 'リソースの種別',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '種別名称',
  `insert_time` datetime(0) NULL DEFAULT NULL COMMENT '記録時間',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '有効フラグ(0:有効 1:無効)',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`t_source_category`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_student_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `t_student_evaluate`;
CREATE TABLE `t_student_evaluate`  (
  `student_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生番号',
  `teacher_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '評価教師',
  `evaluate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生評価',
  `evaluate_time` datetime(0) NULL DEFAULT NULL COMMENT '評価時間',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '有効フラグ(0:有効 1:無効)',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`teacher_id`, `student_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_train_category
-- ----------------------------
DROP TABLE IF EXISTS `t_train_category`;
CREATE TABLE `t_train_category`  (
  `train_type` int(11) NOT NULL AUTO_INCREMENT COMMENT 'トレーニング種別',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '種別名称',
  `insert_time` date NULL DEFAULT NULL COMMENT '記録時間',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '有効フラグ(0:有効 1:無効)',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`train_type`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_train_item
-- ----------------------------
DROP TABLE IF EXISTS `t_train_item`;
CREATE TABLE `t_train_item`  (
  `train_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'トレーニングID',
  `train_type` int(11) NULL DEFAULT NULL COMMENT 'トレーニング種別',
  `train_teacher` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング教师',
  `train_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニングタイトル',
  `train_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング紹介',
  `train_draw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング画像',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`train_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 152 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_train_source
-- ----------------------------
DROP TABLE IF EXISTS `t_train_source`;
CREATE TABLE `t_train_source`  (
  `source_no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'トレーニング資料番号',
  `source_type` int(11) NULL DEFAULT NULL COMMENT 'トレーニング資料種別',
  `source_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料パス',
  `source_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料タイトル',
  `source_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料紹介',
  `source_draw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料画像',
  `source_length` int(11) NULL DEFAULT NULL COMMENT 'トレーニング資料規模(ビデオ:秒 テキスト:頁)',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`source_no`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 195 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_train_source_ralation
-- ----------------------------
DROP TABLE IF EXISTS `t_train_source_ralation`;
CREATE TABLE `t_train_source_ralation`  (
  `train_id` int(11) NOT NULL COMMENT 'トレーニングID',
  `source_no` int(11) NOT NULL COMMENT 'トレーニング資料番号',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`train_id`, `source_no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '番号',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'パスワード',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名前',
  `train_type` int(11) NULL DEFAULT NULL COMMENT 'トレーニング種別',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性別',
  `birthday` date NULL DEFAULT NULL COMMENT '誕生日',
  `apply_date` date NULL DEFAULT NULL COMMENT 'エントリーの日付',
  `vocational` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '職歴',
  `person_draw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本人画像',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紹介',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '備考',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '類別(0:管理员 1：老师 2：学生)',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '有効フラグ(0:有効 1:無効)',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '作成日',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`no`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('Admin', 'QjREQzI0MkZDMTU0RDY0RjZCMzMxQTBFQUQ2MTAwRTg=', 'Admin', 0, 0, '2019-01-04', '2019-01-04', 'string', 'string', 'string', 'string', 0, NULL, '2019-01-04 00:00:00', 'Admin', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
