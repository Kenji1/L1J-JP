SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `restart_locations`
-- ----------------------------
DROP TABLE IF EXISTS `restart_locations`;
CREATE TABLE IF NOT EXISTS `restart_locations` (
  `area` int(10) unsigned NOT NULL,
  `loc_x` int(10) unsigned NOT NULL,
  `loc_y` int(10) unsigned NOT NULL,
  `map_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`area`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
