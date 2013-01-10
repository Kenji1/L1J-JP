SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawnlist_light`
-- ----------------------------
DROP TABLE IF EXISTS `spawnlist_light`;
CREATE TABLE IF NOT EXISTS `spawnlist_light` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `npcid` int(10) unsigned NOT NULL DEFAULT '0',
  `locx` int(10) unsigned NOT NULL DEFAULT '0',
  `locy` int(10) unsigned NOT NULL DEFAULT '0',
  `mapid` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=175 ;
