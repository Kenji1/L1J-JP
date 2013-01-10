SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ub_settings`
-- ----------------------------
DROP TABLE IF EXISTS `ub_settings`;
CREATE TABLE IF NOT EXISTS `ub_settings` (
  `ub_id` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_name` varchar(45) NOT NULL DEFAULT '',
  `ub_mapid` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_x1` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_y1` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_x2` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_area_y2` int(10) unsigned NOT NULL DEFAULT '0',
  `min_lvl` int(10) unsigned NOT NULL DEFAULT '0',
  `max_lvl` int(10) unsigned NOT NULL DEFAULT '0',
  `max_player` int(10) unsigned NOT NULL DEFAULT '0',
  `enter_royal` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_knight` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_mage` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_elf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_darkelf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_dragonknight` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_illusionist` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_male` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_female` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `use_pot` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `hpr_bonus` int(10) NOT NULL DEFAULT '0',
  `mpr_bonus` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ub_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
