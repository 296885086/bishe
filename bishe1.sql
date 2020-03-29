/*
Navicat MySQL Data Transfer

Source Server         : ljq
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : bishe1

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-03-29 23:42:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for audit
-- ----------------------------
DROP TABLE IF EXISTS `audit`;
CREATE TABLE `audit` (
  `auditid` int(10) NOT NULL AUTO_INCREMENT,
  `workid` int(10) NOT NULL,
  `commitdate` datetime NOT NULL,
  `commitbody` varchar(255) DEFAULT NULL,
  `replybody` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `stuid` int(10) NOT NULL,
  PRIMARY KEY (`auditid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of audit
-- ----------------------------
INSERT INTO `audit` VALUES ('6', '94', '2020-03-29 16:56:50', ' ', null, '等待审核...', '1640129320');
INSERT INTO `audit` VALUES ('7', '90', '2020-03-29 21:54:44', ' 分数太低', null, '等待审核...', '1640129314');
INSERT INTO `audit` VALUES ('8', '90', '2020-03-29 21:54:47', ' ', '反馈无效', '已完成', '1640129314');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` int(10) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(255) NOT NULL,
  `courseclass` varchar(255) NOT NULL,
  `teaid` int(10) NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('29', 'HTML', 'MN', '456');
INSERT INTO `course` VALUES ('30', 'java基础知识', 'QM', '456');
INSERT INTO `course` VALUES ('31', 'java基础知识', 'QS', '456');
INSERT INTO `course` VALUES ('35', 'HTML', 'MN', '123');
INSERT INTO `course` VALUES ('37', 'java基础知识', 'QM', '123');

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `workid` int(10) NOT NULL AUTO_INCREMENT,
  `workname` varchar(255) NOT NULL,
  `uploaddate` varchar(255) NOT NULL,
  `enddate` varchar(255) NOT NULL,
  `uploadclass` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) NOT NULL,
  `course` varchar(255) DEFAULT NULL,
  `teaid` int(10) NOT NULL,
  PRIMARY KEY (`workid`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('77', '选课', '2020-03-22', '2020-03-22', 'QM', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/123\\java基础知识\\QM\\选课.xlsx', 'java基础知识', '123');
INSERT INTO `homework` VALUES ('89', '选课', '2020-03-28', '2020-03-28', 'QS', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\java基础知识\\QS\\选课.xlsx', 'java基础知识', '456');
INSERT INTO `homework` VALUES ('90', 'xuesheng', '2020-03-28', '2020-03-28', 'MN', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\HTML\\MN\\xuesheng.xlsx', 'HTML', '456');
INSERT INTO `homework` VALUES ('94', 'bishe', '2020-03-29', '2020-03-29', 'QM', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\java基础知识\\QM\\bishe.sql', 'java基础知识', '456');
INSERT INTO `homework` VALUES ('95', '选课', '2020-03-29', '2020-03-29', 'QM', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\java基础知识\\QM\\选课.xlsx', 'java基础知识', '456');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageid` int(10) NOT NULL AUTO_INCREMENT,
  `leaveid` int(10) DEFAULT NULL,
  `messagebody` varchar(255) DEFAULT NULL,
  `leavedate` datetime DEFAULT NULL,
  `totalreply` int(10) unsigned zerofill DEFAULT NULL,
  `praisenumber` int(10) unsigned zerofill DEFAULT NULL,
  `messagetype` varchar(255) DEFAULT NULL,
  `courseid` int(10) DEFAULT NULL,
  `leavename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('13', '456', '3333333', '2020-03-18 23:08:41', '0000000000', '0000000000', 'tea', '29', '李家庆');
INSERT INTO `message` VALUES ('14', '456', '你好', '2020-03-18 23:31:45', '0000000000', '0000000000', 'tea', '29', '李家庆');
INSERT INTO `message` VALUES ('15', '456', '优秀', '2020-03-18 23:32:16', '0000000000', '0000000000', 'tea', '30', '李家庆');
INSERT INTO `message` VALUES ('16', '1640129314', '66666', '2020-03-18 23:36:45', '0000000000', '0000000000', 'stu', '29', '李家庆1');
INSERT INTO `message` VALUES ('17', '1640129314', '7777', '2020-03-18 23:37:47', '0000000000', '0000000000', 'stu', '29', '李家庆1');
INSERT INTO `message` VALUES ('18', '123', '456', '2020-03-22 22:44:48', '0000000000', '0000000000', 'tea', '35', 'ljq');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `replyid` int(10) NOT NULL AUTO_INCREMENT,
  `messageid` int(10) DEFAULT NULL,
  `replydate` datetime DEFAULT NULL,
  `replybody` varchar(255) DEFAULT NULL,
  `replyname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`replyid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('19', '13', '2020-03-21 10:44:44', '123145', '李家庆1');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `stuid` varchar(255) NOT NULL,
  `score` varchar(5) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `workid` int(10) NOT NULL,
  `scoreid` int(10) NOT NULL AUTO_INCREMENT,
  `uploadworkname` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `selectcourseid` int(10) DEFAULT NULL,
  PRIMARY KEY (`scoreid`)
) ENGINE=InnoDB AUTO_INCREMENT=285 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1640129326', '0', '未上交', '89', '247', null, null, '51');
INSERT INTO `score` VALUES ('1640129327', '0', '未上交', '89', '248', null, null, '52');
INSERT INTO `score` VALUES ('1640129328', '0', '未上交', '89', '249', null, null, '53');
INSERT INTO `score` VALUES ('1640129329', '0', '未上交', '89', '250', null, null, '54');
INSERT INTO `score` VALUES ('1640129330', '0', '未上交', '89', '251', null, null, '55');
INSERT INTO `score` VALUES ('1640129331', '0', '未上交', '89', '252', null, null, '56');
INSERT INTO `score` VALUES ('1640129332', '0', '未上交', '89', '253', null, null, '57');
INSERT INTO `score` VALUES ('1640129314', '1', '已上交', '89', '254', 'xuesheng.xlsx', null, '58');
INSERT INTO `score` VALUES ('1640129314', '3.5', '已上交', '90', '255', 'bishe.sql', null, '39');
INSERT INTO `score` VALUES ('1640129315', '0', '未上交', '90', '256', null, null, '40');
INSERT INTO `score` VALUES ('1640129316', '0', '未上交', '90', '257', null, null, '41');
INSERT INTO `score` VALUES ('1640129317', '0', '未上交', '90', '258', null, null, '42');
INSERT INTO `score` VALUES ('1640129318', '0', '未上交', '90', '259', null, null, '43');
INSERT INTO `score` VALUES ('1640129319', '5', '已上交', '90', '260', 'xuesheng.xlsx', null, '44');
INSERT INTO `score` VALUES ('1640129320', '0', '已上交', '94', '273', '交作业了.docx', null, '45');
INSERT INTO `score` VALUES ('1640129321', '0', '未上交', '94', '274', null, null, '46');
INSERT INTO `score` VALUES ('1640129322', '0', '未上交', '94', '275', null, null, '47');
INSERT INTO `score` VALUES ('1640129323', '0', '未上交', '94', '276', null, null, '48');
INSERT INTO `score` VALUES ('1640129324', '0', '未上交', '94', '277', null, null, '49');
INSERT INTO `score` VALUES ('1640129325', '0', '未上交', '94', '278', null, null, '50');
INSERT INTO `score` VALUES ('1640129320', '0', '已上交', '95', '279', 'bishe.sql', null, '45');
INSERT INTO `score` VALUES ('1640129321', '0', '未上交', '95', '280', null, null, '46');
INSERT INTO `score` VALUES ('1640129322', '0', '未上交', '95', '281', null, null, '47');
INSERT INTO `score` VALUES ('1640129323', '0', '未上交', '95', '282', null, null, '48');
INSERT INTO `score` VALUES ('1640129324', '0', '未上交', '95', '283', null, null, '49');
INSERT INTO `score` VALUES ('1640129325', '0', '未上交', '95', '284', null, null, '50');

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse` (
  `stuid` int(10) DEFAULT NULL,
  `courseclass` varchar(255) DEFAULT NULL,
  `coursename` varchar(255) DEFAULT NULL,
  `teaid` int(10) DEFAULT NULL,
  `selectcourseid` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`selectcourseid`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------
INSERT INTO `selectcourse` VALUES ('1640129314', 'MN', 'HTML', '456', '39');
INSERT INTO `selectcourse` VALUES ('1640129315', 'MN', 'HTML', '456', '40');
INSERT INTO `selectcourse` VALUES ('1640129316', 'MN', 'HTML', '456', '41');
INSERT INTO `selectcourse` VALUES ('1640129317', 'MN', 'HTML', '456', '42');
INSERT INTO `selectcourse` VALUES ('1640129318', 'MN', 'HTML', '456', '43');
INSERT INTO `selectcourse` VALUES ('1640129319', 'MN', 'HTML', '456', '44');
INSERT INTO `selectcourse` VALUES ('1640129320', 'QM', 'java基础知识', '456', '45');
INSERT INTO `selectcourse` VALUES ('1640129321', 'QM', 'java基础知识', '456', '46');
INSERT INTO `selectcourse` VALUES ('1640129322', 'QM', 'java基础知识', '456', '47');
INSERT INTO `selectcourse` VALUES ('1640129323', 'QM', 'java基础知识', '456', '48');
INSERT INTO `selectcourse` VALUES ('1640129324', 'QM', 'java基础知识', '456', '49');
INSERT INTO `selectcourse` VALUES ('1640129325', 'QM', 'java基础知识', '456', '50');
INSERT INTO `selectcourse` VALUES ('1640129326', 'QS', 'java基础知识', '456', '51');
INSERT INTO `selectcourse` VALUES ('1640129327', 'QS', 'java基础知识', '456', '52');
INSERT INTO `selectcourse` VALUES ('1640129328', 'QS', 'java基础知识', '456', '53');
INSERT INTO `selectcourse` VALUES ('1640129329', 'QS', 'java基础知识', '456', '54');
INSERT INTO `selectcourse` VALUES ('1640129330', 'QS', 'java基础知识', '456', '55');
INSERT INTO `selectcourse` VALUES ('1640129331', 'QS', 'java基础知识', '456', '56');
INSERT INTO `selectcourse` VALUES ('1640129332', 'QS', 'java基础知识', '456', '57');
INSERT INTO `selectcourse` VALUES ('1640129314', 'QS', 'java基础知识', '456', '58');
INSERT INTO `selectcourse` VALUES ('1640129332', 'MN', 'HTML', '123', '110');
INSERT INTO `selectcourse` VALUES ('1640129333', 'MN', 'HTML', '123', '111');
INSERT INTO `selectcourse` VALUES ('1640129334', 'MN', 'HTML', '123', '112');
INSERT INTO `selectcourse` VALUES ('1640129335', 'MN', 'HTML', '123', '113');
INSERT INTO `selectcourse` VALUES ('1640129336', 'MN', 'HTML', '123', '114');
INSERT INTO `selectcourse` VALUES ('1640129337', 'MN', 'HTML', '123', '115');
INSERT INTO `selectcourse` VALUES ('1640129338', 'QM', 'java基础知识', '123', '116');
INSERT INTO `selectcourse` VALUES ('1640129339', 'QM', 'java基础知识', '123', '117');
INSERT INTO `selectcourse` VALUES ('1640129340', 'QM', 'java基础知识', '123', '118');
INSERT INTO `selectcourse` VALUES ('1640129341', 'QM', 'java基础知识', '123', '119');
INSERT INTO `selectcourse` VALUES ('1640129342', 'QM', 'java基础知识', '123', '120');
INSERT INTO `selectcourse` VALUES ('1640129343', 'QM', 'java基础知识', '123', '121');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuid` int(20) NOT NULL,
  `stuname` varchar(255) NOT NULL,
  `stusex` char(2) NOT NULL,
  `stuclass` varchar(255) NOT NULL,
  `stuphone` varchar(20) DEFAULT NULL,
  `stupassword` varchar(255) NOT NULL,
  `teaid` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1640129314', '李家庆1', '男', '16软工4班', '13160847917', '123456', '456', '1');
INSERT INTO `student` VALUES ('1640129315', '李家庆2', '男', '16软工4班', '13160847917', '123456', '456', '2');
INSERT INTO `student` VALUES ('1640129316', '李家庆3', '男', '16软工4班', '13160847917', '123456', '456', '3');
INSERT INTO `student` VALUES ('1640129317', '李家庆4', '男', '16软工4班', '13160847917', '123456', '456', '4');
INSERT INTO `student` VALUES ('1640129318', '李家庆5', '男', '16软工4班', '13160847917', '123456', '456', '5');
INSERT INTO `student` VALUES ('1640129319', '李家庆6', '男', '16软工4班', '13160847917', '123456', '456', '6');
INSERT INTO `student` VALUES ('1640129320', '李家庆7', '男', '16软工4班', '13160847917', '123456', '456', '7');
INSERT INTO `student` VALUES ('1640129321', '李家庆8', '男', '16软工11班', '13160847917', '123456', '456', '8');
INSERT INTO `student` VALUES ('1640129322', '李家庆9', '男', '16软工12班', '13160847917', '123456', '456', '9');
INSERT INTO `student` VALUES ('1640129323', '李家庆10', '男', '16软工13班', '13160847917', '123456', '456', '10');
INSERT INTO `student` VALUES ('1640129324', '李家庆11', '男', '16软工14班', '13160847917', '123456', '456', '11');
INSERT INTO `student` VALUES ('1640129325', '李家庆12', '男', '16软工15班', '13160847917', '123456', '456', '12');
INSERT INTO `student` VALUES ('1640129326', '李家庆13', '男', '16软工16班', '13160847917', '123456', '456', '13');
INSERT INTO `student` VALUES ('1640129327', '李家庆14', '男', '16软工17班', '13160847917', '123456', '456', '14');
INSERT INTO `student` VALUES ('1640129328', '李家庆15', '男', '16软工18班', '13160847917', '123456', '456', '15');
INSERT INTO `student` VALUES ('1640129329', '李家庆16', '男', '16软工19班', '13160847917', '123456', '456', '16');
INSERT INTO `student` VALUES ('1640129330', '李家庆17', '男', '16软工20班', '13160847917', '123456', '456', '17');
INSERT INTO `student` VALUES ('1640129331', '李家庆18', '男', '16软工21班', '13160847917', '123456', '456', '18');
INSERT INTO `student` VALUES ('1640129332', '李家庆19', '男', '16软工22班', '13160847917', '123456', '456', '19');
INSERT INTO `student` VALUES ('1640129332', '李家庆19', '男', '16软工22班', '13160847917', '123456', '123', '58');
INSERT INTO `student` VALUES ('1640129333', '李家庆20', '男', '16软工11班', '13160847917', '123456', '123', '59');
INSERT INTO `student` VALUES ('1640129334', '李家庆21', '男', '16软工12班', '13160847917', '123456', '123', '60');
INSERT INTO `student` VALUES ('1640129335', '李家庆22', '男', '16软工13班', '13160847917', '123456', '123', '61');
INSERT INTO `student` VALUES ('1640129336', '李家庆23', '男', '16软工14班', '13160847917', '123456', '123', '62');
INSERT INTO `student` VALUES ('1640129337', '李家庆24', '男', '16软工11班', '13160847917', '123456', '123', '63');
INSERT INTO `student` VALUES ('1640129338', '李家庆25', '男', '16软工12班', '13160847917', '123456', '123', '64');
INSERT INTO `student` VALUES ('1640129339', '李家庆26', '男', '16软工13班', '13160847917', '123456', '123', '65');
INSERT INTO `student` VALUES ('1640129340', '李家庆27', '男', '16软工14班', '13160847917', '123456', '123', '66');
INSERT INTO `student` VALUES ('1640129341', '李家庆28', '男', '16软工11班', '13160847917', '123456', '123', '67');
INSERT INTO `student` VALUES ('1640129342', '李家庆29', '男', '16软工12班', '13160847917', '123456', '123', '68');
INSERT INTO `student` VALUES ('1640129343', '李家庆30', '男', '16软工13班', '13160847917', '123456', '123', '69');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teaid` int(20) NOT NULL,
  `teapassword` varchar(255) NOT NULL,
  `teaname` varchar(255) NOT NULL,
  `teasex` char(2) NOT NULL,
  `teaphone` int(20) DEFAULT NULL,
  PRIMARY KEY (`teaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('123', '1234', 'ljq', '男', '123456578');
INSERT INTO `teacher` VALUES ('456', '1234', '李家庆', '男', '123456578');
INSERT INTO `teacher` VALUES ('1234', '123', '123', '男', '123');
