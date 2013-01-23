SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawn_lights`
-- ----------------------------
DROP TABLE IF EXISTS `spawn_lights`;
CREATE TABLE IF NOT EXISTS `spawn_lights` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_x` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y` int(10) unsigned NOT NULL DEFAULT '0',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=175 ;
