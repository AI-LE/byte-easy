/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cawler

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-07-24 10:28:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `answer`
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号热门回答',
  `title` mediumtext COMMENT '标题',
  `answerone` mediumtext COMMENT '回答一',
  `answertwo` mediumtext COMMENT '回答二',
  `answerthree` mediumtext COMMENT '回答三',
  `answerfour` mediumtext COMMENT '回答四',
  `answerfive` mediumtext COMMENT '回答五',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for `baidu`
-- ----------------------------
DROP TABLE IF EXISTS `baidu`;
CREATE TABLE `baidu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id序号',
  `title` varchar(2550) DEFAULT NULL COMMENT '百度知道标题',
  `creattime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `keyword` varchar(255) DEFAULT NULL COMMENT '搜索的关键词',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of baidu
-- ----------------------------

-- ----------------------------
-- Table structure for `baidukeyword`
-- ----------------------------
DROP TABLE IF EXISTS `baidukeyword`;
CREATE TABLE `baidukeyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) NOT NULL COMMENT '要抓取的关键字',
  `createtime` datetime NOT NULL COMMENT '关键字添加时间',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baidukeyword
-- ----------------------------
INSERT INTO `baidukeyword` VALUES ('1', '百度指数', '2019-07-12 19:43:51', 'admin');
INSERT INTO `baidukeyword` VALUES ('2', '百度', '2019-07-12 23:05:44', 'admin');
INSERT INTO `baidukeyword` VALUES ('4', 'Java学习', '2019-07-13 10:35:10', 'admin');
INSERT INTO `baidukeyword` VALUES ('5', 'Windows', '2019-07-13 12:45:40', 'admin');
INSERT INTO `baidukeyword` VALUES ('6', '海贼王', '2019-07-13 23:42:19', 'admin');
INSERT INTO `baidukeyword` VALUES ('7', '数据库的性能与功能', '2019-07-14 11:57:24', 'admin');

-- ----------------------------
-- Table structure for `baidukeyword_records`
-- ----------------------------
DROP TABLE IF EXISTS `baidukeyword_records`;
CREATE TABLE `baidukeyword_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) NOT NULL COMMENT '关键字',
  `catchtime` datetime NOT NULL COMMENT '抓取的时间',
  `keywordid` int(11) NOT NULL COMMENT '关键字对应的id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `uuid` varchar(255) DEFAULT NULL COMMENT '给每一个抓取记录唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of baidukeyword_records
-- ----------------------------

-- ----------------------------
-- Table structure for `bd_oldrecords`
-- ----------------------------
DROP TABLE IF EXISTS `bd_oldrecords`;
CREATE TABLE `bd_oldrecords` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `bdid` bigint(20) DEFAULT NULL COMMENT '百度历史记录连表id',
  `timejudge` bigint(20) DEFAULT NULL COMMENT '区分文件时间戳',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of bd_oldrecords
-- ----------------------------
INSERT INTO `bd_oldrecords` VALUES ('1', '2019-06-12 19:21:56', '本子', 'admin', '12', null);
INSERT INTO `bd_oldrecords` VALUES ('2', '2019-06-12 19:22:26', '乒乓球', 'admin', '8', null);
INSERT INTO `bd_oldrecords` VALUES ('3', '2019-06-12 19:23:12', '教师', 'admin', '5', null);
INSERT INTO `bd_oldrecords` VALUES ('10', '2019-06-21 16:04:14', '156', 'admin', '14', '1561104237059');
INSERT INTO `bd_oldrecords` VALUES ('11', '2019-06-21 16:05:01', '156', 'admin', '14', '1561104285248');
INSERT INTO `bd_oldrecords` VALUES ('12', '2019-06-21 17:08:41', '本子', 'admin', '12', '1561108100400');
INSERT INTO `bd_oldrecords` VALUES ('13', '2019-06-21 17:11:23', '156', 'admin', '14', '1561108245501');
INSERT INTO `bd_oldrecords` VALUES ('14', '2019-07-12 16:55:31', '本子', 'admin', '12', '1562921685972');

-- ----------------------------
-- Table structure for `bd_records`
-- ----------------------------
DROP TABLE IF EXISTS `bd_records`;
CREATE TABLE `bd_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id序号',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of bd_records
-- ----------------------------
INSERT INTO `bd_records` VALUES ('3', '2019-05-29 17:12:51', '眼镜', 'wth123');
INSERT INTO `bd_records` VALUES ('5', '2019-05-31 23:44:29', '教师', 'admin');
INSERT INTO `bd_records` VALUES ('8', '2019-06-01 00:01:06', '乒乓球', 'admin');
INSERT INTO `bd_records` VALUES ('11', '2019-06-01 13:51:51', '本子', 'admin');
INSERT INTO `bd_records` VALUES ('12', '2019-06-04 19:38:23', '本子', 'admin');
INSERT INTO `bd_records` VALUES ('14', '2019-06-15 22:23:38', '156', 'admin');

-- ----------------------------
-- Table structure for `blacklist`
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `idnumber` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `userid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of blacklist
-- ----------------------------
INSERT INTO `blacklist` VALUES ('5', '8655', null, '545', null);

-- ----------------------------
-- Table structure for `keyword_index`
-- ----------------------------
DROP TABLE IF EXISTS `keyword_index`;
CREATE TABLE `keyword_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` varchar(255) NOT NULL COMMENT '关键字',
  `bdindex` int(11) NOT NULL COMMENT '百度指数',
  `number` int(11) NOT NULL COMMENT '序号，记录抓为的位置，便于下一次抓取',
  `uuid` varchar(255) NOT NULL COMMENT '给某个关键字的抓取添加唯一标识，这样可以并发同时抓取，同过该字段区分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword_index
-- ----------------------------

-- ----------------------------
-- Table structure for `our_referral`
-- ----------------------------
DROP TABLE IF EXISTS `our_referral`;
CREATE TABLE `our_referral` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `idnumber` int(11) DEFAULT NULL COMMENT '身份证号',
  `content` varchar(255) DEFAULT NULL COMMENT '病情描述',
  `time` datetime DEFAULT NULL COMMENT '有效期',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `instruction` varchar(255) DEFAULT NULL COMMENT '说明',
  `office` varchar(255) DEFAULT NULL COMMENT '科室',
  `doctor` varchar(255) DEFAULT NULL COMMENT '医生',
  `username` varchar(255) DEFAULT NULL COMMENT '联系人',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `reason` varchar(255) DEFAULT NULL COMMENT '原因',
  `judge` varchar(255) DEFAULT NULL COMMENT '是否已来',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of our_referral
-- ----------------------------
INSERT INTO `our_referral` VALUES ('1', '1', '1', '2019-04-08 11:47:25', '已通过', null, null, null, '5654', null, null, '已到');
INSERT INTO `our_referral` VALUES ('6', '545', '票', '2019-04-08 00:00:00', '已通过', null, '科室1', null, '8655', null, null, '未到');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `series` varchar(255) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`series`) USING BTREE,
  UNIQUE KEY `UK_bqa9l0go97v49wwyx4c0u3kpd` (`token`) USING BTREE,
  UNIQUE KEY `UK_f8t9fsfwc17s6qcbx0ath6l3h` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for `referral`
-- ----------------------------
DROP TABLE IF EXISTS `referral`;
CREATE TABLE `referral` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `idnumber` int(11) DEFAULT NULL COMMENT '身份证号',
  `content` varchar(255) DEFAULT NULL COMMENT '病情描述',
  `time` datetime DEFAULT NULL COMMENT '有效期',
  `state` varchar(255) DEFAULT NULL COMMENT '状态',
  `file` varchar(255) DEFAULT NULL COMMENT '文件',
  `reason` varchar(255) DEFAULT NULL COMMENT '拒绝理由',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of referral
-- ----------------------------
INSERT INTO `referral` VALUES ('2', '1', '1', '2019-04-08 10:08:23', '已通过', '/fileSuffix/20190409\\20190409101938069_JDCE3F.doc', '');
INSERT INTO `referral` VALUES ('3', '123', '345', '2019-04-03 00:00:00', null, null, null);
INSERT INTO `referral` VALUES ('4', '1', '1', '2019-04-19 00:00:00', null, null, null);
INSERT INTO `referral` VALUES ('5', '234', '234234', '2019-04-10 00:00:00', null, null, null);
INSERT INTO `referral` VALUES ('6', '213', '123', '2019-04-11 00:00:00', null, null, null);
INSERT INTO `referral` VALUES ('7', '0', '00', '2019-04-10 00:00:00', null, '/fileSuffix/20190408\\20190408161619856_A03IX0.doc', null);

-- ----------------------------
-- Table structure for `sina_history`
-- ----------------------------
DROP TABLE IF EXISTS `sina_history`;
CREATE TABLE `sina_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字 ',
  `serchKeyword` text COMMENT '联想 关键字',
  `crawlerTime` datetime DEFAULT NULL COMMENT '爬取时间',
  `uuid` bigint(11) DEFAULT NULL COMMENT '与sina_serch对应关键字的id',
  `path` varchar(255) DEFAULT NULL COMMENT '导出路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sina_history
-- ----------------------------

-- ----------------------------
-- Table structure for `sina_serch`
-- ----------------------------
DROP TABLE IF EXISTS `sina_serch`;
CREATE TABLE `sina_serch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serchName` varchar(255) DEFAULT NULL COMMENT '搜索关键字',
  `content` text COMMENT '联想内容',
  `creatTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='微博搜索关键字';

