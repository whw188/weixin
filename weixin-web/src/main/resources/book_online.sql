/*
Navicat MySQL Data Transfer

Source Server         : 39.107.119.63
Source Server Version : 50721
Source Host           : 39.107.119.63:3306
Source Database       : book_online

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-20 16:54:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for barrage
-- ----------------------------
DROP TABLE IF EXISTS `barrage`;
CREATE TABLE `barrage` (
  `bid` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `rid` bigint(20) NOT NULL,
  `text` varchar(255) NOT NULL DEFAULT '0' COMMENT ' 0：创建 1：已点赞',
  `color` varchar(20) NOT NULL DEFAULT '0' COMMENT ' 0：创建 1：收藏',
  `time` int(5) NOT NULL DEFAULT '0' COMMENT ' 0：创建 1：收藏',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`bid`),
  KEY `uid_index` (`uid`) USING BTREE,
  KEY `rid_index` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of barrage
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `cname` varchar(50) NOT NULL COMMENT '图书奇谱id',
  `detail` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`cid`),
  KEY `book_cname_index` (`cname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'IT/计算机', null, '2018-03-06 16:28:12', '2018-03-06 16:28:42', '0');
INSERT INTO `category` VALUES ('2', '人文社科', null, '2018-03-06 16:29:13', '2018-03-06 16:29:13', '0');
INSERT INTO `category` VALUES ('3', '工程科技', null, '2018-03-06 16:29:20', '2018-03-06 16:29:20', '0');
INSERT INTO `category` VALUES ('4', '自然科学', null, '2018-03-06 16:29:29', '2018-03-06 16:29:29', '0');
INSERT INTO `category` VALUES ('5', '医药卫生', null, '2018-03-06 16:29:37', '2018-03-06 16:29:37', '0');
INSERT INTO `category` VALUES ('6', '农林牧渔', null, '2018-03-06 16:29:44', '2018-03-06 16:29:44', '0');
INSERT INTO `category` VALUES ('7', '生活休闲', null, '2018-03-06 16:29:50', '2018-03-06 16:29:50', '0');
INSERT INTO `category` VALUES ('8', '其他', null, '2018-03-06 16:29:54', '2018-03-06 16:29:54', '0');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `cmid` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_uid` varchar(100) NOT NULL,
  `to_uid` varchar(100) NOT NULL,
  `rid` bigint(20) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `agree_count` int(11) DEFAULT '0',
  `tread_count` int(11) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`cmid`),
  KEY `uid_index` (`from_uid`) USING BTREE,
  KEY `rid_index` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for comment_agree
-- ----------------------------
DROP TABLE IF EXISTS `comment_agree`;
CREATE TABLE `comment_agree` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `cmid` bigint(20) NOT NULL,
  `agree` int(5) DEFAULT '0' COMMENT ' 0：创建 1：已点赞',
  `tread` int(5) DEFAULT '0' COMMENT ' 0：创建 1：收藏',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`) USING BTREE,
  KEY `rid_index` (`cmid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of comment_agree
-- ----------------------------

-- ----------------------------
-- Table structure for recommond
-- ----------------------------
DROP TABLE IF EXISTS `recommond`;
CREATE TABLE `recommond` (
  `rmid` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `search` varchar(100) DEFAULT '0' COMMENT ' 0：创建 1：收藏',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`rmid`),
  KEY `uid_index` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of recommond
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `rid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `file_id` varchar(255) NOT NULL COMMENT '图书奇谱id',
  `file_name` varchar(255) DEFAULT NULL COMMENT '图书奇谱id',
  `uid` varchar(100) NOT NULL COMMENT '章节id',
  `author` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '用户id',
  `summary` varchar(255) DEFAULT NULL COMMENT '内容简介',
  `type` varchar(50) DEFAULT NULL COMMENT '评分',
  `image_id` varchar(50) DEFAULT NULL COMMENT '入口类型',
  `cid` bigint(20) DEFAULT NULL COMMENT '分类id',
  `price` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `agree_count` int(11) DEFAULT '0',
  `shelf_count` int(11) DEFAULT '0' COMMENT '收藏数',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) DEFAULT '0' COMMENT ' 0：创建，待审核 1：已审核 2：删除',
  PRIMARY KEY (`rid`),
  KEY `title_index` (`title`) USING BTREE,
  KEY `uid_index` (`uid`) USING BTREE,
  KEY `cid_index` (`cid`) USING BTREE,
  KEY `rid_index` (`file_id`) USING BTREE,
  KEY `status_index` (`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for shelf
-- ----------------------------
DROP TABLE IF EXISTS `shelf`;
CREATE TABLE `shelf` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `rid` bigint(20) NOT NULL,
  `agree` int(5) DEFAULT '0' COMMENT ' 0：创建 1：已点赞',
  `shelf` int(5) DEFAULT '0' COMMENT ' 0：创建 1：收藏',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`sid`),
  KEY `uid_index` (`uid`) USING BTREE,
  KEY `rid_index` (`rid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of shelf
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `nick` varchar(255) NOT NULL,
  `head` varchar(255) NOT NULL,
  `coin` int(11) NOT NULL DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`) USING BTREE,
  KEY `nick_index` (`nick`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('9', 'oDA605CZh1lIRB_JSDhm9RB7vtsc', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLjJ5A6sRPHOQK6kQBYwpo45r6gWJIwS9aLtmXewB33TXzNUQvXHicsQcOcf16NRVLqn4jJTiclibJ3w/132', '中红', '130', '2018-05-17 20:16:17', '2018-05-20 14:31:49', '0');
INSERT INTO `user` VALUES ('10', 'oDA605PvAqqqnz4vDrHleRNLRY40', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEKemPmaWp1Kt8rFszI3ib8mZ4hh9jxib1e8IQ3wZV5tOdfhFOUMibd7A4xRKBK6LPHbKRTEYEtfRbD3w/132', ' ', '2', '2018-05-17 20:16:57', '2018-05-19 15:20:37', '0');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `rid` bigint(20) NOT NULL,
  `price` int(11) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of user_order
-- ----------------------------

-- ----------------------------
-- Table structure for user_sign
-- ----------------------------
DROP TABLE IF EXISTS `user_sign`;
CREATE TABLE `user_sign` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(100) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(5) DEFAULT '0' COMMENT ' 0：创建 1：删除',
  PRIMARY KEY (`id`),
  KEY `uid_index` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='评分';

-- ----------------------------
-- Records of user_sign
-- ----------------------------
