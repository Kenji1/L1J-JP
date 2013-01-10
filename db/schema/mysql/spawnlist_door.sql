SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawnlist_door`
-- ----------------------------
DROP TABLE IF EXISTS `spawnlist_door`;
CREATE TABLE `spawnlist_door` (
  `id` int(10) NOT NULL DEFAULT '0',
  `location` varchar(25) NOT NULL DEFAULT '',
  `gfxid` int(10) NOT NULL DEFAULT '0',
  `locx` int(10) NOT NULL DEFAULT '0',
  `locy` int(10) NOT NULL DEFAULT '0',
  `mapid` int(10) NOT NULL DEFAULT '0',
  `hp` int(10) NOT NULL DEFAULT '0',
  `keeper` int(10) NOT NULL DEFAULT '0',
  `DoorOpen` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'ドアの開閉フラグ\r\n1＝開状態\r\n0＝閉状態',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
