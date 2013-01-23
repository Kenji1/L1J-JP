SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawn_doors`
-- ----------------------------
DROP TABLE IF EXISTS `spawn_doors`;
CREATE TABLE `spawn_doors` (
  `id` int(10) NOT NULL DEFAULT '0',
  `location` varchar(25) NOT NULL DEFAULT '',
  `gfx_id` int(10) NOT NULL DEFAULT '0',
  `loc_x` int(10) NOT NULL DEFAULT '0',
  `loc_y` int(10) NOT NULL DEFAULT '0',
  `map_id` int(10) NOT NULL DEFAULT '0',
  `hp` int(10) NOT NULL DEFAULT '0',
  `keeper` int(10) NOT NULL DEFAULT '0',
  `is_open` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ドアの開閉フラグ\r\n1＝開状態\r\n0＝閉状態',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
