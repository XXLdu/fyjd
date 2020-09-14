/*
SQLyog v10.2 
MySQL - 5.6.22 : Database - fyjd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fyjd` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fyjd`;

/*Table structure for table `trust_check_log` */

DROP TABLE IF EXISTS `trust_check_log`;

CREATE TABLE `trust_check_log` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `trustId` varchar(36) DEFAULT NULL COMMENT '审核表主键',
  `checkTime` datetime DEFAULT NULL COMMENT '审核时间',
  `checkUserId` int(36) DEFAULT NULL COMMENT '审核人id',
  `checkUserName` varchar(200) DEFAULT NULL COMMENT '审核人名称',
  `checkStatus` int(11) DEFAULT NULL COMMENT '审核状态（0：驳回 1：通过）',
  `checkReason` varchar(1000) DEFAULT NULL COMMENT '驳回原因',
  `checkType` varchar(40) DEFAULT NULL COMMENT '审核类型',
  `checkTypeName` varchar(40) DEFAULT NULL COMMENT '审核类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `trust_check_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
