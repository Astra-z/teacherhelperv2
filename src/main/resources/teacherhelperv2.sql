/*
Navicat MySQL Data Transfer

Source Server         : db
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : teacherhelperv2

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-06-14 10:44:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_college
-- ----------------------------
DROP TABLE IF EXISTS `s_college`;
CREATE TABLE `s_college` (
  `COLLEGE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COLLEGE_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`COLLEGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_college
-- ----------------------------
INSERT INTO `s_college` VALUES ('1', '计算机学院');
INSERT INTO `s_college` VALUES ('2', '文理学院');
INSERT INTO `s_college` VALUES ('3', '法学院');
INSERT INTO `s_college` VALUES ('4', '外国语学院');
INSERT INTO `s_college` VALUES ('5', '环境与化学工程学院');
INSERT INTO `s_college` VALUES ('6', '管理学院');
INSERT INTO `s_college` VALUES ('7', '经济学院');
INSERT INTO `s_college` VALUES ('8', '医学院');
INSERT INTO `s_college` VALUES ('9', '上海电影学院');
INSERT INTO `s_college` VALUES ('10', '美术学院');
INSERT INTO `s_college` VALUES ('11', '222');

-- ----------------------------
-- Table structure for s_course
-- ----------------------------
DROP TABLE IF EXISTS `s_course`;
CREATE TABLE `s_course` (
  `COURSE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE_NAME` varchar(255) NOT NULL,
  `TERM` varchar(255) NOT NULL,
  `TEACHER_ID` varchar(11) DEFAULT NULL,
  `COURSE_TEACHER` varchar(255) DEFAULT NULL,
  `STUDENT_NUM` int(11) DEFAULT NULL,
  `MAX_NUM` int(11) DEFAULT NULL,
  `SPEC_ID` int(11) NOT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `COURSE_ADDRESS` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime NOT NULL,
  `COURSER_HOMEWORK_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`COURSE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_course
-- ----------------------------
INSERT INTO `s_course` VALUES ('1', '高等数学', '2017-2018学年春季学期', 'T1', '张三', '7', '60', '8', null, '1A101', '2020-05-15 15:51:55', '2020-06-13 17:47:49', null);
INSERT INTO `s_course` VALUES ('2', '大学英语', '2017-2018学年春季学期', 'T1', '张三', '30', '60', '1', null, '2B305', '2020-06-05 13:00:01', '2020-06-05 13:00:01', null);
INSERT INTO `s_course` VALUES ('3', '高级数据库', '2017-2018学年春季学期', 'T1', '张三', '50', '50', '1', null, '1A501', '2020-06-05 13:00:31', '2020-06-05 13:00:31', null);
INSERT INTO `s_course` VALUES ('4', '数据挖掘', '2017-2018学年春季学期', 'T1', '张三', '0', '50', '2', null, '3C507', '2020-06-05 13:00:43', '2020-06-05 13:00:43', null);
INSERT INTO `s_course` VALUES ('5', '软件体系结构', '2017-2018学年春季学期', 'T1', '张三', '0', '30', '1', null, '2C201', '2020-06-05 13:01:34', '2020-06-05 13:01:34', null);
INSERT INTO `s_course` VALUES ('6', '软件项目管理', '2017-2018学年春季学期', 'T1', '张三', '0', '40', '1', null, '3D512', '2020-06-05 13:01:49', '2020-06-05 13:01:49', null);
INSERT INTO `s_course` VALUES ('7', '马克思主义与社会科学方法论', '2017-2018学年春季学期', 'T1', '张三', '0', '40', '2', null, '3D301', '2020-06-05 13:02:04', '2020-06-05 13:02:04', null);
INSERT INTO `s_course` VALUES ('8', '算法导论', '2017-2018学年春季学期', 'T1', '张三', '0', '60', '1', null, '2B408', '2020-06-05 15:16:17', '2020-06-05 15:16:17', null);
INSERT INTO `s_course` VALUES ('22', '大学物理2', '2022-2023学年春季学期', 'T1', 'sanzhang', '0', '50', '2', '123', 'G205', '2020-06-09 12:27:26', '2020-06-09 12:27:26', null);

-- ----------------------------
-- Table structure for s_course_frequency
-- ----------------------------
DROP TABLE IF EXISTS `s_course_frequency`;
CREATE TABLE `s_course_frequency` (
  `COURSE_FREQUENCY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSEID` int(11) NOT NULL,
  `WEEKDAY` int(11) DEFAULT NULL,
  `START_LESSON` int(11) DEFAULT NULL,
  `END_LESSON` int(11) DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`COURSE_FREQUENCY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_course_frequency
-- ----------------------------
INSERT INTO `s_course_frequency` VALUES ('1', '1', '0', '1', '5', '2020-01-01 00:00:00', '2020-06-06 10:34:57', '2020-06-06 10:34:57');
INSERT INTO `s_course_frequency` VALUES ('2', '1', '1', '3', '6', null, '2020-06-06 11:04:29', '2020-06-06 11:04:29');
INSERT INTO `s_course_frequency` VALUES ('3', '2', '2', '2', '4', null, '2020-06-06 11:06:19', '2020-06-06 11:06:19');
INSERT INTO `s_course_frequency` VALUES ('4', '2', '3', '3', '3', '2020-06-10 00:00:00', '2020-06-06 11:08:30', '2020-06-06 11:08:30');
INSERT INTO `s_course_frequency` VALUES ('5', '16', '4', '4', '6', '2020-06-10 00:00:00', '2020-06-06 11:09:31', '2020-06-06 11:09:31');
INSERT INTO `s_course_frequency` VALUES ('6', '17', '1', '4', '6', null, '2020-06-06 11:09:53', '2020-06-06 11:09:53');
INSERT INTO `s_course_frequency` VALUES ('7', '20', '1', '0', '3', '2020-06-12 00:00:00', '2020-06-08 16:10:11', '2020-06-08 16:10:11');
INSERT INTO `s_course_frequency` VALUES ('8', '21', '4', '2', '4', '2020-06-10 00:00:00', '2020-06-09 12:07:15', '2020-06-09 12:07:15');
INSERT INTO `s_course_frequency` VALUES ('9', '22', '4', '4', '5', '2020-06-11 00:00:00', '2020-06-09 12:27:26', '2020-06-09 12:27:26');

-- ----------------------------
-- Table structure for s_course_homework
-- ----------------------------
DROP TABLE IF EXISTS `s_course_homework`;
CREATE TABLE `s_course_homework` (
  `COURSE_HOMEWORK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSE_ID` int(11) DEFAULT NULL,
  `COURSE_HOMEWORK_NAME` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`COURSE_HOMEWORK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_course_homework
-- ----------------------------
INSERT INTO `s_course_homework` VALUES ('1', '1', 'homework1', '要求123', '2020-06-07 10:58:12', '2020-06-07 10:58:16');
INSERT INTO `s_course_homework` VALUES ('2', '1', 'homework2', '要求456', '2020-06-07 10:58:50', '2020-06-07 10:58:50');
INSERT INTO `s_course_homework` VALUES ('3', '1', 'homework3', '要求789', '2020-06-07 10:58:59', '2020-06-07 10:58:59');
INSERT INTO `s_course_homework` VALUES ('4', '1', 'h4', 'anshijiao', '2020-06-08 10:30:59', '2020-06-08 10:30:59');
INSERT INTO `s_course_homework` VALUES ('5', '1', '课堂练习', '周五交', '2020-06-09 12:57:28', '2020-06-09 12:57:28');

-- ----------------------------
-- Table structure for s_file
-- ----------------------------
DROP TABLE IF EXISTS `s_file`;
CREATE TABLE `s_file` (
  `FILE_ID` int(11) NOT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `FILE_TYPE` tinyint(4) DEFAULT NULL,
  `FILE_LOCATION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_file
-- ----------------------------

-- ----------------------------
-- Table structure for s_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) NOT NULL COMMENT '菜单/按钮名称',
  `URL` varchar(100) DEFAULT NULL COMMENT '菜单URL',
  `PERMS` text COMMENT '权限标识',
  `ICON` varchar(50) DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) DEFAULT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` bigint(20) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES ('1', '0', '系统管理', '', null, 'el-icon-location', '0', '1', '2017-12-27 16:39:07', '2020-05-30 16:52:49');
INSERT INTO `s_menu` VALUES ('2', '0', '系统监控', null, null, 'el-icon-location', '0', '2', '2017-12-27 16:45:51', '2018-01-17 17:08:28');
INSERT INTO `s_menu` VALUES ('3', '1', '用户管理', 'user', 'user:list', '', '0', '1', '2017-12-27 16:47:13', '2018-04-25 09:00:01');
INSERT INTO `s_menu` VALUES ('4', '1', '角色管理', 'role', 'role:list', '', '0', '2', '2017-12-27 16:48:09', '2018-04-25 09:01:12');
INSERT INTO `s_menu` VALUES ('5', '1', '菜单管理', 'menu', 'menu:list', '', '0', '3', '2017-12-27 16:48:57', '2018-04-25 09:01:30');
INSERT INTO `s_menu` VALUES ('6', '126', '部门管理', 'dept', 'dept:list', '', '0', '4', '2017-12-27 16:57:33', '2020-06-04 14:03:11');
INSERT INTO `s_menu` VALUES ('8', '2', '在线用户', 'session', 'session:list', '', '0', '1', '2017-12-27 16:59:33', '2018-04-25 09:02:04');
INSERT INTO `s_menu` VALUES ('10', '2', '系统日志', 'log', 'log:list', '', '0', '3', '2017-12-27 17:00:50', '2018-04-25 09:02:18');
INSERT INTO `s_menu` VALUES ('11', '3', '新增用户', null, 'user:add', null, '1', null, '2017-12-27 17:02:58', null);
INSERT INTO `s_menu` VALUES ('12', '3', '修改用户', null, 'user:update', null, '1', null, '2017-12-27 17:04:07', null);
INSERT INTO `s_menu` VALUES ('13', '3', '删除用户', null, 'user:delete', null, '1', null, '2017-12-27 17:04:58', null);
INSERT INTO `s_menu` VALUES ('14', '4', '新增角色', null, 'role:add', null, '1', null, '2017-12-27 17:06:38', null);
INSERT INTO `s_menu` VALUES ('15', '4', '修改角色', null, 'role:update', null, '1', null, '2017-12-27 17:06:38', null);
INSERT INTO `s_menu` VALUES ('16', '4', '删除角色', null, 'role:delete', null, '1', null, '2017-12-27 17:06:38', null);
INSERT INTO `s_menu` VALUES ('17', '5', '新增菜单', null, 'menu:add', null, '1', null, '2017-12-27 17:08:02', null);
INSERT INTO `s_menu` VALUES ('18', '5', '修改菜单', null, 'menu:update', null, '1', null, '2017-12-27 17:08:02', null);
INSERT INTO `s_menu` VALUES ('19', '5', '删除菜单', null, 'menu:delete', null, '1', null, '2017-12-27 17:08:02', null);
INSERT INTO `s_menu` VALUES ('20', '6', '新增部门', null, 'dept:add', null, '1', null, '2017-12-27 17:09:24', null);
INSERT INTO `s_menu` VALUES ('21', '6', '修改部门', null, 'dept:update', null, '1', null, '2017-12-27 17:09:24', null);
INSERT INTO `s_menu` VALUES ('22', '6', '删除部门', null, 'dept:delete', null, '1', null, '2017-12-27 17:09:24', null);
INSERT INTO `s_menu` VALUES ('23', '8', '踢出用户', null, 'user:kickout', null, '1', null, '2017-12-27 17:11:13', null);
INSERT INTO `s_menu` VALUES ('24', '10', '删除日志', null, 'log:delete', null, '1', null, '2017-12-27 17:11:45', null);
INSERT INTO `s_menu` VALUES ('58', '0', '网络资源', null, null, 'el-icon-location', '0', '3', '2018-01-12 15:28:48', '2018-01-22 19:49:26');
INSERT INTO `s_menu` VALUES ('59', '58', '天气查询', 'weather', 'weather:list', '', '0', null, '2018-01-12 15:40:02', '2018-04-25 09:02:57');
INSERT INTO `s_menu` VALUES ('61', '58', '每日一文', 'article', 'article:list', '', '0', null, '2018-01-15 17:17:14', '2018-04-25 09:03:08');
INSERT INTO `s_menu` VALUES ('64', '1', '字典管理', 'dict', 'dict:list', '', '0', null, '2018-01-18 10:38:25', '2018-04-25 09:01:50');
INSERT INTO `s_menu` VALUES ('65', '64', '新增字典', null, 'dict:add', null, '1', null, '2018-01-18 19:10:08', null);
INSERT INTO `s_menu` VALUES ('66', '64', '修改字典', null, 'dict:update', null, '1', null, '2018-01-18 19:10:27', null);
INSERT INTO `s_menu` VALUES ('67', '64', '删除字典', null, 'dict:delete', null, '1', null, '2018-01-18 19:10:47', null);
INSERT INTO `s_menu` VALUES ('81', '58', '影视资讯', null, null, null, '0', null, '2018-01-22 14:12:59', null);
INSERT INTO `s_menu` VALUES ('82', '81', '正在热映', 'movie/hot', 'movie:hot', '', '0', null, '2018-01-22 14:13:47', '2018-04-25 09:03:48');
INSERT INTO `s_menu` VALUES ('83', '81', '即将上映', 'movie/coming', 'movie:coming', '', '0', null, '2018-01-22 14:14:36', '2018-04-25 09:04:05');
INSERT INTO `s_menu` VALUES ('86', '58', 'One 一个', null, null, null, '0', null, '2018-01-26 09:42:41', '2018-01-26 09:43:46');
INSERT INTO `s_menu` VALUES ('87', '86', '绘画', 'one/painting', 'one:painting', '', '0', null, '2018-01-26 09:47:14', '2018-04-25 09:04:17');
INSERT INTO `s_menu` VALUES ('88', '86', '语文', 'one/yuwen', 'one:yuwen', '', '0', null, '2018-01-26 09:47:40', '2018-04-25 09:04:30');
INSERT INTO `s_menu` VALUES ('89', '86', '散文', 'one/essay', 'one:essay', '', '0', null, '2018-01-26 09:48:05', '2018-04-25 09:04:42');
INSERT INTO `s_menu` VALUES ('101', '0', '任务调度', null, null, 'el-icon-location', '0', '4', '2018-02-24 15:52:57', null);
INSERT INTO `s_menu` VALUES ('102', '101', '定时任务', 'job', 'job:list', '', '0', null, '2018-02-24 15:53:53', '2018-04-25 09:05:12');
INSERT INTO `s_menu` VALUES ('103', '102', '新增任务', null, 'job:add', null, '1', null, '2018-02-24 15:55:10', null);
INSERT INTO `s_menu` VALUES ('104', '102', '修改任务', null, 'job:update', null, '1', null, '2018-02-24 15:55:53', null);
INSERT INTO `s_menu` VALUES ('105', '102', '删除任务', null, 'job:delete', null, '1', null, '2018-02-24 15:56:18', null);
INSERT INTO `s_menu` VALUES ('106', '102', '暂停任务', null, 'job:pause', null, '1', null, '2018-02-24 15:57:08', null);
INSERT INTO `s_menu` VALUES ('107', '102', '恢复任务', null, 'job:resume', null, '1', null, '2018-02-24 15:58:21', null);
INSERT INTO `s_menu` VALUES ('108', '102', '立即执行任务', null, 'job:run', null, '1', null, '2018-02-24 15:59:45', null);
INSERT INTO `s_menu` VALUES ('109', '101', '调度日志', 'jobLog', 'jobLog:list', '', '0', null, '2018-02-24 16:00:45', '2018-04-25 09:05:25');
INSERT INTO `s_menu` VALUES ('110', '109', '删除日志', null, 'jobLog:delete', null, '1', null, '2018-02-24 16:01:21', null);
INSERT INTO `s_menu` VALUES ('113', '2', 'Redis监控', 'redis/info', 'redis:list', '', '0', null, '2018-06-28 14:29:42', null);
INSERT INTO `s_menu` VALUES ('114', '2', 'Redis终端', 'redis/terminal', 'redis:terminal', '', '0', null, '2018-06-28 15:35:21', null);
INSERT INTO `s_menu` VALUES ('115', '135', '成绩管理', 'score', 'score:list', null, null, null, '2020-05-18 17:31:43', '2020-06-09 11:52:22');
INSERT INTO `s_menu` VALUES ('117', '115', '新增成绩', '', 'score:add', null, null, null, '2020-05-18 17:32:46', '2020-05-18 17:32:46');
INSERT INTO `s_menu` VALUES ('118', '115', '修改成绩', '', 'score:update', null, null, null, '2020-05-18 17:34:00', '2020-05-18 17:34:00');
INSERT INTO `s_menu` VALUES ('120', '115', '删除成绩', 'score', 'score:delete', null, null, null, '2020-05-24 18:27:52', '2020-05-24 18:27:52');
INSERT INTO `s_menu` VALUES ('121', '3', '111', 'college', 'aaa', null, null, null, '2020-05-24 18:34:06', '2020-05-29 20:28:19');
INSERT INTO `s_menu` VALUES ('122', '2', '12321', 'college', 'college:add', null, null, null, '2020-05-24 18:36:09', '2020-05-30 16:59:36');
INSERT INTO `s_menu` VALUES ('123', '1', '123', 'course', 'course:update', null, null, null, '2020-05-24 18:37:52', '2020-05-30 17:03:44');
INSERT INTO `s_menu` VALUES ('124', '122', '112233', 'college', 'college:add', null, null, null, '2020-05-30 16:56:18', '2020-05-30 16:56:18');
INSERT INTO `s_menu` VALUES ('125', '1', '课程管理', 'course', 'course:list', null, null, null, '2020-06-02 13:03:57', '2020-06-02 13:04:23');
INSERT INTO `s_menu` VALUES ('126', '0', '基本功能', '', '', null, null, '5', '2020-06-04 14:02:28', '2020-06-09 11:45:42');
INSERT INTO `s_menu` VALUES ('127', '126', '学院管理', 'college', 'college:list', null, null, null, '2020-06-04 14:34:21', '2020-06-04 14:34:29');
INSERT INTO `s_menu` VALUES ('128', '127', '添加学院', 'college', 'college:add', null, null, null, '2020-06-04 14:34:49', '2020-06-04 14:37:06');
INSERT INTO `s_menu` VALUES ('129', '127', '更新学院', 'college', 'college:update', null, null, null, '2020-06-04 14:35:23', '2020-06-04 14:37:12');
INSERT INTO `s_menu` VALUES ('130', '127', '删除学院', 'college', 'college:delete', null, null, null, '2020-06-04 14:36:46', '2020-06-04 14:36:46');
INSERT INTO `s_menu` VALUES ('131', '127', '专业管理', 'spec', 'spec:list', null, null, null, '2020-06-04 15:32:27', '2020-06-06 10:23:21');
INSERT INTO `s_menu` VALUES ('132', '131', '专业添加', 'spec', 'spec:add', null, null, null, '2020-06-04 15:32:44', '2020-06-04 15:32:44');
INSERT INTO `s_menu` VALUES ('133', '131', '专业更新', 'spec', 'spec:update', null, null, null, '2020-06-04 15:33:01', '2020-06-04 15:33:01');
INSERT INTO `s_menu` VALUES ('134', '131', '专业删除', 'spec', 'spec:delete', null, null, null, '2020-06-04 15:33:21', '2020-06-04 15:33:21');
INSERT INTO `s_menu` VALUES ('135', '126', '课程管理', 'course', 'course:list', null, null, null, '2020-06-05 12:25:39', '2020-06-05 12:25:39');
INSERT INTO `s_menu` VALUES ('136', '135', '课程更新', 'course', 'course:update', null, null, null, '2020-06-05 12:25:56', '2020-06-05 12:25:56');
INSERT INTO `s_menu` VALUES ('137', '135', '课程删除', 'course', 'course:delete', null, null, null, '2020-06-05 12:26:11', '2020-06-05 12:26:11');
INSERT INTO `s_menu` VALUES ('138', '135', '课程添加', 'course', 'course:add', null, null, null, '2020-06-05 12:26:30', '2020-06-05 12:26:30');
INSERT INTO `s_menu` VALUES ('139', '135', '课程时间查看', 'coursefrequency', 'coursefrequency:list', null, null, null, '2020-06-06 10:21:48', '2020-06-06 10:21:48');
INSERT INTO `s_menu` VALUES ('140', '135', '课程时间更新', 'coursefrequency', 'coursefrequency:update', null, null, null, '2020-06-06 10:22:10', '2020-06-06 10:22:10');
INSERT INTO `s_menu` VALUES ('141', '135', '课程时间添加', 'coursefrequency', 'coursefrequency:add', null, null, null, '2020-06-06 10:22:29', '2020-06-06 10:22:29');
INSERT INTO `s_menu` VALUES ('142', '135', '课程时间删除', 'coursefrequency', 'coursefrequency:delete', null, null, null, '2020-06-06 10:22:48', '2020-06-06 10:22:48');
INSERT INTO `s_menu` VALUES ('143', '126', '课程表查看', 'courselist', 'course:list', null, null, null, '2020-06-06 11:25:55', '2020-06-06 11:26:57');
INSERT INTO `s_menu` VALUES ('144', '135', '作业查看', 'coursehomework', 'coursehomework:list', null, null, null, '2020-06-07 10:50:15', '2020-06-07 10:50:15');
INSERT INTO `s_menu` VALUES ('145', '135', '作业修改', 'coursehomework', 'coursehomework:update', null, null, null, '2020-06-07 10:50:29', '2020-06-07 10:50:29');
INSERT INTO `s_menu` VALUES ('146', '135', '作业添加', 'coursehomework', 'coursehomework:add', null, null, null, '2020-06-07 10:50:53', '2020-06-07 10:50:53');
INSERT INTO `s_menu` VALUES ('147', '135', '作业删除', 'coursehomework', 'coursehomework:delete', null, null, null, '2020-06-07 10:51:11', '2020-06-07 10:51:11');
INSERT INTO `s_menu` VALUES ('148', '135', '学生学院查询', 'college', 'college:list', null, null, null, '2020-06-07 20:37:36', '2020-06-07 20:37:36');
INSERT INTO `s_menu` VALUES ('149', '135', '学生专业查询', 'spec', 'spec:list', null, null, null, '2020-06-07 20:38:26', '2020-06-07 20:38:26');
INSERT INTO `s_menu` VALUES ('150', '135', '学成查看已选课程', 'score', 'score:list', null, null, null, '2020-06-08 08:38:50', '2020-06-08 08:38:50');
INSERT INTO `s_menu` VALUES ('151', '135', '学生添加课程', 'score', 'score:add', null, null, null, '2020-06-08 08:39:30', '2020-06-08 08:39:30');
INSERT INTO `s_menu` VALUES ('152', '126', '定时提醒', 'note', 'note:list', null, null, null, '2020-06-08 19:39:21', '2020-06-08 19:39:21');
INSERT INTO `s_menu` VALUES ('153', '152', '添加提醒', 'note', 'note:add', null, null, null, '2020-06-08 19:39:39', '2020-06-08 19:39:39');
INSERT INTO `s_menu` VALUES ('154', '152', '提醒修改', 'note', 'note:update', null, null, null, '2020-06-08 19:39:53', '2020-06-08 19:39:53');
INSERT INTO `s_menu` VALUES ('155', '152', '提醒删除', 'note', 'note:delete', null, null, null, '2020-06-08 19:40:02', '2020-06-08 19:40:02');
INSERT INTO `s_menu` VALUES ('156', '126', '成绩管理', 'score', 'score:list', null, null, null, '2020-06-13 16:07:37', '2020-06-13 16:07:37');
INSERT INTO `s_menu` VALUES ('157', '126', '导师管理', 'studentmentor', 'studentmentor:list', null, null, null, '2020-06-13 21:27:13', '2020-06-13 21:50:48');
INSERT INTO `s_menu` VALUES ('158', '157', '学生添加', 'studentmentor', 'studentmentor:add', null, null, null, '2020-06-13 21:27:28', '2020-06-13 21:27:28');
INSERT INTO `s_menu` VALUES ('159', '157', '学生修改', 'studentmentor', 'studentmentor:update', null, null, null, '2020-06-13 21:27:47', '2020-06-13 21:28:14');
INSERT INTO `s_menu` VALUES ('160', '157', '学生删除', 'studentmentor', 'studentmentor:delete', null, null, null, '2020-06-13 21:28:03', '2020-06-13 21:28:24');

-- ----------------------------
-- Table structure for s_note
-- ----------------------------
DROP TABLE IF EXISTS `s_note`;
CREATE TABLE `s_note` (
  `NOTE_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `NOTE_NAME` varchar(255) NOT NULL,
  `NOTE_TYPE` int(4) DEFAULT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `FILEID` varchar(255) DEFAULT NULL,
  `NOTE_SWITCH` tinyint(4) DEFAULT NULL,
  `CREATE_TIME` datetime(3) DEFAULT NULL,
  `MODIFY_TIME` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`NOTE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_note
-- ----------------------------
INSERT INTO `s_note` VALUES ('1', '123', '1', '2020-06-08 17:47:52', '2020-06-13 13:07:57', '1', '44444', null, '0', null, '2020-06-13 13:08:29.000');
INSERT INTO `s_note` VALUES ('28', '开会', '2', '2020-06-09 13:01:17', '2020-06-09 13:01:30', '3', '7777', '1111', '0', '2020-06-09 13:01:17.000', '2020-06-12 21:13:38.000');
INSERT INTO `s_note` VALUES ('29', '111', '1', '2020-06-13 13:23:31', '2020-06-20 18:27:59', '1', '111', null, '0', '2020-06-13 13:23:32.000', '2020-06-13 18:55:32.222');
INSERT INTO `s_note` VALUES ('30', '2131', '2', '2020-06-13 13:29:08', '2020-06-13 14:22:26', '1', '1212', null, '0', '2020-06-13 13:29:09.000', '2020-06-13 14:22:17.640');
INSERT INTO `s_note` VALUES ('31', '11', '2', '2020-06-13 18:12:22', '2020-06-13 18:20:42', '1', '22', null, '1', '2020-06-13 18:12:23.000', '2020-06-13 18:20:36.936');
INSERT INTO `s_note` VALUES ('32', '上课', '2', '2020-06-13 18:28:25', '2020-06-13 18:29:50', '1', '带书', null, '0', '2020-06-13 18:28:25.000', '2020-06-13 18:29:46.948');
INSERT INTO `s_note` VALUES ('33', '2121', '2', '2020-06-13 18:30:22', '2020-06-13 18:30:24', '1', '2121', null, '1', '2020-06-13 18:30:22.000', '2020-06-13 18:30:22.408');
INSERT INTO `s_note` VALUES ('34', '2121', '2', '2020-06-13 18:33:12', '2020-06-13 18:33:14', '1', '2121', null, '1', '2020-06-13 18:33:13.000', '2020-06-13 18:33:12.898');
INSERT INTO `s_note` VALUES ('35', '2121', '2', '2020-06-13 18:34:02', '2020-06-13 18:34:08', '1', '2121', null, '1', '2020-06-13 18:34:03.000', '2020-06-13 18:34:02.860');
INSERT INTO `s_note` VALUES ('36', '2121', '2', '2020-06-13 18:35:11', '2020-06-13 18:35:15', '1', '2121', null, '1', '2020-06-13 18:35:11.000', '2020-06-13 18:35:11.496');
INSERT INTO `s_note` VALUES ('37', 'hhhhhhhhh', '2', '2020-06-13 18:41:39', '2020-06-13 18:41:48', '1', null, null, '1', '2020-06-13 18:41:39.613', '2020-06-13 18:41:39.613');
INSERT INTO `s_note` VALUES ('38', 'hhhhhhhhh', '2', '2020-06-13 18:46:49', '2020-06-13 18:46:56', '1', '12312', null, '1', '2020-06-13 18:46:48.923', '2020-06-13 18:46:48.923');
INSERT INTO `s_note` VALUES ('39', 'wwww', '2', '2020-06-13 18:53:32', '2020-06-13 18:53:37', '1', null, null, '0', '2020-06-13 18:53:33.234', '2020-06-13 18:53:33.234');
INSERT INTO `s_note` VALUES ('40', 'wwww', '2', '2020-06-13 18:53:35', '2020-06-13 18:53:37', '1', null, null, '1', '2020-06-13 18:53:43.371', '2020-06-13 18:53:43.371');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(100) NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', '管理员', '管理员', '2020-05-01 16:23:11', '2020-06-13 21:32:26');
INSERT INTO `s_role` VALUES ('2', '教师', '教师账号，可增删部分信息', '2020-05-01 16:25:09', '2020-06-13 16:30:54');
INSERT INTO `s_role` VALUES ('3', '学生', '只可查看部分信息', '2020-05-08 20:00:15', '2020-06-08 08:39:59');
INSERT INTO `s_role` VALUES ('14', '123', '222', '2020-05-30 11:28:14', '2020-06-02 12:58:37');

-- ----------------------------
-- Table structure for s_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `s_role_menu`;
CREATE TABLE `s_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID',
  `MENU_ID` bigint(20) NOT NULL COMMENT '菜单/按钮ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_menu
-- ----------------------------
INSERT INTO `s_role_menu` VALUES ('63', '58');
INSERT INTO `s_role_menu` VALUES ('63', '81');
INSERT INTO `s_role_menu` VALUES ('63', '82');
INSERT INTO `s_role_menu` VALUES ('63', '83');
INSERT INTO `s_role_menu` VALUES ('24', '8');
INSERT INTO `s_role_menu` VALUES ('24', '2');
INSERT INTO `s_role_menu` VALUES ('24', '10');
INSERT INTO `s_role_menu` VALUES ('65', '86');
INSERT INTO `s_role_menu` VALUES ('65', '88');
INSERT INTO `s_role_menu` VALUES ('65', '89');
INSERT INTO `s_role_menu` VALUES ('65', '58');
INSERT INTO `s_role_menu` VALUES ('65', '61');
INSERT INTO `s_role_menu` VALUES ('23', '11');
INSERT INTO `s_role_menu` VALUES ('23', '12');
INSERT INTO `s_role_menu` VALUES ('23', '13');
INSERT INTO `s_role_menu` VALUES ('23', '3');
INSERT INTO `s_role_menu` VALUES ('23', '1');
INSERT INTO `s_role_menu` VALUES ('64', '59');
INSERT INTO `s_role_menu` VALUES ('64', '58');
INSERT INTO `s_role_menu` VALUES ('25', '1');
INSERT INTO `s_role_menu` VALUES ('25', '2');
INSERT INTO `s_role_menu` VALUES ('25', '3');
INSERT INTO `s_role_menu` VALUES ('14', '1');
INSERT INTO `s_role_menu` VALUES ('14', '3');
INSERT INTO `s_role_menu` VALUES ('14', '2');
INSERT INTO `s_role_menu` VALUES ('3', '3');
INSERT INTO `s_role_menu` VALUES ('3', '4');
INSERT INTO `s_role_menu` VALUES ('3', '5');
INSERT INTO `s_role_menu` VALUES ('3', '64');
INSERT INTO `s_role_menu` VALUES ('3', '8');
INSERT INTO `s_role_menu` VALUES ('3', '10');
INSERT INTO `s_role_menu` VALUES ('3', '59');
INSERT INTO `s_role_menu` VALUES ('3', '61');
INSERT INTO `s_role_menu` VALUES ('3', '81');
INSERT INTO `s_role_menu` VALUES ('3', '82');
INSERT INTO `s_role_menu` VALUES ('3', '83');
INSERT INTO `s_role_menu` VALUES ('3', '86');
INSERT INTO `s_role_menu` VALUES ('3', '87');
INSERT INTO `s_role_menu` VALUES ('3', '88');
INSERT INTO `s_role_menu` VALUES ('3', '89');
INSERT INTO `s_role_menu` VALUES ('3', '102');
INSERT INTO `s_role_menu` VALUES ('3', '109');
INSERT INTO `s_role_menu` VALUES ('3', '126');
INSERT INTO `s_role_menu` VALUES ('3', '131');
INSERT INTO `s_role_menu` VALUES ('3', '135');
INSERT INTO `s_role_menu` VALUES ('3', '139');
INSERT INTO `s_role_menu` VALUES ('3', '144');
INSERT INTO `s_role_menu` VALUES ('3', '148');
INSERT INTO `s_role_menu` VALUES ('3', '149');
INSERT INTO `s_role_menu` VALUES ('3', '150');
INSERT INTO `s_role_menu` VALUES ('3', '151');
INSERT INTO `s_role_menu` VALUES ('3', '143');
INSERT INTO `s_role_menu` VALUES ('2', '8');
INSERT INTO `s_role_menu` VALUES ('2', '10');
INSERT INTO `s_role_menu` VALUES ('2', '24');
INSERT INTO `s_role_menu` VALUES ('2', '59');
INSERT INTO `s_role_menu` VALUES ('2', '61');
INSERT INTO `s_role_menu` VALUES ('2', '81');
INSERT INTO `s_role_menu` VALUES ('2', '82');
INSERT INTO `s_role_menu` VALUES ('2', '83');
INSERT INTO `s_role_menu` VALUES ('2', '126');
INSERT INTO `s_role_menu` VALUES ('2', '127');
INSERT INTO `s_role_menu` VALUES ('2', '131');
INSERT INTO `s_role_menu` VALUES ('2', '135');
INSERT INTO `s_role_menu` VALUES ('2', '115');
INSERT INTO `s_role_menu` VALUES ('2', '117');
INSERT INTO `s_role_menu` VALUES ('2', '118');
INSERT INTO `s_role_menu` VALUES ('2', '120');
INSERT INTO `s_role_menu` VALUES ('2', '136');
INSERT INTO `s_role_menu` VALUES ('2', '137');
INSERT INTO `s_role_menu` VALUES ('2', '138');
INSERT INTO `s_role_menu` VALUES ('2', '139');
INSERT INTO `s_role_menu` VALUES ('2', '140');
INSERT INTO `s_role_menu` VALUES ('2', '141');
INSERT INTO `s_role_menu` VALUES ('2', '142');
INSERT INTO `s_role_menu` VALUES ('2', '144');
INSERT INTO `s_role_menu` VALUES ('2', '145');
INSERT INTO `s_role_menu` VALUES ('2', '146');
INSERT INTO `s_role_menu` VALUES ('2', '147');
INSERT INTO `s_role_menu` VALUES ('2', '143');
INSERT INTO `s_role_menu` VALUES ('2', '156');
INSERT INTO `s_role_menu` VALUES ('1', '1');
INSERT INTO `s_role_menu` VALUES ('1', '3');
INSERT INTO `s_role_menu` VALUES ('1', '11');
INSERT INTO `s_role_menu` VALUES ('1', '12');
INSERT INTO `s_role_menu` VALUES ('1', '13');
INSERT INTO `s_role_menu` VALUES ('1', '4');
INSERT INTO `s_role_menu` VALUES ('1', '14');
INSERT INTO `s_role_menu` VALUES ('1', '15');
INSERT INTO `s_role_menu` VALUES ('1', '16');
INSERT INTO `s_role_menu` VALUES ('1', '5');
INSERT INTO `s_role_menu` VALUES ('1', '17');
INSERT INTO `s_role_menu` VALUES ('1', '18');
INSERT INTO `s_role_menu` VALUES ('1', '19');
INSERT INTO `s_role_menu` VALUES ('1', '64');
INSERT INTO `s_role_menu` VALUES ('1', '65');
INSERT INTO `s_role_menu` VALUES ('1', '66');
INSERT INTO `s_role_menu` VALUES ('1', '67');
INSERT INTO `s_role_menu` VALUES ('1', '2');
INSERT INTO `s_role_menu` VALUES ('1', '8');
INSERT INTO `s_role_menu` VALUES ('1', '23');
INSERT INTO `s_role_menu` VALUES ('1', '10');
INSERT INTO `s_role_menu` VALUES ('1', '24');
INSERT INTO `s_role_menu` VALUES ('1', '113');
INSERT INTO `s_role_menu` VALUES ('1', '114');
INSERT INTO `s_role_menu` VALUES ('1', '58');
INSERT INTO `s_role_menu` VALUES ('1', '59');
INSERT INTO `s_role_menu` VALUES ('1', '61');
INSERT INTO `s_role_menu` VALUES ('1', '81');
INSERT INTO `s_role_menu` VALUES ('1', '82');
INSERT INTO `s_role_menu` VALUES ('1', '83');
INSERT INTO `s_role_menu` VALUES ('1', '86');
INSERT INTO `s_role_menu` VALUES ('1', '87');
INSERT INTO `s_role_menu` VALUES ('1', '88');
INSERT INTO `s_role_menu` VALUES ('1', '89');
INSERT INTO `s_role_menu` VALUES ('1', '101');
INSERT INTO `s_role_menu` VALUES ('1', '102');
INSERT INTO `s_role_menu` VALUES ('1', '103');
INSERT INTO `s_role_menu` VALUES ('1', '104');
INSERT INTO `s_role_menu` VALUES ('1', '105');
INSERT INTO `s_role_menu` VALUES ('1', '106');
INSERT INTO `s_role_menu` VALUES ('1', '107');
INSERT INTO `s_role_menu` VALUES ('1', '108');
INSERT INTO `s_role_menu` VALUES ('1', '109');
INSERT INTO `s_role_menu` VALUES ('1', '110');
INSERT INTO `s_role_menu` VALUES ('1', '126');
INSERT INTO `s_role_menu` VALUES ('1', '127');
INSERT INTO `s_role_menu` VALUES ('1', '128');
INSERT INTO `s_role_menu` VALUES ('1', '129');
INSERT INTO `s_role_menu` VALUES ('1', '130');
INSERT INTO `s_role_menu` VALUES ('1', '131');
INSERT INTO `s_role_menu` VALUES ('1', '132');
INSERT INTO `s_role_menu` VALUES ('1', '133');
INSERT INTO `s_role_menu` VALUES ('1', '134');
INSERT INTO `s_role_menu` VALUES ('1', '135');
INSERT INTO `s_role_menu` VALUES ('1', '115');
INSERT INTO `s_role_menu` VALUES ('1', '117');
INSERT INTO `s_role_menu` VALUES ('1', '118');
INSERT INTO `s_role_menu` VALUES ('1', '120');
INSERT INTO `s_role_menu` VALUES ('1', '136');
INSERT INTO `s_role_menu` VALUES ('1', '137');
INSERT INTO `s_role_menu` VALUES ('1', '138');
INSERT INTO `s_role_menu` VALUES ('1', '139');
INSERT INTO `s_role_menu` VALUES ('1', '140');
INSERT INTO `s_role_menu` VALUES ('1', '141');
INSERT INTO `s_role_menu` VALUES ('1', '142');
INSERT INTO `s_role_menu` VALUES ('1', '144');
INSERT INTO `s_role_menu` VALUES ('1', '145');
INSERT INTO `s_role_menu` VALUES ('1', '146');
INSERT INTO `s_role_menu` VALUES ('1', '147');
INSERT INTO `s_role_menu` VALUES ('1', '152');
INSERT INTO `s_role_menu` VALUES ('1', '153');
INSERT INTO `s_role_menu` VALUES ('1', '154');
INSERT INTO `s_role_menu` VALUES ('1', '155');
INSERT INTO `s_role_menu` VALUES ('1', '156');
INSERT INTO `s_role_menu` VALUES ('1', '157');

-- ----------------------------
-- Table structure for s_score
-- ----------------------------
DROP TABLE IF EXISTS `s_score`;
CREATE TABLE `s_score` (
  `SCORE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STUDENT_ID` int(11) NOT NULL,
  `STUDENT_NAME` varchar(255) DEFAULT NULL,
  `COURSE_ID` int(11) NOT NULL,
  `TEACHER_ID` varchar(255) NOT NULL,
  `CLASS_ID` int(11) DEFAULT NULL,
  `SCORE` float DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`SCORE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_score
-- ----------------------------
INSERT INTO `s_score` VALUES ('1', '1971111', '1号同学', '1', 'T1', null, '99', '2020-06-07 21:02:47', '2020-06-13 16:55:14');
INSERT INTO `s_score` VALUES ('2', '1971111', '1号同学', '2', 'T1', null, null, '2020-06-07 21:10:38', '2020-06-07 21:10:38');
INSERT INTO `s_score` VALUES ('12', '1971111', '1号同学', '1', 'T1', null, null, '2020-06-08 09:23:20', '2020-06-08 09:23:20');

-- ----------------------------
-- Table structure for s_spec
-- ----------------------------
DROP TABLE IF EXISTS `s_spec`;
CREATE TABLE `s_spec` (
  `SPEC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `COLLEGE_ID` int(11) NOT NULL,
  `SPEC_NAME` varchar(255) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`SPEC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_spec
-- ----------------------------
INSERT INTO `s_spec` VALUES ('1', '1', '软工', '2020-06-04 20:23:49', '2020-06-04 20:23:53');
INSERT INTO `s_spec` VALUES ('2', '1', '计算机', '2020-06-04 20:24:28', '2020-06-04 20:24:28');
INSERT INTO `s_spec` VALUES ('3', '2', '新闻', '2020-06-04 20:25:18', '2020-06-04 20:25:18');
INSERT INTO `s_spec` VALUES ('4', '3', '教育', '2020-06-04 20:25:26', '2020-06-04 20:25:26');
INSERT INTO `s_spec` VALUES ('8', '2', '应用数学', '2020-06-09 12:34:42', '2020-06-09 12:34:42');

-- ----------------------------
-- Table structure for s_student_mentor
-- ----------------------------
DROP TABLE IF EXISTS `s_student_mentor`;
CREATE TABLE `s_student_mentor` (
  `STUDENT_ID` int(11) NOT NULL,
  `MENTOR_ID` int(11) NOT NULL,
  `FILE_PATH` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_student_mentor
-- ----------------------------
INSERT INTO `s_student_mentor` VALUES ('4', '1', null);
INSERT INTO `s_student_mentor` VALUES ('5', '1', null);

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `USER_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `SID` varchar(255) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `REALNAME` varchar(255) DEFAULT NULL,
  `SEX` varchar(255) DEFAULT NULL,
  `BIRTH` varchar(255) DEFAULT NULL,
  `ENROLL_DATE` datetime DEFAULT NULL,
  `USER_CLASS` varchar(255) DEFAULT NULL,
  `POLITICS_STATUS` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ADRESS` varchar(255) DEFAULT NULL,
  `COLLEGE_ID` int(11) DEFAULT NULL,
  `SPEC_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', '19744441', 'zhangsan', 'd33f2e6e9bd6adff52a232e9a007fe7f', '张三', '男', '', null, '1', '团员', '', '', '0', '0', '2020-05-16 02:42:09', '2020-05-16 02:42:09');
INSERT INTO `s_user` VALUES ('2', '19755551', 'lisi', 'd6f70f38e32806c6a449fd661e431574', '李四', '男', '', null, '1', '团员', '', '', '2', '0', '2020-05-16 02:46:20', '2020-05-16 02:46:20');
INSERT INTO `s_user` VALUES ('3', 'T1', 'teacher1', '90b0269f2e55447e77a38646c88e3bd4', '张三教师', '男', null, null, null, null, null, null, null, null, '2020-06-07 20:13:07', '2020-06-07 20:13:07');
INSERT INTO `s_user` VALUES ('4', '1971111', 'student1', '6f49f95aa053ebb4411dbe64745b06f6', '1号同学', '男', null, null, null, null, null, null, null, null, '2020-06-07 20:19:05', '2020-06-07 20:19:05');
INSERT INTO `s_user` VALUES ('5', '1972222', 'student2', '9a2d6a5c1a86193fa90d81ad6d4f991b', '2号同学', '男', null, null, null, null, null, null, null, null, '2020-06-07 20:19:15', '2020-06-07 20:19:15');
INSERT INTO `s_user` VALUES ('6', '1973333', 'student3', 'fa211bb4cf626087ad0f5cd070ba6173', '3号同学', '男', null, null, null, null, null, null, null, null, '2020-06-07 20:19:25', '2020-06-07 20:19:25');

-- ----------------------------
-- Table structure for s_user_role
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1');
INSERT INTO `s_user_role` VALUES ('2', '3');
INSERT INTO `s_user_role` VALUES ('4', '3');
INSERT INTO `s_user_role` VALUES ('5', '3');
INSERT INTO `s_user_role` VALUES ('6', '3');
INSERT INTO `s_user_role` VALUES ('3', '2');
