/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50703
 Source Host           : localhost:3306
 Source Schema         : training

 Target Server Type    : MySQL
 Target Server Version : 50703
 File Encoding         : 65001

 Date: 07/12/2018 11:42:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record`;
CREATE TABLE `t_chat_record`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '連番',
  `student_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学员编号（关联用户信息表中的user_id）',
  `teacher_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `chat_log` blob NULL COMMENT '聊天记录',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `insert_person` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_person` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`, `student_id`, `teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_learn_record
-- ----------------------------
DROP TABLE IF EXISTS `t_learn_record`;
CREATE TABLE `t_learn_record`  (
  `student_id` int(11) NOT NULL COMMENT '学员编号（关联用户信息表中的user_id）',
  `train_item_id` int(11) NOT NULL COMMENT '培训项目编号',
  `category_id` int(11) NOT NULL COMMENT '培训类别编号',
  `train_id` int(11) NOT NULL COMMENT '培训資料编号',
  `learn_percent` decimal(10, 0) NULL DEFAULT NULL COMMENT '学习百分比（单个项目学习的百分比）',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '是否禁用(0:正常 1：禁用)',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `insert_person` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_person` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`student_id`, `train_item_id`, `category_id`, `train_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `t_operator_log`;
CREATE TABLE `t_operator_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `module` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块名称',
  `method` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `status_desc` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `args` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工ID-员工编号生成规则',
  `ip` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人IP',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_student_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `t_student_evaluate`;
CREATE TABLE `t_student_evaluate`  (
  `student_id` int(11) NOT NULL COMMENT '学生番号',
  `user_id` int(11) NOT NULL COMMENT '連番',
  `evaluate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生評価',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '是否禁用(0:正常 1：禁用)',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `insert_person` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `update_person` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`student_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_train
-- ----------------------------
DROP TABLE IF EXISTS `t_train`;
CREATE TABLE `t_train`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'トレーニング資料種別',
  `source_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料パス',
  `source_title` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'トレーニング資料タイトル',
  `source_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料紹介',
  `source_draw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング資料画像',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '作成時間',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新時間',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`, `source_title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_train_category
-- ----------------------------
DROP TABLE IF EXISTS `t_train_category`;
CREATE TABLE `t_train_category`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'トレーニング種別',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '種別名称',
  `insert_date` datetime(0) NULL DEFAULT NULL COMMENT '記録時間',
  `train_teacher` int(11) NOT NULL COMMENT 'トレーニング教师',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '使用状態(0:正常使用 1：禁止使用)',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '作成時間',
  `insert_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作成人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新時間',
  `update_person` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`, `train_teacher`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_train_item
-- ----------------------------
DROP TABLE IF EXISTS `t_train_item`;
CREATE TABLE `t_train_item`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'トレーニングID',
  `category_id` int(11) NOT NULL COMMENT 'トレーニング種別',
  `train_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニングタイトル',
  `train_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング紹介',
  `train_draw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'トレーニング画像',
  `source_type` tinyint(4) NOT NULL COMMENT 'トレーニング資料種別（0：テキスト 1：ビデオ）',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '作成時間',
  `insert_person` int(11) NULL DEFAULT NULL COMMENT '作成人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新時間',
  `update_person` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`, `category_id`, `source_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ユーザーID',
  `no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '番号',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'パスワード',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名前',
  `age` int(11) NULL DEFAULT NULL COMMENT '年齢',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性別',
  `birthday` date NULL DEFAULT NULL COMMENT '誕生日',
  `apply_date` date NULL DEFAULT NULL COMMENT 'エントリーの日付',
  `vocational` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '職歴',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紹介',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '備考',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '類別(0:管理员 1：老师 2：学生)',
  `use_status` tinyint(4) NULL DEFAULT NULL COMMENT '是否禁用(0:正常 1：禁用)',
  `insert_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '作成時間',
  `insert_person` int(11) NULL DEFAULT NULL COMMENT '作成人',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新時間',
  `update_person` int(11) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`, `no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
