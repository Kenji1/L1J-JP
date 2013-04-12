SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `letters`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `letters` (
  `item_obj_id` int(10) unsigned NOT NULL,
  `code` int(10) unsigned NOT NULL DEFAULT '0',
  `sender` varchar(255) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `template_id` int(10) unsigned NOT NULL DEFAULT '0',
  `subject` blob,
  `content` blob,
  PRIMARY KEY (`item_obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
