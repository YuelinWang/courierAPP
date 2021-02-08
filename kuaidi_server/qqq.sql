/*
SQLyog v10.2 
MySQL - 5.5.28 : Database - kuaidi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kuaidi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kuaidi`;

/*Table structure for table `all_type` */

DROP TABLE IF EXISTS `all_type`;

CREATE TABLE `all_type` (
  `long_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bytes_binary` binary(1) DEFAULT NULL,
  `bytes_blob` blob,
  `bytes_mediumblob` mediumblob,
  `bytes_varbinary` varbinary(200) DEFAULT NULL,
  `bytes_tinyblob` tinyblob,
  `bytes_longblob` longblob,
  `boolean_bit` bit(1) DEFAULT NULL,
  `boolean_bool` tinyint(1) DEFAULT NULL,
  `boolean_boolean` tinyint(1) DEFAULT NULL,
  `string_enum` enum('1','2') DEFAULT NULL,
  `string_char` char(1) DEFAULT NULL,
  `string_longtext` longtext,
  `string_mediumtext` mediumtext,
  `string_varchar` varchar(200) DEFAULT NULL,
  `string_set` set('a','b') DEFAULT NULL,
  `string_tinytext` tinytext,
  `string_text` text,
  `byte_tinyint` tinyint(4) DEFAULT NULL,
  `short_smallint` smallint(6) DEFAULT NULL,
  `integer_int` int(11) DEFAULT NULL,
  `integer_mediumint` mediumint(9) DEFAULT NULL,
  `float_float` float DEFAULT NULL,
  `double_double` double DEFAULT NULL,
  `double_real` double DEFAULT NULL,
  `bigdecimal_decimal` decimal(10,0) DEFAULT NULL,
  `bigdecimal_numeric` decimal(10,0) DEFAULT NULL,
  `date_date` date DEFAULT NULL,
  `date_year` year(4) DEFAULT NULL,
  `time_time` time DEFAULT NULL,
  `timestemp_datetime` datetime DEFAULT NULL,
  `timestemp_timestemp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`long_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `all_type` */

insert  into `all_type`(`long_id`,`bytes_binary`,`bytes_blob`,`bytes_mediumblob`,`bytes_varbinary`,`bytes_tinyblob`,`bytes_longblob`,`boolean_bit`,`boolean_bool`,`boolean_boolean`,`string_enum`,`string_char`,`string_longtext`,`string_mediumtext`,`string_varchar`,`string_set`,`string_tinytext`,`string_text`,`byte_tinyint`,`short_smallint`,`integer_int`,`integer_mediumint`,`float_float`,`double_double`,`double_real`,`bigdecimal_decimal`,`bigdecimal_numeric`,`date_date`,`date_year`,`time_time`,`timestemp_datetime`,`timestemp_timestemp`) values (1,'','','','','','','',1,1,'1','1','1','1','1','a','1','1',1,1,1,1,1,1,1,'1','1','2018-08-30',2018,'09:59:26','2018-08-29 09:42:48','2018-08-29 09:59:28');

/*Table structure for table `dict` */

DROP TABLE IF EXISTS `dict`;

CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_category` varchar(50) NOT NULL DEFAULT '0' COMMENT '字典大类',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '编码',
  `name` varchar(300) NOT NULL DEFAULT '' COMMENT '名称',
  `dict_description` longtext COMMENT '描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '所属父类',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `order_no` tinyint(3) DEFAULT '0' COMMENT '序号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=334 DEFAULT CHARSET=utf8mb4 COMMENT='字典';

/*Data for the table `dict` */

insert  into `dict`(`id`,`dict_category`,`code`,`name`,`dict_description`,`parent_id`,`create_time`,`update_time`,`order_no`) values (333,'系统配置','a-sys_config','系统配置','',NULL,'2018-09-10 09:24:42','2018-09-10 09:24:44',1);

/*Table structure for table `forders` */

DROP TABLE IF EXISTS `forders`;

CREATE TABLE `forders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `oid` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `forders` */

insert  into `forders`(`id`,`uid`,`oid`) values (1,2,1),(2,19,4),(3,20,4),(4,20,4),(5,20,4),(6,20,4),(7,39,5);

/*Table structure for table `friend` */

DROP TABLE IF EXISTS `friend`;

CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `fid` int(11) DEFAULT NULL COMMENT '好友id',
  `time` datetime DEFAULT NULL COMMENT '添加好友时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `friend` */

insert  into `friend`(`id`,`uid`,`fid`,`time`) values (1,37,19,'2019-01-13 16:57:45'),(2,37,20,'2019-01-13 17:01:12'),(3,37,24,'2019-01-13 17:16:58'),(4,37,39,'2019-01-13 20:44:15'),(5,40,19,'2019-01-13 22:18:42'),(6,40,32,'2019-01-13 22:27:49');

/*Table structure for table `logistics` */

DROP TABLE IF EXISTS `logistics`;

CREATE TABLE `logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(200) DEFAULT NULL COMMENT '物流信息',
  `time` datetime DEFAULT NULL COMMENT '更新时间',
  `oid` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `logistics` */

insert  into `logistics`(`id`,`message`,`time`,`oid`) values (1,'已揽件，等待发货','2019-01-09 13:03:28',2),(2,'快递已经到北京，等待派送','2019-01-09 13:03:51',2),(3,'已经开始派送，快递员：张三，电话121232','2019-01-09 13:04:20',2),(4,'111','2018-12-12 12:12:12',4);

/*Table structure for table `moments` */

DROP TABLE IF EXISTS `moments`;

CREATE TABLE `moments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '朋友圈',
  `content` varchar(500) DEFAULT NULL COMMENT '朋友圈内容',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `moments` */

insert  into `moments`(`id`,`content`,`uid`,`time`) values (1,'今天有一条快递到了',1,'2019-01-09 12:42:26'),(2,'下雪了',1,'2019-01-09 12:42:33'),(3,'测试',40,'2019-01-13 22:19:06'),(4,'啦',40,'2019-01-13 22:27:27');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` char(12) DEFAULT NULL COMMENT '快递单号',
  `receiver` varchar(20) DEFAULT NULL COMMENT '收货人',
  `sender` varchar(20) DEFAULT NULL COMMENT '发货人',
  `saddress` varchar(200) DEFAULT NULL COMMENT '发货地址',
  `raddress` varchar(200) DEFAULT NULL COMMENT '收获地址',
  `sphone` varchar(11) DEFAULT NULL COMMENT '发货人手机号',
  `rphone` varchar(11) DEFAULT NULL COMMENT '收货人手机号',
  `uid` int(11) DEFAULT NULL COMMENT '寄件人id',
  `state` int(11) DEFAULT '0' COMMENT '0为快递中1为已派件2以签收',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`number`,`receiver`,`sender`,`saddress`,`raddress`,`sphone`,`rphone`,`uid`,`state`) values (1,'111','收件人','发件人','北京天安门','南京xxx','12321323','123123',35,1),(2,'1001','嗯啦','冻恩','阿菊凸凸','冻膜','255555','大',37,2),(3,'1002','德恩','冬末出来了公里','酷兔兔','啊酷兔兔','35555','122455',38,1),(4,'1004','啦啦啦','阿菊','东西','具体','2255','3666',37,0),(5,'1004','魔团','发来看看','不吐','来了喔哦哦','3669','333666',37,1),(6,'123456789','李哥','刘','北京','北京','18041362491','13800001234',40,0);

/*Table structure for table `sys_admin_user` */

DROP TABLE IF EXISTS `sys_admin_user`;

CREATE TABLE `sys_admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(20) DEFAULT '' COMMENT '用户名',
  `password` varchar(50) DEFAULT '' COMMENT '密码',
  `real_name` varchar(25) DEFAULT '' COMMENT '真名',
  `email` varchar(30) DEFAULT '' COMMENT '邮箱',
  `telephone` varchar(20) DEFAULT '' COMMENT '座机号',
  `mobile_phone` varchar(20) DEFAULT '' COMMENT '手机号',
  `address` varchar(100) DEFAULT '' COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `role_id` bigint(20) DEFAULT '0' COMMENT '角色',
  `account_non_expired` tinyint(1) DEFAULT '0' COMMENT '是否未失效',
  `account_non_locked` tinyint(1) DEFAULT '0' COMMENT '是否未锁定',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '是否未失效',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`username`),
  KEY `s_a_r_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `sys_admin_user` */

insert  into `sys_admin_user`(`id`,`username`,`password`,`real_name`,`email`,`telephone`,`mobile_phone`,`address`,`create_time`,`update_time`,`role_id`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`) values (3,'admin','21232f297a57a5a743894a0e4a801fc3','管理员','','','','','2018-07-30 00:00:00','2018-07-30 00:00:00',1,0,0,0,1),(4,'test','098f6bcd4621d373cade4e832627b4f6','测试人员','','','','','2018-09-03 00:00:00','2018-09-03 00:00:00',2,0,0,0,1),(7,'magicalcoder','6860c37b00ed4e444a7d2c6e8fb66886','系统默认超级管理员账号','','','','','2018-09-06 00:00:00','2018-09-06 00:00:00',1,0,0,0,1);