-- ----------------------------
-- Records of sina_serch
-- ----------------------------
INSERT INTO `sina_serch` VALUES ('8', '垃圾分类', null, '2019-07-18 11:41:36');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKmeds2u6ae5usj0ko0bqj3k0eo` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) NOT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK3fekum3ead5klp7y4lckn5ohi` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '', '顶级栏目', '100', null, '0', null, '0');
INSERT INTO `sys_resource` VALUES ('2', '', '权限配置', '8', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('3', '', '角色管理', '102', '/role', '0', '/role', '2');
INSERT INTO `sys_resource` VALUES ('4', '', '权限管理', '103', '/resource', '0', '/resource', '2');
INSERT INTO `sys_resource` VALUES ('5', '', '用户管理', '1', '/user', '0', '/user', '2');
INSERT INTO `sys_resource` VALUES ('6', '', '编辑', '100', '/role/editor-role', '1', '/role/editor-role', '3');
INSERT INTO `sys_resource` VALUES ('7', '', '添加权限节点', '100', '/resource/add-permission', '1', '/resource/add-permission', '4');
INSERT INTO `sys_resource` VALUES ('9', '', '添加角色', '100', '/role/add-role', '1', '/role/add-role', '3');
INSERT INTO `sys_resource` VALUES ('10', '', '删除角色', '100', '/role/delete', '1', '/role/delete', '3');
INSERT INTO `sys_resource` VALUES ('12', '', '删除权限', '100', '/resource/delete', '1', '/resource/delete', '4');
INSERT INTO `sys_resource` VALUES ('13', '', '启用', '100', '/user/available-user', '1', '/user/available-user', '3');
INSERT INTO `sys_resource` VALUES ('14', '', '修改管理员密码', '100', '/user/modify-password', '1', '/user/modify-password', '5');
INSERT INTO `sys_resource` VALUES ('16', null, '权限编辑', '100', '/resource/editor-permission', '1', '/resource/editor-permission', '4');
INSERT INTO `sys_resource` VALUES ('150', '', '编辑管理员信息', '100', '/user/edit-user', '1', '/user/edit-user', '5');
INSERT INTO `sys_resource` VALUES ('167', null, '界面信息', '1', '/admin/tUnitprice', '0', '/admin/tUnitprice', '166');
INSERT INTO `sys_resource` VALUES ('168', null, '个人信息管理', '2', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('177', null, '用户信息', '2', '/admin/tUser', '0', '/admin/tUser', '168');
INSERT INTO `sys_resource` VALUES ('180', null, '添加管理员', '100', '/user/add-user', '1', '/user/add-user', '5');
INSERT INTO `sys_resource` VALUES ('181', null, '删除用户', '100', '/user/delete-user', '1', '/user/delete-user', '5');
INSERT INTO `sys_resource` VALUES ('187', null, '个人信息', '2', 'admin/tUser/myself', '0', 'admin/tUser/myself', '168');
INSERT INTO `sys_resource` VALUES ('192', null, '信息抓取', '2', '', '0', '', '1');
INSERT INTO `sys_resource` VALUES ('193', null, '知道抓取', '2', '/admin/bdRecords', '0', '/admin/bdRecords', '192');
INSERT INTO `sys_resource` VALUES ('195', null, '知乎抓取', '4', '/admin/zhihuRecords', '0', '/admin/zhihuRecords', '192');
INSERT INTO `sys_resource` VALUES ('201', null, '微博抓取', '15', '/admin/tBlogger', '0', '/admin/tBlogger', '192');
INSERT INTO `sys_resource` VALUES ('202', null, '百度关键字抓取', '1', '/admin/baidukeyword', '1', '/admin/baidukeyword', '192');
INSERT INTO `sys_resource` VALUES ('203', null, '微博问答抓取', '1', '/weiboQandA/weiboqa/admin/weiboqa', '0', '/admin/weiboqa', '192');
INSERT INTO `sys_resource` VALUES ('204', null, '微信抓取', '1', '/admin/wechat', '0', '/admin/wechat', '192');
INSERT INTO `sys_resource` VALUES ('205', null, '微博文章抓取', '6', '/admin/tBloggerArticle', '0', '/admin/tBloggerArticle', '192');
INSERT INTO `sys_resource` VALUES ('207', null, '微博搜索抓取', '9', '/admin/sinaSerch', '0', '/admin/sinaSerch', '192');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null, '管理员', '管理员');
INSERT INTO `sys_role` VALUES ('9', null, '可以登录网站执行不同的权利', '用户');

-- ----------------------------
-- Table structure for `sys_role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `sys_role_id` bigint(20) NOT NULL,
  `resources_id` bigint(20) NOT NULL,
  KEY `FKog6jj4v6yh9e1ilxk2mwuk75a` (`resources_id`) USING BTREE,
  KEY `FKsqkqfd2hpr5cc2kbrtgoced2w` (`sys_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------
INSERT INTO `sys_role_resources` VALUES ('9', '192');
INSERT INTO `sys_role_resources` VALUES ('9', '193');
INSERT INTO `sys_role_resources` VALUES ('9', '195');
INSERT INTO `sys_role_resources` VALUES ('9', '192');
INSERT INTO `sys_role_resources` VALUES ('9', '193');
INSERT INTO `sys_role_resources` VALUES ('9', '195');
INSERT INTO `sys_role_resources` VALUES ('9', '202');
INSERT INTO `sys_role_resources` VALUES ('1', '2');
INSERT INTO `sys_role_resources` VALUES ('1', '3');
INSERT INTO `sys_role_resources` VALUES ('1', '6');
INSERT INTO `sys_role_resources` VALUES ('1', '9');
INSERT INTO `sys_role_resources` VALUES ('1', '10');
INSERT INTO `sys_role_resources` VALUES ('1', '13');
INSERT INTO `sys_role_resources` VALUES ('1', '4');
INSERT INTO `sys_role_resources` VALUES ('1', '7');
INSERT INTO `sys_role_resources` VALUES ('1', '12');
INSERT INTO `sys_role_resources` VALUES ('1', '16');
INSERT INTO `sys_role_resources` VALUES ('1', '5');
INSERT INTO `sys_role_resources` VALUES ('1', '14');
INSERT INTO `sys_role_resources` VALUES ('1', '150');
INSERT INTO `sys_role_resources` VALUES ('1', '180');
INSERT INTO `sys_role_resources` VALUES ('1', '181');
INSERT INTO `sys_role_resources` VALUES ('1', '192');
INSERT INTO `sys_role_resources` VALUES ('1', '193');
INSERT INTO `sys_role_resources` VALUES ('1', '195');
INSERT INTO `sys_role_resources` VALUES ('1', '201');
INSERT INTO `sys_role_resources` VALUES ('1', '202');
INSERT INTO `sys_role_resources` VALUES ('1', '203');
INSERT INTO `sys_role_resources` VALUES ('1', '204');
INSERT INTO `sys_role_resources` VALUES ('1', '205');
INSERT INTO `sys_role_resources` VALUES ('1', '207');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createtime` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `available` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `sex_type` int(11) DEFAULT NULL COMMENT '性别(0.男,1.女)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2017-07-11 17:42:18', '$2a$10$SIU57gfkh/TsIVYALXBNAeDnQzkm652FT9cg4h8wtEfC306uliyYa', '2019-01-11 07:34:38', 'admin', '', '1191134106@qq.com', '15030103078', '1');
INSERT INTO `sys_user` VALUES ('58', '2019-05-21 10:48:04', '$2a$10$tGhwyD5LfQBx6H2fgZl9UOWkmDkKZ5ntv89XGZ8WCMGil/E6xXgyq', '2019-05-21 10:48:04', 'wth2066272', '', '553404185@qq.com', '18833231190', '1');
INSERT INTO `sys_user` VALUES ('59', '2019-05-24 11:53:56', '$2a$10$eKeI/i7p8Ygym7glTsb9L.EG7i1Te39nJM1394OdVDodEgIyk.kW6', '2019-05-24 11:53:56', 'wth123', '', 'wth20662723@163.com', '18833231190', '1');

-- ----------------------------
-- Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `sys_user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  KEY `FKdpvc6d7xqpqr43dfuk1s27cqh` (`roles_id`) USING BTREE,
  KEY `FKd0ut7sloes191bygyf7a3pk52` (`sys_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1');
INSERT INTO `sys_user_roles` VALUES ('58', '1');
INSERT INTO `sys_user_roles` VALUES ('59', '9');

-- ----------------------------
-- Table structure for `t_blogger`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger`;
CREATE TABLE `t_blogger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `bloggername` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '博主名字',
  `uid` bigint(20) DEFAULT NULL COMMENT '博主的uid',
  `containerid` bigint(20) DEFAULT NULL COMMENT '博主的containerid',
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `wordmax` bigint(20) DEFAULT NULL COMMENT '最大字数',
  `wordmin` bigint(20) DEFAULT NULL COMMENT '最小字数',
  `timestart` datetime DEFAULT NULL COMMENT '抓取时间节点',
  `timeend` datetime DEFAULT NULL COMMENT '抓取时间尾节点',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `commitchioce` int(255) DEFAULT NULL COMMENT '评论的:0等于no 1等于yes',
  `pointersum` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `judge` int(255) DEFAULT NULL COMMENT '选择模式：0等于纯文字 1等于文字和图片 2文字加图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blogger
-- ----------------------------
INSERT INTO `t_blogger` VALUES ('18', '组织二号头目', '1782961197', null, '2019-06-24 18:39:24', '1', null, '2019-06-20 10:58:40', '2019-06-21 14:08:41', 'admin', '0', '1', '0');
INSERT INTO `t_blogger` VALUES ('23', '卢湾伯爵', '5876465418', null, '2019-06-24 09:54:17', '0', null, '2019-06-22 00:00:00', '2019-06-24 09:54:16', 'admin', '0', null, '0');
INSERT INTO `t_blogger` VALUES ('24', '和硕礼亲王', '2245159373', null, '2019-06-21 20:52:43', '0', null, '2019-06-21 00:00:00', '2019-06-21 19:30:55', 'admin', '1', '0', '1');
INSERT INTO `t_blogger` VALUES ('25', '天津眼叔', '1439660697', null, '2019-06-21 20:47:02', '0', null, '2019-06-01 00:00:00', '2019-06-21 00:00:00', 'admin', '0', null, '1');
INSERT INTO `t_blogger` VALUES ('28', '村西边王寡妇', '3224580794', null, '2019-06-25 18:20:28', '0', null, '2019-06-21 00:00:00', '2019-06-23 00:00:00', 'admin', '0', '0', '0');
INSERT INTO `t_blogger` VALUES ('29', '健康养生日志', '5685858421', null, '2019-06-24 09:11:50', '0', null, '2019-06-21 00:00:00', '2019-06-24 00:00:00', 'admin', '0', null, '0');
INSERT INTO `t_blogger` VALUES ('30', '散户小韭菜', '1703827472', null, '2019-06-24 09:12:11', '0', null, '2019-06-21 00:00:00', '2019-06-24 00:00:00', 'admin', '0', null, '0');
INSERT INTO `t_blogger` VALUES ('31', '红茶家的三叔', '2611641261', null, '2019-06-24 13:36:09', '0', null, '2019-06-01 00:00:00', '2019-06-24 00:00:00', 'admin', '0', '0', '0');

-- ----------------------------
-- Table structure for `t_blogger_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger_article`;
CREATE TABLE `t_blogger_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blogger_article
-- ----------------------------
INSERT INTO `t_blogger_article` VALUES ('10', '迪丽热巴', '2019-07-13 14:34:23');

-- ----------------------------
-- Table structure for `t_blogger_article_old_records`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger_article_old_records`;
CREATE TABLE `t_blogger_article_old_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kid` bigint(11) DEFAULT NULL COMMENT '文章关键字id',
  `keywords` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `timejudge` bigint(20) DEFAULT NULL COMMENT '区分文件时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blogger_article_old_records
-- ----------------------------
INSERT INTO `t_blogger_article_old_records` VALUES ('1', '10', '迪丽热巴', '2019-07-24 10:22:07', '1563934927676');

-- ----------------------------
-- Table structure for `t_blogger_content`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger_content`;
CREATE TABLE `t_blogger_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '内容',
  `bloggerid` bigint(20) DEFAULT NULL COMMENT '文章和博主连表id',
  `contentrealy` mediumtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '转发',
  `onlyid` bigint(20) DEFAULT NULL COMMENT '对应的回答及转发连',
  `contentid` bigint(20) DEFAULT NULL COMMENT '连表',
  `createtime` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '博主创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blogger_content
-- ----------------------------

-- ----------------------------
-- Table structure for `t_blogger_img`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger_img`;
CREATE TABLE `t_blogger_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `imgsourceurl` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '照片路径',
  `imglocalurl` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '本地照片路径',
  `contentid` bigint(20) DEFAULT NULL COMMENT '连表id代表内容里的照片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blogger_img
-- ----------------------------
INSERT INTO `t_blogger_img` VALUES ('1', 0x68747470733A2F2F7778322E73696E61696D672E636E2F6F726A3336302F6330333333326261677931673333336E74706B6F6A6A32306A363262716777612E6A7067, 0x443A2F776F72642F3230313930363235313134393131343239342E6A7067, '4372568481300319');
INSERT INTO `t_blogger_img` VALUES ('2', 0x68747470733A2F2F7778322E73696E61696D672E636E2F6F726A3336302F6330333333326261677931673333336E76356164776A3230376F3039773074342E6A7067, 0x443A2F776F72642F3230313930363235313134393235333036302E6A7067, '4372568481300319');

-- ----------------------------
-- Table structure for `t_blogger_old`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger_old`;
CREATE TABLE `t_blogger_old` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `bloggername` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '博主名字',
  `uid` bigint(20) DEFAULT NULL COMMENT '博主的uid',
  `containerid` bigint(20) DEFAULT NULL COMMENT '博主的containerid',
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `wordmax` bigint(20) DEFAULT NULL COMMENT '最大字数',
  `wordmin` bigint(20) DEFAULT NULL COMMENT '最小字数',
  `timestart` datetime DEFAULT NULL COMMENT '抓取时间节点',
  `timeend` datetime DEFAULT NULL COMMENT '抓取时间尾节点',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `commitchioce` int(255) DEFAULT NULL COMMENT '0等于no 1等于yes',
  `pointersum` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `judge` int(255) DEFAULT NULL COMMENT '0等于纯文字 1等于文字加图片',
  `timeduring` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '时间阶段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blogger_old
-- ----------------------------

-- ----------------------------
-- Table structure for `t_blogger_point`
-- ----------------------------
DROP TABLE IF EXISTS `t_blogger_point`;
CREATE TABLE `t_blogger_point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `comment` mediumtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '评论',
  `pointsum` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `contentid` bigint(200) DEFAULT NULL COMMENT '连表内容id代表该内容的点赞数和评论',
  `commitername` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '回复者名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_blogger_point
-- ----------------------------

-- ----------------------------
-- Table structure for `t_recordssum`
-- ----------------------------
DROP TABLE IF EXISTS `t_recordssum`;
CREATE TABLE `t_recordssum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `records` bigint(20) DEFAULT NULL COMMENT '记录',
  `createtime` datetime DEFAULT NULL COMMENT '抓取时间',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '抓取类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1299 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_recordssum
-- ----------------------------
INSERT INTO `t_recordssum` VALUES ('12', '150', '2019-06-02 16:17:55', '知乎');
INSERT INTO `t_recordssum` VALUES ('14', '200', '2019-06-03 16:30:52', '百度知道');
INSERT INTO `t_recordssum` VALUES ('15', '150', '2019-05-29 16:38:33', '微博');
INSERT INTO `t_recordssum` VALUES ('16', '120', '2019-06-03 17:08:56', '知乎');
INSERT INTO `t_recordssum` VALUES ('17', '200', '2019-06-03 18:08:44', '百度知道');
INSERT INTO `t_recordssum` VALUES ('18', '200', '2019-06-03 18:11:11', '百度知道');
INSERT INTO `t_recordssum` VALUES ('19', '200', '2019-06-03 18:12:19', '百度知道');
INSERT INTO `t_recordssum` VALUES ('20', '200', '2019-06-03 18:14:12', '百度知道');
INSERT INTO `t_recordssum` VALUES ('21', '200', '2019-06-03 18:16:17', '百度知道');
INSERT INTO `t_recordssum` VALUES ('22', '200', '2019-06-03 18:19:41', '百度知道');
INSERT INTO `t_recordssum` VALUES ('23', '200', '2019-06-03 18:21:13', '百度知道');
INSERT INTO `t_recordssum` VALUES ('24', '200', '2019-06-03 18:22:32', '百度知道');
INSERT INTO `t_recordssum` VALUES ('25', '200', '2019-06-03 20:55:34', '百度知道');
INSERT INTO `t_recordssum` VALUES ('26', '200', '2019-06-03 21:21:17', '百度知道');
INSERT INTO `t_recordssum` VALUES ('27', '200', '2019-06-03 21:22:10', '百度知道');
INSERT INTO `t_recordssum` VALUES ('28', '200', '2019-06-03 21:23:00', '百度知道');
INSERT INTO `t_recordssum` VALUES ('29', '200', '2019-06-03 21:24:46', '百度知道');
INSERT INTO `t_recordssum` VALUES ('30', '122', '2019-06-03 21:27:24', '知乎');
INSERT INTO `t_recordssum` VALUES ('31', '122', '2019-06-03 21:28:07', '知乎');
INSERT INTO `t_recordssum` VALUES ('32', '122', '2019-06-03 21:29:51', '知乎');
INSERT INTO `t_recordssum` VALUES ('33', '122', '2019-06-03 21:36:20', '知乎');
INSERT INTO `t_recordssum` VALUES ('34', '122', '2019-06-03 21:39:37', '知乎');
INSERT INTO `t_recordssum` VALUES ('35', '122', '2019-06-03 21:40:09', '知乎');
INSERT INTO `t_recordssum` VALUES ('36', '139', '2019-06-03 21:46:41', '微博');
INSERT INTO `t_recordssum` VALUES ('37', '148', '2019-06-03 21:48:21', '微博');
INSERT INTO `t_recordssum` VALUES ('38', '148', '2019-06-04 09:21:02', '微博');
INSERT INTO `t_recordssum` VALUES ('39', '114', '2019-06-04 09:47:06', '知乎');
INSERT INTO `t_recordssum` VALUES ('40', '116', '2019-06-04 09:49:57', '知乎');
INSERT INTO `t_recordssum` VALUES ('41', '200', '2019-06-04 10:38:45', '百度知道');
INSERT INTO `t_recordssum` VALUES ('42', '200', '2019-06-04 10:41:20', '百度知道');
INSERT INTO `t_recordssum` VALUES ('43', '200', '2019-06-04 10:42:02', '百度知道');
INSERT INTO `t_recordssum` VALUES ('44', '200', '2019-06-04 10:43:07', '百度知道');
INSERT INTO `t_recordssum` VALUES ('45', '200', '2019-06-04 10:43:56', '百度知道');
INSERT INTO `t_recordssum` VALUES ('46', '200', '2019-06-04 10:46:06', '百度知道');
INSERT INTO `t_recordssum` VALUES ('47', '200', '2019-06-04 10:49:23', '百度知道');
INSERT INTO `t_recordssum` VALUES ('48', '200', '2019-06-04 10:50:37', '百度知道');
INSERT INTO `t_recordssum` VALUES ('49', '200', '2019-06-04 10:51:13', '百度知道');
INSERT INTO `t_recordssum` VALUES ('50', '200', '2019-06-04 10:54:04', '百度知道');
INSERT INTO `t_recordssum` VALUES ('51', '112', '2019-06-04 10:54:38', '知乎');
INSERT INTO `t_recordssum` VALUES ('52', '148', '2019-06-04 10:55:38', '微博');
INSERT INTO `t_recordssum` VALUES ('53', '200', '2019-06-04 10:57:20', '百度知道');
INSERT INTO `t_recordssum` VALUES ('54', '200', '2019-06-04 10:59:28', '百度知道');
INSERT INTO `t_recordssum` VALUES ('55', '200', '2019-06-04 11:01:54', '百度知道');
INSERT INTO `t_recordssum` VALUES ('56', '112', '2019-06-04 11:02:36', '知乎');
INSERT INTO `t_recordssum` VALUES ('57', '148', '2019-06-04 11:03:21', '微博');
INSERT INTO `t_recordssum` VALUES ('58', '148', '2019-06-04 11:04:17', '微博');
INSERT INTO `t_recordssum` VALUES ('59', '200', '2019-06-04 11:09:32', '百度知道');
INSERT INTO `t_recordssum` VALUES ('60', '111', '2019-06-04 11:09:38', '知乎');
INSERT INTO `t_recordssum` VALUES ('61', '148', '2019-06-04 11:10:23', '微博');
INSERT INTO `t_recordssum` VALUES ('62', '148', '2019-06-04 11:11:43', '微博');
INSERT INTO `t_recordssum` VALUES ('63', '148', '2019-06-04 11:13:23', '微博');
INSERT INTO `t_recordssum` VALUES ('64', '148', '2019-06-04 11:14:38', '微博');
INSERT INTO `t_recordssum` VALUES ('65', '148', '2019-06-04 11:18:24', '微博');
INSERT INTO `t_recordssum` VALUES ('66', '200', '2019-06-04 11:19:36', '百度知道');
INSERT INTO `t_recordssum` VALUES ('67', '111', '2019-06-04 11:19:41', '知乎');
INSERT INTO `t_recordssum` VALUES ('68', '111', '2019-06-04 11:20:00', '知乎');
INSERT INTO `t_recordssum` VALUES ('69', '200', '2019-06-04 11:27:49', '百度知道');
INSERT INTO `t_recordssum` VALUES ('70', '200', '2019-06-04 11:28:34', '百度知道');
INSERT INTO `t_recordssum` VALUES ('71', '200', '2019-06-04 11:29:40', '百度知道');
INSERT INTO `t_recordssum` VALUES ('72', '200', '2019-06-04 11:44:36', '百度知道');
INSERT INTO `t_recordssum` VALUES ('73', '148', '2019-06-04 11:45:29', '微博');
INSERT INTO `t_recordssum` VALUES ('74', '200', '2019-06-04 11:50:01', '百度知道');
INSERT INTO `t_recordssum` VALUES ('75', '200', '2019-06-04 11:51:14', '百度知道');
INSERT INTO `t_recordssum` VALUES ('76', '200', '2019-06-04 11:52:52', '百度知道');
INSERT INTO `t_recordssum` VALUES ('77', '200', '2019-06-04 11:55:12', '百度知道');
INSERT INTO `t_recordssum` VALUES ('78', '147', '2019-06-04 11:55:28', '知乎');
INSERT INTO `t_recordssum` VALUES ('79', '147', '2019-06-04 11:57:57', '知乎');
INSERT INTO `t_recordssum` VALUES ('80', '148', '2019-06-04 11:58:49', '微博');
INSERT INTO `t_recordssum` VALUES ('81', '143', '2019-06-04 14:04:31', '知乎');
INSERT INTO `t_recordssum` VALUES ('82', '143', '2019-06-04 14:04:58', '知乎');
INSERT INTO `t_recordssum` VALUES ('83', '148', '2019-06-04 14:05:44', '微博');
INSERT INTO `t_recordssum` VALUES ('84', '148', '2019-06-04 14:08:37', '微博');
INSERT INTO `t_recordssum` VALUES ('85', '143', '2019-06-04 14:16:33', '知乎');
INSERT INTO `t_recordssum` VALUES ('86', '143', '2019-06-04 14:22:57', '知乎');
INSERT INTO `t_recordssum` VALUES ('87', '143', '2019-06-04 14:24:26', '知乎');
INSERT INTO `t_recordssum` VALUES ('88', '143', '2019-06-04 14:25:06', '知乎');
INSERT INTO `t_recordssum` VALUES ('89', '142', '2019-06-04 14:26:40', '知乎');
INSERT INTO `t_recordssum` VALUES ('90', '148', '2019-06-04 14:27:42', '微博');
INSERT INTO `t_recordssum` VALUES ('91', '148', '2019-06-04 14:29:33', '微博');
INSERT INTO `t_recordssum` VALUES ('92', '142', '2019-06-04 14:31:13', '知乎');
INSERT INTO `t_recordssum` VALUES ('93', '200', '2019-06-04 14:31:44', '百度知道');
INSERT INTO `t_recordssum` VALUES ('94', '148', '2019-06-04 14:32:29', '微博');
INSERT INTO `t_recordssum` VALUES ('95', '148', '2019-06-04 14:33:10', '微博');
INSERT INTO `t_recordssum` VALUES ('96', '148', '2019-06-04 14:34:07', '微博');
INSERT INTO `t_recordssum` VALUES ('97', '142', '2019-06-04 14:34:11', '知乎');
INSERT INTO `t_recordssum` VALUES ('98', '200', '2019-06-04 14:34:53', '百度知道');
INSERT INTO `t_recordssum` VALUES ('99', '161', '2019-06-04 14:37:46', '微博');
INSERT INTO `t_recordssum` VALUES ('100', '144', '2019-06-04 14:40:02', '微博');
INSERT INTO `t_recordssum` VALUES ('101', '200', '2019-06-04 14:41:48', '百度知道');
INSERT INTO `t_recordssum` VALUES ('102', '200', '2019-06-04 14:44:27', '百度知道');
INSERT INTO `t_recordssum` VALUES ('103', '143', '2019-06-04 14:44:33', '知乎');
INSERT INTO `t_recordssum` VALUES ('104', '148', '2019-06-04 14:45:45', '微博');
INSERT INTO `t_recordssum` VALUES ('105', '148', '2019-06-04 14:54:18', '微博');
INSERT INTO `t_recordssum` VALUES ('106', '200', '2019-06-04 14:59:26', '百度知道');
INSERT INTO `t_recordssum` VALUES ('107', '141', '2019-06-04 14:59:38', '知乎');
INSERT INTO `t_recordssum` VALUES ('108', '148', '2019-06-04 15:06:02', '微博');
INSERT INTO `t_recordssum` VALUES ('109', '200', '2019-06-04 20:10:12', '百度知道');
INSERT INTO `t_recordssum` VALUES ('110', '200', '2019-06-04 20:10:38', '百度知道');
INSERT INTO `t_recordssum` VALUES ('111', '134', '2019-06-04 20:13:48', '知乎');
INSERT INTO `t_recordssum` VALUES ('112', '200', '2019-06-04 20:18:01', '百度知道');
INSERT INTO `t_recordssum` VALUES ('113', '200', '2019-06-04 20:18:20', '百度知道');
INSERT INTO `t_recordssum` VALUES ('114', '200', '2019-06-04 20:19:43', '百度知道');
INSERT INTO `t_recordssum` VALUES ('115', '200', '2019-06-04 20:20:00', '百度知道');
INSERT INTO `t_recordssum` VALUES ('116', '200', '2019-06-04 20:20:17', '百度知道');
INSERT INTO `t_recordssum` VALUES ('117', '200', '2019-06-04 20:20:35', '百度知道');
INSERT INTO `t_recordssum` VALUES ('118', '148', '2019-06-04 20:29:45', '微博');
INSERT INTO `t_recordssum` VALUES ('119', '148', '2019-06-04 20:36:43', '微博');
INSERT INTO `t_recordssum` VALUES ('120', '296', '2019-06-04 20:37:49', '微博');
INSERT INTO `t_recordssum` VALUES ('121', '148', '2019-06-04 20:39:49', '微博');
INSERT INTO `t_recordssum` VALUES ('122', '148', '2019-06-04 20:43:15', '微博');
INSERT INTO `t_recordssum` VALUES ('123', '148', '2019-06-04 20:45:22', '微博');
INSERT INTO `t_recordssum` VALUES ('124', '148', '2019-06-04 20:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('125', '148', '2019-06-04 20:49:26', '微博');
INSERT INTO `t_recordssum` VALUES ('126', '148', '2019-06-04 20:53:24', '微博');
INSERT INTO `t_recordssum` VALUES ('127', '148', '2019-06-04 20:58:52', '微博');
INSERT INTO `t_recordssum` VALUES ('128', '148', '2019-06-04 21:18:46', '微博');
INSERT INTO `t_recordssum` VALUES ('129', '144', '2019-06-04 21:20:11', '微博');
INSERT INTO `t_recordssum` VALUES ('130', '144', '2019-06-04 21:24:09', '微博');
INSERT INTO `t_recordssum` VALUES ('131', '144', '2019-06-04 21:39:39', '微博');
INSERT INTO `t_recordssum` VALUES ('132', '144', '2019-06-04 21:50:18', '微博');
INSERT INTO `t_recordssum` VALUES ('133', '144', '2019-06-04 21:58:24', '微博');
INSERT INTO `t_recordssum` VALUES ('134', '134', '2019-06-04 22:04:21', '微博');
INSERT INTO `t_recordssum` VALUES ('135', '144', '2019-06-04 22:13:54', '微博');
INSERT INTO `t_recordssum` VALUES ('136', '144', '2019-06-04 22:48:11', '微博');
INSERT INTO `t_recordssum` VALUES ('137', '144', '2019-06-04 22:50:05', '微博');
INSERT INTO `t_recordssum` VALUES ('138', '144', '2019-06-04 23:00:06', '微博');
INSERT INTO `t_recordssum` VALUES ('139', '144', '2019-06-04 23:09:09', '微博');
INSERT INTO `t_recordssum` VALUES ('140', '144', '2019-06-04 23:26:17', '微博');
INSERT INTO `t_recordssum` VALUES ('141', '148', '2019-06-04 23:34:11', '微博');
INSERT INTO `t_recordssum` VALUES ('142', '222', '2019-06-05 00:06:20', '微博');
INSERT INTO `t_recordssum` VALUES ('143', '148', '2019-06-05 00:08:47', '微博');
INSERT INTO `t_recordssum` VALUES ('144', '144', '2019-06-05 00:13:50', '微博');
INSERT INTO `t_recordssum` VALUES ('145', '144', '2019-06-05 00:18:25', '微博');
INSERT INTO `t_recordssum` VALUES ('146', '144', '2019-06-05 00:22:54', '微博');
INSERT INTO `t_recordssum` VALUES ('147', '144', '2019-06-05 00:26:43', '微博');
INSERT INTO `t_recordssum` VALUES ('148', '145', '2019-06-05 00:27:46', '微博');
INSERT INTO `t_recordssum` VALUES ('149', '145', '2019-06-05 00:32:22', '微博');
INSERT INTO `t_recordssum` VALUES ('150', '144', '2019-06-05 00:36:12', '微博');
INSERT INTO `t_recordssum` VALUES ('151', '148', '2019-06-05 11:42:23', '微博');
INSERT INTO `t_recordssum` VALUES ('152', '200', '2019-06-05 11:43:23', '百度知道');
INSERT INTO `t_recordssum` VALUES ('153', '129', '2019-06-05 11:43:34', '知乎');
INSERT INTO `t_recordssum` VALUES ('154', '129', '2019-06-05 11:43:56', '知乎');
INSERT INTO `t_recordssum` VALUES ('155', '148', '2019-06-05 11:48:38', '微博');
INSERT INTO `t_recordssum` VALUES ('156', '148', '2019-06-05 11:51:06', '微博');
INSERT INTO `t_recordssum` VALUES ('157', '148', '2019-06-05 12:15:17', '微博');
INSERT INTO `t_recordssum` VALUES ('158', '148', '2019-06-05 12:16:36', '微博');
INSERT INTO `t_recordssum` VALUES ('159', '148', '2019-06-05 12:17:47', '微博');
INSERT INTO `t_recordssum` VALUES ('160', '148', '2019-06-05 12:18:53', '微博');
INSERT INTO `t_recordssum` VALUES ('161', '148', '2019-06-05 12:21:38', '微博');
INSERT INTO `t_recordssum` VALUES ('162', '296', '2019-06-05 12:22:44', '微博');
INSERT INTO `t_recordssum` VALUES ('163', '144', '2019-06-05 12:23:44', '微博');
INSERT INTO `t_recordssum` VALUES ('164', '148', '2019-06-05 12:26:14', '微博');
INSERT INTO `t_recordssum` VALUES ('165', '144', '2019-06-05 12:55:11', '微博');
INSERT INTO `t_recordssum` VALUES ('166', '147', '2019-06-05 16:22:37', '微博');
INSERT INTO `t_recordssum` VALUES ('167', '148', '2019-06-05 16:25:23', '微博');
INSERT INTO `t_recordssum` VALUES ('168', '148', '2019-06-05 16:32:50', '微博');
INSERT INTO `t_recordssum` VALUES ('169', '148', '2019-06-05 16:34:47', '微博');
INSERT INTO `t_recordssum` VALUES ('170', '148', '2019-06-05 16:36:43', '微博');
INSERT INTO `t_recordssum` VALUES ('171', '148', '2019-06-05 16:50:15', '微博');
INSERT INTO `t_recordssum` VALUES ('172', '200', '2019-06-05 17:11:07', '百度知道');
INSERT INTO `t_recordssum` VALUES ('173', '148', '2019-06-05 17:13:05', '微博');
INSERT INTO `t_recordssum` VALUES ('174', '581', '2019-06-05 17:38:42', '微博');
INSERT INTO `t_recordssum` VALUES ('175', '343', '2019-06-05 17:48:15', '微博');
INSERT INTO `t_recordssum` VALUES ('176', '343', '2019-06-05 17:54:29', '微博');
INSERT INTO `t_recordssum` VALUES ('177', '343', '2019-06-05 17:57:15', '微博');
INSERT INTO `t_recordssum` VALUES ('178', '343', '2019-06-05 18:03:50', '微博');
INSERT INTO `t_recordssum` VALUES ('179', '699', '2019-06-05 18:15:07', '微博');
INSERT INTO `t_recordssum` VALUES ('180', '1', '2019-06-05 18:16:03', '微博');
INSERT INTO `t_recordssum` VALUES ('181', '1', '2019-06-05 18:16:54', '微博');
INSERT INTO `t_recordssum` VALUES ('182', '1', '2019-06-05 18:21:22', '微博');
INSERT INTO `t_recordssum` VALUES ('183', '16', '2019-06-05 18:35:58', '微博');
INSERT INTO `t_recordssum` VALUES ('184', '167', '2019-06-05 19:03:50', '微博');
INSERT INTO `t_recordssum` VALUES ('185', '230', '2019-06-05 19:05:47', '微博');
INSERT INTO `t_recordssum` VALUES ('186', '230', '2019-06-05 19:11:23', '微博');
INSERT INTO `t_recordssum` VALUES ('187', '394', '2019-06-05 19:13:29', '微博');
INSERT INTO `t_recordssum` VALUES ('188', '43', '2019-06-05 19:26:05', '微博');
INSERT INTO `t_recordssum` VALUES ('189', '43', '2019-06-05 19:26:43', '微博');
INSERT INTO `t_recordssum` VALUES ('190', '43', '2019-06-05 19:27:00', '微博');
INSERT INTO `t_recordssum` VALUES ('191', '43', '2019-06-05 19:29:37', '微博');
INSERT INTO `t_recordssum` VALUES ('192', '43', '2019-06-05 19:35:31', '微博');
INSERT INTO `t_recordssum` VALUES ('193', '167', '2019-06-05 19:36:36', '微博');
INSERT INTO `t_recordssum` VALUES ('194', '43', '2019-06-05 19:51:19', '微博');
INSERT INTO `t_recordssum` VALUES ('195', '4', '2019-06-05 19:51:25', '微博');
INSERT INTO `t_recordssum` VALUES ('196', '4', '2019-06-05 19:51:45', '微博');
INSERT INTO `t_recordssum` VALUES ('197', '4', '2019-06-05 19:53:15', '微博');
INSERT INTO `t_recordssum` VALUES ('198', '4', '2019-06-05 20:02:23', '微博');
INSERT INTO `t_recordssum` VALUES ('199', '4', '2019-06-05 20:03:54', '微博');
INSERT INTO `t_recordssum` VALUES ('200', '56', '2019-06-06 21:50:10', '微博');
INSERT INTO `t_recordssum` VALUES ('201', '5', '2019-06-06 22:16:53', '微博');
INSERT INTO `t_recordssum` VALUES ('202', '283', '2019-06-06 22:20:08', '微博');
INSERT INTO `t_recordssum` VALUES ('203', '84', '2019-06-10 09:39:37', '微博');
INSERT INTO `t_recordssum` VALUES ('204', '285', '2019-06-10 10:46:17', '微博');
INSERT INTO `t_recordssum` VALUES ('205', '260', '2019-06-10 10:47:54', '微博');
INSERT INTO `t_recordssum` VALUES ('206', '1280', '2019-06-10 10:50:14', '微博');
INSERT INTO `t_recordssum` VALUES ('207', '1280', '2019-06-10 10:58:20', '微博');
INSERT INTO `t_recordssum` VALUES ('208', '490', '2019-06-10 11:34:21', '微博');
INSERT INTO `t_recordssum` VALUES ('209', '6', '2019-06-10 11:43:04', '微博');
INSERT INTO `t_recordssum` VALUES ('210', '6', '2019-06-10 11:44:00', '微博');
INSERT INTO `t_recordssum` VALUES ('211', '490', '2019-06-10 13:58:44', '微博');
INSERT INTO `t_recordssum` VALUES ('212', '275', '2019-06-10 16:26:39', '微博');
INSERT INTO `t_recordssum` VALUES ('213', '50', '2019-06-10 16:27:23', '微博');
INSERT INTO `t_recordssum` VALUES ('214', '489', '2019-06-10 16:32:34', '微博');
INSERT INTO `t_recordssum` VALUES ('215', '386', '2019-06-10 16:40:11', '微博');
INSERT INTO `t_recordssum` VALUES ('216', '505', '2019-06-10 16:46:32', '微博');
INSERT INTO `t_recordssum` VALUES ('217', '50', '2019-06-10 16:48:00', '微博');
INSERT INTO `t_recordssum` VALUES ('218', '489', '2019-06-10 17:42:25', '微博');
INSERT INTO `t_recordssum` VALUES ('219', '84', '2019-06-10 17:44:01', '微博');
INSERT INTO `t_recordssum` VALUES ('220', '88', '2019-06-10 21:24:19', '微博');
INSERT INTO `t_recordssum` VALUES ('221', '64', '2019-06-10 21:49:37', '微博');
INSERT INTO `t_recordssum` VALUES ('222', '4', '2019-06-10 21:49:46', '微博');
INSERT INTO `t_recordssum` VALUES ('223', '34', '2019-06-10 21:51:46', '微博');
INSERT INTO `t_recordssum` VALUES ('224', '34', '2019-06-10 22:03:03', '微博');
INSERT INTO `t_recordssum` VALUES ('225', '178', '2019-06-10 22:55:38', '知乎');
INSERT INTO `t_recordssum` VALUES ('226', '41', '2019-06-11 00:05:11', '微博');
INSERT INTO `t_recordssum` VALUES ('227', '79', '2019-06-11 00:10:53', '微博');
INSERT INTO `t_recordssum` VALUES ('228', '89', '2019-06-11 00:25:08', '微博');
INSERT INTO `t_recordssum` VALUES ('229', '89', '2019-06-11 00:27:17', '微博');
INSERT INTO `t_recordssum` VALUES ('230', '109', '2019-06-11 09:13:49', '微博');
INSERT INTO `t_recordssum` VALUES ('231', '89', '2019-06-11 09:35:23', '微博');
INSERT INTO `t_recordssum` VALUES ('232', '89', '2019-06-11 09:40:01', '微博');
INSERT INTO `t_recordssum` VALUES ('233', '90', '2019-06-11 10:19:29', '微博');
INSERT INTO `t_recordssum` VALUES ('234', '167', '2019-06-11 11:08:47', '微博');
INSERT INTO `t_recordssum` VALUES ('235', '168', '2019-06-11 11:08:51', '微博');
INSERT INTO `t_recordssum` VALUES ('236', '169', '2019-06-11 11:08:55', '微博');
INSERT INTO `t_recordssum` VALUES ('237', '170', '2019-06-11 11:08:59', '微博');
INSERT INTO `t_recordssum` VALUES ('238', '171', '2019-06-11 11:09:03', '微博');
INSERT INTO `t_recordssum` VALUES ('239', '172', '2019-06-11 11:09:07', '微博');
INSERT INTO `t_recordssum` VALUES ('240', '173', '2019-06-11 11:09:11', '微博');
INSERT INTO `t_recordssum` VALUES ('241', '174', '2019-06-11 11:09:14', '微博');
INSERT INTO `t_recordssum` VALUES ('242', '175', '2019-06-11 11:09:23', '微博');
INSERT INTO `t_recordssum` VALUES ('243', '176', '2019-06-11 11:09:27', '微博');
INSERT INTO `t_recordssum` VALUES ('244', '177', '2019-06-11 11:09:31', '微博');
INSERT INTO `t_recordssum` VALUES ('245', '178', '2019-06-11 11:09:35', '微博');
INSERT INTO `t_recordssum` VALUES ('246', '179', '2019-06-11 11:09:39', '微博');
INSERT INTO `t_recordssum` VALUES ('247', '180', '2019-06-11 11:09:44', '微博');
INSERT INTO `t_recordssum` VALUES ('248', '181', '2019-06-11 11:09:47', '微博');
INSERT INTO `t_recordssum` VALUES ('249', '182', '2019-06-11 11:09:51', '微博');
INSERT INTO `t_recordssum` VALUES ('250', '183', '2019-06-11 11:10:00', '微博');
INSERT INTO `t_recordssum` VALUES ('251', '184', '2019-06-11 11:10:04', '微博');
INSERT INTO `t_recordssum` VALUES ('252', '185', '2019-06-11 11:10:08', '微博');
INSERT INTO `t_recordssum` VALUES ('253', '186', '2019-06-11 11:10:12', '微博');
INSERT INTO `t_recordssum` VALUES ('254', '187', '2019-06-11 11:10:16', '微博');
INSERT INTO `t_recordssum` VALUES ('255', '37', '2019-06-11 11:14:13', '微博');
INSERT INTO `t_recordssum` VALUES ('256', '140', '2019-06-11 11:22:19', '微博');
INSERT INTO `t_recordssum` VALUES ('257', '140', '2019-06-11 11:40:00', '微博');
INSERT INTO `t_recordssum` VALUES ('258', '140', '2019-06-11 11:49:06', '微博');
INSERT INTO `t_recordssum` VALUES ('259', '142', '2019-06-11 11:58:43', '微博');
INSERT INTO `t_recordssum` VALUES ('260', '63', '2019-06-11 13:01:40', '微博');
INSERT INTO `t_recordssum` VALUES ('261', '1', '2019-06-11 13:54:12', '微博');
INSERT INTO `t_recordssum` VALUES ('262', '254', '2019-06-11 14:08:21', '微博');
INSERT INTO `t_recordssum` VALUES ('263', '254', '2019-06-11 14:47:46', '微博');
INSERT INTO `t_recordssum` VALUES ('264', '37', '2019-06-11 15:01:06', '微博');
INSERT INTO `t_recordssum` VALUES ('265', '254', '2019-06-11 15:23:22', '微博');
INSERT INTO `t_recordssum` VALUES ('266', '97', '2019-06-11 16:20:11', '微博');
INSERT INTO `t_recordssum` VALUES ('267', '97', '2019-06-11 16:29:32', '微博');
INSERT INTO `t_recordssum` VALUES ('268', '149', '2019-06-12 10:43:12', '知乎');
INSERT INTO `t_recordssum` VALUES ('269', '149', '2019-06-12 10:46:48', '知乎');
INSERT INTO `t_recordssum` VALUES ('270', '149', '2019-06-12 10:47:11', '知乎');
INSERT INTO `t_recordssum` VALUES ('271', '148', '2019-06-12 10:50:53', '知乎');
INSERT INTO `t_recordssum` VALUES ('272', '144', '2019-06-12 10:56:12', '知乎');
INSERT INTO `t_recordssum` VALUES ('273', '145', '2019-06-12 11:03:41', '知乎');
INSERT INTO `t_recordssum` VALUES ('274', '145', '2019-06-12 11:04:30', '知乎');
INSERT INTO `t_recordssum` VALUES ('275', '145', '2019-06-12 11:06:38', '知乎');
INSERT INTO `t_recordssum` VALUES ('276', '152', '2019-06-12 11:10:39', '知乎');
INSERT INTO `t_recordssum` VALUES ('277', '91', '2019-06-12 18:32:55', '知乎');
INSERT INTO `t_recordssum` VALUES ('278', '132', '2019-06-12 18:48:00', '知乎');
INSERT INTO `t_recordssum` VALUES ('279', '132', '2019-06-12 18:50:12', '知乎');
INSERT INTO `t_recordssum` VALUES ('280', '118', '2019-06-12 19:08:14', '微博');
INSERT INTO `t_recordssum` VALUES ('281', '60', '2019-06-12 19:13:26', '微博');
INSERT INTO `t_recordssum` VALUES ('282', '131', '2019-06-12 19:14:28', '知乎');
INSERT INTO `t_recordssum` VALUES ('283', '200', '2019-06-12 19:21:56', '百度知道');
INSERT INTO `t_recordssum` VALUES ('284', '200', '2019-06-12 19:22:26', '百度知道');
INSERT INTO `t_recordssum` VALUES ('285', '93', '2019-06-12 19:22:38', '知乎');
INSERT INTO `t_recordssum` VALUES ('286', '200', '2019-06-12 19:23:12', '百度知道');
INSERT INTO `t_recordssum` VALUES ('287', '144', '2019-06-12 19:23:26', '知乎');
INSERT INTO `t_recordssum` VALUES ('288', '93', '2019-06-12 19:25:34', '知乎');
INSERT INTO `t_recordssum` VALUES ('289', '144', '2019-06-12 19:26:02', '知乎');
INSERT INTO `t_recordssum` VALUES ('290', '144', '2019-06-12 19:26:22', '知乎');
INSERT INTO `t_recordssum` VALUES ('291', '157', '2019-06-12 19:29:04', '知乎');
INSERT INTO `t_recordssum` VALUES ('292', '176', '2019-06-12 19:32:03', '知乎');
INSERT INTO `t_recordssum` VALUES ('293', '132', '2019-06-12 19:52:22', '知乎');
INSERT INTO `t_recordssum` VALUES ('294', '159', '2019-06-12 19:53:11', '知乎');
INSERT INTO `t_recordssum` VALUES ('295', '144', '2019-06-12 19:58:04', '知乎');
INSERT INTO `t_recordssum` VALUES ('296', '39', '2019-06-12 20:07:25', '知乎');
INSERT INTO `t_recordssum` VALUES ('297', '100', '2019-06-12 20:08:39', '知乎');
INSERT INTO `t_recordssum` VALUES ('298', '131', '2019-06-12 20:14:14', '知乎');
INSERT INTO `t_recordssum` VALUES ('299', '65', '2019-06-12 20:17:19', '知乎');
INSERT INTO `t_recordssum` VALUES ('300', '182', '2019-06-13 10:32:30', '知乎');
INSERT INTO `t_recordssum` VALUES ('301', '182', '2019-06-13 10:35:40', '知乎');
INSERT INTO `t_recordssum` VALUES ('302', '180', '2019-06-13 10:39:28', '知乎');
INSERT INTO `t_recordssum` VALUES ('303', '180', '2019-06-13 10:48:01', '知乎');
INSERT INTO `t_recordssum` VALUES ('304', '180', '2019-06-13 10:50:11', '知乎');
INSERT INTO `t_recordssum` VALUES ('305', '181', '2019-06-13 10:52:02', '知乎');
INSERT INTO `t_recordssum` VALUES ('306', '180', '2019-06-13 10:56:53', '知乎');
INSERT INTO `t_recordssum` VALUES ('307', '200', '2019-06-13 10:59:05', '百度知道');
INSERT INTO `t_recordssum` VALUES ('308', '182', '2019-06-13 11:00:04', '知乎');
INSERT INTO `t_recordssum` VALUES ('309', '182', '2019-06-13 11:09:41', '知乎');
INSERT INTO `t_recordssum` VALUES ('310', '182', '2019-06-13 11:10:15', '知乎');
INSERT INTO `t_recordssum` VALUES ('311', '182', '2019-06-13 11:17:24', '知乎');
INSERT INTO `t_recordssum` VALUES ('312', '135', '2019-06-14 09:12:57', '知乎');
INSERT INTO `t_recordssum` VALUES ('313', '181', '2019-06-14 09:39:47', '知乎');
INSERT INTO `t_recordssum` VALUES ('314', '182', '2019-06-14 14:11:07', '知乎');
INSERT INTO `t_recordssum` VALUES ('315', '200', '2019-06-14 14:20:22', '知乎');
INSERT INTO `t_recordssum` VALUES ('316', '200', '2019-06-14 14:47:36', '百度知道');
INSERT INTO `t_recordssum` VALUES ('317', '200', '2019-06-14 15:00:16', '知乎');
INSERT INTO `t_recordssum` VALUES ('318', '182', '2019-06-14 16:56:29', '知乎');
INSERT INTO `t_recordssum` VALUES ('319', '181', '2019-06-14 22:00:31', '知乎');
INSERT INTO `t_recordssum` VALUES ('320', '200', '2019-06-15 22:22:54', '百度知道');
INSERT INTO `t_recordssum` VALUES ('321', '200', '2019-06-15 22:24:01', '百度知道');
INSERT INTO `t_recordssum` VALUES ('322', '200', '2019-06-15 22:25:32', '百度知道');
INSERT INTO `t_recordssum` VALUES ('323', '25', '2019-06-15 22:45:47', '知乎');
INSERT INTO `t_recordssum` VALUES ('324', '1', '2019-06-16 23:35:21', '微博');
INSERT INTO `t_recordssum` VALUES ('325', '1', '2019-06-16 23:36:30', '微博');
INSERT INTO `t_recordssum` VALUES ('326', '1', '2019-06-16 23:36:44', '微博');
INSERT INTO `t_recordssum` VALUES ('327', '114', '2019-06-16 23:45:33', '微博');
INSERT INTO `t_recordssum` VALUES ('328', '1', '2019-06-16 23:45:59', '微博');
INSERT INTO `t_recordssum` VALUES ('329', '1', '2019-06-16 23:46:32', '微博');
INSERT INTO `t_recordssum` VALUES ('330', '1', '2019-06-16 23:47:02', '微博');
INSERT INTO `t_recordssum` VALUES ('331', '1', '2019-06-16 23:48:18', '微博');
INSERT INTO `t_recordssum` VALUES ('332', '1', '2019-06-16 23:52:54', '微博');
INSERT INTO `t_recordssum` VALUES ('333', '1', '2019-06-16 23:56:30', '微博');
INSERT INTO `t_recordssum` VALUES ('334', '1', '2019-06-17 00:00:27', '微博');
INSERT INTO `t_recordssum` VALUES ('335', '1', '2019-06-17 00:01:54', '微博');
INSERT INTO `t_recordssum` VALUES ('336', '1', '2019-06-17 00:04:58', '微博');
INSERT INTO `t_recordssum` VALUES ('337', '2', '2019-06-17 00:06:44', '微博');
INSERT INTO `t_recordssum` VALUES ('338', '1', '2019-06-17 00:07:36', '微博');
INSERT INTO `t_recordssum` VALUES ('339', '1', '2019-06-17 00:10:35', '微博');
INSERT INTO `t_recordssum` VALUES ('340', '1', '2019-06-17 00:16:29', '微博');
INSERT INTO `t_recordssum` VALUES ('341', '2', '2019-06-17 00:23:46', '微博');
INSERT INTO `t_recordssum` VALUES ('342', '3', '2019-06-17 00:23:46', '微博');
INSERT INTO `t_recordssum` VALUES ('343', '113', '2019-06-17 12:24:15', '微博');
INSERT INTO `t_recordssum` VALUES ('344', '114', '2019-06-17 12:24:24', '微博');
INSERT INTO `t_recordssum` VALUES ('345', '115', '2019-06-17 12:24:33', '微博');
INSERT INTO `t_recordssum` VALUES ('346', '116', '2019-06-17 12:24:36', '微博');
INSERT INTO `t_recordssum` VALUES ('347', '117', '2019-06-17 12:24:40', '微博');
INSERT INTO `t_recordssum` VALUES ('348', '118', '2019-06-17 12:24:48', '微博');
INSERT INTO `t_recordssum` VALUES ('349', '119', '2019-06-17 12:24:52', '微博');
INSERT INTO `t_recordssum` VALUES ('350', '120', '2019-06-17 12:25:06', '微博');
INSERT INTO `t_recordssum` VALUES ('351', '121', '2019-06-17 12:25:09', '微博');
INSERT INTO `t_recordssum` VALUES ('352', '122', '2019-06-17 12:25:12', '微博');
INSERT INTO `t_recordssum` VALUES ('353', '123', '2019-06-17 12:25:16', '微博');
INSERT INTO `t_recordssum` VALUES ('354', '124', '2019-06-17 12:25:26', '微博');
INSERT INTO `t_recordssum` VALUES ('355', '125', '2019-06-17 12:25:29', '微博');
INSERT INTO `t_recordssum` VALUES ('356', '126', '2019-06-17 12:25:38', '微博');
INSERT INTO `t_recordssum` VALUES ('357', '127', '2019-06-17 12:25:41', '微博');
INSERT INTO `t_recordssum` VALUES ('358', '128', '2019-06-17 12:25:49', '微博');
INSERT INTO `t_recordssum` VALUES ('359', '129', '2019-06-17 12:26:03', '微博');
INSERT INTO `t_recordssum` VALUES ('360', '130', '2019-06-17 12:26:06', '微博');
INSERT INTO `t_recordssum` VALUES ('361', '131', '2019-06-17 12:26:10', '微博');
INSERT INTO `t_recordssum` VALUES ('362', '132', '2019-06-17 12:26:18', '微博');
INSERT INTO `t_recordssum` VALUES ('363', '133', '2019-06-17 12:26:22', '微博');
INSERT INTO `t_recordssum` VALUES ('364', '134', '2019-06-17 12:26:30', '微博');
INSERT INTO `t_recordssum` VALUES ('365', '135', '2019-06-17 12:26:38', '微博');
INSERT INTO `t_recordssum` VALUES ('366', '136', '2019-06-17 12:26:42', '微博');
INSERT INTO `t_recordssum` VALUES ('367', '137', '2019-06-17 12:26:50', '微博');
INSERT INTO `t_recordssum` VALUES ('368', '138', '2019-06-17 12:26:59', '微博');
INSERT INTO `t_recordssum` VALUES ('369', '139', '2019-06-17 12:27:12', '微博');
INSERT INTO `t_recordssum` VALUES ('370', '140', '2019-06-17 12:27:15', '微博');
INSERT INTO `t_recordssum` VALUES ('371', '141', '2019-06-17 12:27:19', '微博');
INSERT INTO `t_recordssum` VALUES ('372', '142', '2019-06-17 12:27:27', '微博');
INSERT INTO `t_recordssum` VALUES ('373', '143', '2019-06-17 12:27:36', '微博');
INSERT INTO `t_recordssum` VALUES ('374', '144', '2019-06-17 12:27:40', '微博');
INSERT INTO `t_recordssum` VALUES ('375', '145', '2019-06-17 12:27:48', '微博');
INSERT INTO `t_recordssum` VALUES ('376', '146', '2019-06-17 12:27:57', '微博');
INSERT INTO `t_recordssum` VALUES ('377', '147', '2019-06-17 12:28:06', '微博');
INSERT INTO `t_recordssum` VALUES ('378', '148', '2019-06-17 12:28:15', '微博');
INSERT INTO `t_recordssum` VALUES ('379', '149', '2019-06-17 12:28:24', '微博');
INSERT INTO `t_recordssum` VALUES ('380', '150', '2019-06-17 12:28:27', '微博');
INSERT INTO `t_recordssum` VALUES ('381', '151', '2019-06-17 12:28:36', '微博');
INSERT INTO `t_recordssum` VALUES ('382', '152', '2019-06-17 12:28:45', '微博');
INSERT INTO `t_recordssum` VALUES ('383', '153', '2019-06-17 12:28:48', '微博');
INSERT INTO `t_recordssum` VALUES ('384', '154', '2019-06-17 12:28:57', '微博');
INSERT INTO `t_recordssum` VALUES ('385', '155', '2019-06-17 12:29:00', '微博');
INSERT INTO `t_recordssum` VALUES ('386', '156', '2019-06-17 12:29:09', '微博');
INSERT INTO `t_recordssum` VALUES ('387', '157', '2019-06-17 12:29:17', '微博');
INSERT INTO `t_recordssum` VALUES ('388', '158', '2019-06-17 12:29:26', '微博');
INSERT INTO `t_recordssum` VALUES ('389', '159', '2019-06-17 12:29:30', '微博');
INSERT INTO `t_recordssum` VALUES ('390', '160', '2019-06-17 12:29:38', '微博');
INSERT INTO `t_recordssum` VALUES ('391', '161', '2019-06-17 12:29:42', '微博');
INSERT INTO `t_recordssum` VALUES ('392', '162', '2019-06-17 12:29:50', '微博');
INSERT INTO `t_recordssum` VALUES ('393', '163', '2019-06-17 12:29:58', '微博');
INSERT INTO `t_recordssum` VALUES ('394', '164', '2019-06-17 12:30:07', '微博');
INSERT INTO `t_recordssum` VALUES ('395', '165', '2019-06-17 12:30:10', '微博');
INSERT INTO `t_recordssum` VALUES ('396', '166', '2019-06-17 12:30:24', '微博');
INSERT INTO `t_recordssum` VALUES ('397', '167', '2019-06-17 12:30:33', '微博');
INSERT INTO `t_recordssum` VALUES ('398', '168', '2019-06-17 12:30:36', '微博');
INSERT INTO `t_recordssum` VALUES ('399', '169', '2019-06-17 12:30:39', '微博');
INSERT INTO `t_recordssum` VALUES ('400', '170', '2019-06-17 12:30:43', '微博');
INSERT INTO `t_recordssum` VALUES ('401', '171', '2019-06-17 12:30:52', '微博');
INSERT INTO `t_recordssum` VALUES ('402', '172', '2019-06-17 12:31:01', '微博');
INSERT INTO `t_recordssum` VALUES ('403', '173', '2019-06-17 12:31:09', '微博');
INSERT INTO `t_recordssum` VALUES ('404', '174', '2019-06-17 12:31:17', '微博');
INSERT INTO `t_recordssum` VALUES ('405', '175', '2019-06-17 12:31:27', '微博');
INSERT INTO `t_recordssum` VALUES ('406', '176', '2019-06-17 12:31:35', '微博');
INSERT INTO `t_recordssum` VALUES ('407', '177', '2019-06-17 12:31:44', '微博');
INSERT INTO `t_recordssum` VALUES ('408', '178', '2019-06-17 12:31:47', '微博');
INSERT INTO `t_recordssum` VALUES ('409', '179', '2019-06-17 12:31:51', '微博');
INSERT INTO `t_recordssum` VALUES ('410', '180', '2019-06-17 12:31:54', '微博');
INSERT INTO `t_recordssum` VALUES ('411', '181', '2019-06-17 12:31:58', '微博');
INSERT INTO `t_recordssum` VALUES ('412', '182', '2019-06-17 12:32:06', '微博');
INSERT INTO `t_recordssum` VALUES ('413', '183', '2019-06-17 12:32:15', '微博');
INSERT INTO `t_recordssum` VALUES ('414', '184', '2019-06-17 12:32:24', '微博');
INSERT INTO `t_recordssum` VALUES ('415', '185', '2019-06-17 12:32:27', '微博');
INSERT INTO `t_recordssum` VALUES ('416', '186', '2019-06-17 12:32:31', '微博');
INSERT INTO `t_recordssum` VALUES ('417', '187', '2019-06-17 12:32:40', '微博');
INSERT INTO `t_recordssum` VALUES ('418', '188', '2019-06-17 12:32:48', '微博');
INSERT INTO `t_recordssum` VALUES ('419', '189', '2019-06-17 12:32:52', '微博');
INSERT INTO `t_recordssum` VALUES ('420', '190', '2019-06-17 12:33:00', '微博');
INSERT INTO `t_recordssum` VALUES ('421', '191', '2019-06-17 12:33:04', '微博');
INSERT INTO `t_recordssum` VALUES ('422', '192', '2019-06-17 12:33:08', '微博');
INSERT INTO `t_recordssum` VALUES ('423', '193', '2019-06-17 12:33:22', '微博');
INSERT INTO `t_recordssum` VALUES ('424', '194', '2019-06-17 12:33:31', '微博');
INSERT INTO `t_recordssum` VALUES ('425', '195', '2019-06-17 12:33:40', '微博');
INSERT INTO `t_recordssum` VALUES ('426', '196', '2019-06-17 12:33:49', '微博');
INSERT INTO `t_recordssum` VALUES ('427', '197', '2019-06-17 12:33:53', '微博');
INSERT INTO `t_recordssum` VALUES ('428', '198', '2019-06-17 12:33:57', '微博');
INSERT INTO `t_recordssum` VALUES ('429', '199', '2019-06-17 12:34:05', '微博');
INSERT INTO `t_recordssum` VALUES ('430', '200', '2019-06-17 12:34:14', '微博');
INSERT INTO `t_recordssum` VALUES ('431', '201', '2019-06-17 12:34:18', '微博');
INSERT INTO `t_recordssum` VALUES ('432', '202', '2019-06-17 12:34:22', '微博');
INSERT INTO `t_recordssum` VALUES ('433', '203', '2019-06-17 12:34:26', '微博');
INSERT INTO `t_recordssum` VALUES ('434', '204', '2019-06-17 12:34:29', '微博');
INSERT INTO `t_recordssum` VALUES ('435', '205', '2019-06-17 12:34:43', '微博');
INSERT INTO `t_recordssum` VALUES ('436', '206', '2019-06-17 12:34:52', '微博');
INSERT INTO `t_recordssum` VALUES ('437', '207', '2019-06-17 12:34:56', '微博');
INSERT INTO `t_recordssum` VALUES ('438', '208', '2019-06-17 12:35:00', '微博');
INSERT INTO `t_recordssum` VALUES ('439', '209', '2019-06-17 12:35:08', '微博');
INSERT INTO `t_recordssum` VALUES ('440', '210', '2019-06-17 12:35:11', '微博');
INSERT INTO `t_recordssum` VALUES ('441', '211', '2019-06-17 12:35:20', '微博');
INSERT INTO `t_recordssum` VALUES ('442', '212', '2019-06-17 12:35:29', '微博');
INSERT INTO `t_recordssum` VALUES ('443', '213', '2019-06-17 12:35:37', '微博');
INSERT INTO `t_recordssum` VALUES ('444', '214', '2019-06-17 12:35:46', '微博');
INSERT INTO `t_recordssum` VALUES ('445', '215', '2019-06-17 12:35:49', '微博');
INSERT INTO `t_recordssum` VALUES ('446', '216', '2019-06-17 12:35:58', '微博');
INSERT INTO `t_recordssum` VALUES ('447', '217', '2019-06-17 12:36:07', '微博');
INSERT INTO `t_recordssum` VALUES ('448', '218', '2019-06-17 12:36:11', '微博');
INSERT INTO `t_recordssum` VALUES ('449', '219', '2019-06-17 12:36:21', '微博');
INSERT INTO `t_recordssum` VALUES ('450', '220', '2019-06-17 12:36:25', '微博');
INSERT INTO `t_recordssum` VALUES ('451', '221', '2019-06-17 12:36:29', '微博');
INSERT INTO `t_recordssum` VALUES ('452', '222', '2019-06-17 12:36:38', '微博');
INSERT INTO `t_recordssum` VALUES ('453', '223', '2019-06-17 12:36:57', '微博');
INSERT INTO `t_recordssum` VALUES ('454', '224', '2019-06-17 12:37:00', '微博');
INSERT INTO `t_recordssum` VALUES ('455', '225', '2019-06-17 12:37:04', '微博');
INSERT INTO `t_recordssum` VALUES ('456', '226', '2019-06-17 12:37:12', '微博');
INSERT INTO `t_recordssum` VALUES ('457', '227', '2019-06-17 12:37:16', '微博');
INSERT INTO `t_recordssum` VALUES ('458', '228', '2019-06-17 12:37:20', '微博');
INSERT INTO `t_recordssum` VALUES ('459', '229', '2019-06-17 12:37:23', '微博');
INSERT INTO `t_recordssum` VALUES ('460', '230', '2019-06-17 12:37:26', '微博');
INSERT INTO `t_recordssum` VALUES ('461', '231', '2019-06-17 12:37:30', '微博');
INSERT INTO `t_recordssum` VALUES ('462', '232', '2019-06-17 12:37:34', '微博');
INSERT INTO `t_recordssum` VALUES ('463', '233', '2019-06-17 12:37:42', '微博');
INSERT INTO `t_recordssum` VALUES ('464', '234', '2019-06-17 12:37:51', '微博');
INSERT INTO `t_recordssum` VALUES ('465', '235', '2019-06-17 12:37:55', '微博');
INSERT INTO `t_recordssum` VALUES ('466', '236', '2019-06-17 12:38:03', '微博');
INSERT INTO `t_recordssum` VALUES ('467', '237', '2019-06-17 12:38:07', '微博');
INSERT INTO `t_recordssum` VALUES ('468', '238', '2019-06-17 12:38:10', '微博');
INSERT INTO `t_recordssum` VALUES ('469', '239', '2019-06-17 12:38:14', '微博');
INSERT INTO `t_recordssum` VALUES ('470', '240', '2019-06-17 12:38:18', '微博');
INSERT INTO `t_recordssum` VALUES ('471', '241', '2019-06-17 12:38:27', '微博');
INSERT INTO `t_recordssum` VALUES ('472', '242', '2019-06-17 12:38:30', '微博');
INSERT INTO `t_recordssum` VALUES ('473', '243', '2019-06-17 12:38:44', '微博');
INSERT INTO `t_recordssum` VALUES ('474', '244', '2019-06-17 12:38:53', '微博');
INSERT INTO `t_recordssum` VALUES ('475', '245', '2019-06-17 12:38:57', '微博');
INSERT INTO `t_recordssum` VALUES ('476', '246', '2019-06-17 12:39:05', '微博');
INSERT INTO `t_recordssum` VALUES ('477', '247', '2019-06-17 12:39:08', '微博');
INSERT INTO `t_recordssum` VALUES ('478', '248', '2019-06-17 12:39:13', '微博');
INSERT INTO `t_recordssum` VALUES ('479', '249', '2019-06-17 12:39:22', '微博');
INSERT INTO `t_recordssum` VALUES ('480', '250', '2019-06-17 12:39:31', '微博');
INSERT INTO `t_recordssum` VALUES ('481', '251', '2019-06-17 12:39:35', '微博');
INSERT INTO `t_recordssum` VALUES ('482', '252', '2019-06-17 12:39:38', '微博');
INSERT INTO `t_recordssum` VALUES ('483', '253', '2019-06-17 12:39:47', '微博');
INSERT INTO `t_recordssum` VALUES ('484', '254', '2019-06-17 12:39:50', '微博');
INSERT INTO `t_recordssum` VALUES ('485', '255', '2019-06-17 12:39:54', '微博');
INSERT INTO `t_recordssum` VALUES ('486', '256', '2019-06-17 12:39:57', '微博');
INSERT INTO `t_recordssum` VALUES ('487', '257', '2019-06-17 12:40:06', '微博');
INSERT INTO `t_recordssum` VALUES ('488', '258', '2019-06-17 12:40:14', '微博');
INSERT INTO `t_recordssum` VALUES ('489', '259', '2019-06-17 12:40:36', '微博');
INSERT INTO `t_recordssum` VALUES ('490', '260', '2019-06-17 12:40:40', '微博');
INSERT INTO `t_recordssum` VALUES ('491', '261', '2019-06-17 12:40:43', '微博');
INSERT INTO `t_recordssum` VALUES ('492', '262', '2019-06-17 12:40:47', '微博');
INSERT INTO `t_recordssum` VALUES ('493', '263', '2019-06-17 12:40:51', '微博');
INSERT INTO `t_recordssum` VALUES ('494', '264', '2019-06-17 12:40:55', '微博');
INSERT INTO `t_recordssum` VALUES ('495', '265', '2019-06-17 12:40:58', '微博');
INSERT INTO `t_recordssum` VALUES ('496', '266', '2019-06-17 12:41:02', '微博');
INSERT INTO `t_recordssum` VALUES ('497', '267', '2019-06-17 12:41:09', '微博');
INSERT INTO `t_recordssum` VALUES ('498', '268', '2019-06-17 12:41:18', '微博');
INSERT INTO `t_recordssum` VALUES ('499', '269', '2019-06-17 12:41:27', '微博');
INSERT INTO `t_recordssum` VALUES ('500', '270', '2019-06-17 12:41:36', '微博');
INSERT INTO `t_recordssum` VALUES ('501', '271', '2019-06-17 12:41:39', '微博');
INSERT INTO `t_recordssum` VALUES ('502', '272', '2019-06-17 12:41:44', '微博');
INSERT INTO `t_recordssum` VALUES ('503', '273', '2019-06-17 12:41:52', '微博');
INSERT INTO `t_recordssum` VALUES ('504', '274', '2019-06-17 12:42:01', '微博');
INSERT INTO `t_recordssum` VALUES ('505', '275', '2019-06-17 12:42:05', '微博');
INSERT INTO `t_recordssum` VALUES ('506', '276', '2019-06-17 12:42:08', '微博');
INSERT INTO `t_recordssum` VALUES ('507', '277', '2019-06-17 12:42:17', '微博');
INSERT INTO `t_recordssum` VALUES ('508', '278', '2019-06-17 12:42:26', '微博');
INSERT INTO `t_recordssum` VALUES ('509', '279', '2019-06-17 12:42:29', '微博');
INSERT INTO `t_recordssum` VALUES ('510', '280', '2019-06-17 12:42:33', '微博');
INSERT INTO `t_recordssum` VALUES ('511', '281', '2019-06-17 12:42:37', '微博');
INSERT INTO `t_recordssum` VALUES ('512', '282', '2019-06-17 12:42:45', '微博');
INSERT INTO `t_recordssum` VALUES ('513', '283', '2019-06-17 12:42:54', '微博');
INSERT INTO `t_recordssum` VALUES ('514', '284', '2019-06-17 12:42:58', '微博');
INSERT INTO `t_recordssum` VALUES ('515', '285', '2019-06-17 12:43:06', '微博');
INSERT INTO `t_recordssum` VALUES ('516', '286', '2019-06-17 12:43:09', '微博');
INSERT INTO `t_recordssum` VALUES ('517', '287', '2019-06-17 12:43:18', '微博');
INSERT INTO `t_recordssum` VALUES ('518', '288', '2019-06-17 12:43:27', '微博');
INSERT INTO `t_recordssum` VALUES ('519', '289', '2019-06-17 12:43:35', '微博');
INSERT INTO `t_recordssum` VALUES ('520', '290', '2019-06-17 12:43:39', '微博');
INSERT INTO `t_recordssum` VALUES ('521', '291', '2019-06-17 12:43:47', '微博');
INSERT INTO `t_recordssum` VALUES ('522', '292', '2019-06-17 12:43:55', '微博');
INSERT INTO `t_recordssum` VALUES ('523', '293', '2019-06-17 12:43:59', '微博');
INSERT INTO `t_recordssum` VALUES ('524', '294', '2019-06-17 12:44:03', '微博');
INSERT INTO `t_recordssum` VALUES ('525', '295', '2019-06-17 13:36:54', '微博');
INSERT INTO `t_recordssum` VALUES ('526', '296', '2019-06-17 13:37:04', '微博');
INSERT INTO `t_recordssum` VALUES ('527', '297', '2019-06-17 13:37:09', '微博');
INSERT INTO `t_recordssum` VALUES ('528', '298', '2019-06-17 13:37:14', '微博');
INSERT INTO `t_recordssum` VALUES ('529', '299', '2019-06-17 13:37:19', '微博');
INSERT INTO `t_recordssum` VALUES ('530', '300', '2019-06-17 13:37:28', '微博');
INSERT INTO `t_recordssum` VALUES ('531', '301', '2019-06-17 13:37:33', '微博');
INSERT INTO `t_recordssum` VALUES ('532', '302', '2019-06-17 13:37:40', '微博');
INSERT INTO `t_recordssum` VALUES ('533', '303', '2019-06-17 13:37:50', '微博');
INSERT INTO `t_recordssum` VALUES ('534', '304', '2019-06-17 13:37:54', '微博');
INSERT INTO `t_recordssum` VALUES ('535', '305', '2019-06-17 13:38:04', '微博');
INSERT INTO `t_recordssum` VALUES ('536', '306', '2019-06-17 13:38:08', '微博');
INSERT INTO `t_recordssum` VALUES ('537', '307', '2019-06-17 13:38:17', '微博');
INSERT INTO `t_recordssum` VALUES ('538', '308', '2019-06-17 13:38:21', '微博');
INSERT INTO `t_recordssum` VALUES ('539', '110', '2019-06-17 13:52:21', '微博');
INSERT INTO `t_recordssum` VALUES ('540', '111', '2019-06-17 13:52:30', '微博');
INSERT INTO `t_recordssum` VALUES ('541', '112', '2019-06-17 13:52:38', '微博');
INSERT INTO `t_recordssum` VALUES ('542', '113', '2019-06-17 13:52:42', '微博');
INSERT INTO `t_recordssum` VALUES ('543', '114', '2019-06-17 13:52:45', '微博');
INSERT INTO `t_recordssum` VALUES ('544', '115', '2019-06-17 13:52:59', '微博');
INSERT INTO `t_recordssum` VALUES ('545', '116', '2019-06-17 13:53:03', '微博');
INSERT INTO `t_recordssum` VALUES ('546', '117', '2019-06-17 13:53:11', '微博');
INSERT INTO `t_recordssum` VALUES ('547', '118', '2019-06-17 13:53:15', '微博');
INSERT INTO `t_recordssum` VALUES ('548', '119', '2019-06-17 13:53:19', '微博');
INSERT INTO `t_recordssum` VALUES ('549', '120', '2019-06-17 13:53:22', '微博');
INSERT INTO `t_recordssum` VALUES ('550', '121', '2019-06-17 13:53:31', '微博');
INSERT INTO `t_recordssum` VALUES ('551', '122', '2019-06-17 13:53:34', '微博');
INSERT INTO `t_recordssum` VALUES ('552', '123', '2019-06-17 13:53:43', '微博');
INSERT INTO `t_recordssum` VALUES ('553', '124', '2019-06-17 13:53:52', '微博');
INSERT INTO `t_recordssum` VALUES ('554', '125', '2019-06-17 13:54:00', '微博');
INSERT INTO `t_recordssum` VALUES ('555', '126', '2019-06-17 13:54:09', '微博');
INSERT INTO `t_recordssum` VALUES ('556', '127', '2019-06-17 13:54:12', '微博');
INSERT INTO `t_recordssum` VALUES ('557', '128', '2019-06-17 13:54:16', '微博');
INSERT INTO `t_recordssum` VALUES ('558', '129', '2019-06-17 13:54:25', '微博');
INSERT INTO `t_recordssum` VALUES ('559', '130', '2019-06-17 13:54:28', '微博');
INSERT INTO `t_recordssum` VALUES ('560', '106', '2019-06-17 14:11:38', '微博');
INSERT INTO `t_recordssum` VALUES ('561', '107', '2019-06-17 14:11:47', '微博');
INSERT INTO `t_recordssum` VALUES ('562', '108', '2019-06-17 14:11:56', '微博');
INSERT INTO `t_recordssum` VALUES ('563', '109', '2019-06-17 14:11:59', '微博');
INSERT INTO `t_recordssum` VALUES ('564', '110', '2019-06-17 14:12:02', '微博');
INSERT INTO `t_recordssum` VALUES ('565', '111', '2019-06-17 14:12:16', '微博');
INSERT INTO `t_recordssum` VALUES ('566', '110', '2019-06-17 14:33:09', '微博');
INSERT INTO `t_recordssum` VALUES ('567', '111', '2019-06-17 14:35:14', '微博');
INSERT INTO `t_recordssum` VALUES ('568', '109', '2019-06-17 14:54:03', '微博');
INSERT INTO `t_recordssum` VALUES ('569', '110', '2019-06-17 14:54:16', '微博');
INSERT INTO `t_recordssum` VALUES ('570', '111', '2019-06-17 15:02:15', '微博');
INSERT INTO `t_recordssum` VALUES ('571', '102', '2019-06-17 15:23:33', '微博');
INSERT INTO `t_recordssum` VALUES ('572', '103', '2019-06-17 15:24:20', '微博');
INSERT INTO `t_recordssum` VALUES ('573', '143', '2019-06-17 15:48:12', '微博');
INSERT INTO `t_recordssum` VALUES ('574', '67', '2019-06-17 16:09:27', '微博');
INSERT INTO `t_recordssum` VALUES ('575', '74', '2019-06-17 16:23:12', '微博');
INSERT INTO `t_recordssum` VALUES ('576', '155', '2019-06-18 15:28:08', '微博');
INSERT INTO `t_recordssum` VALUES ('577', '203', '2019-06-18 16:11:56', '微博');
INSERT INTO `t_recordssum` VALUES ('578', '204', '2019-06-18 16:12:01', '微博');
INSERT INTO `t_recordssum` VALUES ('579', '205', '2019-06-18 16:12:10', '微博');
INSERT INTO `t_recordssum` VALUES ('580', '206', '2019-06-18 16:12:15', '微博');
INSERT INTO `t_recordssum` VALUES ('581', '207', '2019-06-18 16:12:20', '微博');
INSERT INTO `t_recordssum` VALUES ('582', '208', '2019-06-18 16:12:30', '微博');
INSERT INTO `t_recordssum` VALUES ('583', '209', '2019-06-18 16:12:51', '微博');
INSERT INTO `t_recordssum` VALUES ('584', '210', '2019-06-18 16:12:56', '微博');
INSERT INTO `t_recordssum` VALUES ('585', '211', '2019-06-18 16:13:01', '微博');
INSERT INTO `t_recordssum` VALUES ('586', '212', '2019-06-18 16:13:06', '微博');
INSERT INTO `t_recordssum` VALUES ('587', '213', '2019-06-18 16:13:10', '微博');
INSERT INTO `t_recordssum` VALUES ('588', '214', '2019-06-18 16:13:15', '微博');
INSERT INTO `t_recordssum` VALUES ('589', '215', '2019-06-18 16:13:20', '微博');
INSERT INTO `t_recordssum` VALUES ('590', '216', '2019-06-18 16:13:25', '微博');
INSERT INTO `t_recordssum` VALUES ('591', '217', '2019-06-18 16:13:34', '微博');
INSERT INTO `t_recordssum` VALUES ('592', '218', '2019-06-18 16:13:39', '微博');
INSERT INTO `t_recordssum` VALUES ('593', '219', '2019-06-18 16:13:55', '微博');
INSERT INTO `t_recordssum` VALUES ('594', '220', '2019-06-18 16:14:00', '微博');
INSERT INTO `t_recordssum` VALUES ('595', '221', '2019-06-18 16:14:05', '微博');
INSERT INTO `t_recordssum` VALUES ('596', '222', '2019-06-18 16:14:10', '微博');
INSERT INTO `t_recordssum` VALUES ('597', '223', '2019-06-18 16:14:14', '微博');
INSERT INTO `t_recordssum` VALUES ('598', '224', '2019-06-18 16:14:25', '微博');
INSERT INTO `t_recordssum` VALUES ('599', '225', '2019-06-18 16:14:29', '微博');
INSERT INTO `t_recordssum` VALUES ('600', '226', '2019-06-18 16:14:39', '微博');
INSERT INTO `t_recordssum` VALUES ('601', '227', '2019-06-18 16:14:49', '微博');
INSERT INTO `t_recordssum` VALUES ('602', '228', '2019-06-18 16:14:53', '微博');
INSERT INTO `t_recordssum` VALUES ('603', '229', '2019-06-18 16:15:08', '微博');
INSERT INTO `t_recordssum` VALUES ('604', '230', '2019-06-18 16:15:13', '微博');
INSERT INTO `t_recordssum` VALUES ('605', '231', '2019-06-18 16:15:18', '微博');
INSERT INTO `t_recordssum` VALUES ('606', '232', '2019-06-18 16:15:28', '微博');
INSERT INTO `t_recordssum` VALUES ('607', '233', '2019-06-18 16:15:37', '微博');
INSERT INTO `t_recordssum` VALUES ('608', '234', '2019-06-18 16:15:42', '微博');
INSERT INTO `t_recordssum` VALUES ('609', '235', '2019-06-18 16:15:47', '微博');
INSERT INTO `t_recordssum` VALUES ('610', '236', '2019-06-18 16:15:53', '微博');
INSERT INTO `t_recordssum` VALUES ('611', '237', '2019-06-18 16:15:57', '微博');
INSERT INTO `t_recordssum` VALUES ('612', '238', '2019-06-18 16:16:02', '微博');
INSERT INTO `t_recordssum` VALUES ('613', '239', '2019-06-18 16:16:11', '微博');
INSERT INTO `t_recordssum` VALUES ('614', '240', '2019-06-18 16:16:21', '微博');
INSERT INTO `t_recordssum` VALUES ('615', '241', '2019-06-18 16:16:31', '微博');
INSERT INTO `t_recordssum` VALUES ('616', '242', '2019-06-18 16:16:46', '微博');
INSERT INTO `t_recordssum` VALUES ('617', '243', '2019-06-18 16:16:56', '微博');
INSERT INTO `t_recordssum` VALUES ('618', '244', '2019-06-18 16:17:00', '微博');
INSERT INTO `t_recordssum` VALUES ('619', '245', '2019-06-18 16:17:06', '微博');
INSERT INTO `t_recordssum` VALUES ('620', '246', '2019-06-18 16:17:16', '微博');
INSERT INTO `t_recordssum` VALUES ('621', '247', '2019-06-18 16:17:20', '微博');
INSERT INTO `t_recordssum` VALUES ('622', '248', '2019-06-18 16:17:30', '微博');
INSERT INTO `t_recordssum` VALUES ('623', '249', '2019-06-18 16:17:34', '微博');
INSERT INTO `t_recordssum` VALUES ('624', '250', '2019-06-18 16:17:39', '微博');
INSERT INTO `t_recordssum` VALUES ('625', '251', '2019-06-18 16:17:45', '微博');
INSERT INTO `t_recordssum` VALUES ('626', '252', '2019-06-18 16:17:54', '微博');
INSERT INTO `t_recordssum` VALUES ('627', '253', '2019-06-18 16:17:59', '微博');
INSERT INTO `t_recordssum` VALUES ('628', '254', '2019-06-18 16:18:04', '微博');
INSERT INTO `t_recordssum` VALUES ('629', '255', '2019-06-18 16:18:09', '微博');
INSERT INTO `t_recordssum` VALUES ('630', '256', '2019-06-18 16:18:14', '微博');
INSERT INTO `t_recordssum` VALUES ('631', '257', '2019-06-18 16:18:23', '微博');
INSERT INTO `t_recordssum` VALUES ('632', '258', '2019-06-18 16:18:28', '微博');
INSERT INTO `t_recordssum` VALUES ('633', '259', '2019-06-18 16:18:38', '微博');
INSERT INTO `t_recordssum` VALUES ('634', '260', '2019-06-18 16:18:48', '微博');
INSERT INTO `t_recordssum` VALUES ('635', '261', '2019-06-18 16:18:58', '微博');
INSERT INTO `t_recordssum` VALUES ('636', '262', '2019-06-18 16:19:03', '微博');
INSERT INTO `t_recordssum` VALUES ('637', '263', '2019-06-18 16:19:13', '微博');
INSERT INTO `t_recordssum` VALUES ('638', '264', '2019-06-18 16:19:23', '微博');
INSERT INTO `t_recordssum` VALUES ('639', '265', '2019-06-18 16:19:28', '微博');
INSERT INTO `t_recordssum` VALUES ('640', '266', '2019-06-18 16:19:33', '微博');
INSERT INTO `t_recordssum` VALUES ('641', '267', '2019-06-18 16:19:43', '微博');
INSERT INTO `t_recordssum` VALUES ('642', '268', '2019-06-18 16:19:48', '微博');
INSERT INTO `t_recordssum` VALUES ('643', '269', '2019-06-18 16:19:53', '微博');
INSERT INTO `t_recordssum` VALUES ('644', '270', '2019-06-18 16:19:57', '微博');
INSERT INTO `t_recordssum` VALUES ('645', '271', '2019-06-18 16:20:07', '微博');
INSERT INTO `t_recordssum` VALUES ('646', '272', '2019-06-18 16:20:17', '微博');
INSERT INTO `t_recordssum` VALUES ('647', '273', '2019-06-18 16:20:27', '微博');
INSERT INTO `t_recordssum` VALUES ('648', '274', '2019-06-18 16:20:31', '微博');
INSERT INTO `t_recordssum` VALUES ('649', '275', '2019-06-18 16:20:41', '微博');
INSERT INTO `t_recordssum` VALUES ('650', '276', '2019-06-18 16:20:46', '微博');
INSERT INTO `t_recordssum` VALUES ('651', '277', '2019-06-18 16:20:56', '微博');
INSERT INTO `t_recordssum` VALUES ('652', '278', '2019-06-18 16:21:00', '微博');
INSERT INTO `t_recordssum` VALUES ('653', '279', '2019-06-18 16:21:10', '微博');
INSERT INTO `t_recordssum` VALUES ('654', '280', '2019-06-18 16:21:15', '微博');
INSERT INTO `t_recordssum` VALUES ('655', '281', '2019-06-18 16:21:30', '微博');
INSERT INTO `t_recordssum` VALUES ('656', '282', '2019-06-18 16:21:40', '微博');
INSERT INTO `t_recordssum` VALUES ('657', '283', '2019-06-18 16:21:45', '微博');
INSERT INTO `t_recordssum` VALUES ('658', '284', '2019-06-18 16:21:50', '微博');
INSERT INTO `t_recordssum` VALUES ('659', '285', '2019-06-18 16:21:55', '微博');
INSERT INTO `t_recordssum` VALUES ('660', '286', '2019-06-18 16:21:59', '微博');
INSERT INTO `t_recordssum` VALUES ('661', '287', '2019-06-18 16:22:06', '微博');
INSERT INTO `t_recordssum` VALUES ('662', '288', '2019-06-18 16:22:21', '微博');
INSERT INTO `t_recordssum` VALUES ('663', '289', '2019-06-18 16:22:26', '微博');
INSERT INTO `t_recordssum` VALUES ('664', '290', '2019-06-18 16:22:31', '微博');
INSERT INTO `t_recordssum` VALUES ('665', '291', '2019-06-18 16:22:41', '微博');
INSERT INTO `t_recordssum` VALUES ('666', '292', '2019-06-18 16:22:45', '微博');
INSERT INTO `t_recordssum` VALUES ('667', '293', '2019-06-18 16:22:50', '微博');
INSERT INTO `t_recordssum` VALUES ('668', '294', '2019-06-18 16:22:54', '微博');
INSERT INTO `t_recordssum` VALUES ('669', '295', '2019-06-18 16:23:04', '微博');
INSERT INTO `t_recordssum` VALUES ('670', '296', '2019-06-18 16:23:08', '微博');
INSERT INTO `t_recordssum` VALUES ('671', '297', '2019-06-18 16:23:14', '微博');
INSERT INTO `t_recordssum` VALUES ('672', '298', '2019-06-18 16:23:29', '微博');
INSERT INTO `t_recordssum` VALUES ('673', '299', '2019-06-18 16:23:34', '微博');
INSERT INTO `t_recordssum` VALUES ('674', '300', '2019-06-18 16:23:40', '微博');
INSERT INTO `t_recordssum` VALUES ('675', '301', '2019-06-18 16:23:50', '微博');
INSERT INTO `t_recordssum` VALUES ('676', '302', '2019-06-18 16:23:55', '微博');
INSERT INTO `t_recordssum` VALUES ('677', '303', '2019-06-18 16:24:00', '微博');
INSERT INTO `t_recordssum` VALUES ('678', '304', '2019-06-18 16:24:05', '微博');
INSERT INTO `t_recordssum` VALUES ('679', '305', '2019-06-18 16:24:14', '微博');
INSERT INTO `t_recordssum` VALUES ('680', '306', '2019-06-18 16:24:19', '微博');
INSERT INTO `t_recordssum` VALUES ('681', '307', '2019-06-18 16:24:39', '微博');
INSERT INTO `t_recordssum` VALUES ('682', '308', '2019-06-18 16:24:49', '微博');
INSERT INTO `t_recordssum` VALUES ('683', '309', '2019-06-18 16:24:58', '微博');
INSERT INTO `t_recordssum` VALUES ('684', '310', '2019-06-18 16:25:08', '微博');
INSERT INTO `t_recordssum` VALUES ('685', '311', '2019-06-18 16:25:17', '微博');
INSERT INTO `t_recordssum` VALUES ('686', '312', '2019-06-18 16:25:23', '微博');
INSERT INTO `t_recordssum` VALUES ('687', '313', '2019-06-18 16:25:33', '微博');
INSERT INTO `t_recordssum` VALUES ('688', '314', '2019-06-18 16:25:38', '微博');
INSERT INTO `t_recordssum` VALUES ('689', '315', '2019-06-18 16:25:46', '微博');
INSERT INTO `t_recordssum` VALUES ('690', '316', '2019-06-18 16:25:50', '微博');
INSERT INTO `t_recordssum` VALUES ('691', '317', '2019-06-18 16:26:05', '微博');
INSERT INTO `t_recordssum` VALUES ('692', '318', '2019-06-18 16:26:11', '微博');
INSERT INTO `t_recordssum` VALUES ('693', '319', '2019-06-18 16:26:16', '微博');
INSERT INTO `t_recordssum` VALUES ('694', '320', '2019-06-18 16:26:20', '微博');
INSERT INTO `t_recordssum` VALUES ('695', '321', '2019-06-18 16:26:30', '微博');
INSERT INTO `t_recordssum` VALUES ('696', '322', '2019-06-18 16:26:35', '微博');
INSERT INTO `t_recordssum` VALUES ('697', '323', '2019-06-18 16:26:45', '微博');
INSERT INTO `t_recordssum` VALUES ('698', '324', '2019-06-18 16:26:55', '微博');
INSERT INTO `t_recordssum` VALUES ('699', '325', '2019-06-18 16:27:00', '微博');
INSERT INTO `t_recordssum` VALUES ('700', '326', '2019-06-18 16:27:05', '微博');
INSERT INTO `t_recordssum` VALUES ('701', '327', '2019-06-18 16:27:15', '微博');
INSERT INTO `t_recordssum` VALUES ('702', '328', '2019-06-18 16:27:25', '微博');
INSERT INTO `t_recordssum` VALUES ('703', '329', '2019-06-18 16:27:34', '微博');
INSERT INTO `t_recordssum` VALUES ('704', '330', '2019-06-18 16:27:39', '微博');
INSERT INTO `t_recordssum` VALUES ('705', '331', '2019-06-18 16:27:49', '微博');
INSERT INTO `t_recordssum` VALUES ('706', '332', '2019-06-18 16:27:59', '微博');
INSERT INTO `t_recordssum` VALUES ('707', '333', '2019-06-18 16:28:09', '微博');
INSERT INTO `t_recordssum` VALUES ('708', '334', '2019-06-18 16:28:14', '微博');
INSERT INTO `t_recordssum` VALUES ('709', '335', '2019-06-18 16:28:23', '微博');
INSERT INTO `t_recordssum` VALUES ('710', '336', '2019-06-18 16:28:28', '微博');
INSERT INTO `t_recordssum` VALUES ('711', '337', '2019-06-18 16:28:33', '微博');
INSERT INTO `t_recordssum` VALUES ('712', '338', '2019-06-18 16:28:38', '微博');
INSERT INTO `t_recordssum` VALUES ('713', '339', '2019-06-18 16:28:43', '微博');
INSERT INTO `t_recordssum` VALUES ('714', '340', '2019-06-18 16:28:52', '微博');
INSERT INTO `t_recordssum` VALUES ('715', '341', '2019-06-18 16:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('716', '342', '2019-06-18 16:29:08', '微博');
INSERT INTO `t_recordssum` VALUES ('717', '343', '2019-06-18 16:29:17', '微博');
INSERT INTO `t_recordssum` VALUES ('718', '344', '2019-06-18 16:29:22', '微博');
INSERT INTO `t_recordssum` VALUES ('719', '345', '2019-06-18 16:29:28', '微博');
INSERT INTO `t_recordssum` VALUES ('720', '346', '2019-06-18 16:29:37', '微博');
INSERT INTO `t_recordssum` VALUES ('721', '347', '2019-06-18 16:29:42', '微博');
INSERT INTO `t_recordssum` VALUES ('722', '348', '2019-06-18 16:29:47', '微博');
INSERT INTO `t_recordssum` VALUES ('723', '349', '2019-06-18 16:29:57', '微博');
INSERT INTO `t_recordssum` VALUES ('724', '350', '2019-06-18 16:30:07', '微博');
INSERT INTO `t_recordssum` VALUES ('725', '351', '2019-06-18 16:30:22', '微博');
INSERT INTO `t_recordssum` VALUES ('726', '352', '2019-06-18 16:30:28', '微博');
INSERT INTO `t_recordssum` VALUES ('727', '353', '2019-06-18 16:30:33', '微博');
INSERT INTO `t_recordssum` VALUES ('728', '354', '2019-06-18 16:30:38', '微博');
INSERT INTO `t_recordssum` VALUES ('729', '355', '2019-06-18 16:30:49', '微博');
INSERT INTO `t_recordssum` VALUES ('730', '356', '2019-06-18 16:31:00', '微博');
INSERT INTO `t_recordssum` VALUES ('731', '357', '2019-06-18 16:31:04', '微博');
INSERT INTO `t_recordssum` VALUES ('732', '358', '2019-06-18 16:31:09', '微博');
INSERT INTO `t_recordssum` VALUES ('733', '359', '2019-06-18 16:31:19', '微博');
INSERT INTO `t_recordssum` VALUES ('734', '360', '2019-06-18 16:31:29', '微博');
INSERT INTO `t_recordssum` VALUES ('735', '361', '2019-06-18 16:31:39', '微博');
INSERT INTO `t_recordssum` VALUES ('736', '362', '2019-06-18 16:31:49', '微博');
INSERT INTO `t_recordssum` VALUES ('737', '363', '2019-06-18 16:31:54', '微博');
INSERT INTO `t_recordssum` VALUES ('738', '364', '2019-06-18 16:31:59', '微博');
INSERT INTO `t_recordssum` VALUES ('739', '365', '2019-06-18 16:32:10', '微博');
INSERT INTO `t_recordssum` VALUES ('740', '366', '2019-06-18 16:32:15', '微博');
INSERT INTO `t_recordssum` VALUES ('741', '367', '2019-06-18 16:32:25', '微博');
INSERT INTO `t_recordssum` VALUES ('742', '368', '2019-06-18 16:32:35', '微博');
INSERT INTO `t_recordssum` VALUES ('743', '369', '2019-06-18 16:32:40', '微博');
INSERT INTO `t_recordssum` VALUES ('744', '370', '2019-06-18 16:32:44', '微博');
INSERT INTO `t_recordssum` VALUES ('745', '371', '2019-06-18 16:32:55', '微博');
INSERT INTO `t_recordssum` VALUES ('746', '372', '2019-06-18 16:33:04', '微博');
INSERT INTO `t_recordssum` VALUES ('747', '373', '2019-06-18 16:33:09', '微博');
INSERT INTO `t_recordssum` VALUES ('748', '374', '2019-06-18 16:33:14', '微博');
INSERT INTO `t_recordssum` VALUES ('749', '375', '2019-06-18 16:33:24', '微博');
INSERT INTO `t_recordssum` VALUES ('750', '376', '2019-06-18 16:33:34', '微博');
INSERT INTO `t_recordssum` VALUES ('751', '377', '2019-06-18 16:33:39', '微博');
INSERT INTO `t_recordssum` VALUES ('752', '378', '2019-06-18 16:33:49', '微博');
INSERT INTO `t_recordssum` VALUES ('753', '379', '2019-06-18 16:33:59', '微博');
INSERT INTO `t_recordssum` VALUES ('754', '380', '2019-06-18 16:34:09', '微博');
INSERT INTO `t_recordssum` VALUES ('755', '381', '2019-06-18 16:34:24', '微博');
INSERT INTO `t_recordssum` VALUES ('756', '382', '2019-06-18 16:34:29', '微博');
INSERT INTO `t_recordssum` VALUES ('757', '383', '2019-06-18 16:34:40', '微博');
INSERT INTO `t_recordssum` VALUES ('758', '384', '2019-06-18 16:34:45', '微博');
INSERT INTO `t_recordssum` VALUES ('759', '385', '2019-06-18 16:34:50', '微博');
INSERT INTO `t_recordssum` VALUES ('760', '386', '2019-06-18 16:35:00', '微博');
INSERT INTO `t_recordssum` VALUES ('761', '387', '2019-06-18 16:35:05', '微博');
INSERT INTO `t_recordssum` VALUES ('762', '388', '2019-06-18 16:35:10', '微博');
INSERT INTO `t_recordssum` VALUES ('763', '389', '2019-06-18 16:35:15', '微博');
INSERT INTO `t_recordssum` VALUES ('764', '390', '2019-06-18 16:35:30', '微博');
INSERT INTO `t_recordssum` VALUES ('765', '391', '2019-06-18 16:35:40', '微博');
INSERT INTO `t_recordssum` VALUES ('766', '392', '2019-06-18 16:35:50', '微博');
INSERT INTO `t_recordssum` VALUES ('767', '393', '2019-06-18 16:36:00', '微博');
INSERT INTO `t_recordssum` VALUES ('768', '394', '2019-06-18 16:36:11', '微博');
INSERT INTO `t_recordssum` VALUES ('769', '395', '2019-06-18 16:36:17', '微博');
INSERT INTO `t_recordssum` VALUES ('770', '396', '2019-06-18 16:36:22', '微博');
INSERT INTO `t_recordssum` VALUES ('771', '397', '2019-06-18 16:36:33', '微博');
INSERT INTO `t_recordssum` VALUES ('772', '398', '2019-06-18 16:36:43', '微博');
INSERT INTO `t_recordssum` VALUES ('773', '399', '2019-06-18 16:36:48', '微博');
INSERT INTO `t_recordssum` VALUES ('774', '400', '2019-06-18 16:36:58', '微博');
INSERT INTO `t_recordssum` VALUES ('775', '401', '2019-06-18 16:37:03', '微博');
INSERT INTO `t_recordssum` VALUES ('776', '402', '2019-06-18 16:37:09', '微博');
INSERT INTO `t_recordssum` VALUES ('777', '403', '2019-06-18 16:37:19', '微博');
INSERT INTO `t_recordssum` VALUES ('778', '404', '2019-06-18 16:37:29', '微博');
INSERT INTO `t_recordssum` VALUES ('779', '405', '2019-06-18 16:37:40', '微博');
INSERT INTO `t_recordssum` VALUES ('780', '406', '2019-06-18 16:37:45', '微博');
INSERT INTO `t_recordssum` VALUES ('781', '407', '2019-06-18 16:37:55', '微博');
INSERT INTO `t_recordssum` VALUES ('782', '408', '2019-06-18 16:38:05', '微博');
INSERT INTO `t_recordssum` VALUES ('783', '409', '2019-06-18 16:38:15', '微博');
INSERT INTO `t_recordssum` VALUES ('784', '410', '2019-06-18 16:38:26', '微博');
INSERT INTO `t_recordssum` VALUES ('785', '411', '2019-06-18 16:38:36', '微博');
INSERT INTO `t_recordssum` VALUES ('786', '412', '2019-06-18 16:38:46', '微博');
INSERT INTO `t_recordssum` VALUES ('787', '413', '2019-06-18 16:38:56', '微博');
INSERT INTO `t_recordssum` VALUES ('788', '414', '2019-06-18 16:39:01', '微博');
INSERT INTO `t_recordssum` VALUES ('789', '415', '2019-06-18 16:39:11', '微博');
INSERT INTO `t_recordssum` VALUES ('790', '416', '2019-06-18 16:39:21', '微博');
INSERT INTO `t_recordssum` VALUES ('791', '417', '2019-06-18 16:39:26', '微博');
INSERT INTO `t_recordssum` VALUES ('792', '418', '2019-06-18 16:39:31', '微博');
INSERT INTO `t_recordssum` VALUES ('793', '419', '2019-06-18 16:39:36', '微博');
INSERT INTO `t_recordssum` VALUES ('794', '420', '2019-06-18 16:39:46', '微博');
INSERT INTO `t_recordssum` VALUES ('795', '421', '2019-06-18 16:39:56', '微博');
INSERT INTO `t_recordssum` VALUES ('796', '422', '2019-06-18 16:40:06', '微博');
INSERT INTO `t_recordssum` VALUES ('797', '423', '2019-06-18 16:40:16', '微博');
INSERT INTO `t_recordssum` VALUES ('798', '424', '2019-06-18 16:40:21', '微博');
INSERT INTO `t_recordssum` VALUES ('799', '425', '2019-06-18 16:40:26', '微博');
INSERT INTO `t_recordssum` VALUES ('800', '426', '2019-06-18 16:40:37', '微博');
INSERT INTO `t_recordssum` VALUES ('801', '427', '2019-06-18 16:40:42', '微博');
INSERT INTO `t_recordssum` VALUES ('802', '428', '2019-06-18 16:40:47', '微博');
INSERT INTO `t_recordssum` VALUES ('803', '429', '2019-06-18 16:40:57', '微博');
INSERT INTO `t_recordssum` VALUES ('804', '430', '2019-06-18 16:41:12', '微博');
INSERT INTO `t_recordssum` VALUES ('805', '431', '2019-06-18 16:41:17', '微博');
INSERT INTO `t_recordssum` VALUES ('806', '432', '2019-06-18 16:41:26', '微博');
INSERT INTO `t_recordssum` VALUES ('807', '433', '2019-06-18 16:41:31', '微博');
INSERT INTO `t_recordssum` VALUES ('808', '434', '2019-06-18 16:41:36', '微博');
INSERT INTO `t_recordssum` VALUES ('809', '435', '2019-06-18 16:41:41', '微博');
INSERT INTO `t_recordssum` VALUES ('810', '436', '2019-06-18 16:41:46', '微博');
INSERT INTO `t_recordssum` VALUES ('811', '437', '2019-06-18 16:41:55', '微博');
INSERT INTO `t_recordssum` VALUES ('812', '438', '2019-06-18 16:42:05', '微博');
INSERT INTO `t_recordssum` VALUES ('813', '439', '2019-06-18 16:42:15', '微博');
INSERT INTO `t_recordssum` VALUES ('814', '440', '2019-06-18 16:42:30', '微博');
INSERT INTO `t_recordssum` VALUES ('815', '441', '2019-06-18 16:42:35', '微博');
INSERT INTO `t_recordssum` VALUES ('816', '442', '2019-06-18 16:42:45', '微博');
INSERT INTO `t_recordssum` VALUES ('817', '443', '2019-06-18 16:42:50', '微博');
INSERT INTO `t_recordssum` VALUES ('818', '444', '2019-06-18 16:43:00', '微博');
INSERT INTO `t_recordssum` VALUES ('819', '445', '2019-06-18 16:43:10', '微博');
INSERT INTO `t_recordssum` VALUES ('820', '446', '2019-06-18 16:43:14', '微博');
INSERT INTO `t_recordssum` VALUES ('821', '447', '2019-06-18 16:43:20', '微博');
INSERT INTO `t_recordssum` VALUES ('822', '448', '2019-06-18 16:43:25', '微博');
INSERT INTO `t_recordssum` VALUES ('823', '449', '2019-06-18 16:43:35', '微博');
INSERT INTO `t_recordssum` VALUES ('824', '450', '2019-06-18 16:43:46', '微博');
INSERT INTO `t_recordssum` VALUES ('825', '451', '2019-06-18 16:43:56', '微博');
INSERT INTO `t_recordssum` VALUES ('826', '452', '2019-06-18 16:44:01', '微博');
INSERT INTO `t_recordssum` VALUES ('827', '453', '2019-06-18 16:44:11', '微博');
INSERT INTO `t_recordssum` VALUES ('828', '454', '2019-06-18 16:44:21', '微博');
INSERT INTO `t_recordssum` VALUES ('829', '455', '2019-06-18 16:44:31', '微博');
INSERT INTO `t_recordssum` VALUES ('830', '456', '2019-06-18 16:44:41', '微博');
INSERT INTO `t_recordssum` VALUES ('831', '457', '2019-06-18 16:44:46', '微博');
INSERT INTO `t_recordssum` VALUES ('832', '458', '2019-06-18 16:44:56', '微博');
INSERT INTO `t_recordssum` VALUES ('833', '459', '2019-06-18 16:45:06', '微博');
INSERT INTO `t_recordssum` VALUES ('834', '460', '2019-06-18 16:45:17', '微博');
INSERT INTO `t_recordssum` VALUES ('835', '461', '2019-06-18 16:45:22', '微博');
INSERT INTO `t_recordssum` VALUES ('836', '462', '2019-06-18 16:45:32', '微博');
INSERT INTO `t_recordssum` VALUES ('837', '463', '2019-06-18 16:45:41', '微博');
INSERT INTO `t_recordssum` VALUES ('838', '464', '2019-06-18 16:45:47', '微博');
INSERT INTO `t_recordssum` VALUES ('839', '465', '2019-06-18 16:45:57', '微博');
INSERT INTO `t_recordssum` VALUES ('840', '466', '2019-06-18 16:46:02', '微博');
INSERT INTO `t_recordssum` VALUES ('841', '467', '2019-06-18 16:46:12', '微博');
INSERT INTO `t_recordssum` VALUES ('842', '468', '2019-06-18 16:46:22', '微博');
INSERT INTO `t_recordssum` VALUES ('843', '469', '2019-06-18 16:46:32', '微博');
INSERT INTO `t_recordssum` VALUES ('844', '470', '2019-06-18 16:46:38', '微博');
INSERT INTO `t_recordssum` VALUES ('845', '471', '2019-06-18 16:46:48', '微博');
INSERT INTO `t_recordssum` VALUES ('846', '472', '2019-06-18 16:46:58', '微博');
INSERT INTO `t_recordssum` VALUES ('847', '473', '2019-06-18 16:47:08', '微博');
INSERT INTO `t_recordssum` VALUES ('848', '474', '2019-06-18 16:47:13', '微博');
INSERT INTO `t_recordssum` VALUES ('849', '475', '2019-06-18 16:47:23', '微博');
INSERT INTO `t_recordssum` VALUES ('850', '476', '2019-06-18 16:47:28', '微博');
INSERT INTO `t_recordssum` VALUES ('851', '477', '2019-06-18 16:47:33', '微博');
INSERT INTO `t_recordssum` VALUES ('852', '478', '2019-06-18 16:47:44', '微博');
INSERT INTO `t_recordssum` VALUES ('853', '479', '2019-06-18 16:47:54', '微博');
INSERT INTO `t_recordssum` VALUES ('854', '480', '2019-06-18 16:47:59', '微博');
INSERT INTO `t_recordssum` VALUES ('855', '481', '2019-06-18 16:48:09', '微博');
INSERT INTO `t_recordssum` VALUES ('856', '482', '2019-06-18 16:48:19', '微博');
INSERT INTO `t_recordssum` VALUES ('857', '483', '2019-06-18 16:48:30', '微博');
INSERT INTO `t_recordssum` VALUES ('858', '484', '2019-06-18 16:48:41', '微博');
INSERT INTO `t_recordssum` VALUES ('859', '485', '2019-06-18 16:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('860', '486', '2019-06-18 16:48:56', '微博');
INSERT INTO `t_recordssum` VALUES ('861', '487', '2019-06-18 16:49:06', '微博');
INSERT INTO `t_recordssum` VALUES ('862', '488', '2019-06-18 16:49:21', '微博');
INSERT INTO `t_recordssum` VALUES ('863', '489', '2019-06-18 16:49:31', '微博');
INSERT INTO `t_recordssum` VALUES ('864', '490', '2019-06-18 16:49:41', '微博');
INSERT INTO `t_recordssum` VALUES ('865', '491', '2019-06-18 16:49:51', '微博');
INSERT INTO `t_recordssum` VALUES ('866', '492', '2019-06-18 16:49:56', '微博');
INSERT INTO `t_recordssum` VALUES ('867', '493', '2019-06-18 16:50:01', '微博');
INSERT INTO `t_recordssum` VALUES ('868', '494', '2019-06-18 16:50:11', '微博');
INSERT INTO `t_recordssum` VALUES ('869', '495', '2019-06-18 16:50:21', '微博');
INSERT INTO `t_recordssum` VALUES ('870', '496', '2019-06-18 16:50:26', '微博');
INSERT INTO `t_recordssum` VALUES ('871', '497', '2019-06-18 16:50:36', '微博');
INSERT INTO `t_recordssum` VALUES ('872', '498', '2019-06-18 16:50:47', '微博');
INSERT INTO `t_recordssum` VALUES ('873', '499', '2019-06-18 16:50:57', '微博');
INSERT INTO `t_recordssum` VALUES ('874', '500', '2019-06-18 16:51:08', '微博');
INSERT INTO `t_recordssum` VALUES ('875', '501', '2019-06-18 16:51:18', '微博');
INSERT INTO `t_recordssum` VALUES ('876', '502', '2019-06-18 16:51:23', '微博');
INSERT INTO `t_recordssum` VALUES ('877', '503', '2019-06-18 16:51:33', '微博');
INSERT INTO `t_recordssum` VALUES ('878', '504', '2019-06-18 16:51:38', '微博');
INSERT INTO `t_recordssum` VALUES ('879', '505', '2019-06-18 16:51:42', '微博');
INSERT INTO `t_recordssum` VALUES ('880', '506', '2019-06-18 16:51:48', '微博');
INSERT INTO `t_recordssum` VALUES ('881', '507', '2019-06-18 16:51:58', '微博');
INSERT INTO `t_recordssum` VALUES ('882', '508', '2019-06-18 16:52:13', '微博');
INSERT INTO `t_recordssum` VALUES ('883', '509', '2019-06-18 16:52:23', '微博');
INSERT INTO `t_recordssum` VALUES ('884', '510', '2019-06-18 16:52:28', '微博');
INSERT INTO `t_recordssum` VALUES ('885', '511', '2019-06-18 16:52:38', '微博');
INSERT INTO `t_recordssum` VALUES ('886', '512', '2019-06-18 16:52:43', '微博');
INSERT INTO `t_recordssum` VALUES ('887', '513', '2019-06-18 16:52:49', '微博');
INSERT INTO `t_recordssum` VALUES ('888', '514', '2019-06-18 16:52:54', '微博');
INSERT INTO `t_recordssum` VALUES ('889', '515', '2019-06-18 16:53:05', '微博');
INSERT INTO `t_recordssum` VALUES ('890', '516', '2019-06-18 16:53:10', '微博');
INSERT INTO `t_recordssum` VALUES ('891', '517', '2019-06-18 16:53:21', '微博');
INSERT INTO `t_recordssum` VALUES ('892', '518', '2019-06-18 16:53:26', '微博');
INSERT INTO `t_recordssum` VALUES ('893', '519', '2019-06-18 16:53:37', '微博');
INSERT INTO `t_recordssum` VALUES ('894', '520', '2019-06-18 16:53:42', '微博');
INSERT INTO `t_recordssum` VALUES ('895', '706', '2019-06-18 17:22:52', '微博');
INSERT INTO `t_recordssum` VALUES ('896', '707', '2019-06-18 17:23:05', '微博');
INSERT INTO `t_recordssum` VALUES ('897', '708', '2019-06-18 17:23:13', '微博');
INSERT INTO `t_recordssum` VALUES ('898', '709', '2019-06-18 17:23:21', '微博');
INSERT INTO `t_recordssum` VALUES ('899', '710', '2019-06-18 17:23:34', '微博');
INSERT INTO `t_recordssum` VALUES ('900', '711', '2019-06-18 17:23:53', '微博');
INSERT INTO `t_recordssum` VALUES ('901', '712', '2019-06-18 17:24:01', '微博');
INSERT INTO `t_recordssum` VALUES ('902', '713', '2019-06-18 17:24:13', '微博');
INSERT INTO `t_recordssum` VALUES ('903', '714', '2019-06-18 17:24:20', '微博');
INSERT INTO `t_recordssum` VALUES ('904', '715', '2019-06-18 17:24:28', '微博');
INSERT INTO `t_recordssum` VALUES ('905', '716', '2019-06-18 17:24:40', '微博');
INSERT INTO `t_recordssum` VALUES ('906', '717', '2019-06-18 17:25:03', '微博');
INSERT INTO `t_recordssum` VALUES ('907', '718', '2019-06-18 17:25:11', '微博');
INSERT INTO `t_recordssum` VALUES ('908', '719', '2019-06-18 17:25:19', '微博');
INSERT INTO `t_recordssum` VALUES ('909', '720', '2019-06-18 17:25:27', '微博');
INSERT INTO `t_recordssum` VALUES ('910', '721', '2019-06-18 17:25:34', '微博');
INSERT INTO `t_recordssum` VALUES ('911', '722', '2019-06-18 17:25:42', '微博');
INSERT INTO `t_recordssum` VALUES ('912', '723', '2019-06-18 17:25:49', '微博');
INSERT INTO `t_recordssum` VALUES ('913', '724', '2019-06-18 17:25:57', '微博');
INSERT INTO `t_recordssum` VALUES ('914', '725', '2019-06-18 17:26:09', '微博');
INSERT INTO `t_recordssum` VALUES ('915', '726', '2019-06-18 17:26:17', '微博');
INSERT INTO `t_recordssum` VALUES ('916', '209', '2019-06-18 18:00:52', '微博');
INSERT INTO `t_recordssum` VALUES ('917', '221', '2019-06-20 09:41:27', '微博');
INSERT INTO `t_recordssum` VALUES ('918', '15', '2019-06-20 10:25:52', '微博');
INSERT INTO `t_recordssum` VALUES ('919', '15', '2019-06-20 10:29:29', '微博');
INSERT INTO `t_recordssum` VALUES ('920', '15', '2019-06-20 10:31:39', '微博');
INSERT INTO `t_recordssum` VALUES ('921', '128', '2019-06-20 10:47:40', '微博');
INSERT INTO `t_recordssum` VALUES ('922', '119', '2019-06-20 10:57:16', '微博');
INSERT INTO `t_recordssum` VALUES ('923', '1', '2019-06-20 11:00:42', '微博');
INSERT INTO `t_recordssum` VALUES ('924', '1', '2019-06-20 11:02:55', '微博');
INSERT INTO `t_recordssum` VALUES ('925', '7', '2019-06-20 11:04:58', '微博');
INSERT INTO `t_recordssum` VALUES ('926', '387', '2019-06-20 12:01:51', '微博');
INSERT INTO `t_recordssum` VALUES ('927', '406', '2019-06-20 14:28:11', '微博');
INSERT INTO `t_recordssum` VALUES ('928', '192', '2019-06-20 15:16:19', '微博');
INSERT INTO `t_recordssum` VALUES ('929', '181', '2019-06-20 17:14:46', '知乎');
INSERT INTO `t_recordssum` VALUES ('930', '193', '2019-06-21 14:27:41', '微博');
INSERT INTO `t_recordssum` VALUES ('931', '42', '2019-06-21 15:14:07', '微博');
INSERT INTO `t_recordssum` VALUES ('932', '15', '2019-06-21 15:21:41', '微博');
INSERT INTO `t_recordssum` VALUES ('933', '15', '2019-06-21 15:27:22', '微博');
INSERT INTO `t_recordssum` VALUES ('934', '15', '2019-06-21 15:29:38', '微博');
INSERT INTO `t_recordssum` VALUES ('935', '181', '2019-06-21 15:47:52', '知乎');
INSERT INTO `t_recordssum` VALUES ('936', '200', '2019-06-21 16:01:13', '百度知道');
INSERT INTO `t_recordssum` VALUES ('937', '200', '2019-06-21 16:04:14', '百度知道');
INSERT INTO `t_recordssum` VALUES ('938', '200', '2019-06-21 16:05:01', '百度知道');
INSERT INTO `t_recordssum` VALUES ('939', '15', '2019-06-21 16:08:12', '微博');
INSERT INTO `t_recordssum` VALUES ('940', '181', '2019-06-21 16:34:39', '知乎');
INSERT INTO `t_recordssum` VALUES ('941', '181', '2019-06-21 16:35:27', '知乎');
INSERT INTO `t_recordssum` VALUES ('942', '3', '2019-06-21 16:37:10', '微博');
INSERT INTO `t_recordssum` VALUES ('943', '3', '2019-06-21 16:38:51', '微博');
INSERT INTO `t_recordssum` VALUES ('944', '15', '2019-06-21 16:40:45', '微博');
INSERT INTO `t_recordssum` VALUES ('945', '181', '2019-06-21 16:45:27', '知乎');
INSERT INTO `t_recordssum` VALUES ('946', '181', '2019-06-21 16:48:47', '知乎');
INSERT INTO `t_recordssum` VALUES ('947', '181', '2019-06-21 16:49:48', '知乎');
INSERT INTO `t_recordssum` VALUES ('948', '1', '2019-06-21 16:53:40', '微博');
INSERT INTO `t_recordssum` VALUES ('949', '1', '2019-06-21 16:54:25', '微博');
INSERT INTO `t_recordssum` VALUES ('950', '200', '2019-06-21 17:08:41', '百度知道');
INSERT INTO `t_recordssum` VALUES ('951', '200', '2019-06-21 17:11:23', '百度知道');
INSERT INTO `t_recordssum` VALUES ('952', '181', '2019-06-21 17:15:48', '知乎');
INSERT INTO `t_recordssum` VALUES ('953', '191', '2019-06-21 17:28:56', '微博');
INSERT INTO `t_recordssum` VALUES ('954', '432', '2019-06-21 18:19:32', '微博');
INSERT INTO `t_recordssum` VALUES ('955', '26', '2019-06-21 18:38:48', '微博');
INSERT INTO `t_recordssum` VALUES ('956', '55', '2019-06-21 18:41:59', '微博');
INSERT INTO `t_recordssum` VALUES ('957', '13', '2019-06-21 19:30:58', '微博');
INSERT INTO `t_recordssum` VALUES ('958', '96', '2019-06-21 20:47:05', '微博');
INSERT INTO `t_recordssum` VALUES ('959', '14', '2019-06-21 20:52:46', '微博');
INSERT INTO `t_recordssum` VALUES ('960', '2', '2019-06-22 09:15:08', '微博');
INSERT INTO `t_recordssum` VALUES ('961', '16', '2019-06-22 09:15:53', '微博');
INSERT INTO `t_recordssum` VALUES ('962', '182', '2019-06-22 09:17:19', '知乎');
INSERT INTO `t_recordssum` VALUES ('963', '4', '2019-06-22 17:36:13', '微博');
INSERT INTO `t_recordssum` VALUES ('964', '4', '2019-06-22 17:36:58', '微博');
INSERT INTO `t_recordssum` VALUES ('965', '182', '2019-06-22 17:38:17', '知乎');
INSERT INTO `t_recordssum` VALUES ('966', '25', '2019-06-23 22:17:14', '微博');
INSERT INTO `t_recordssum` VALUES ('967', '25', '2019-06-23 22:19:29', '微博');
INSERT INTO `t_recordssum` VALUES ('968', '23', '2019-06-23 22:59:34', '微博');
INSERT INTO `t_recordssum` VALUES ('969', '23', '2019-06-23 23:03:52', '微博');
INSERT INTO `t_recordssum` VALUES ('970', '23', '2019-06-23 23:09:01', '微博');
INSERT INTO `t_recordssum` VALUES ('971', '25', '2019-06-23 23:11:50', '微博');
INSERT INTO `t_recordssum` VALUES ('972', '23', '2019-06-23 23:22:24', '微博');
INSERT INTO `t_recordssum` VALUES ('973', '26', '2019-06-23 23:52:16', '微博');
INSERT INTO `t_recordssum` VALUES ('974', '26', '2019-06-24 00:11:07', '微博');
INSERT INTO `t_recordssum` VALUES ('975', '23', '2019-06-24 00:17:30', '微博');
INSERT INTO `t_recordssum` VALUES ('976', '23', '2019-06-24 00:23:04', '微博');
INSERT INTO `t_recordssum` VALUES ('977', '29', '2019-06-24 00:27:45', '微博');
INSERT INTO `t_recordssum` VALUES ('978', '29', '2019-06-24 00:31:24', '微博');
INSERT INTO `t_recordssum` VALUES ('979', '7', '2019-06-24 00:36:04', '微博');
INSERT INTO `t_recordssum` VALUES ('980', '9', '2019-06-24 00:39:06', '微博');
INSERT INTO `t_recordssum` VALUES ('981', '9', '2019-06-24 00:44:50', '微博');
INSERT INTO `t_recordssum` VALUES ('982', '22', '2019-06-24 01:29:43', '微博');
INSERT INTO `t_recordssum` VALUES ('983', '25', '2019-06-24 01:33:34', '微博');
INSERT INTO `t_recordssum` VALUES ('984', '23', '2019-06-24 09:03:23', '微博');
INSERT INTO `t_recordssum` VALUES ('985', '46', '2019-06-24 09:04:55', '微博');
INSERT INTO `t_recordssum` VALUES ('986', '23', '2019-06-24 09:11:27', '微博');
INSERT INTO `t_recordssum` VALUES ('987', '13', '2019-06-24 09:16:46', '微博');
INSERT INTO `t_recordssum` VALUES ('988', '23', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('989', '24', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('990', '25', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('991', '26', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('992', '27', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('993', '27', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('994', '28', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('995', '29', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('996', '30', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('997', '30', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('998', '30', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('999', '31', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1000', '32', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1001', '33', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1002', '34', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1003', '35', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1004', '36', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1005', '37', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1006', '38', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1007', '39', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1008', '40', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1009', '41', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1010', '42', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1011', '43', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1012', '44', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1013', '45', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1014', '46', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1015', '47', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1016', '48', '2019-06-24 09:23:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1017', '23', '2019-06-24 09:31:58', '微博');
INSERT INTO `t_recordssum` VALUES ('1018', '126', '2019-06-24 09:39:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1019', '63', '2019-06-24 09:47:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1020', '28', '2019-06-24 09:54:19', '微博');
INSERT INTO `t_recordssum` VALUES ('1021', '13', '2019-06-24 09:57:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1022', '13', '2019-06-24 10:09:45', '微博');
INSERT INTO `t_recordssum` VALUES ('1023', '13', '2019-06-24 10:12:34', '微博');
INSERT INTO `t_recordssum` VALUES ('1024', '13', '2019-06-24 11:09:00', '微博');
INSERT INTO `t_recordssum` VALUES ('1025', '28', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1026', '29', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1027', '30', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1028', '31', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1029', '32', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1030', '33', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1031', '34', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1032', '35', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1033', '36', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1034', '37', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1035', '38', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1036', '39', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1037', '40', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1038', '41', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1039', '42', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1040', '43', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1041', '44', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1042', '45', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1043', '46', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1044', '47', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1045', '48', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1046', '49', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1047', '50', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1048', '51', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1049', '52', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1050', '53', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1051', '54', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1052', '55', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1053', '56', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1054', '57', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1055', '58', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1056', '59', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1057', '60', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1058', '61', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1059', '62', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1060', '63', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1061', '64', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1062', '65', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1063', '66', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1064', '67', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1065', '68', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1066', '69', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1067', '70', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1068', '71', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1069', '72', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1070', '73', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1071', '74', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1072', '75', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1073', '76', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1074', '77', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1075', '78', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1076', '79', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1077', '80', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1078', '81', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1079', '82', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1080', '83', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1081', '84', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1082', '85', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1083', '86', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1084', '87', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1085', '88', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1086', '89', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1087', '90', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1088', '91', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1089', '92', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1090', '93', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1091', '94', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1092', '95', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1093', '96', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1094', '97', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1095', '98', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1096', '99', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1097', '100', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1098', '101', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1099', '102', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1100', '103', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1101', '104', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1102', '105', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1103', '106', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1104', '107', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1105', '108', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1106', '109', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1107', '110', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1108', '111', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1109', '112', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1110', '112', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1111', '113', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1112', '114', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1113', '115', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1114', '116', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1115', '117', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1116', '118', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1117', '119', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1118', '120', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1119', '121', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1120', '122', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1121', '123', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1122', '124', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1123', '125', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1124', '126', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1125', '127', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1126', '128', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1127', '129', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1128', '130', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1129', '131', '2019-06-24 11:14:41', '微博');
INSERT INTO `t_recordssum` VALUES ('1130', '13', '2019-06-24 11:23:45', '微博');
INSERT INTO `t_recordssum` VALUES ('1131', '17', '2019-06-24 11:33:43', '微博');
INSERT INTO `t_recordssum` VALUES ('1132', '30', '2019-06-24 12:15:52', '微博');
INSERT INTO `t_recordssum` VALUES ('1133', '31', '2019-06-24 12:15:52', '微博');
INSERT INTO `t_recordssum` VALUES ('1134', '32', '2019-06-24 12:15:52', '微博');
INSERT INTO `t_recordssum` VALUES ('1135', '33', '2019-06-24 12:15:52', '微博');
INSERT INTO `t_recordssum` VALUES ('1136', '32', '2019-06-24 12:19:04', '微博');
INSERT INTO `t_recordssum` VALUES ('1137', '30', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1138', '31', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1139', '32', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1140', '33', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1141', '34', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1142', '35', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1143', '36', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1144', '37', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1145', '38', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1146', '39', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1147', '40', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1148', '41', '2019-06-24 12:29:03', '微博');
INSERT INTO `t_recordssum` VALUES ('1149', '31', '2019-06-24 12:37:50', '微博');
INSERT INTO `t_recordssum` VALUES ('1150', '31', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1151', '32', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1152', '33', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1153', '34', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1154', '35', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1155', '36', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1156', '37', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1157', '38', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1158', '39', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1159', '40', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1160', '41', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1161', '42', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1162', '43', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1163', '44', '2019-06-24 12:42:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1164', '32', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1165', '33', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1166', '34', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1167', '35', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1168', '36', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1169', '37', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1170', '38', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1171', '39', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1172', '40', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1173', '41', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1174', '42', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1175', '43', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1176', '44', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1177', '45', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1178', '46', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1179', '47', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1180', '48', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1181', '49', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1182', '50', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1183', '51', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1184', '52', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1185', '53', '2019-06-24 12:48:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1186', '32', '2019-06-24 12:59:00', '微博');
INSERT INTO `t_recordssum` VALUES ('1187', '32', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1188', '33', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1189', '34', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1190', '35', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1191', '36', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1192', '37', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1193', '38', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1194', '39', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1195', '40', '2019-06-24 13:07:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1196', '32', '2019-06-24 13:12:57', '微博');
INSERT INTO `t_recordssum` VALUES ('1197', '32', '2019-06-24 13:17:19', '微博');
INSERT INTO `t_recordssum` VALUES ('1198', '32', '2019-06-24 13:21:27', '微博');
INSERT INTO `t_recordssum` VALUES ('1199', '32', '2019-06-24 13:29:37', '微博');
INSERT INTO `t_recordssum` VALUES ('1200', '11', '2019-06-24 16:04:08', '微博');
INSERT INTO `t_recordssum` VALUES ('1201', '7', '2019-06-24 16:31:29', '微博');
INSERT INTO `t_recordssum` VALUES ('1202', '7', '2019-06-24 16:44:53', '微博');
INSERT INTO `t_recordssum` VALUES ('1203', '7', '2019-06-24 17:04:44', '微博');
INSERT INTO `t_recordssum` VALUES ('1204', '7', '2019-06-24 17:39:38', '微博');
INSERT INTO `t_recordssum` VALUES ('1205', '24', '2019-06-24 17:45:15', '微博');
INSERT INTO `t_recordssum` VALUES ('1206', '7', '2019-06-24 17:46:54', '微博');
INSERT INTO `t_recordssum` VALUES ('1207', '6', '2019-06-24 17:53:50', '微博');
INSERT INTO `t_recordssum` VALUES ('1208', '26', '2019-06-24 18:39:26', '微博');
INSERT INTO `t_recordssum` VALUES ('1209', '10', '2019-06-24 18:43:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1210', '98', '2019-06-24 19:25:55', '微博');
INSERT INTO `t_recordssum` VALUES ('1211', '98', '2019-06-24 19:25:54', '微博');
INSERT INTO `t_recordssum` VALUES ('1212', '26', '2019-06-24 20:17:19', '微博');
INSERT INTO `t_recordssum` VALUES ('1213', '4', '2019-06-24 20:20:36', '微博');
INSERT INTO `t_recordssum` VALUES ('1214', '29', '2019-06-24 21:34:39', '微博');
INSERT INTO `t_recordssum` VALUES ('1215', '29', '2019-06-24 21:39:26', '微博');
INSERT INTO `t_recordssum` VALUES ('1216', '29', '2019-06-24 22:17:01', '微博');
INSERT INTO `t_recordssum` VALUES ('1217', '29', '2019-06-24 22:23:46', '微博');
INSERT INTO `t_recordssum` VALUES ('1218', '29', '2019-06-24 22:29:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1219', '30', '2019-06-24 22:29:18', '微博');
INSERT INTO `t_recordssum` VALUES ('1220', '29', '2019-06-24 22:35:51', '微博');
INSERT INTO `t_recordssum` VALUES ('1221', '30', '2019-06-24 22:35:51', '微博');
INSERT INTO `t_recordssum` VALUES ('1222', '29', '2019-06-24 22:45:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1223', '29', '2019-06-24 22:54:49', '微博');
INSERT INTO `t_recordssum` VALUES ('1224', '29', '2019-06-24 22:57:50', '微博');
INSERT INTO `t_recordssum` VALUES ('1225', '29', '2019-06-24 23:01:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1226', '29', '2019-06-24 23:08:07', '微博');
INSERT INTO `t_recordssum` VALUES ('1227', '30', '2019-06-24 23:11:11', '微博');
INSERT INTO `t_recordssum` VALUES ('1228', '31', '2019-06-24 23:20:17', '微博');
INSERT INTO `t_recordssum` VALUES ('1229', '32', '2019-06-24 23:23:33', '微博');
INSERT INTO `t_recordssum` VALUES ('1230', '32', '2019-06-24 23:25:53', '微博');
INSERT INTO `t_recordssum` VALUES ('1231', '34', '2019-06-24 23:32:02', '微博');
INSERT INTO `t_recordssum` VALUES ('1232', '37', '2019-06-24 23:36:44', '微博');
INSERT INTO `t_recordssum` VALUES ('1233', '38', '2019-06-24 23:39:37', '微博');
INSERT INTO `t_recordssum` VALUES ('1234', '38', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1235', '39', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1236', '40', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1237', '41', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1238', '42', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1239', '43', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1240', '44', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1241', '45', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1242', '46', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1243', '47', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1244', '48', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1245', '49', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1246', '50', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1247', '51', '2019-06-24 23:50:12', '微博');
INSERT INTO `t_recordssum` VALUES ('1248', '38', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1249', '39', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1250', '40', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1251', '41', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1252', '42', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1253', '43', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1254', '44', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1255', '45', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1256', '46', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1257', '47', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1258', '48', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1259', '49', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1260', '50', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1261', '51', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1262', '52', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1263', '53', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1264', '54', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1265', '55', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1266', '56', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1267', '57', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1268', '58', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1269', '59', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1270', '60', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1271', '61', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1272', '62', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1273', '63', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1274', '64', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1275', '65', '2019-06-24 23:56:05', '微博');
INSERT INTO `t_recordssum` VALUES ('1276', '38', '2019-06-25 00:02:40', '微博');
INSERT INTO `t_recordssum` VALUES ('1277', '18', '2019-06-25 09:22:44', '微博');
INSERT INTO `t_recordssum` VALUES ('1278', '41', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1279', '42', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1280', '43', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1281', '44', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1282', '45', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1283', '46', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1284', '47', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1285', '48', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1286', '49', '2019-06-25 11:34:28', '微博');
INSERT INTO `t_recordssum` VALUES ('1287', '41', '2019-06-25 11:49:04', '微博');
INSERT INTO `t_recordssum` VALUES ('1288', '37', '2019-06-25 18:20:31', '微博');
INSERT INTO `t_recordssum` VALUES ('1289', '37', '2019-06-25 18:38:22', '微博');
INSERT INTO `t_recordssum` VALUES ('1290', '37', '2019-06-25 18:44:04', '微博');
INSERT INTO `t_recordssum` VALUES ('1291', '182', '2019-07-12 15:47:06', '知乎');
INSERT INTO `t_recordssum` VALUES ('1292', '181', '2019-07-12 17:07:26', '知乎');
INSERT INTO `t_recordssum` VALUES ('1293', '182', '2019-07-12 17:27:12', '知乎');
INSERT INTO `t_recordssum` VALUES ('1294', '182', '2019-07-12 18:12:54', '知乎');
INSERT INTO `t_recordssum` VALUES ('1295', '182', '2019-07-12 20:03:56', '知乎');
INSERT INTO `t_recordssum` VALUES ('1296', '182', '2019-07-12 20:05:15', '知乎');
INSERT INTO `t_recordssum` VALUES ('1297', '182', '2019-07-12 20:06:05', '知乎');
INSERT INTO `t_recordssum` VALUES ('1298', '19', '2019-07-24 10:22:39', '微博文章');

-- ----------------------------
-- Table structure for `wechat`
-- ----------------------------
DROP TABLE IF EXISTS `wechat`;
CREATE TABLE `wechat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关键字id',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '抓取状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='微信关键字表';

-- ----------------------------
-- Records of wechat
-- ----------------------------
INSERT INTO `wechat` VALUES ('23', '垃圾分类', '2019-07-18 15:46:26', '2');

-- ----------------------------
-- Table structure for `wechat_oldrecords`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_oldrecords`;
CREATE TABLE `wechat_oldrecords` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '抓取状态',
  `wechatid` bigint(20) DEFAULT NULL COMMENT '爬取微信对应的id',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信文章抓取历史记录表';

-- ----------------------------
-- Records of wechat_oldrecords
-- ----------------------------

-- ----------------------------
-- Table structure for `weiboqa`
-- ----------------------------
DROP TABLE IF EXISTS `weiboqa`;
CREATE TABLE `weiboqa` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `question` varchar(50) NOT NULL COMMENT '问题',
  `path` text COMMENT '路径',
  `createtime` varchar(50) DEFAULT NULL COMMENT '问题发表时间范围',
  `content` bit(1) DEFAULT NULL COMMENT '内容分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weiboqa
-- ----------------------------
INSERT INTO `weiboqa` VALUES ('9', '垃圾分类', null, '', '');

-- ----------------------------
-- Table structure for `weiboqahistory`
-- ----------------------------
DROP TABLE IF EXISTS `weiboqahistory`;
CREATE TABLE `weiboqahistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(20) NOT NULL COMMENT '文件名',
  `creattime` datetime DEFAULT NULL COMMENT '文件创建时间',
  `path` text COMMENT '文件路径',
  `status` bit(1) NOT NULL COMMENT '问题或问题和回答',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weiboqahistory
-- ----------------------------

-- ----------------------------
-- Table structure for `zhihu`
-- ----------------------------
DROP TABLE IF EXISTS `zhihu`;
CREATE TABLE `zhihu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `title` varchar(4000) DEFAULT NULL COMMENT '标题',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of zhihu
-- ----------------------------

-- ----------------------------
-- Table structure for `zhihu_oldrecords`
-- ----------------------------
DROP TABLE IF EXISTS `zhihu_oldrecords`;
CREATE TABLE `zhihu_oldrecords` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `keyword` varchar(255) DEFAULT NULL COMMENT '知乎关键词',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `zhihuid` bigint(20) DEFAULT NULL COMMENT '连表id',
  `uuid` varchar(255) DEFAULT NULL COMMENT '文件名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of zhihu_oldrecords
-- ----------------------------
INSERT INTO `zhihu_oldrecords` VALUES ('13', '2019-07-13 14:04:43', '如何去面试互联网公司', 'admin', '63', '1562997883826');

-- ----------------------------
-- Table structure for `zhihu_records`
-- ----------------------------
DROP TABLE IF EXISTS `zhihu_records`;
CREATE TABLE `zhihu_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `keyword` varchar(255) DEFAULT NULL COMMENT '关键词',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `typechoice` int(255) DEFAULT NULL COMMENT '0不抓取答案 1抓取答案',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of zhihu_records
-- ----------------------------
INSERT INTO `zhihu_records` VALUES ('60', '2019-06-21 16:48:31', '电视', 'admin', '0');
INSERT INTO `zhihu_records` VALUES ('61', '2019-06-21 16:48:45', '2014年世界杯', 'admin', '0');
INSERT INTO `zhihu_records` VALUES ('62', '2019-06-21 17:15:46', '2018世界杯', 'admin', '1');
INSERT INTO `zhihu_records` VALUES ('63', '2019-06-22 09:17:12', '如何去面试互联网公司', 'admin', '1');
INSERT INTO `zhihu_records` VALUES ('64', '2019-06-22 17:38:15', '毛不易唱歌好吗', 'admin', '1');
INSERT INTO `zhihu_records` VALUES ('65', '2019-07-12 16:36:25', '海贼王947话', 'admin', '1');
