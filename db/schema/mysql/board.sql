SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `board`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `board` (
  `id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
