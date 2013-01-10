SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ub_times`
-- ----------------------------
DROP TABLE IF EXISTS `ub_times`;
CREATE TABLE IF NOT EXISTS `ub_times` (
  `ub_id` int(10) unsigned NOT NULL DEFAULT '0',
  `ub_time` int(10) unsigned NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
