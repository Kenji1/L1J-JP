SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawn_ub`
-- ----------------------------
DROP TABLE IF EXISTS `spawn_ub`;
CREATE TABLE IF NOT EXISTS `spawn_ub` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ub_id` int(10) unsigned NOT NULL DEFAULT '0',
  `pattern` int(10) unsigned NOT NULL DEFAULT '0',
  `group_id` int(10) unsigned NOT NULL DEFAULT '0',
  `location` varchar(255) NOT NULL DEFAULT '',
  `npc_template_id` int(10) unsigned NOT NULL DEFAULT '0',
  `count` int(10) unsigned NOT NULL DEFAULT '0',
  `spawn_delay` int(10) unsigned NOT NULL DEFAULT '0',
  `seal_count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1120 ;
