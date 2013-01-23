SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `restart_locations`
-- ----------------------------
DROP TABLE IF EXISTS `restart_locations`;
CREATE TABLE IF NOT EXISTS `restart_locations` (
  `area` int(10) unsigned NOT NULL DEFAULT '0',
  `note` varchar(50) DEFAULT NULL,
  `loc_x` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y` int(10) unsigned NOT NULL DEFAULT '0',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
