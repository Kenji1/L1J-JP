SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `npc_actions`
-- ----------------------------
DROP TABLE IF EXISTS `npc_actions`;
CREATE TABLE `npc_actions` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `normal_action` varchar(255) NOT NULL DEFAULT '',
  `chaotic_action` varchar(255) NOT NULL DEFAULT '',
  `teleport_url` varchar(255) NOT NULL DEFAULT '',
  `teleport_urla` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`npc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
