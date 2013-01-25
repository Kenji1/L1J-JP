SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawn_boss`
-- ----------------------------
DROP TABLE IF EXISTS `spawn_boss`;
CREATE TABLE `spawn_boss` (
  `id` int(10) unsigned NOT NULL,
  `location` varchar(255) NOT NULL DEFAULT '',
  `cycle_type` varchar(255) NOT NULL DEFAULT '',
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `group_id` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_x` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y` int(10) unsigned NOT NULL DEFAULT '0',
  `random_x` int(10) unsigned NOT NULL DEFAULT '0',
  `random_y` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_x1` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y1` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_x2` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y2` int(10) unsigned NOT NULL DEFAULT '0',
  `heading` int(10) unsigned NOT NULL DEFAULT '0',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `respawn_screen` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `movement_distance` int(10) unsigned NOT NULL DEFAULT '0',
  `rest` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `spawn_type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `percentage` tinyint(3) unsigned NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
