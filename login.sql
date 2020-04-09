/*
 Navicat Premium Data Transfer

 Source Server         : ssm
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : login

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 09/04/2020 14:55:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `passward` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES (1, 'bh', '123');
INSERT INTO `tab_user` VALUES (2, 'hah', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tab_user` VALUES (3, 'hah1', '903ce9225fca3e988c2af215d4e544d3');
INSERT INTO `tab_user` VALUES (4, 'qw', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tab_user` VALUES (5, 'wer', '202cb962ac59075b964b07152d234b70');

SET FOREIGN_KEY_CHECKS = 1;
