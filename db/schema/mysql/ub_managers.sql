SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ub_managers`
-- ----------------------------
DROP TABLE IF EXISTS `ub_managers`;
CREATE TABLE IF NOT EXISTS `ub_managers` (
  `ub_id` int(10) unsigned NOT NULL,
  `npc_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ub_id`, `npc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
