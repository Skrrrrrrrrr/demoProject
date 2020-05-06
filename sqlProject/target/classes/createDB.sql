SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;  
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;  
CREATE DATABASE /*!32312 IF NOT EXISTS*/`@@@dbName@@@` /*!40100 DEFAULT CHARACTER SET utf8 */;  
  
USE `@@@dbName@@@`;  
  
  
DROP TABLE IF EXISTS `tb_abc`;  
  
CREATE TABLE `tb_abc` (  
  `id` varchar(36) NOT NULL,  
  `days` int(11) DEFAULT NULL,  
  `last_update_user` varchar(50) DEFAULT NULL,  
  `last_update_time` datetime DEFAULT NULL,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;