SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `log_enchant`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `log_enchant` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_id` int(10) NOT NULL DEFAULT '0',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `old_enchant_level` int(3) NOT NULL DEFAULT '0',
  `new_enchant_level` int(3) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `key_id` (`char_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
