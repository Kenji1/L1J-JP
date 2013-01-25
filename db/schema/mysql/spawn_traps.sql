SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawn_traps`
-- ----------------------------
DROP TABLE IF EXISTS `spawn_traps`;
CREATE TABLE IF NOT EXISTS `spawn_traps` (
  `id` int(10) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `trap_id` int(10) NOT NULL,
  `map_id` int(10) NOT NULL,
  `loc_x` int(10) NOT NULL,
  `loc_y` int(10) NOT NULL,
  `loc_rnd_x` int(10) NOT NULL DEFAULT '0',
  `loc_rnd_y` int(10) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '1',
  `span` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
