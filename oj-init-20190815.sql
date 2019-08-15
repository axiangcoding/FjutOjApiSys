/*
SQLyog Enterprise Trial - MySQL GUI v7.11 
MySQL - 8.0.15 : Database - oj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`oj` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

/*Table structure for table `ceinfo` */

DROP TABLE IF EXISTS `ceinfo`;

CREATE TABLE `ceinfo` (
  `rid` int(11) NOT NULL,
  `info` mediumtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `contest` */

DROP TABLE IF EXISTS `contest`;

CREATE TABLE `contest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `beginTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `rankType` int(11) NOT NULL,
  `ctype` int(11) NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `registerstarttime` datetime NOT NULL DEFAULT '2015-01-01 00:00:00',
  `registerendtime` datetime NOT NULL,
  `info` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `computerating` int(11) NOT NULL,
  `createuser` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `kind` int(11) DEFAULT NULL COMMENT '0：练习；1：积分；2：趣味；3：正式；4：隐藏；5：DIY',
  `problemCanPutTag` bit(1) NOT NULL,
  `statusReadOut` bit(1) NOT NULL,
  `registerShowComplete` bit(1) NOT NULL,
  `hideRankMinute` int(11) NOT NULL,
  `isHideOthersStatus` bit(1) NOT NULL,
  `isHideOthersStatusInfo` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=659 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `contestproblems` */

DROP TABLE IF EXISTS `contestproblems`;

CREATE TABLE `contestproblems` (
  `cid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `tpid` int(11) NOT NULL,
  PRIMARY KEY (`cid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `contestuser` */

DROP TABLE IF EXISTS `contestuser`;

CREATE TABLE `contestuser` (
  `cid` int(11) NOT NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `statu` int(11) NOT NULL,
  `info` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`cid`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `newdiscuss` */

DROP TABLE IF EXISTS `newdiscuss`;

CREATE TABLE `newdiscuss` (
  `discussid` int(11) NOT NULL AUTO_INCREMENT COMMENT '讨论id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '讨论标题',
  `time` datetime NOT NULL COMMENT '时间',
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '发起人',
  `priority` double DEFAULT '-1' COMMENT '优先级',
  PRIMARY KEY (`discussid`)
) ENGINE=InnoDB AUTO_INCREMENT=1132 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `newdiscussreply` */

DROP TABLE IF EXISTS `newdiscussreply`;

CREATE TABLE `newdiscussreply` (
  `discussid` int(11) NOT NULL COMMENT '讨论id',
  `replyid` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `time` datetime NOT NULL COMMENT '时间',
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '回复人',
  `text` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '内容',
  PRIMARY KEY (`discussid`,`replyid`),
  KEY `replyid` (`replyid`)
) ENGINE=InnoDB AUTO_INCREMENT=1803 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `pid` int(11) NOT NULL,
  `ptype` int(11) NOT NULL,
  `title` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ojid` int(11) NOT NULL,
  `ojspid` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `visiable` int(11) NOT NULL DEFAULT '0',
  `author` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `spj` tinyint(1) NOT NULL,
  `totalSubmit` int(11) NOT NULL DEFAULT '0',
  `totalSubmitUser` int(11) NOT NULL DEFAULT '0',
  `totalAc` int(11) NOT NULL DEFAULT '0',
  `totalAcUser` int(11) NOT NULL DEFAULT '0',
  `owner` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `statu` */

DROP TABLE IF EXISTS `statu`;

CREATE TABLE `statu` (
  `id` int(11) NOT NULL,
  `ruser` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `lang` int(11) NOT NULL,
  `submitTime` datetime NOT NULL,
  `result` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '-1',
  `timeUsed` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `memoryUsed` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `code` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `codelen` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `suoying` (`ruser`),
  KEY `suoying2` (`pid`),
  KEY `cidsuoyint` (`cid`),
  KEY `indexResult` (`result`),
  KEY `indexLanguage` (`lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_acborder` */

DROP TABLE IF EXISTS `t_acborder`;

CREATE TABLE `t_acborder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `acbchange` int(11) NOT NULL,
  `reason` int(11) NOT NULL,
  `mark` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `t_ai_info` */

DROP TABLE IF EXISTS `t_ai_info`;

CREATE TABLE `t_ai_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `game_id` int(11) NOT NULL,
  `ai_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ai_code` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `introduce` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `isHide` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `t_bug_report` */

DROP TABLE IF EXISTS `t_bug_report`;

CREATE TABLE `t_bug_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `t_challenge_block` */

DROP TABLE IF EXISTS `t_challenge_block`;

CREATE TABLE `t_challenge_block` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gro` int(11) DEFAULT NULL COMMENT '0：基础；1：数据结构；2：数学；3：几何；4：图论；5：搜索；6：动态规划',
  `text` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `isEditing` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=613 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_challenge_condition` */

DROP TABLE IF EXISTS `t_challenge_condition`;

CREATE TABLE `t_challenge_condition` (
  `belongBlockId` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `par` int(11) DEFAULT NULL COMMENT '前置条件的模块ID',
  `num` int(11) DEFAULT NULL COMMENT '对应的前置条件所需的积分',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_challenge_openblock` */

DROP TABLE IF EXISTS `t_challenge_openblock`;

CREATE TABLE `t_challenge_openblock` (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `block` int(11) NOT NULL,
  PRIMARY KEY (`username`,`block`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_challenge_problem` */

DROP TABLE IF EXISTS `t_challenge_problem`;

CREATE TABLE `t_challenge_problem` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `tpid` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`,`pid`),
  UNIQUE KEY `tpid` (`tpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_clock_in` */

DROP TABLE IF EXISTS `t_clock_in`;

CREATE TABLE `t_clock_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `sign` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `todytimes` int(11) NOT NULL COMMENT '当天第几次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_discuss` */

DROP TABLE IF EXISTS `t_discuss`;

CREATE TABLE `t_discuss` (
  `id` int(11) NOT NULL,
  `cid` int(11) NOT NULL DEFAULT '-1',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `panelclass` int(11) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `text` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `priority` double NOT NULL,
  `top` tinyint(1) NOT NULL DEFAULT '0',
  `visiable` tinyint(1) NOT NULL DEFAULT '0',
  `reply` tinyint(1) NOT NULL DEFAULT '1',
  `shownum` int(11) NOT NULL DEFAULT '-1' COMMENT '-1 å…¨éƒ¨æ˜¾ç¤º',
  `panelnobody` tinyint(1) NOT NULL DEFAULT '0',
  `showauthor` tinyint(1) NOT NULL DEFAULT '1',
  `showtime` tinyint(1) NOT NULL DEFAULT '1',
  `replyhidden` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_discussreply` */

DROP TABLE IF EXISTS `t_discussreply`;

CREATE TABLE `t_discussreply` (
  `rid` int(11) NOT NULL,
  `did` int(11) NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `visiable` tinyint(1) NOT NULL,
  `panelclass` int(11) NOT NULL,
  `adminreplay` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  PRIMARY KEY (`rid`,`did`),
  KEY `did` (`did`),
  CONSTRAINT `t_discussreply_ibfk_1` FOREIGN KEY (`did`) REFERENCES `t_discuss` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_game_repetition` */

DROP TABLE IF EXISTS `t_game_repetition`;

CREATE TABLE `t_game_repetition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blackId` int(11) NOT NULL,
  `blackAuthor` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `whiteId` int(11) NOT NULL,
  `whiteAuthor` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `processes` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `win` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `t_group` */

DROP TABLE IF EXISTS `t_group`;

CREATE TABLE `t_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_group_member` */

DROP TABLE IF EXISTS `t_group_member`;

CREATE TABLE `t_group_member` (
  `group_id` int(11) NOT NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) NOT NULL,
  `join_time` datetime NOT NULL,
  PRIMARY KEY (`group_id`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_gv` */

DROP TABLE IF EXISTS `t_gv`;

CREATE TABLE `t_gv` (
  `key` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `value` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ipAddress` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `t_mall` */

DROP TABLE IF EXISTS `t_mall`;

CREATE TABLE `t_mall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `acb` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `des` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `isHidden` tinyint(4) NOT NULL,
  `user` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` time NOT NULL,
  `buyLimit` int(11) NOT NULL,
  `buyVerifyLimit` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `statu` int(11) NOT NULL DEFAULT '0' COMMENT '0未读1已读',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `deadline` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `goodsId` int(11) NOT NULL,
  `acb` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `isCancel` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `goodsid` (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_problem_sample` */

DROP TABLE IF EXISTS `t_problem_sample`;

CREATE TABLE `t_problem_sample` (
  `pid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `input` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `output` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pid`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_problem_tag` */

DROP TABLE IF EXISTS `t_problem_tag`;

CREATE TABLE `t_problem_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ttype` int(11) NOT NULL DEFAULT '0',
  `priority` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_problem_tag_record` */

DROP TABLE IF EXISTS `t_problem_tag_record`;

CREATE TABLE `t_problem_tag_record` (
  `pid` int(11) NOT NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `tagid` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`username`,`tagid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_problemview` */

DROP TABLE IF EXISTS `t_problemview`;

CREATE TABLE `t_problemview` (
  `pid` int(11) NOT NULL,
  `timelimit` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `MenoryLimit` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Int64` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `spj` int(11) NOT NULL,
  `Dis` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Input` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Output` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_rank_icpc` */

DROP TABLE IF EXISTS `t_rank_icpc`;

CREATE TABLE `t_rank_icpc` (
  `cid` int(11) NOT NULL,
  `penalty` int(11) NOT NULL COMMENT 'ç½šæ—¶(åˆ†é’Ÿ)',
  `mtype_1` int(11) NOT NULL,
  `m1` int(11) NOT NULL,
  `mtype_2` int(11) NOT NULL,
  `m2` int(11) NOT NULL,
  `mtype_3` int(11) NOT NULL,
  `m3` int(11) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_rank_shortcode` */

DROP TABLE IF EXISTS `t_rank_shortcode`;

CREATE TABLE `t_rank_shortcode` (
  `cid` int(11) NOT NULL,
  `mtype_1` int(11) NOT NULL,
  `m1` int(11) NOT NULL,
  `mtype_2` int(11) NOT NULL,
  `m2` int(11) NOT NULL,
  `mtype_3` int(11) NOT NULL,
  `m3` int(11) NOT NULL,
  `chengfa` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_rank_training` */

DROP TABLE IF EXISTS `t_rank_training`;

CREATE TABLE `t_rank_training` (
  `cid` int(11) NOT NULL,
  `mtype_1` int(11) NOT NULL,
  `m1` int(11) NOT NULL,
  `mtype_2` int(11) NOT NULL,
  `m2` int(11) NOT NULL,
  `mtype_3` int(11) NOT NULL,
  `m3` int(11) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_rating` */

DROP TABLE IF EXISTS `t_rating`;

CREATE TABLE `t_rating` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `cid` int(11) NOT NULL,
  `prating` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `ratingnum` int(11) NOT NULL,
  `rank` int(11) NOT NULL,
  PRIMARY KEY (`username`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_register_team` */

DROP TABLE IF EXISTS `t_register_team`;

CREATE TABLE `t_register_team` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cid` int(11) NOT NULL,
  `teamusername` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `teampassword` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `teamname` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `statu` int(11) NOT NULL,
  `info` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`username`,`cid`),
  KEY `cid` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `t_replyreply` */

DROP TABLE IF EXISTS `t_replyreply`;

CREATE TABLE `t_replyreply` (
  `did` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  `rrid` int(11) NOT NULL,
  `replyRid` int(11) NOT NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `visible` tinyint(4) NOT NULL,
  PRIMARY KEY (`did`,`rid`,`rrid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_some_opt_record` */

DROP TABLE IF EXISTS `t_some_opt_record`;

CREATE TABLE `t_some_opt_record` (
  `username` varchar(30) NOT NULL,
  `time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `type` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `data` varchar(100) NOT NULL,
  PRIMARY KEY (`username`,`time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `t_star` */

DROP TABLE IF EXISTS `t_star`;

CREATE TABLE `t_star` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `star_id` int(11) NOT NULL,
  `text` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `t_team_member_info` */

DROP TABLE IF EXISTS `t_team_member_info`;

CREATE TABLE `t_team_member_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` date NOT NULL,
  `username1` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username2` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username3` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name1` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name2` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name3` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `contest_level` int(11) NOT NULL,
  `awards_level` int(11) NOT NULL,
  `text` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_title` */

DROP TABLE IF EXISTS `t_title`;

CREATE TABLE `t_title` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `id` int(11) NOT NULL,
  `jd` int(11) NOT NULL DEFAULT '0',
  `endtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_title_config` */

DROP TABLE IF EXISTS `t_title_config`;

CREATE TABLE `t_title_config` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `isShow` bit(1) NOT NULL DEFAULT b'0',
  `config` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `adj` int(11) NOT NULL DEFAULT '-1',
  `n` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cid` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `school` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `faculty` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `cla` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`,`cid`,`id`),
  KEY `cid` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `t_usersolve` */

DROP TABLE IF EXISTS `t_usersolve`;

CREATE TABLE `t_usersolve` (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pid` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`username`,`pid`),
  KEY `suoying` (`pid`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_verify` */

DROP TABLE IF EXISTS `t_verify`;

CREATE TABLE `t_verify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `VerifyType` int(11) DEFAULT NULL COMMENT '0：申请修改资料；1：申请为ACM集训队员；2：申请ACM退役；3：认证为中世竞创协会成员；4：认证为FJUT在校学生；5：认证为其他学校学生',
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `school` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '0：未知；1:男；2：女',
  `faculty` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `major` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cla` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `result` int(11) NOT NULL,
  `reason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `graduationTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `t_viewcode` */

DROP TABLE IF EXISTS `t_viewcode`;

CREATE TABLE `t_viewcode` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pid` int(11) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`,`pid`,`type`),
  KEY `pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_vote` */

DROP TABLE IF EXISTS `t_vote`;

CREATE TABLE `t_vote` (
  `did` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `isHide` int(11) NOT NULL,
  `isDisable` int(11) NOT NULL,
  `des` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`did`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `userper` */

DROP TABLE IF EXISTS `userper`;

CREATE TABLE `userper` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `perid` int(11) NOT NULL COMMENT '1：题目总管；2：查看代码；3：重判；4：新增比赛；5：计算rating；6：新增讨论；7：新增标签；\r\n    8：签到管理；9：权限管理；10：奖励ACB；11：审核比赛报名；12：增加本地题目；13：挑战模式管理；\r\n    14：密码重置；15：用户管理；16：查看log；17：考试管理；18：集训队员管理；19：商城管理；\r\n    20：APP更新；21：认证管理（全部）；22：认证管理（校内人员）；23：认证管理（协会成员）；\r\n    24：认证管理（退役人员）；25：认证管理（集训队员）；26：集训队员自动报名；27：添加题目；\r\n    28：称号管理；29：组队管理',
  PRIMARY KEY (`username`,`perid`),
  CONSTRAINT `userper_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(11) NOT NULL,
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `motto` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `registertime` datetime NOT NULL,
  `type` int(11) NOT NULL,
  `Mark` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '-100000',
  `ratingnum` int(11) NOT NULL DEFAULT '0',
  `acb` int(11) NOT NULL DEFAULT '0',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `faculty` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cla` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `acnum` int(11) NOT NULL DEFAULT '0',
  `inTeamStatus` int(11) NOT NULL COMMENT '队员状态 0非队员 1现役队员 2退役队员',
  `inTeamLv` int(11) NOT NULL COMMENT '队员等级',
  `rank` int(11) DEFAULT NULL,
  `graduationTime` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `usersolve_view` */

DROP TABLE IF EXISTS `usersolve_view`;

/*!50001 DROP VIEW IF EXISTS `usersolve_view` */;
/*!50001 DROP TABLE IF EXISTS `usersolve_view` */;

/*!50001 CREATE TABLE `usersolve_view` (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pid` int(11) NOT NULL,
  `solved` bigint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*Table structure for table `v_contestuser` */

DROP TABLE IF EXISTS `v_contestuser`;

/*!50001 DROP VIEW IF EXISTS `v_contestuser` */;
/*!50001 DROP TABLE IF EXISTS `v_contestuser` */;

/*!50001 CREATE TABLE `v_contestuser` (
  `cid` int(11) NOT NULL,
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `rating` int(11) DEFAULT NULL,
  `ratingnum` int(11) DEFAULT NULL,
  `statu` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `gender` int(11),
  `faculty` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `cla` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `info` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*Table structure for table `v_problem` */

DROP TABLE IF EXISTS `v_problem`;

/*!50001 DROP VIEW IF EXISTS `v_problem` */;
/*!50001 DROP TABLE IF EXISTS `v_problem` */;

/*!50001 CREATE TABLE `v_problem` (
  `pid` int(11) NOT NULL,
  `ptype` int(11) NOT NULL,
  `title` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ojid` int(11) NOT NULL,
  `ojspid` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `visiable` int(11) NOT NULL DEFAULT '0',
  `acusernum` bigint(21) DEFAULT NULL,
  `submitnum` bigint(21) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*Table structure for table `v_problem_tag` */

DROP TABLE IF EXISTS `v_problem_tag`;

/*!50001 DROP VIEW IF EXISTS `v_problem_tag` */;
/*!50001 DROP TABLE IF EXISTS `v_problem_tag` */;

/*!50001 CREATE TABLE `v_problem_tag` (
  `pid` int(11) NOT NULL,
  `tagid` int(11) NOT NULL,
  `rating` decimal(33,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*Table structure for table `v_solved` */

DROP TABLE IF EXISTS `v_solved`;

/*!50001 DROP VIEW IF EXISTS `v_solved` */;
/*!50001 DROP TABLE IF EXISTS `v_solved` */;

/*!50001 CREATE TABLE `v_solved` (
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `acnum` decimal(25,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*Table structure for table `v_user_` */

DROP TABLE IF EXISTS `v_user_`;

/*!50001 DROP VIEW IF EXISTS `v_user_` */;
/*!50001 DROP TABLE IF EXISTS `v_user_` */;

/*!50001 CREATE TABLE `v_user_` (
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(11) NOT NULL,
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `motto` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `registertime` datetime NOT NULL,
  `type` int(11) NOT NULL,
  `Mark` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `rating` int(11) NOT NULL DEFAULT '0',
  `showrating` double(17,0) DEFAULT NULL,
  `ratingnum` int(11) NOT NULL DEFAULT '0',
  `acnum` int(11) NOT NULL DEFAULT '0',
  `acb` int(11) NOT NULL DEFAULT '0',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `faculty` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `major` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `cla` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `no` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `inTeamLv` int(11) NOT NULL COMMENT '队员等级',
  `inTeamStatus` int(11) NOT NULL COMMENT '队员状态 0非队员 1现役队员 2退役队员'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*Table structure for table `v_user_status` */

DROP TABLE IF EXISTS `v_user_status`;

/*!50001 DROP VIEW IF EXISTS `v_user_status` */;
/*!50001 DROP TABLE IF EXISTS `v_user_status` */;

/*!50001 CREATE TABLE `v_user_status` (
  `id` int(11) NOT NULL,
  `nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ruser` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `pid` int(11) NOT NULL,
  `lang` int(11) NOT NULL,
  `submitTime` datetime NOT NULL,
  `result` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `timeUsed` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `memoryUsed` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `codelen` int(11) NOT NULL,
  `code` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin */;

/*View structure for view usersolve_view */

/*!50001 DROP TABLE IF EXISTS `usersolve_view` */;
/*!50001 DROP VIEW IF EXISTS `usersolve_view` */;

/*!50001 CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `usersolve_view` AS select `statu`.`ruser` AS `username`,`statu`.`pid` AS `pid`,max((`statu`.`result` = 1)) AS `solved` from `statu` group by `statu`.`ruser`,`statu`.`pid` */;

/*View structure for view v_contestuser */

/*!50001 DROP TABLE IF EXISTS `v_contestuser` */;
/*!50001 DROP VIEW IF EXISTS `v_contestuser` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_contestuser` AS select `contestuser`.`cid` AS `cid`,`contestuser`.`username` AS `username`,`users`.`nick` AS `nick`,`users`.`rating` AS `rating`,`users`.`ratingnum` AS `ratingnum`,`contestuser`.`statu` AS `statu`,`contestuser`.`time` AS `time`,`users`.`name` AS `name`,`users`.`gender` AS `gender`,`users`.`faculty` AS `faculty`,`users`.`major` AS `major`,`users`.`cla` AS `cla`,`users`.`no` AS `no`,`users`.`phone` AS `phone`,`contestuser`.`info` AS `info` from (`contestuser` left join `users` on((`contestuser`.`username` = `users`.`username`))) */;

/*View structure for view v_problem */

/*!50001 DROP TABLE IF EXISTS `v_problem` */;
/*!50001 DROP VIEW IF EXISTS `v_problem` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_problem` AS select `problem`.`pid` AS `pid`,`problem`.`ptype` AS `ptype`,`problem`.`title` AS `title`,`problem`.`ojid` AS `ojid`,`problem`.`ojspid` AS `ojspid`,`problem`.`visiable` AS `visiable`,(select count(`usersolve_view`.`username`) AS `COUNT(username)` from `usersolve_view` where ((`usersolve_view`.`pid` = `problem`.`pid`) and (`usersolve_view`.`solved` = 1))) AS `acusernum`,(select count(`statu`.`id`) AS `COUNT(id)` from `statu` where (`statu`.`pid` = `problem`.`pid`)) AS `submitnum` from `problem` */;

/*View structure for view v_problem_tag */

/*!50001 DROP TABLE IF EXISTS `v_problem_tag` */;
/*!50001 DROP VIEW IF EXISTS `v_problem_tag` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_problem_tag` AS select `t_problem_tag_record`.`pid` AS `pid`,`t_problem_tag_record`.`tagid` AS `tagid`,sum((`t_problem_tag_record`.`rating` - 500)) AS `rating` from `t_problem_tag_record` group by `t_problem_tag_record`.`pid`,`t_problem_tag_record`.`tagid` */;

/*View structure for view v_solved */

/*!50001 DROP TABLE IF EXISTS `v_solved` */;
/*!50001 DROP VIEW IF EXISTS `v_solved` */;

/*!50001 CREATE ALGORITHM=TEMPTABLE DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_solved` AS select `t_usersolve`.`username` AS `username`,sum(`t_usersolve`.`status`) AS `acnum` from `t_usersolve` group by `t_usersolve`.`username` */;

/*View structure for view v_user_ */

/*!50001 DROP TABLE IF EXISTS `v_user_` */;
/*!50001 DROP VIEW IF EXISTS `v_user_` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_` AS select `a`.`username` AS `username`,`a`.`password` AS `password`,`a`.`nick` AS `nick`,`a`.`gender` AS `gender`,`a`.`school` AS `school`,`a`.`Email` AS `Email`,`a`.`motto` AS `motto`,`a`.`registertime` AS `registertime`,`a`.`type` AS `type`,`a`.`Mark` AS `Mark`,`a`.`rating` AS `rating`,round(((((`a`.`rating` - 700) * (1 - pow(0.6,`a`.`ratingnum`))) + 700) + 0.5),0) AS `showrating`,`a`.`ratingnum` AS `ratingnum`,`a`.`acnum` AS `acnum`,`a`.`acb` AS `acb`,`a`.`name` AS `name`,`a`.`faculty` AS `faculty`,`a`.`major` AS `major`,`a`.`cla` AS `cla`,`a`.`no` AS `no`,`a`.`phone` AS `phone`,`a`.`inTeamLv` AS `inTeamLv`,`a`.`inTeamStatus` AS `inTeamStatus` from `users` `a` */;

/*View structure for view v_user_status */

/*!50001 DROP TABLE IF EXISTS `v_user_status` */;
/*!50001 DROP VIEW IF EXISTS `v_user_status` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_user_status` AS select `statu`.`id` AS `id`,`users`.`nick` AS `nick`,`statu`.`ruser` AS `ruser`,`statu`.`pid` AS `pid`,`statu`.`lang` AS `lang`,`statu`.`submitTime` AS `submitTime`,`statu`.`result` AS `result`,`statu`.`score` AS `score`,`statu`.`timeUsed` AS `timeUsed`,`statu`.`memoryUsed` AS `memoryUsed`,`statu`.`codelen` AS `codelen`,`statu`.`code` AS `code` from (`statu` join `users`) where (`statu`.`ruser` = `users`.`username`) order by `statu`.`id` desc */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
