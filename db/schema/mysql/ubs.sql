SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ubs`
-- ----------------------------
DROP TABLE IF EXISTS `ubs`;
CREATE TABLE IF NOT EXISTS `ubs` (
  `id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `area_x1` int(10) unsigned NOT NULL DEFAULT '0',
  `area_y1` int(10) unsigned NOT NULL DEFAULT '0',
  `area_x2` int(10) unsigned NOT NULL DEFAULT '0',
  `area_y2` int(10) unsigned NOT NULL DEFAULT '0',
  `min_level` int(10) unsigned NOT NULL DEFAULT '0',
  `max_level` int(10) unsigned NOT NULL DEFAULT '0',
  `max_player` int(10) unsigned NOT NULL DEFAULT '0',
  `enter_royal` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_knight` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_wizard` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_elf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_darkelf` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_dragonknight` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_illusionist` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_male` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `enter_female` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `use_pot` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `hpr_bonus` int(10) NOT NULL DEFAULT '0',
  `mpr_bonus` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
