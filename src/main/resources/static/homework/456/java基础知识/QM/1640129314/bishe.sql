/*
Navicat MySQL Data Transfer

Source Server         : ljq
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : bishe

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-02-16 21:34:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `courseid` varchar(255) NOT NULL,
  `coursename` varchar(255) NOT NULL,
  `courseclass` varchar(255) NOT NULL,
  `teaid` varchar(255) NOT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'html', 'MN', '456');
INSERT INTO `course` VALUES ('2', 'java基础知识', 'QM', '456');
INSERT INTO `course` VALUES ('3', 'java基础知识', 'QN', '123');
INSERT INTO `course` VALUES ('4', 'java基础知识', 'QS', '456');

-- ----------------------------
-- Table structure for finishsituation
-- ----------------------------
DROP TABLE IF EXISTS `finishsituation`;
CREATE TABLE `finishsituation` (
  `workid` int(10) NOT NULL,
  `courseclass` varchar(255) NOT NULL DEFAULT '0',
  `finished` int(3) NOT NULL,
  PRIMARY KEY (`workid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finishsituation
-- ----------------------------

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework` (
  `workid` int(255) NOT NULL AUTO_INCREMENT,
  `workname` varchar(255) NOT NULL,
  `uploaddate` varchar(255) NOT NULL,
  `enddate` varchar(255) NOT NULL,
  `uploadclass` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) NOT NULL,
  `course` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`workid`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES ('89', '新建 Microsoft Word 文档', '2020-02-20', '2020-02-22', 'QS', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/java基础知识\\QS\\新建 Microsoft Word 文档.docx', 'java基础知识');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `stuid` varchar(255) NOT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `score` int(3) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `uploadclass` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('1640129313', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/java基础知识\\QS\\新建 Microsoft Word 文档.docx', '0', '已上传', 'java基础知识', 'QS');
INSERT INTO `score` VALUES ('1640129314', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/java基础知识\\QS\\新建 Microsoft Word 文档.docx', '0', '已上传', 'java基础知识', 'QS');
INSERT INTO `score` VALUES ('1640129315', 'C:/Users/ljq/IdeaProjects/bishe/src/main/resources/static/homework/java基础知识\\QS\\新建 Microsoft Word 文档.docx', '0', '已上传', 'java基础知识', 'QS');

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
  `course` varchar(255) DEFAULT NULL,
  `coursename` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectcourse
-- ----------------------------

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
  `courseclass` varchar(255) DEFAULT NULL,
  `course` varchar(255) DEFAULT NULL,
  `courseid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1640129313', '123', '男', '软工4班', '1316084791', '123', 'QS', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129314', '李嘉庆', '男', '软工4班', '1316084791', '123', 'QS', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129315', '李贾青', '男', '软工4班', '1316084791', '123', 'QS', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129316', '李家庆1', '男', '软工4班', '1316084791', '123', 'QM', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129317', '李家庆2', '男', '软工4班', '1316084791', '123', 'QM', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129318', '李家庆3', '男', '软工4班', '1316084791', '123', 'QN', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129319', '李家庆4', '男', '软工4班', '1316084791', '123', 'QN', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129320', '李家庆5', '男', '软工4班', '1316084791', '123', 'MN', 'html', null);
INSERT INTO `student` VALUES ('1640129321', '李家庆6', '男', '软工4班', '1316084791', '123', 'QM', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129322', '李家庆7', '男', '软工4班', '1316084791', '123', 'QM', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129323', '李家庆', '男', '软工4班', '1316084791', '123', 'MN', 'html', null);
INSERT INTO `student` VALUES ('1640129324', '李家庆', '男', '软工4班', '1316084791', '123', 'QM', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129325', '李家庆', '男', '软工4班', '1316084791', '123', 'QN', 'java基础知识', null);
INSERT INTO `student` VALUES ('1640129326', '李家庆', '男', '软工4班', '1316084791', '123', 'MN', 'html', null);
INSERT INTO `student` VALUES ('1640129313', '123', '男', '软工4班', '1316084791', '123', 'MN', 'html', null);

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
