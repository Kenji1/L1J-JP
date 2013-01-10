SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_config`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `character_config` (
  `object_id` int(10) unsigned NOT NULL DEFAULT '0',
  `length` int(10) unsigned NOT NULL DEFAULT '0',
  `data` blob,
  PRIMARY KEY (`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
