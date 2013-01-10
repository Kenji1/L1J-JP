SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `clan_data`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `clan_data` (
  `clan_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clan_name` varchar(45) NOT NULL DEFAULT '',
  `leader_id` int(10) unsigned NOT NULL DEFAULT '0',
  `leader_name` varchar(45) NOT NULL DEFAULT '',
  `has_castle` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `has_house` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`clan_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
