/*
Navicat MySQL Data Transfer

Source Server         : ljq
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : bishe1

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-03-14 20:56:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` int(255) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(255) NOT NULL,
  `courseclass` varchar(255) NOT NULL,
  `teaid` varchar(255) NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'html', 'MN', '456');
INSERT INTO `course` VALUES ('2', 'java基础知识', 'QM', '456');
INSERT INTO `course` VALUES ('3', 'java基础知识', 'QN', '123');
INSERT INTO `course` VALUES ('4', 'java基础知识', 'QS', '456');
INSERT INTO `course` VALUES ('17', 'html', 'BQ', '456');
INSERT INTO `course` VALUES ('18', 'LINUX基础教程', 'QM', '456');
INSERT INTO `course` VALUES ('19', 'XML基础教程', 'QS', '456');

-- ----------------------------
-- Table structure for finishsituation
-- ----------------------------
DROP TABLE IF EXISTS `finishsituation`;
CREATE TABLE `finishsituation` (
  `workid` int(10) NOT NULL,
  `courseclass` varchar(255) NOT NULL DEFAULT '0',
  `finished` int(3) NOT NULL,
  `finishsituationid` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`finishsituationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finishsituation
-- ----------------------------

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
  `teaid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`workid`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('56', 'java知识', '2020-03-12', '2020-03-20', 'MN', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\html\\MN\\java知识.docx', 'html', '456');
INSERT INTO `homework` VALUES ('57', 'java知识', '2020-03-14', '2020-03-21', 'QM', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\java基础知识\\QM\\java知识.docx', 'java基础知识', '456');
INSERT INTO `homework` VALUES ('58', '简历', '2020-03-13', '2020-03-21', 'MN', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\html\\MN\\简历.jpg', 'html', '456');
INSERT INTO `homework` VALUES ('59', 'java知识', '2020-03-12', '2020-03-12', 'QM', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\java基础知识\\QM\\java知识.docx', 'java基础知识', '456');
INSERT INTO `homework` VALUES ('60', '李家庆简历', '2020-03-12', '2020-03-21', 'MN', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\html\\MN\\李家庆简历.docx', 'html', '456');
INSERT INTO `homework` VALUES ('61', '新建 Microsoft Word 文档', '2020-03-13', '2020-03-14', 'QM', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/456\\java基础知识\\QM\\新建 Microsoft Word 文档.docx', 'java基础知识', '456');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `stuid` varchar(255) NOT NULL,
  `score` int(3) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `workid` int(10) NOT NULL,
  `scoreid` int(10) NOT NULL AUTO_INCREMENT,
  `uploadworkname` varchar(255) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `selectcourseid` int(10) DEFAULT NULL,
  PRIMARY KEY (`scoreid`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1640129313', '0', '未上交', '56', '38', null, ' 你给的分数也太低了', '1');
INSERT INTO `score` VALUES ('1640129317', '0', '未上交', '56', '39', null, null, '5');
INSERT INTO `score` VALUES ('1640129321', '0', '未上交', '56', '40', null, null, '9');
INSERT INTO `score` VALUES ('1640129314', '0', '未上交', '56', '41', null, null, '11');
INSERT INTO `score` VALUES ('1640129314', '0', '未上交', '57', '42', null, null, '2');
INSERT INTO `score` VALUES ('1640129318', '0', '未上交', '57', '43', null, null, '6');
INSERT INTO `score` VALUES ('1640129322', '0', '未上交', '57', '44', null, null, '10');
INSERT INTO `score` VALUES ('1640129313', '0', '未上交', '58', '45', null, null, '1');
INSERT INTO `score` VALUES ('1640129317', '0', '未上交', '58', '46', null, null, '5');
INSERT INTO `score` VALUES ('1640129321', '0', '未上交', '58', '47', null, null, '9');
INSERT INTO `score` VALUES ('1640129314', '0', '未上交', '58', '48', null, null, '11');
INSERT INTO `score` VALUES ('1640129313', '0', '未上交', '60', '49', null, null, '1');
INSERT INTO `score` VALUES ('1640129317', '0', '未上交', '60', '50', null, null, '5');
INSERT INTO `score` VALUES ('1640129321', '0', '未上交', '60', '51', null, null, '9');
INSERT INTO `score` VALUES ('1640129314', '0', '未上交', '60', '52', null, null, '11');
INSERT INTO `score` VALUES ('1640129314', '0', '未上交', '61', '53', null, null, '2');
INSERT INTO `score` VALUES ('1640129318', '0', '未上交', '61', '54', null, null, '6');
INSERT INTO `score` VALUES ('1640129322', '0', '未上交', '61', '55', null, null, '10');
INSERT INTO `score` VALUES ('1640129313', '90', '已上交', '61', '56', 'java知识.docx', '谢谢老师！', '12');

-- ----------------------------
-- Table structure for scorelist
-- ----------------------------
DROP TABLE IF EXISTS `scorelist`;
CREATE TABLE `scorelist` (
  `stuid` int(20) NOT NULL,
  `courseid` varchar(255) NOT NULL,
  `score` varchar(255) DEFAULT NULL,
  `courseclass` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scorelist
-- ----------------------------
INSERT INTO `scorelist` VALUES ('1640129313', '4', '85,未上交,80', 'QS');
INSERT INTO `scorelist` VALUES ('1640129314', '4', '80,已上交,90', 'QS');
INSERT INTO `scorelist` VALUES ('1640129315', '4', '80,未上交,未上交', 'QS');
INSERT INTO `scorelist` VALUES ('1640129316', '2', '80,已上交,60', 'QM');
INSERT INTO `scorelist` VALUES ('1640129317', '2', '80,已上交,70', 'QM');
INSERT INTO `scorelist` VALUES ('1640129318', '3', '80,已上交,未上交', 'QN');
INSERT INTO `scorelist` VALUES ('1640129319', '3', '80,已上交,60', 'QN');
INSERT INTO `scorelist` VALUES ('1640129320', '1', '80,已上交,90', 'MN');
INSERT INTO `scorelist` VALUES ('1640129321', '2', '80,未上交,未上交', 'QM');
INSERT INTO `scorelist` VALUES ('1640129321', '2', '未上交,已上交,未上交', 'QM');
INSERT INTO `scorelist` VALUES ('1640129322', '1', '80,未上交,78', 'MN');

-- ----------------------------
-- Table structure for selectcourse
-- ----------------------------
DROP TABLE IF EXISTS `selectcourse`;
CREATE TABLE `selectcourse` (
  `stuid` varchar(255) DEFAULT NULL,
  `courseclass` varchar(255) DEFAULT NULL,
  `coursename` varchar(255) DEFAULT NULL,
  `teaid` int(10) DEFAULT NULL,
  `selectcourseid` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`selectcourseid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------
INSERT INTO `selectcourse` VALUES ('1640129313', 'MN', 'html', '456', '1');
INSERT INTO `selectcourse` VALUES ('1640129314', 'QM', 'java基础知识', '456', '2');
INSERT INTO `selectcourse` VALUES ('1640129315', 'QN', 'java基础知识', '123', '3');
INSERT INTO `selectcourse` VALUES ('1640129316', 'QS', 'java基础知识', '456', '4');
INSERT INTO `selectcourse` VALUES ('1640129317', 'MN', 'html', '456', '5');
INSERT INTO `selectcourse` VALUES ('1640129318', 'QM', 'java基础知识', '456', '6');
INSERT INTO `selectcourse` VALUES ('1640129319', 'QN', 'java基础知识', '123', '7');
INSERT INTO `selectcourse` VALUES ('1640129320', 'QS', 'java基础知识', '456', '8');
INSERT INTO `selectcourse` VALUES ('1640129321', 'MN', 'html', '456', '9');
INSERT INTO `selectcourse` VALUES ('1640129322', 'QM', 'java基础知识', '456', '10');
INSERT INTO `selectcourse` VALUES ('1640129314', 'MN', 'html', '456', '11');
INSERT INTO `selectcourse` VALUES ('1640129313', 'QM', 'java基础知识', '456', '12');
INSERT INTO `selectcourse` VALUES ('1640129313', 'BQ', 'html', '456', '13');
INSERT INTO `selectcourse` VALUES ('1640129313', 'BQ', 'java基础教程', '456', '16');
INSERT INTO `selectcourse` VALUES ('1640129314', 'QM', 'LINUX基础教程', '456', '17');
INSERT INTO `selectcourse` VALUES ('1640129315', 'QS', 'XML基础教程', '456', '18');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuid` int(20) NOT NULL,
  `stuname` varchar(255) NOT NULL,
  `stusex` char(2) NOT NULL,
  `stuclass` varchar(255) NOT NULL,
  `stuphone` int(11) DEFAULT NULL,
  `stupassword` varchar(255) NOT NULL,
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1640129313', '123', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129314', '李嘉庆', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129315', '李贾青', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129316', '李家庆1', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129317', '李家庆2', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129318', '李家庆3', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129319', '李家庆4', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129320', '李家庆5', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129321', '李家庆6', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129322', '李家庆7', '男', '软工4班', '1316084791', '123');
INSERT INTO `student` VALUES ('1640129323', '李家庆8', '男', '软工4班', '1316084791', '123');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teaid` int(20) NOT NULL,
  `teapassword` varchar(255) NOT NULL,
  `teaname` varchar(255) NOT NULL,
  `teasex` char(2) NOT NULL,
  `teaphone` int(11) DEFAULT NULL,
  PRIMARY KEY (`teaid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('123', '1234', 'ljq', '男', '123456578');
INSERT INTO `teacher` VALUES ('456', '1234', '李家庆', '男', '123456578');
