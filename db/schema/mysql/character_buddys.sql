SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_buddys`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `character_buddys` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_id` int(10) unsigned NOT NULL DEFAULT '0',
  `buddy_id` int(10) unsigned NOT NULL DEFAULT '0',
  `buddy_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`char_id`,`buddy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
