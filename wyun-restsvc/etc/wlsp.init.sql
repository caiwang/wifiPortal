## Tables for user registration
## and authentication
## used by
## local server and (or) central server


SET FOREIGN_KEY_CHECKS=0;
## ----------------------------
## Table structure for authclient
## ----------------------------
DROP TABLE IF EXISTS `authclient`;
CREATE TABLE `authclient` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `cid` varchar(30) DEFAULT NULL,
  `ctype` varchar(10) DEFAULT NULL,
  `stat` varchar(3) DEFAULT '0',
  `phone` varchar(30) DEFAULT NULL,
  `sphone` varchar(30) DEFAULT NULL,
  `msg` varchar(8) DEFAULT NULL,
  `plan` varchar(20) DEFAULT NULL,
  `question` varchar(30) DEFAULT NULL,
  `answer` varchar(30) DEFAULT NULL,
  `token` int(10) DEFAULT NULL,
  `mac` varchar(18) DEFAULT NULL,
  `img` varchar(128) DEFAULT '',
  `imgchk1` smallint(6) DEFAULT '0',
  `imgchk2` smallint(6) DEFAULT '0',
  `imgchk3` smallint(6) DEFAULT '0',
  `manstat` smallint(3) DEFAULT '0',
  `manchker` varchar(16) DEFAULT NULL,
  `manid` varchar(30) DEFAULT NULL,
  `mantype` varchar(10) DEFAULT NULL,
  `mantime` datetime DEFAULT NULL,
  `optflag` tinyint(2) DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `srcname` varchar(64) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `phone` (`phone`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for authmac
## ----------------------------
DROP TABLE IF EXISTS `authmac`;
CREATE TABLE `authmac` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `mac` varchar(36) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `stat` smallint(6) DEFAULT NULL,
  `fromtime` datetime DEFAULT NULL,
  `lasting` int(20) DEFAULT NULL,
  `pushflag` smallint(6) DEFAULT NULL,
  `pushurl` varchar(127) DEFAULT NULL,
  `pushtime` int(10) DEFAULT NULL,
  `cid` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `token` int(10) DEFAULT NULL,
  `base` varchar(10) DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `srcname` varchar(64) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mac` (`mac`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for authsms
## ----------------------------
DROP TABLE IF EXISTS `authsms`;
CREATE TABLE `authsms` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `srcid` BIGINT DEFAULT NULL,
  `prefix` varchar(60) DEFAULT '',
  `sms` varchar(10) DEFAULT NULL,
  `postfix` varchar(30) DEFAULT '',
  `mac` varchar(36) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `phone` varchar(30) DEFAULT '',
  `stat` smallint(3) DEFAULT '0',
  `optflag` smallint(3) DEFAULT '0',
  `msgtype` varchar(36) DEFAULT NULL,
  `token` int(10) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for authblkmac
## table for local server to keep a mac list to kick off
## ----------------------------
DROP TABLE IF EXISTS `authblkmac`;
CREATE TABLE `authblkmac` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `mac` varchar(20) DEFAULT NULL,
  `blktime` varchar(6) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for authmacip
## table for local server to keep a record of each visit
## ----------------------------
DROP TABLE IF EXISTS `authmacip`;
CREATE TABLE `authmacip` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `mac` varchar(36) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `called` varchar(36) DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `procid` varchar(36) DEFAULT NULL,
  `userurl` varchar(1024) DEFAULT NULL,
  `orgurl` varchar(1024) DEFAULT NULL,
  `token` int(20) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mac` (`mac`),
  KEY `ip` (`ip`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for authnlist
## table for central server side to keep a record
## of who can call the webservice
## ----------------------------
DROP TABLE IF EXISTS `authnlist`;
CREATE TABLE `authnlist` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `mac` varchar(17) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `passwd` varchar(64) DEFAULT NULL,
  `secret` varchar(64) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

## ----------------------------
## Records of authnlist
## ----------------------------
INSERT INTO `authnlist` VALUES ('1', null, null, '192.168.1.252', 'zgbdh001', '1234567890', 'ab', '2013-05-12 10:23:25');



##
## Tables for network visit and action recording (wlcap version)
##
##

## ----------------------------
## Table structure for actvst
## ----------------------------
DROP TABLE IF EXISTS `actvst`;
CREATE TABLE `actvst` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `pkttime` datetime DEFAULT NULL,
  `timefrac` float DEFAULT NULL,
  `srcmac` varchar(64) DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `destip` varchar(64) DEFAULT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for actvrf
## ----------------------------
DROP TABLE IF EXISTS `actvrf`;
CREATE TABLE `actvrf` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `level` varchar(16) DEFAULT NULL,
  `dname` varchar(36) DEFAULT NULL,
  `mlen` int(8) DEFAULT NULL,
  `ltime` int(8) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  `rectime` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `level` (`level`),
  KEY `dname` (`dname`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

## ----------------------------
## Records of actvrf
## ----------------------------
INSERT INTO `actvrf` VALUES ('1', null, '0001.0000.0000', 'www.baidu.com', '0', '0', '1', '2013-03-13 14:05:43', '20');
INSERT INTO `actvrf` VALUES ('2', null, '0002.0000.0000', 'www.google.com', '0', '0', '1', '2013-03-13 14:13:33', '20');
INSERT INTO `actvrf` VALUES ('3', null, '0001.0001.0000', 'im.baidu.com', '30', '45', '1', '2013-03-13 14:17:49', '20');
INSERT INTO `actvrf` VALUES ('4', null, '3000.0001.0000', '360.cn', '30', '1200', '1', '2013-03-13 14:18:19', '20');
INSERT INTO `actvrf` VALUES ('5', null, '9999.9999.9999', 'default', '20', '120', '1', '2013-03-13 14:19:00', '20');
INSERT INTO `actvrf` VALUES ('6', null, '3000.0002.0000', 'sogou.com', '20', '600', '1', '2013-03-13 14:29:41', '20');
INSERT INTO `actvrf` VALUES ('7', null, '9999.8001.0000', '.jpg', '4', '300', '1', '2013-03-13 15:29:11', '10');
INSERT INTO `actvrf` VALUES ('8', null, '9999.8002.0000', '.png', '4', '300', '1', '2013-03-13 15:29:11', '10');
INSERT INTO `actvrf` VALUES ('9', null, '9999.8003.0000', '.gif', '4', '300', '1', '2013-03-13 15:29:11', '10');
INSERT INTO `actvrf` VALUES ('10', null, '9999.8004.0000', '.js', '4', '300', '1', '2013-03-13 15:29:11', '10');
INSERT INTO `actvrf` VALUES ('11', null, '9999.8005.0000', '.swf', '4', '300', '1', '2013-03-14 09:27:18', '10');


##
## Tables for url push
##
-- ----------------------------
-- Table structure for pushrurl
-- ----------------------------
DROP TABLE IF EXISTS `pushrurl`;
CREATE TABLE `pushrurl` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `prio` int(4) DEFAULT '0',
  `srcip` varchar(64) DEFAULT NULL,
  `type` int(4) DEFAULT '1',
  `cnd` varchar(16) DEFAULT NULL,
  `ftime` datetime DEFAULT NULL,
  `dura` int(4) DEFAULT '3',
  `rurl` varchar(128) DEFAULT NULL,
  `rurltoken` varchar(32) DEFAULT '',
  `active` tinyint(4) DEFAULT '1',
  `rectime` datetime DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `level` (`cnd`),
  KEY `dname` (`srcip`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pushrurl
-- ----------------------------


INSERT INTO `pushrurl` VALUES ('1', null, '1','default', '10', '', '2013-08-26 15:00:00', '20', 
'http://172.16.0.1/push/push1.html','172.16.0.1', '1', '2013-08-26 15:00:00',null,null,null,null);

INSERT INTO `pushrurl` VALUES ('2', null, '0','172.16.0.100', '10', null, '2013-08-26 15:00:00', 
'40', 'http://172.16.0.1/push/push2.html','172.16.0.1', '1', '2013-08-26 15:00:00',null,null,null,null);




##
## Tables, Views and Triggers
## for wireless packet sniffer
##


## ----------------------------
## Table structure for wlact
## ----------------------------
DROP TABLE IF EXISTS `wlact`;
CREATE TABLE `wlact` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `event` varchar(64) DEFAULT NULL,
  `mac` varchar(36) DEFAULT NULL,
  `subevent` varchar(64) DEFAULT NULL,
  `oldvalue` varchar(64) DEFAULT NULL,
  `newvalue` varchar(64) DEFAULT NULL,
  `firstseen` datetime DEFAULT NULL,
  `lastseen` datetime DEFAULT NULL,
  `stat` smallint(6) DEFAULT NULL,
  `ssid` varchar(36) DEFAULT NULL,
  `action` smallint(6) DEFAULT NULL,
  `tcount` int(10) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mac` (`mac`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for wlpkt
## ----------------------------
DROP TABLE IF EXISTS `wlpkt`;
CREATE TABLE `wlpkt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `mac` varchar(36) DEFAULT NULL,
  `ssid` varchar(36) DEFAULT NULL,
  `rssi` smallint(6) DEFAULT NULL,
  `stat` tinyint(4) DEFAULT NULL,
  `type` varchar(36) DEFAULT NULL,
  `subtype` varchar(36) DEFAULT NULL,
  `pmac` varchar(36) DEFAULT NULL,
  `bssid` varchar(36) DEFAULT NULL,
  `pkttime` datetime DEFAULT NULL,
  `timefrac` float DEFAULT NULL,
  `timebyminute` datetime DEFAULT NULL,
  `timesecond` tinyint(4) DEFAULT NULL,
  `timebyhour` datetime DEFAULT NULL,
  `timeminute` tinyint(4) DEFAULT NULL,
  `frameproto` varchar(36) DEFAULT NULL,
  `chan` varchar(36) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `packettime` (`pkttime`),
  KEY `mac` (`mac`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


## ----------------------------
## Table structure for wlsta
## ----------------------------
DROP TABLE IF EXISTS `wlsta`;
CREATE TABLE `wlsta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `srcid` int DEFAULT NULL,
  `tcount` int(10) DEFAULT NULL,
  `mac` varchar(36) DEFAULT NULL,
  `ssid` varchar(36) DEFAULT NULL,
  `rssi` float DEFAULT NULL,
  `stat` smallint(6) DEFAULT NULL,
  `setby` varchar(20) DEFAULT NULL,
  `keepalive` tinyint(4) DEFAULT NULL,
  `firstseen` datetime DEFAULT NULL,
  `lastseen` datetime DEFAULT NULL,
  `rtrend` smallint(6) DEFAULT NULL,
  `npacket` int(6) DEFAULT NULL,
  `ptrend` smallint(6) DEFAULT NULL,
  `action` smallint(6) DEFAULT NULL,
  `ostype` varchar(36) DEFAULT NULL,
  `alivetime` int(10) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL, 
  `netid` varchar(36) DEFAULT NULL, 
  `progid` varchar(36) DEFAULT NULL, 
  `optime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mac` (`mac`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

## ----------------------------
## Table structure for useraccounts
## ----------------------------
DROP TABLE IF EXISTS `useraccounts`;	#	用户主记录
CREATE TABLE `useraccounts` (	#	
  `id` bigint NOT NULL AUTO_INCREMENT,	#	
  `userid` varchar(30) DEFAULT NULL,	#	为user分配一个id，默认等于上列id
  `srcid` int(11) DEFAULT NULL,	#	iserver字段
  `token` int(10) DEFAULT NULL,	#	8位随机数，由ihost产生
  `srcnode` varchar(10) DEFAULT NULL,	#	（预留）
  `usercode` varchar(30) DEFAULT NULL,	#	用户编码（预留）
  `mac` varchar(36) DEFAULT NULL,	#	mac地址
  `user_uuid` varchar(36) DEFAULT NULL,
  `userpass` varchar(30) DEFAULT NULL,	#	用户密码
  `useremail1` varchar(64) DEFAULT NULL,	#	用户email
  `useremail2` varchar(64) DEFAULT NULL,	#	用户备用email
  `question` varchar(30) DEFAULT NULL,	#	密码提示问题
  `answer` varchar(30) DEFAULT NULL,	#	密码答案，用于找回密码
  `fname` varchar(20) DEFAULT NULL,	#	名字
  `lname` varchar(20) DEFAULT NULL,	#	姓
   `userrole` varchar(20) DEFAULT NULL,
  `usertype` varchar(10) DEFAULT NULL,	#	用户类型：听众，专家，会务（microma用）
  `integral` int(10) DEFAULT NULL,	#	userid下的积分
  `byear` smallint(3) DEFAULT NULL,	#	生日，年
  `bmonth` smallint(3) DEFAULT NULL,	#	生日，月
  `bday` smallint(3) DEFAULT NULL,	#	生日，日
  `gender` varchar(8) DEFAULT NULL,	#	性别
  `occup` varchar(30) DEFAULT NULL,	#	职业
  `orgn` varchar(64) DEFAULT NULL,	#	工作单位
  `pntfactor` int(11) DEFAULT '1000',
  `title` varchar(32) DEFAULT NULL,	#	职务
  `cid` varchar(30) DEFAULT NULL,	#	证件号
  `ctype` varchar(10) DEFAULT NULL,	#	证件类别
  `captcha` varchar(10) DEFAULT NULL,
  `regphone` varchar(30) DEFAULT NULL,	#	（预）注册所用的电话号码
  `smscheck` varchar(3) DEFAULT '100',
  `phone` varchar(30) DEFAULT NULL,	#	常用电话号码
  `pushflag` smallint(5) unsigned DEFAULT '1',
  `address` varchar(128) DEFAULT NULL,	#	地址
  `location` varchar(32) DEFAULT NULL,	#	所在区域
  `action` varchar(128) DEFAULT NULL,	#	活动（预留）
  `stat` varchar(3) DEFAULT '100',	#	数据状态 100-有效 
  `open1` varchar(3) DEFAULT '100',	#	数据对招聘者公开，100-公开，0-不公开
  `open2` varchar(3) DEFAULT '100',	#	数据对求职者公开，100-公开，0-不公开
  `check` varchar(3) DEFAULT '100',	#	短信验证，100-验证，0-不验证
  `memo` varchar(128) DEFAULT NULL,	#	备注
  `srcip` varchar(64) DEFAULT NULL,	#	iserver字段
  `sender` varchar(36) DEFAULT NULL, 	#	iserver字段
  `netid` varchar(36) DEFAULT NULL, 	#	iserver字段
  `progid` varchar(36) DEFAULT NULL, 	#	iserver字段
  `intid` varchar(30) DEFAULT NULL,	#	与phpyun的对应关系（记录学历等其他个人信息）
  `updtime` datetime DEFAULT NULL,	#	记录更新时间
  `rectime` datetime DEFAULT NULL,	#	记录时间
  PRIMARY KEY (`id`),	#	
  KEY `userid` (`userid`),	#	
  KEY `phone` (`phone`)	#	
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;	#	
	#	
	#	
DROP TABLE IF EXISTS `usermacs`;	#	记录useraccount 与 终端、电话号码的多对多关系
CREATE TABLE `usermacs` (	#	
  `id` bigint NOT NULL AUTO_INCREMENT,	#	
  `userid` varchar(30) DEFAULT NULL,	#	mac对应的userid，多对多的关系
  `srcid` int(11) DEFAULT NULL,	#	iserver字段
  `token` int(10) DEFAULT NULL,	#	8位随机数，由ihost产生
  `srcnode` varchar(10) DEFAULT NULL,	#	（预留）
  `usercode` varchar(30) DEFAULT NULL,	#	用户编码（预留）
  `mac` varchar(36) DEFAULT NULL,	#	用户的mac地址（一个mac可以对多用户，一个用户可以有多mac）
  `phone` varchar(30) DEFAULT NULL,	#	用户提供的手机号（与用户多对多关系）
  `pushflag` smallint(5) unsigned DEFAULT '1',
  `stat` varchar(3) DEFAULT '100',	#	数据状态 100-有效 
  `dft` varchar(3) DEFAULT NULL,	#	100-此记录的mac-userid是默认值，一个mac一个userrole下同时只有一个默认userid
  `prio` varchar(3) DEFAULT NULL,	#	多个mac-userrole-userid的排序优先级；大于0的最大值可用于自动签到
  `userrole` varchar(30) DEFAULT NULL,	#	不同角色，每个mac在每个userrole中有一个default userid
  `pntmaster` varchar(3) DEFAULT NULL,	#	积分主记录标识，100-此userid为主记录，一个mac只对一个userid积分
  `memo` varchar(128) DEFAULT NULL,	#	备注
  `srcip` varchar(64) DEFAULT NULL,	#	iserver字段
  `sender` varchar(36) DEFAULT NULL, 	#	iserver字段
  `netid` varchar(36) DEFAULT NULL, 	#	iserver字段
  `progid` varchar(36) DEFAULT NULL, 	#	iserver字段
  `updtime` datetime DEFAULT NULL,	#	记录更新时间
  `rectime` datetime DEFAULT NULL,	#	记录时间
  PRIMARY KEY (`id`),	#	
  KEY `userid` (`userid`),	#	
  KEY `usercode` (`usercode`)	#	
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;	#	

## ----------------------------
## Table structure for ihostloc
## ----------------------------
DROP TABLE IF EXISTS `ihostloc`;
CREATE TABLE `ihostloc` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `mac` varchar(36) DEFAULT NULL, #mac address of eth0 on the ihost
  `priip` varchar(64) DEFAULT NULL, #private ip addr of eth0
  `pubip` varchar(64) DEFAULT NULL, #public ip addr retrived form request url
  `rpttype` enum('start', 'end', 'mid') not NULL default 'start', #status of this report:start;end;mid; of work
  `apptype` enum('ma', 'hr', 'ms', 'other') not NULL default 'other', #ma-meeting affair;hr-human resource;ms-mobile station
  `dbinput` varchar(128) DEFAULT NULL, #db file imported into ihost at the begining (github url)
  `dboutput` varchar(128) DEFAULT NULL, #db file dumped from ihost in the end (github url)
  `city` varchar(64) DEFAULT NULL, #city name
  `location` varchar(64) DEFAULT NULL, #street name or building name
  `company` varchar(64) DEFAULT NULL, #company name
  `owner` varchar(64) DEFAULT NULL, #the owner of the ihost
  `latitude` varchar(36) DEFAULT NULL,
  `longitude` varchar(36) DEFAULT NULL,
  `admin` varchar(36) DEFAULT NULL, #administrator
  `phone` varchar(64) DEFAULT NULL, #phone number of the administrator
  `wechatid` varchar(64) DEFAULT NULL,
  `memo` varchar(128) DEFAULT NULL,
  `progid` varchar(36) DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mac` (`mac`)   #mac address of the ihost
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `prodorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodorder` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userid` varchar(36) DEFAULT NULL,
  `username` varchar(36) DEFAULT NULL,
  `srcid` int(11) DEFAULT NULL,
  `prodcode` varchar(10) DEFAULT NULL,
  `prodname` varchar(36) DEFAULT NULL,
  `prodtype` varchar(36) DEFAULT NULL,
  `prodspec` varchar(36) DEFAULT NULL,
  `proddesp` varchar(36) DEFAULT NULL,
  `quan` decimal(10,2) DEFAULT NULL,
  `unit` varchar(36) DEFAULT NULL,
  `pkg` varchar(36) DEFAULT NULL,
  `recipaddr` varchar(128) DEFAULT NULL,
  `recipname` varchar(36) DEFAULT NULL,
  `recipphone1` varchar(30) NOT NULL,
  `recipphone2` varchar(30) DEFAULT NULL,
  `recipemail` varchar(64) DEFAULT NULL,
  `assignto` varchar(64) DEFAULT 'iserver',
  `delicode` varchar(36) DEFAULT NULL,
  `delidesp` varchar(128) DEFAULT NULL,
  `delimemo` varchar(128) DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL,
  `netid` varchar(36) DEFAULT NULL,
  `progid` varchar(36) DEFAULT NULL,
  `updtime` datetime DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `pushflag` smallint(5) unsigned DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `delicode` (`delicode`),
  KEY `userid` (`userid`),
  KEY `prodcode` (`prodcode`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `useractive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useractive` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `srcid` int(11) DEFAULT NULL,
  `mac` varchar(36) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `userrole` varchar(30) DEFAULT NULL,
  `userid` varchar(36) DEFAULT NULL,
  `onsite` tinyint(4) DEFAULT '0',
  `online` tinyint(4) DEFAULT '0',
  `macfirst` datetime DEFAULT '1970-01-01 00:00:00',
  `macmark` datetime DEFAULT NULL,
  `maclast` datetime DEFAULT '1970-01-01 00:00:00',
  `pagefirst` datetime DEFAULT '1970-01-01 00:00:00',
  `pagemark` datetime DEFAULT NULL,
  `pagelast` datetime DEFAULT '1970-01-01 00:00:00',
  `updby` varchar(128) DEFAULT NULL,
  `insby` varchar(128) DEFAULT NULL,
  `srcip` varchar(64) DEFAULT NULL,
  `sender` varchar(36) DEFAULT NULL,
  `netid` varchar(36) DEFAULT NULL,
  `progid` varchar(36) DEFAULT NULL,
  `updtime` datetime DEFAULT NULL,
  `rectime` datetime DEFAULT NULL,
  `pushflag` smallint(5) unsigned DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `userid` (`mac`),
  KEY `mac` (`mac`)
) ENGINE=MyISAM AUTO_INCREMENT=12383 DEFAULT CHARSET=utf8;

drop table if exists video;
create table video(
  id BIGINT(20) not null auto_increment, 
  name varchar(128) not null, 
  url varchar(250) not null, 
  duration BIGINT(20),
  primary key(id)
);