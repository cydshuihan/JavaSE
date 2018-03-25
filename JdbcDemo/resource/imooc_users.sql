/*
Navicat MySQL Data Transfer

Source Server         : cyd
Source Server Version : 50555
Source Host           : localhost:3306
Source Database       : imooc

Target Server Type    : MYSQL
Target Server Version : 50555
File Encoding         : 65001

Date: 2018-03-25 15:50:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for imooc_users
-- ----------------------------
DROP TABLE IF EXISTS `imooc_users`;
CREATE TABLE `imooc_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `sex` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `create_user` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `updae_user` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `updae_date` date DEFAULT NULL,
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;
