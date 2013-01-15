SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `npcaction`
-- ----------------------------
DROP TABLE IF EXISTS `npcaction`;
CREATE TABLE `npcaction` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `normal_action` varchar(45) NOT NULL DEFAULT '',
  `chaotic_action` varchar(45) NOT NULL DEFAULT '',
  `teleport_url` varchar(45) NOT NULL DEFAULT '',
  `teleport_urla` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`npcid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
