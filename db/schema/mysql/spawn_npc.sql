SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawn_npc`
-- ----------------------------
DROP TABLE IF EXISTS `spawn_npc`;
CREATE TABLE `spawn_npc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location` varchar(19) NOT NULL DEFAULT '',
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  `npc_template_id` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_x` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y` int(10) unsigned NOT NULL DEFAULT '0',
  `random_x` int(10) unsigned NOT NULL DEFAULT '0',
  `random_y` int(10) unsigned NOT NULL DEFAULT '0',
  `heading` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '↑1 ↗2→3↘4↓5 ↙6←7↖0',
  `respawn_delay` int(10) unsigned NOT NULL DEFAULT '0',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `movement_distance` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1400185 DEFAULT CHARSET=utf8;
