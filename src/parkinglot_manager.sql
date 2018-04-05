/*
Navicat MySQL Data Transfer

Source Server         : 停车场管理系统数据库
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : parkinglot_manager

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-04-05 15:05:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'IC卡号',
  `status` varchar(255) DEFAULT NULL COMMENT '卡状态',
  `type` varchar(255) DEFAULT NULL COMMENT '卡类型',
  `money` decimal(10,0) DEFAULT NULL COMMENT '账户余额',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名字',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `available` int(255) DEFAULT NULL COMMENT '可停车总次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('22', '正常', '月卡/年卡', '120', '习近平', '男', '456', '123', '1999-06-06 00:00:00', '1');
INSERT INTO `card` VALUES ('23', '正常', '月卡/年卡', '20', '吴晓燕', '男', '456', '1234', '2011-01-23 00:00:00', '2');
INSERT INTO `card` VALUES ('24', '正常', '月卡/年卡', '30', '刘不已', '男', '456', '1235', '2012-08-15 00:00:00', '3');
INSERT INTO `card` VALUES ('25', '正常', '月卡/年卡', '40', '王大婶', '女', '456', '12356', '2010-09-20 00:00:00', '4');
INSERT INTO `card` VALUES ('26', '正常', '月卡/年卡', '50', '陈武林', '女', '456', '123500', '2003-09-20 00:00:00', '5');
INSERT INTO `card` VALUES ('27', '正常', '月卡/年卡', '60', '李自成', '女', '456', '123545', '1989-07-20 00:00:00', '6');
INSERT INTO `card` VALUES ('28', '正常', '月卡/年卡', '70', '朱自清', '女', '456', '123523', '2003-08-20 00:00:00', '7');
INSERT INTO `card` VALUES ('29', '正常', '月卡/年卡', '80', '邓小平', '男', '456', '1235890', '2003-09-20 00:00:00', '8');
INSERT INTO `card` VALUES ('30', '正常', '月卡/年卡', '90', '李小龙', '女', '456', '123568', '2005-11-30 00:00:00', '9');
INSERT INTO `card` VALUES ('31', '正常', '月卡/年卡', '100', '孙悟空', '女', '456', '12353', '2003-03-20 00:00:00', '10');
INSERT INTO `card` VALUES ('32', '正常', '月卡/年卡', '110', '方滨兴', '女', '456', '1235555', '2000-10-17 00:00:00', '11');
INSERT INTO `card` VALUES ('33', '正常', '月卡/年卡', '120', '习近平', '男', '456', '123', '1999-06-06 00:00:00', '1');
INSERT INTO `card` VALUES ('34', '正常', '月卡/年卡', '30', '刘不已', '男', '456', '1235', '2012-08-15 00:00:00', '3');
INSERT INTO `card` VALUES ('35', '正常', '月卡/年卡', '60', '李自成', '女', '456', '123545', '1989-07-20 00:00:00', '6');
INSERT INTO `card` VALUES ('36', '正常', '月卡/年卡', '50', '陈武林', '女', '456', '123500', '2003-09-20 00:00:00', '5');
INSERT INTO `card` VALUES ('37', '正常', '月卡/年卡', '30', '刘不已', '男', '456', '1235', '2012-08-15 00:00:00', '3');
INSERT INTO `card` VALUES ('38', '正常', '月卡/年卡', '80', '邓小平', '男', '456', '1235890', '2003-09-20 00:00:00', '8');
INSERT INTO `card` VALUES ('39', '正常', '月卡/年卡', '70', '朱自清', '女', '456', '123523', '2003-08-20 00:00:00', '7');
INSERT INTO `card` VALUES ('40', '正常', '月卡/年卡', '110', '方滨兴', '女', '456', '1235555', '2000-10-17 00:00:00', '11');
INSERT INTO `card` VALUES ('41', '正常', '月卡/年卡', '90', '李小龙', '女', '456', '123568', '2005-11-30 00:00:00', '9');
INSERT INTO `card` VALUES ('42', '正常', '月卡/年卡', '110', '方滨兴', '女', '456', '1235555', '2000-10-17 00:00:00', '11');
INSERT INTO `card` VALUES ('43', '正常', '月卡/年卡', '70', '朱自清', '女', '456', '123523', '2003-08-20 00:00:00', '7');

-- ----------------------------
-- Table structure for car_photo
-- ----------------------------
DROP TABLE IF EXISTS `car_photo`;
CREATE TABLE `car_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆抓拍图片id',
  `photo_url` varchar(255) DEFAULT NULL COMMENT '抓拍图片保存的url',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '记录修改时间',
  `creator` int(11) DEFAULT NULL COMMENT '记录创建人id',
  `modifier` int(11) DEFAULT NULL COMMENT '记录修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of car_photo
-- ----------------------------

-- ----------------------------
-- Table structure for manager_info
-- ----------------------------
DROP TABLE IF EXISTS `manager_info`;
CREATE TABLE `manager_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员主键id',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '管理员姓名',
  `id_card` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证号码',
  `sex` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '性别',
  `phone_number` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `photo` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '照片',
  `date_birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `creator` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '记录创建者id',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `modifier` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '记录修改者id',
  `modified_time` datetime DEFAULT NULL COMMENT '记录修改时间',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `account` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13039 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of manager_info
-- ----------------------------
INSERT INTO `manager_info` VALUES ('1', '郑宇轩', '350181198802151312', '男', '18780312331', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照1.jpg', '1988-02-12 00:00:00', '超级管理员admin', '2018-03-07 10:57:04', '超级管理员admin', '2018-03-07 10:57:04', '1', '1', '路径1/路径11/用户1');
INSERT INTO `manager_info` VALUES ('2', '郑铭杰', '350181198702151623', '男', '18757789442', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照3.jpg', '1988-02-10 00:00:00', '超级管理员admin', '2018-03-07 10:57:04', '超级管理员admin', '2018-03-07 10:57:04', '2', '2', '路径1/路径11/用户2');
INSERT INTO `manager_info` VALUES ('3', '郑兴文', '350181198802151312', '男', '18780312331', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照4.jpg', '1988-07-19 00:00:00', '超级管理员admin', '2018-03-07 10:57:04', '超级管理员admin', '2018-03-07 10:57:04', '3', '3', '路径1/路径11/用户3');
INSERT INTO `manager_info` VALUES ('4', '郑自在', '350181198802151312', '男', '18757656331', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照6.jpg', '1988-08-16 00:00:00', '超级管理员admin', '2018-03-07 10:57:04', '超级管理员admin', '2018-03-07 10:57:04', '4', '4', '路径1/路径12/用户4');
INSERT INTO `manager_info` VALUES ('5', '郑俊文', '350181198802151312', '男', '18779805231', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照5.jpg', '1988-02-07 00:00:00', '超级管理员admin', '2018-03-07 10:57:04', '超级管理员admin', '2018-03-07 10:57:04', '5', '5', '路径1/路径12/用户5');
INSERT INTO `manager_info` VALUES ('6', '郑俊心', '350181198802151312', '男', '18779805231', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照7.jpg', '1988-02-07 00:00:00', '超级管理员admin', '2018-03-07 10:57:04', '超级管理员admin', '2018-03-07 10:57:04', '6', '6', '路径1/路径13/用户6');
INSERT INTO `manager_info` VALUES ('15', '吴亦凡', '123', '男', '456', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照2.jpg', '2018-01-12 00:00:00', null, null, null, null, '12345', '789', '路径1/路径11/吴亦凡');
INSERT INTO `manager_info` VALUES ('16', '嘻嘻哒', '123', '男', '456', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照6.jpg', '1999-01-12 00:00:00', null, null, null, null, '123', '789', '路径1/路径13/嘻嘻哒');
INSERT INTO `manager_info` VALUES ('13038', '普通收费员', '350181199603782321', '男', '17825673621', 'C:\\Users\\franklin\\Desktop\\证件照\\证件照2.jpg', '1998-01-08 00:00:00', null, null, null, null, '123456', '', '路径1/新节点/普通收费员');

-- ----------------------------
-- Table structure for manager_parkinglot_relation
-- ----------------------------
DROP TABLE IF EXISTS `manager_parkinglot_relation`;
CREATE TABLE `manager_parkinglot_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `manager_role` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '该用户对应的停车场角色(收费员或者负责人)',
  `manager_id` int(11) DEFAULT NULL COMMENT '管理员工号',
  `parkinglot_id` int(11) DEFAULT NULL COMMENT '停车场id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of manager_parkinglot_relation
-- ----------------------------
INSERT INTO `manager_parkinglot_relation` VALUES ('1', '超级管理员admin', '2018-03-07 15:20:05', '超级管理员admin', '2018-03-07 15:20:05', 'CHARGER', '5', '3');
INSERT INTO `manager_parkinglot_relation` VALUES ('2', 'admin', '2018-03-17 00:00:00', 'admin', '2018-03-17 00:00:00', 'CHARGER', '1', '4');
INSERT INTO `manager_parkinglot_relation` VALUES ('3', 'admin', '2018-03-17 00:00:00', 'admin', '2018-03-17 00:00:00', 'ADMIN', '6', '1');
INSERT INTO `manager_parkinglot_relation` VALUES ('4', 'admin', '2018-03-17 00:00:00', 'admin', '2018-03-17 00:00:00', 'CHARGER', '3', '2');
INSERT INTO `manager_parkinglot_relation` VALUES ('5', 'admin', '2018-03-17 00:00:00', 'admin', '2018-03-17 00:00:00', 'ADMIN', '4', '19');
INSERT INTO `manager_parkinglot_relation` VALUES ('6', 'admin', '2018-04-05 00:00:00', 'admin', '2018-04-05 00:00:00', 'CHARGER', '13038', '20');
INSERT INTO `manager_parkinglot_relation` VALUES ('7', 'admin', '2018-04-05 00:00:00', 'admin', '2018-04-05 00:00:00', 'ADMIN', '13038', '20');

-- ----------------------------
-- Table structure for parkinglot_info
-- ----------------------------
DROP TABLE IF EXISTS `parkinglot_info`;
CREATE TABLE `parkinglot_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creator` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modifier` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '停车场地址',
  `charge_standard` decimal(10,0) DEFAULT NULL COMMENT '停车场收费标准',
  `parkingspace_coord` varchar(2550) CHARACTER SET utf8 DEFAULT NULL COMMENT '停车位坐标列表(逗号分隔)',
  `entry_coord` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '停车场入口坐标',
  `exit_coord` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '停车场出口坐标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parkinglot_info
-- ----------------------------
INSERT INTO `parkinglot_info` VALUES ('1', '超级管理员admin', '2018-03-07 10:43:16', '超级管理员admin', '2018-03-07 10:43:16', '四川省成都市高新西区西源大道2006号', '3', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,24,39,25,46,61,76,91,106,121,136', null, null);
INSERT INTO `parkinglot_info` VALUES ('2', '超级管理员admin', '2018-03-07 10:47:10', '超级管理员admin', '2018-03-07 10:47:10', '福建省福州市长乐县玉屏街道532号', '3', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,24,39,25,46,61,76,91,106,121,136', null, null);
INSERT INTO `parkinglot_info` VALUES ('3', '超级管理员admin', '2018-03-07 10:48:24', '超级管理员admin', '2018-03-07 10:48:24', '北京市海淀区玉泉路89号', '3', '1,2,3,4,5', null, null);
INSERT INTO `parkinglot_info` VALUES ('4', '超级管理员admin', '2018-03-07 10:49:22', '超级管理员admin', '2018-03-07 10:49:22', '广东省深圳市南山区699号', '3', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,18,24,39,25,46,61,76,91,106,121,136', null, null);
INSERT INTO `parkinglot_info` VALUES ('19', '超级管理员admin', '2018-03-07 10:49:51', '超级管理员admin', '2018-03-07 10:49:51', '四川省成都市双流区656号', '3', '1,2,3,4,5', null, null);
INSERT INTO `parkinglot_info` VALUES ('20', 'admin', '2018-04-05 00:00:00', 'admin', '2018-04-05 00:00:00', '四川省成都市高新西区西源大道2006号', '7', '1,2,4,3,5,6,8,7,9,11,10,12,14,13,15,30,45,60,75,136,137,139,138,140,141,142,143,144,146,147,145,148,149,150,135,105,31,16,90,61,76,91,121,106,93,108,94,110,95,33,48,63,64,65,34,35,37,52,67,38,39,69,54,41,42,43,58,73,72,71,97,98,99,114,112,101,102,103,116,118', '46', '120');

-- ----------------------------
-- Table structure for parking_log
-- ----------------------------
DROP TABLE IF EXISTS `parking_log`;
CREATE TABLE `parking_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `enter_time` datetime DEFAULT NULL COMMENT '车辆进场时间',
  `leave_time` datetime DEFAULT NULL COMMENT '车辆出场时间',
  `fee` decimal(10,0) DEFAULT NULL COMMENT '停车费用',
  `manager_id` int(11) DEFAULT NULL COMMENT '停车场管理员工号',
  `plate_number` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '车牌号',
  `enter_photo_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '车辆入场抓拍图片url',
  `leave_photo_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '车辆出场抓拍图片url',
  `parkinglot_id` int(11) DEFAULT NULL COMMENT '停车场id',
  `parkingspace_coord` int(11) DEFAULT NULL COMMENT '停车位坐标',
  `card_id` int(11) DEFAULT NULL COMMENT '本次停车所用IC卡的id',
  `creator` varchar(255) DEFAULT NULL COMMENT '记录创建人id',
  `modifier` varchar(255) DEFAULT NULL COMMENT '记录修改人id',
  `created_time` datetime DEFAULT NULL COMMENT '记录创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '记录修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of parking_log
-- ----------------------------
INSERT INTO `parking_log` VALUES ('2', '2018-03-07 23:35:24', '2018-03-07 01:35:33', '2', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '2', '1', null, null, '2018-03-07 23:35:54', '2018-03-07 23:35:57');
INSERT INTO `parking_log` VALUES ('3', '2018-03-07 23:36:06', '2018-03-07 05:36:12', '3', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '3', '2', null, null, '2018-03-07 23:36:30', '2018-03-07 23:36:33');
INSERT INTO `parking_log` VALUES ('4', '2018-03-07 23:36:06', '2018-03-07 03:36:12', '4', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '4', '3', null, null, '2018-03-07 23:36:30', '2018-03-07 23:36:33');
INSERT INTO `parking_log` VALUES ('5', '2018-03-07 21:37:09', '2018-03-07 23:37:16', '5', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '5', '7', null, null, '2018-03-07 23:37:40', '2018-03-07 23:37:43');
INSERT INTO `parking_log` VALUES ('8', '2018-03-18 14:38:31', '2018-03-30 14:49:13', '6', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '1', '4', null, null, null, null);
INSERT INTO `parking_log` VALUES ('9', '2018-03-18 14:38:53', '2018-03-06 14:49:17', '7', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '2', '5', null, null, null, null);
INSERT INTO `parking_log` VALUES ('10', '2018-03-18 14:39:30', '2018-03-16 14:49:21', '8', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '3', '6', null, null, null, null);
INSERT INTO `parking_log` VALUES ('12', '2018-03-18 16:59:40', '2018-03-17 14:49:24', '9', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '4', '15', null, null, null, null);
INSERT INTO `parking_log` VALUES ('13', '2018-03-18 16:59:40', '2018-03-11 14:49:27', '10', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '5', '18', null, null, null, null);
INSERT INTO `parking_log` VALUES ('14', '2018-03-18 17:02:23', '2018-03-19 10:23:48', '11', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '6', '20', null, null, null, null);
INSERT INTO `parking_log` VALUES ('15', '2018-03-19 23:01:07', '2018-03-19 23:01:41', '12', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '6', '8', null, null, null, null);
INSERT INTO `parking_log` VALUES ('16', '2018-03-19 23:01:07', '2018-03-13 14:49:31', '13', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '6', '6', null, null, null, null);
INSERT INTO `parking_log` VALUES ('17', '2018-03-19 23:10:28', '2018-03-27 14:49:34', '14', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '7', '17', null, null, null, null);
INSERT INTO `parking_log` VALUES ('18', '2018-03-19 23:13:54', '2018-03-14 14:49:37', '15', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '8', '12', null, null, null, null);
INSERT INTO `parking_log` VALUES ('19', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '16', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('20', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '17', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('21', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '18', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('22', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '19', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('23', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '20', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('24', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '21', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('25', '2018-03-19 23:15:42', '2018-02-28 14:49:40', '22', '3', '闽A20678', 'C:\\Users\\franklin\\Desktop\\呵呵.jpg', 'C:\\Users\\franklin\\Desktop\\校徽.jpg', '19', '9', '13', null, null, null, null);
INSERT INTO `parking_log` VALUES ('26', '2018-04-05 10:58:00', null, null, '13038', '津E28437', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\津E28437.jpg', null, '20', '61', '30', null, null, null, null);
INSERT INTO `parking_log` VALUES ('27', '2018-04-05 11:00:04', null, null, '13038', '沪JS6999', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\沪JS6999.jpg', null, '20', '31', '30', null, null, null, null);
INSERT INTO `parking_log` VALUES ('28', '2018-04-05 11:00:52', null, null, '13038', '沪K62933', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\沪K62933.jpg', null, '20', '48', '30', null, null, null, null);
INSERT INTO `parking_log` VALUES ('29', '2018-04-05 11:01:29', null, null, '13038', '沪D71603', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\沪D71603.jpg', null, '20', '63', '25', null, null, null, null);
INSERT INTO `parking_log` VALUES ('30', '2018-04-05 11:01:58', null, null, '13038', '京A88731', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\京A88731.jpg', null, '20', '33', '35', null, null, null, null);
INSERT INTO `parking_log` VALUES ('31', '2018-04-05 11:02:23', null, null, '13038', '津NRL118', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\津NRL118.jpg', null, '20', '76', '27', null, null, null, null);
INSERT INTO `parking_log` VALUES ('32', '2018-04-05 11:02:51', null, null, '13038', '川A09X20', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\川A09X20.jpg', null, '20', '16', '28', null, null, null, null);
INSERT INTO `parking_log` VALUES ('33', '2018-04-05 11:03:32', null, null, '13038', '黑A16341', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\黑A16341.jpg', null, '20', '2', '29', null, null, null, null);
INSERT INTO `parking_log` VALUES ('34', '2018-04-05 11:03:55', '2018-04-05 11:11:09', '666', '13038', '京CX8888', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\京CX8888.jpg', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\京CX8888.jpg', '20', '91', '36', null, null, null, null);
INSERT INTO `parking_log` VALUES ('35', '2018-04-05 11:04:58', null, null, '13038', '津DTG667', 'C:\\Users\\franklin\\Desktop\\车辆进出抓拍照片\\津DTG667.jpg', null, '20', '93', '38', null, null, null, null);

-- ----------------------------
-- Table structure for tb_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_auth`;
CREATE TABLE `tb_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限中文名称',
  `name_zh` varchar(255) DEFAULT NULL COMMENT '权限中文标识符',
  `name_en` varchar(255) DEFAULT NULL COMMENT '权限英文标识符',
  `path` varchar(255) DEFAULT NULL COMMENT '根路径',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_auth
-- ----------------------------
INSERT INTO `tb_auth` VALUES ('30', '车辆进场模块权限', 'AUTH_CAR_IN', '路径1/停车点管理/车辆进场模块权限', '车辆进场模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('31', '车辆出场模块权限', 'AUTH_CAR_OUT', '路径1/停车点管理/车辆出场模块权限', '车辆出场模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('32', '停车场管理模块权限', 'AUTH_PARKINGLOT_MANAGEMENT', '路径1/停车点管理/停车场管理模块权限', '停车点管理/停车场管理模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('33', '车辆进出记录模块权限', 'AUTH_CAR_LOG', '路径1/停车点管理/车辆进出记录模块权限', '车辆进出记录模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('34', '停车场反向寻车模块权限', 'AUTH_CAR_SEARCH', '路径1/停车点管理/停车场反向寻车模块权限', '停车场反向寻车模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('36', 'IC卡授权发行模块权限', 'AUTH_CARD_RELEASE', '路径1/IC停车卡管理/IC卡授权发行模块权限', 'IC卡授权发行模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('44', 'IC卡消费记录查询模块权限', 'AUTH_CARD_LOG', '路径1/IC停车卡管理/IC卡消费记录查询模块权限', 'IC卡消费记录查询模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('45', 'IC卡充值/续期模块权限', 'AUTH_CARD_RECHARGE', '路径1/IC停车卡管理/IC卡充值|续期|模块权限', 'IC卡充值续期模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('46', '账户挂失/注销/解挂模块权限', 'AUTH_CARD_STATUS', '路径1/IC停车卡管理/账户挂失|注销|解挂模块权限', '账户挂失注销解挂模块权限', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('48', 'IC卡账户查询模块权限', 'AUTH_CARD_SEARCH', '路径1/IC停车卡管理/IC卡账户查询模块权限', null, null, null, null, null);
INSERT INTO `tb_auth` VALUES ('57', '权限管理', 'AUTH_ACCESS', '路径1/系统管理员模块/权限管理', '权限管理', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('58', '角色管理', 'AUTH_ROLE', '路径1/系统管理员模块/角色管理', '', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('63', '用户管理', 'AUTH_USER', '路径1/系统管理员模块/用户管理', '用户管理', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('69', '待我审批的权限申请', 'AUTH_REQUEST', '路径1/系统管理员模块/待我审批的权限申请', '待我审批的权限申请', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('70', '我的权限审批历史', 'AUTH_LOG', '路径1/系统管理员模块/我的权限审批历史', '我的权限审批历史', null, null, null, null);
INSERT INTO `tb_auth` VALUES ('73', '数据分析', 'AUTH_DATA', '路径1/数据分析模块/数据分析', null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_zh` varchar(255) DEFAULT NULL COMMENT '角色中文名称',
  `name_en` varchar(255) DEFAULT NULL COMMENT '角色英文名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `path` varchar(255) DEFAULT NULL COMMENT '根路径',
  `creator` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('9', '停车场收费员', 'ROLE_CHARGER', '该角色为停车场管理员角色', '路径1/普通管理员/停车场收费员', null, null, null, null);
INSERT INTO `tb_role` VALUES ('10', 'IC停车卡管理员', 'ROLE_CARD', '这是IC停车卡管理员角色', '路径1/普通管理员/IC停车卡管理员', null, null, null, null);
INSERT INTO `tb_role` VALUES ('11', '系统超级管理员', 'ADMIN', '系统超级管理员(ADMIN)角色具有该系统所有的\n权限。', '路径1/超级管理员/系统超级管理员', null, null, null, null);
INSERT INTO `tb_role` VALUES ('12', '数据分析管理员', 'ROLE_DATA', '数据分析管理员', '路径1/普通管理员/数据分析管理员', null, null, null, null);
INSERT INTO `tb_role` VALUES ('13', '停车场信息维护员', 'ROLE_PARKINGLOT_MAINTAINER', '该角色负责维护停车场的基本信息', '路径1/普通管理员/停车场信息维护员', null, null, null, null);

-- ----------------------------
-- Table structure for tb_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_auth`;
CREATE TABLE `tb_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `auth_id` int(11) DEFAULT NULL COMMENT '权限id',
  `creator` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `authName_zh` varchar(255) DEFAULT NULL COMMENT '权限中文名称',
  `authName_en` varchar(255) DEFAULT NULL COMMENT '权限英文名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_auth
-- ----------------------------
INSERT INTO `tb_role_auth` VALUES ('9', '9', '30', null, null, null, null, '车辆进场模块权限', 'AUTH_CAR_IN');
INSERT INTO `tb_role_auth` VALUES ('10', '9', '31', null, null, null, null, '车辆出场模块权限', 'AUTH_CAR_OUT');
INSERT INTO `tb_role_auth` VALUES ('11', '9', '32', null, null, null, null, '停车场管理模块权限', 'AUTH_PARKINGLOT_MANAGEMENT');
INSERT INTO `tb_role_auth` VALUES ('12', '9', '33', null, null, null, null, '车辆进出记录模块权限', 'AUTH_CAR_LOG');
INSERT INTO `tb_role_auth` VALUES ('13', '9', '34', null, null, null, null, '停车场反向寻车模块权限', 'AUTH_CAR_SEARCH');
INSERT INTO `tb_role_auth` VALUES ('14', '10', '36', null, null, null, null, 'IC卡授权发行模块权限', 'AUTH_CARD_RELEASE');
INSERT INTO `tb_role_auth` VALUES ('15', '10', '48', null, null, null, null, 'IC卡账户查询模块权限', 'AUTH_CARD_SEARCH');
INSERT INTO `tb_role_auth` VALUES ('16', '10', '44', null, null, null, null, 'IC卡消费记录查询模块权限', 'AUTH_CARD_LOG');
INSERT INTO `tb_role_auth` VALUES ('17', '10', '45', null, null, null, null, 'IC卡充值/续期模块权限', 'AUTH_CARD_RECHARGE');
INSERT INTO `tb_role_auth` VALUES ('18', '10', '46', null, null, null, null, '账户挂失/注销/解挂模块权限', 'AUTH_CARD_STATUS');
INSERT INTO `tb_role_auth` VALUES ('19', '12', '73', null, null, null, null, '数据分析', 'AUTH_DATA');
INSERT INTO `tb_role_auth` VALUES ('20', '13', '32', null, null, null, null, '停车场管理模块权限', 'AUTH_PARKINGLOT_MANAGEMENT');
INSERT INTO `tb_role_auth` VALUES ('21', '13', '34', null, null, null, null, '停车场反向寻车模块权限', 'AUTH_CAR_SEARCH');
INSERT INTO `tb_role_auth` VALUES ('22', '13', '33', null, null, null, null, '车辆进出记录模块权限', 'AUTH_CAR_LOG');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `creator` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `roleName_zh` varchar(255) DEFAULT NULL,
  `roleName_en` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT '审核通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('8', '15', '1', null, null, null, null, '角色1', 'ROLE_1', '审核通过');
INSERT INTO `tb_user_role` VALUES ('9', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '审核通过');
INSERT INTO `tb_user_role` VALUES ('10', '16', '4', null, null, null, null, '角色4', 'ROLE_4', '审核通过');
INSERT INTO `tb_user_role` VALUES ('11', '16', '1', null, null, null, null, '角色1', 'ROLE_1', '等待审核');
INSERT INTO `tb_user_role` VALUES ('12', '16', '2', null, null, null, null, '角色2', 'ROLE_2', '等待审核');
INSERT INTO `tb_user_role` VALUES ('13', '16', '3', null, null, null, null, '角色3', 'ROLE_3', '审核通过');
INSERT INTO `tb_user_role` VALUES ('15', '16', '1', null, null, null, null, '角色1', 'ROLE_1', '等待审核');
INSERT INTO `tb_user_role` VALUES ('17', '15', '1', null, null, null, null, '角色1', 'ROLE_1', '等待审核');
INSERT INTO `tb_user_role` VALUES ('20', '16', '3', null, null, null, null, '角色3', 'ROLE_3', '等待审核');
INSERT INTO `tb_user_role` VALUES ('21', '16', '4', null, null, null, null, '角色4', 'ROLE_4', '等待审核');
INSERT INTO `tb_user_role` VALUES ('22', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '等待审核');
INSERT INTO `tb_user_role` VALUES ('23', '16', '3', null, null, null, null, '角色3', 'ROLE_3', '等待审核');
INSERT INTO `tb_user_role` VALUES ('24', '16', '4', null, null, null, null, '角色4', 'ROLE_4', '等待审核');
INSERT INTO `tb_user_role` VALUES ('25', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '等待审核');
INSERT INTO `tb_user_role` VALUES ('26', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '等待审核');
INSERT INTO `tb_user_role` VALUES ('27', '16', '4', null, null, null, null, '角色4', 'ROLE_4', '等待审核');
INSERT INTO `tb_user_role` VALUES ('28', '16', '1', null, null, null, null, '角色1', 'ROLE_1', '等待审核');
INSERT INTO `tb_user_role` VALUES ('29', '15', '1', null, null, null, null, '角色1', 'ROLE_1', '等待审核');
INSERT INTO `tb_user_role` VALUES ('30', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '等待审核');
INSERT INTO `tb_user_role` VALUES ('31', '15', '1', null, null, null, null, '角色1', 'ROLE_1', '等待审核');
INSERT INTO `tb_user_role` VALUES ('32', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '等待审核');
INSERT INTO `tb_user_role` VALUES ('33', '16', '3', null, null, null, null, '角色3', 'ROLE_3', '等待审核');
INSERT INTO `tb_user_role` VALUES ('34', '16', '3', null, null, null, null, '角色3', 'ROLE_3', '等待审核');
INSERT INTO `tb_user_role` VALUES ('35', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '审核通过');
INSERT INTO `tb_user_role` VALUES ('36', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '审核不通过');
INSERT INTO `tb_user_role` VALUES ('37', '15', '2', null, null, null, null, '角色2', 'ROLE_2', '审核不通过');
INSERT INTO `tb_user_role` VALUES ('38', '15', '1', null, null, null, null, '角色1', 'ROLE_1', '审核通过');
INSERT INTO `tb_user_role` VALUES ('39', '16', '3', null, null, null, null, '角色3', 'ROLE_3', '审核通过');
INSERT INTO `tb_user_role` VALUES ('40', '13038', null, null, null, null, null, null, 'ADMIN', '审核通过');
