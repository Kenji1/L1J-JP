SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `spawnlist_time`
-- ----------------------------
DROP TABLE IF EXISTS `spawnlist_time`;
CREATE TABLE IF NOT EXISTS `spawnlist_time` (
  `spawn_id` int(10) NOT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `delete_at_endtime` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`spawn_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spawnlist_time
-- ----------------------------
INSERT INTO `spawnlist_time` VALUES(62086, '18:00:00', '04:00:00', 1);
INSERT INTO `spawnlist_time` VALUES(62087, '18:00:00', '04:00:00', 1);
INSERT INTO `spawnlist_time` VALUES(62088, '18:00:00', '04:00:00', 1);
INSERT INTO `spawnlist_time` VALUES(62089, '18:00:00', '04:00:00', 1);
INSERT INTO `spawnlist_time` VALUES(62092, '18:00:00', '04:00:00', 1);