/*Table structure for table `sys_global_permit_url` */

DROP TABLE IF EXISTS `sys_global_permit_url`;

CREATE TABLE `sys_global_permit_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permit_name` varchar(200) DEFAULT '' COMMENT '通过名称',
  `backend_url_reg` varchar(500) DEFAULT '' COMMENT '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序',
  `module_id` bigint(20) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`id`),
  KEY `sy_m_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后端请求地址全局允许过滤器,在此表的统一不用再去权限表匹配了';

/*Data for the table `sys_global_permit_url` */

insert  into `sys_global_permit_url`(`id`,`permit_name`,`backend_url_reg`,`module_id`) values (2,'允许进入后台,使用有些通用接口（请勿删除）','/admin/(rmp|page|common_file_rest).*',NULL),(3,'通用获取权限相关接口（请勿删除）','/admin/rest_rmp/(get_permission_list|get_module_list).*',NULL);

/*Table structure for table `sys_log_admin_operate` */

DROP TABLE IF EXISTS `sys_log_admin_operate`;

CREATE TABLE `sys_log_admin_operate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_user_id` bigint(20) DEFAULT '0' COMMENT '管理员',
  `user_name` varchar(200) DEFAULT '0' COMMENT '管理员名称',
  `table_name` varchar(100) DEFAULT '' COMMENT '表名',
  `operate_type` varchar(50) DEFAULT '' COMMENT '操作类型',
  `url` varchar(100) DEFAULT '' COMMENT '链接',
  `primary_id_value` varchar(100) DEFAULT '' COMMENT '主键值',
  `form_body` text COMMENT '提交内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=373 DEFAULT CHARSET=utf8;

/*Data for the table `sys_log_admin_operate` */

insert  into `sys_log_admin_operate`(`id`,`admin_user_id`,`user_name`,`table_name`,`operate_type`,`url`,`primary_id_value`,`form_body`,`create_time`) values (308,3,'管理员','sys_log_admin_operate_rest','delete','/admin/sys_log_admin_operate_rest/delete/307','','{}','2018-09-10 17:00:43'),(309,3,'管理员','sys_module_category_rest','delete','/admin/sys_module_category_rest/delete/1',NULL,'{}','2018-12-17 15:04:54'),(310,3,'管理员','sys_module_category_rest','save','/admin/sys_module_category_rest/save','','{\"id\":[\"\"],\"moduleCategoryName\":[\"时兼APP后台管理\"],\"sortNum\":[\"1\"]}','2018-12-17 15:05:52'),(311,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-17 15:09:00'),(312,3,'管理员','sys_module_rest','save','/admin/sys_module_rest/save','','{\"id\":[\"\"],\"moduleCategoryId\":[\"4\"],\"moduleTitle\":[\"用户管理\"],\"moduleName\":[\"user\"],\"moduleUrl\":[\"admin/page/user/list\"],\"sortNum\":[\"1\"],\"ifShow\":[\"1\"]}','2018-12-17 15:09:39'),(313,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"module_21\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-17 15:10:01'),(314,3,'管理员','sys_module_rest','save','/admin/sys_module_rest/save','','{\"id\":[\"\"],\"moduleCategoryId\":[\"4\"],\"moduleTitle\":[\"兼职管理\"],\"moduleName\":[\"job\"],\"moduleUrl\":[\"admin/page/job/list\"],\"sortNum\":[\"2\"],\"ifShow\":[\"1\"]}','2018-12-19 19:26:33'),(315,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"module_21\",\"module_22\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-19 19:26:53'),(316,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"module_21\",\"module_22\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-19 19:32:23'),(317,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','','{\"id\":[\"\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/user_rest/.*\"],\"frontDom\":[\"user\"],\"frontAction\":[\"show\"],\"moduleId\":[\"21\"]}','2018-12-19 19:34:08'),(318,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','26','{\"id\":[\"26\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/user_rest/.*\"],\"frontDom\":[\"user\"],\"frontAction\":[\"show\"],\"moduleId\":[\"21\"]}','2018-12-19 19:35:17'),(319,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"module_21\",\"permission_26\",\"module_22\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-19 19:35:27'),(320,3,'管理员','user_rest','delete','/admin/user_rest/delete/2',NULL,'{}','2018-12-19 19:36:08'),(321,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','','{\"id\":[\"\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/job_rest/.*\"],\"frontDom\":[\"job\"],\"frontAction\":[\"show\"],\"moduleId\":[\"22\"]}','2018-12-22 13:23:22'),(322,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"module_21\",\"permission_26\",\"module_22\",\"permission_27\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-22 13:23:37'),(323,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:31:53'),(324,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:31:54'),(325,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:35:49'),(326,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:35:50'),(327,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:42:17'),(328,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:42:17'),(329,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:42:18'),(330,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:42:20'),(331,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:42:21'),(332,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:45:07'),(333,3,'管理员','user_rest','update','/admin/user_rest/update/34',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:45:08'),(334,3,'管理员','user_rest','update','/admin/user_rest/update/19',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:45:40'),(335,3,'管理员','user_rest','update','/admin/user_rest/update/30',NULL,'{\"state\":[\"0\"]}','2018-12-30 01:45:44'),(336,3,'管理员','user_rest','update','/admin/user_rest/update/30',NULL,'{\"state\":[\"1\"]}','2018-12-30 01:49:47'),(337,3,'管理员','job_rest','update','/admin/job_rest/update/20',NULL,'{\"uid\":[\"33\"]}','2018-12-30 01:49:59'),(338,3,'管理员','job_rest','update','/admin/job_rest/update/20',NULL,'{\"state\":[\"2\"]}','2018-12-30 02:01:33'),(339,3,'管理员','job_rest','update','/admin/job_rest/update/20',NULL,'{\"state\":[\"0\"]}','2018-12-30 02:01:36'),(340,3,'管理员','job_rest','update','/admin/job_rest/update/20',NULL,'{\"state\":[\"1\"]}','2018-12-30 02:01:47'),(341,3,'管理员','job_rest','update','/admin/job_rest/update/20',NULL,'{\"state\":[\"2\"]}','2018-12-30 02:01:58'),(342,3,'管理员','job_rest','save','/admin/job_rest/save','20','{\"id\":[\"20\"],\"title\":[\"广告公司\"],\"uid\":[\"33\"],\"createTime\":[\"2018-12-30 00:58:08\"],\"price\":[\"200一天\"],\"tel\":[\"16768888\"],\"content\":[\"兔兔\"],\"state\":[\"1\"]}','2018-12-30 02:06:16'),(343,3,'管理员','sys_module_rest','save','/admin/sys_module_rest/save','','{\"id\":[\"\"],\"moduleCategoryId\":[\"4\"],\"moduleTitle\":[\"订单管理\"],\"moduleName\":[\"orders\"],\"moduleUrl\":[\"admin/page/orders/list\"],\"sortNum\":[\"3\"],\"ifShow\":[\"1\"]}','2018-12-30 02:11:09'),(344,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','','{\"id\":[\"\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/orders_rest/.*\"],\"frontDom\":[\"orders\"],\"frontAction\":[\"show\"],\"moduleId\":[\"23\"]}','2018-12-30 02:11:28'),(345,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_4\",\"module_21\",\"permission_26\",\"module_22\",\"permission_27\",\"module_23\",\"permission_28\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2018-12-30 02:11:37'),(346,3,'管理员','orders_rest','save','/admin/orders_rest/save','28','{\"id\":[\"28\"],\"uid\":[\"34\"],\"jid\":[\"19\"],\"createTime\":[\"2018-12-30 00:54:05\"],\"ujid\":[\"24\"]}','2018-12-30 02:18:03'),(347,3,'管理员','sys_module_category_rest','delete','/admin/sys_module_category_rest/delete/4',NULL,'{}','2019-01-13 19:29:56'),(348,3,'管理员','sys_module_category_rest','save','/admin/sys_module_category_rest/save','','{\"id\":[\"\"],\"moduleCategoryName\":[\"快递后台模块\"],\"sortNum\":[\"2\"]}','2019-01-13 19:50:08'),(349,3,'管理员','sys_module_rest','save','/admin/sys_module_rest/save','','{\"id\":[\"\"],\"moduleCategoryId\":[\"5\"],\"moduleTitle\":[\"订单管理\"],\"moduleName\":[\"orders\"],\"moduleUrl\":[\"admin/page/orders/list\"],\"sortNum\":[\"1\"],\"ifShow\":[\"1\"]}','2019-01-13 19:50:45'),(350,3,'管理员','sys_module_category_rest','update','/admin/sys_module_category_rest/update/5',NULL,'{\"sortNum\":[\"1\"]}','2019-01-13 19:52:26'),(351,3,'管理员','sys_module_category_rest','save','/admin/sys_module_category_rest/save','5','{\"id\":[\"5\"],\"moduleCategoryName\":[\"快递后台模块\"],\"sortNum\":[\"1\"]}','2019-01-13 19:53:11'),(352,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','','{\"id\":[\"\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/orders_rest/.*\"],\"frontDom\":[\"orders\"],\"frontAction\":[\"show\"],\"moduleId\":[\"24\"]}','2019-01-13 19:53:27'),(353,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_5\",\"module_24\",\"permission_29\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2019-01-13 19:53:37'),(354,3,'管理员','sys_module_rest','save','/admin/sys_module_rest/save','','{\"id\":[\"\"],\"moduleCategoryId\":[\"5\"],\"moduleTitle\":[\"用户管理\"],\"moduleName\":[\"user\"],\"moduleUrl\":[\"admin/page/user/list\"],\"sortNum\":[\"2\"],\"ifShow\":[\"1\"]}','2019-01-13 20:00:51'),(355,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','','{\"id\":[\"\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/user_rest/.*\"],\"frontDom\":[\"user\"],\"frontAction\":[\"show\"],\"moduleId\":[\"25\"]}','2019-01-13 20:01:01'),(356,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_5\",\"module_24\",\"permission_29\",\"module_25\",\"permission_30\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2019-01-13 20:01:09'),(357,3,'管理员','orders_rest','update','/admin/orders_rest/update/3',NULL,'{\"uid\":[\"38\"]}','2019-01-13 20:01:40'),(358,3,'管理员','orders_rest','update','/admin/orders_rest/update/2',NULL,'{\"state\":[\"1\"]}','2019-01-13 20:07:06'),(359,3,'管理员','orders_rest','update','/admin/orders_rest/update/2',NULL,'{\"state\":[\"0\"]}','2019-01-13 20:07:21'),(360,3,'管理员','orders_rest','update','/admin/orders_rest/update/2',NULL,'{\"state\":[\"1\"]}','2019-01-13 20:08:03'),(361,3,'管理员','orders_rest','update','/admin/orders_rest/update/3',NULL,'{\"state\":[\"1\"]}','2019-01-13 20:13:58'),(362,3,'管理员','orders_rest','update','/admin/orders_rest/update/3',NULL,'{\"state\":[\"0\"]}','2019-01-13 20:14:29'),(363,3,'管理员','orders_rest','update','/admin/orders_rest/update/3',NULL,'{\"state\":[\"1\"]}','2019-01-13 20:18:27'),(364,3,'管理员','orders_rest','update','/admin/orders_rest/update/3',NULL,'{\"state\":[\"2\"]}','2019-01-13 20:18:29'),(365,3,'管理员','orders_rest','update','/admin/orders_rest/update/3',NULL,'{\"state\":[\"1\"]}','2019-01-13 20:18:31'),(366,3,'管理员','orders_rest','update','/admin/orders_rest/update/5',NULL,'{\"state\":[\"1\"]}','2019-01-13 22:12:48'),(367,3,'管理员','sys_module_rest','save','/admin/sys_module_rest/save','','{\"id\":[\"\"],\"moduleCategoryId\":[\"5\"],\"moduleTitle\":[\"物流信息管理\"],\"moduleName\":[\"logistics\"],\"moduleUrl\":[\"admin/page/logistics/list\"],\"sortNum\":[\"3\"],\"ifShow\":[\"1\"]}','2019-01-13 22:36:39'),(368,3,'管理员','sys_permission_rest','save','/admin/sys_permission_rest/save','','{\"id\":[\"\"],\"permissionName\":[\"全部权限\"],\"filterPlatform\":[\"front_backend\"],\"backendUrlReg\":[\"/admin/logistics_rest/.*\"],\"frontDom\":[\"logistics\"],\"frontAction\":[\"show\"],\"moduleId\":[\"26\"]}','2019-01-13 22:36:52'),(369,3,'管理员','rest_rmp','save_tree_data','/admin/rest_rmp/save_tree_data/1',NULL,'{\"ids[]\":[\"category_5\",\"module_24\",\"permission_29\",\"module_25\",\"permission_30\",\"module_26\",\"permission_31\",\"category_3\",\"module_17\",\"permission_21\",\"module_11\",\"module_7\",\"module_5\",\"module_8\",\"module_4\",\"module_20\",\"permission_25\"]}','2019-01-13 22:37:03'),(370,3,'管理员','logistics_rest','save','/admin/logistics_rest/save','','{\"id\":[\"\"],\"message\":[\"111\"],\"time\":[\"111\"],\"oid\":[\"4\"]}','2019-01-13 22:48:08'),(371,3,'管理员','logistics_rest','update','/admin/logistics_rest/update/4',NULL,'{\"time\":[\"2018-12-12\"]}','2019-01-13 23:11:59'),(372,3,'管理员','logistics_rest','save','/admin/logistics_rest/save','4','{\"id\":[\"4\"],\"message\":[\"111\"],\"time\":[\"2018-12-12 12:12:12\"],\"oid\":[\"4\"]}','2019-01-13 23:12:06');

/*Table structure for table `sys_module` */

DROP TABLE IF EXISTS `sys_module`;

CREATE TABLE `sys_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_name` varchar(200) DEFAULT '' COMMENT '模块唯一键',
  `module_url` varchar(200) DEFAULT '' COMMENT '模块URL',
  `module_category_id` bigint(20) DEFAULT '0' COMMENT '模块分类',
  `sort_num` int(2) DEFAULT '0' COMMENT '排序',
  `module_title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `if_show` tinyint(1) DEFAULT '1' COMMENT '是否显示',
  PRIMARY KEY (`id`),
  KEY `FK_module` (`module_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='模块';

/*Data for the table `sys_module` */

insert  into `sys_module`(`id`,`module_name`,`module_url`,`module_category_id`,`sort_num`,`module_title`,`if_show`) values (4,'sys_log_admin_operate','admin/page/sys_log_admin_operate/list',3,7,'系统日志',1),(5,'sys_module_category','admin/page/sys_module_category/list',3,3,'菜单管理',1),(7,'sys_admin_user','admin/page/sys_admin_user/list',3,2,'管理员',1),(8,'sys_global_permit_url','admin/page/sys_global_permit_url/list',3,6,'全局地址过滤',1),(11,'sys_role','admin/page/sys_role/list',3,1,'角色管理',1),(14,'all_type','admin/page/all_type/list',1,3,'全类型测试',1),(17,'sys','',3,-1,'系统表权限载体（请勿删除）',0),(20,'dict','admin/page/dict/list',3,10,'字典配置',1),(21,'user','admin/page/user/list',4,1,'用户管理',1),(22,'job','admin/page/job/list',4,2,'兼职管理',1),(23,'orders','admin/page/orders/list',4,3,'订单管理',1),(24,'orders','admin/page/orders/list',5,1,'订单管理',1),(25,'user','admin/page/user/list',5,2,'用户管理',1),(26,'logistics','admin/page/logistics/list',5,3,'物流信息管理',1);

/*Table structure for table `sys_module_category` */

DROP TABLE IF EXISTS `sys_module_category`;

CREATE TABLE `sys_module_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_category_name` varchar(20) DEFAULT '' COMMENT '模块名称',
  `sort_num` int(2) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='模块分类';

/*Data for the table `sys_module_category` */

insert  into `sys_module_category`(`id`,`module_category_name`,`sort_num`) values (3,'系统模块',2),(5,'快递后台模块',1);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(50) DEFAULT '' COMMENT '过滤器名称:审核通过',
  `filter_platform` varchar(50) DEFAULT '' COMMENT '过滤端:front|backend|front_backend',
  `backend_url_reg` varchar(200) DEFAULT '' COMMENT '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序',
  `front_dom` varchar(1024) DEFAULT '' COMMENT '前端Key,多个逗号分割',
  `front_action` varchar(50) DEFAULT '' COMMENT '前端处理方式show|disabled',
  `module_id` bigint(20) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`id`),
  KEY `sy_m_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='后端请求地址允许过滤器,不在过滤器的统一拒绝';

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`permission_name`,`filter_platform`,`backend_url_reg`,`front_dom`,`front_action`,`module_id`) values (2,'查询权限','backend','/admin/goods_category_rest/.*','','',2),(13,'全类型测试','backend','/admin/all_type_rest/.*',NULL,'',14),(21,'所有系统表的所有权限(请勿删除)','backend','/admin/(rest_rmp|sys_[a-z_]+_rest)/.*','','show',17),(25,'字典全部权限','front_backend','/admin/dict_rest/.*','','show',20),(26,'全部权限','front_backend','/admin/user_rest/.*','user','show',21),(27,'全部权限','front_backend','/admin/job_rest/.*','job','show',22),(28,'全部权限','front_backend','/admin/orders_rest/.*','orders','show',23),(29,'全部权限','front_backend','/admin/orders_rest/.*','orders','show',24),(30,'全部权限','front_backend','/admin/user_rest/.*','user','show',25),(31,'全部权限','front_backend','/admin/logistics_rest/.*','logistics','show',26);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) DEFAULT '' COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`) values (1,'超级管理员'),(2,'测试账号');

/*Table structure for table `sys_role_module_permission` */

DROP TABLE IF EXISTS `sys_role_module_permission`;

CREATE TABLE `sys_role_module_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`),
  KEY `s_r_id` (`role_id`),
  KEY `s_m_id` (`module_id`),
  KEY `s_pid` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=521 DEFAULT CHARSET=utf8 COMMENT='角色模块权限';

/*Data for the table `sys_role_module_permission` */

insert  into `sys_role_module_permission`(`id`,`role_id`,`module_id`,`permission_id`) values (426,2,1,15),(427,2,1,17),(428,2,1,18),(429,2,1,19),(430,2,1,22),(431,2,18,NULL),(432,2,19,NULL),(511,1,4,NULL),(512,1,5,NULL),(513,1,7,NULL),(514,1,8,NULL),(515,1,11,NULL),(516,1,17,21),(517,1,20,25),(518,1,24,29),(519,1,25,30),(520,1,26,31);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) DEFAULT NULL COMMENT '用户名，手机号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `realname` varchar(20) DEFAULT '默认昵称' COMMENT '姓名',
  `state` int(11) DEFAULT '0' COMMENT '状态0正常1禁用',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`realname`,`state`,`phone`) values (1,'admin','admin','默认昵称',0,'15690220214'),(19,'a','1','李哥',0,'15855'),(20,'b','1','来福',0,'47777'),(21,'兼职','1','意境晨',0,'1'),(22,'f','1','王局要',0,'1'),(23,'j','1','张美兰',0,'1'),(24,'fa','1','汪汪',0,'258'),(25,'田','123456','甜甜',0,'17606011111'),(26,'11','11','李华',0,'111'),(27,'1111','11','派可爱',1,'11'),(28,'2','123','李梦',0,'2'),(29,'张三','123','张三',0,'123456'),(30,'152','admin','田美丽',0,'111'),(31,'李四','123','李四',0,'15112981254'),(32,'张悟','123','张张',0,'17606114534'),(33,'12312321','admin','真是姓名',0,'3423'),(34,'k','1','老王',0,'7855'),(35,'kk','1','邻居',0,'14777'),(36,'u','1','吃',0,'1477'),(37,'kay','1','出来了',0,'147'),(38,'qa','1','出来了',0,'258'),(39,'lkl','1','小李子',0,'1477'),(40,'liu','123','刘',0,'18041362491');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
