SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `clans`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `clans` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `leader_id` int(10) unsigned NOT NULL,
  `has_castle` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `has_house` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
