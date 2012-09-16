SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `map_timer`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `map_timer` (
  `char_id` int(10) unsigned NOT NULL,
  `map_id` int(10) unsigned NOT NULL,
  `area_id` int(10) unsigned NOT NULL,
  `enter_time` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_id`,`area_id`),
  KEY `map_id` (`map_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of map_timer
-- ----------------------------
