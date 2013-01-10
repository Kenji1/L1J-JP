SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawnlist_trap`
-- ----------------------------
DROP TABLE IF EXISTS `spawnlist_trap`;
CREATE TABLE IF NOT EXISTS `spawnlist_trap` (
  `id` int(10) NOT NULL,
  `note` varchar(64) DEFAULT NULL,
  `trap_id` int(10) NOT NULL,
  `mapid` int(10) NOT NULL,
  `locx` int(10) NOT NULL,
  `locy` int(10) NOT NULL,
  `loc_rnd_x` int(10) NOT NULL DEFAULT '0',
  `loc_rnd_y` int(10) NOT NULL DEFAULT '0',
  `count` int(10) NOT NULL DEFAULT '1',
  `span` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
