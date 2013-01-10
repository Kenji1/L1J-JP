SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `inn_key`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `inn_key` (
  `item_obj_id` int(10) unsigned NOT NULL,
  `key_id` int(10) unsigned NOT NULL,
  `npc_id` int(10) unsigned DEFAULT NULL,
  `hall` int(10) unsigned DEFAULT NULL,
  `due_time` datetime DEFAULT NULL,
  PRIMARY KEY (`item_obj_id`,`key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
