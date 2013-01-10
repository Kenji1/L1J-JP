SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `getback_restart`
-- ----------------------------
DROP TABLE IF EXISTS `getback_restart`;
CREATE TABLE IF NOT EXISTS `getback_restart` (
  `area` int(10) unsigned NOT NULL DEFAULT '0',
  `note` varchar(50) DEFAULT NULL,
  `locx` int(10) unsigned NOT NULL DEFAULT '0',
  `locy` int(10) unsigned NOT NULL DEFAULT '0',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
