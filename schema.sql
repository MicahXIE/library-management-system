/*
lms schema creation
*/

DROP DATABASE `lms`;
CREATE DATABASE `lms`;

USE `lms`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `isbn` varchar(255) DEFAULT '',
  `book_name` varchar(255) NOT NULL DEFAULT '',
  `author` varchar(255) DEFAULT '',
  `publish_date` varchar(20) DEFAULT '',
  `summary` varchar(255) DEFAULT '',
  `available` boolean NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_name` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(11) NOT NULL DEFAULT 5,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `borrow_date` varchar(20) DEFAULT '',
  `expiry_date` varchar(20) DEFAULT '',
  `return_date` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `fk_record_user_on_uid` (`uid`),
  CONSTRAINT `fk_record_user_on_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  KEY `fk_record_book_on_bid` (`bid`),
  CONSTRAINT `fk_record_book_on_bid` FOREIGN KEY (`bid`) REFERENCES `book` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




