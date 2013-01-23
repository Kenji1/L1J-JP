SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `dungeons`
-- ----------------------------
DROP TABLE IF EXISTS `dungeons`;
CREATE TABLE `dungeons` (
  `src_x` int(10) unsigned NOT NULL DEFAULT '0',
  `src_y` int(10) unsigned NOT NULL DEFAULT '0',
  `src_map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `new_x` int(10) unsigned NOT NULL DEFAULT '0',
  `new_y` int(10) unsigned NOT NULL DEFAULT '0',
  `new_map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `new_heading` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `note` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`src_x`,`src_y`,`src_map_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
