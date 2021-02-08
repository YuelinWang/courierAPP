-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.24-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 kuaidi 的数据库结构
CREATE DATABASE IF NOT EXISTS `kuaidi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kuaidi`;

-- 导出  表 kuaidi.all_type 结构
DROP TABLE IF EXISTS `all_type`;
CREATE TABLE IF NOT EXISTS `all_type` (
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

-- 数据导出被取消选择。
-- 导出  表 kuaidi.dict 结构
DROP TABLE IF EXISTS `dict`;
CREATE TABLE IF NOT EXISTS `dict` (
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

-- 数据导出被取消选择。
-- 导出  表 kuaidi.forders 结构
DROP TABLE IF EXISTS `forders`;
CREATE TABLE IF NOT EXISTS `forders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `oid` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 kuaidi.friend 结构
DROP TABLE IF EXISTS `friend`;
CREATE TABLE IF NOT EXISTS `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `fid` int(11) DEFAULT NULL COMMENT '好友id',
  `time` datetime DEFAULT NULL COMMENT '添加好友时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 kuaidi.logistics 结构
DROP TABLE IF EXISTS `logistics`;
CREATE TABLE IF NOT EXISTS `logistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(200) DEFAULT NULL COMMENT '物流信息',
  `time` datetime DEFAULT NULL COMMENT '更新时间',
  `oid` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 kuaidi.moments 结构
DROP TABLE IF EXISTS `moments`;
CREATE TABLE IF NOT EXISTS `moments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '朋友圈',
  `content` varchar(500) DEFAULT NULL COMMENT '朋友圈内容',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 kuaidi.orders 结构
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_admin_user 结构
DROP TABLE IF EXISTS `sys_admin_user`;
CREATE TABLE IF NOT EXISTS `sys_admin_user` (
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

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_global_permit_url 结构
DROP TABLE IF EXISTS `sys_global_permit_url`;
CREATE TABLE IF NOT EXISTS `sys_global_permit_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permit_name` varchar(200) DEFAULT '' COMMENT '通过名称',
  `backend_url_reg` varchar(500) DEFAULT '' COMMENT '后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序',
  `module_id` bigint(20) DEFAULT NULL COMMENT '所属模块',
  PRIMARY KEY (`id`),
  KEY `sy_m_id` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='后端请求地址全局允许过滤器,在此表的统一不用再去权限表匹配了';

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_log_admin_operate 结构
DROP TABLE IF EXISTS `sys_log_admin_operate`;
CREATE TABLE IF NOT EXISTS `sys_log_admin_operate` (
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
) ENGINE=InnoDB AUTO_INCREMENT=427 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_module 结构
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE IF NOT EXISTS `sys_module` (
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

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_module_category 结构
DROP TABLE IF EXISTS `sys_module_category`;
CREATE TABLE IF NOT EXISTS `sys_module_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `module_category_name` varchar(20) DEFAULT '' COMMENT '模块名称',
  `sort_num` int(2) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='模块分类';

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_permission 结构
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE IF NOT EXISTS `sys_permission` (
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

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) DEFAULT '' COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色';

-- 数据导出被取消选择。
-- 导出  表 kuaidi.sys_role_module_permission 结构
DROP TABLE IF EXISTS `sys_role_module_permission`;
CREATE TABLE IF NOT EXISTS `sys_role_module_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色',
  `module_id` bigint(20) DEFAULT NULL COMMENT '模块',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`),
  KEY `s_r_id` (`role_id`),
  KEY `s_m_id` (`module_id`),
  KEY `s_pid` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=521 DEFAULT CHARSET=utf8 COMMENT='角色模块权限';

-- 数据导出被取消选择。
-- 导出  表 kuaidi.user 结构
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(11) DEFAULT NULL COMMENT '用户名，手机号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `realname` varchar(20) DEFAULT '默认昵称' COMMENT '姓名',
  `state` int(11) DEFAULT '0' COMMENT '状态0正常1禁用',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
