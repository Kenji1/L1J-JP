SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ub_times`
-- ----------------------------
DROP TABLE IF EXISTS `ub_times`;
CREATE TABLE IF NOT EXISTS `ub_times` (
  `ub_id` int(10) unsigned NOT NULL,
  `ub_time` int(10) unsigned NOT NULL
  PRIMARY KEY (`ub_id`, `npc_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
