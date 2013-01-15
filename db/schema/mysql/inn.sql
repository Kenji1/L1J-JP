SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `inn`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `inn` (
  `name` varchar(45) DEFAULT '',
  `npc_id` int(10) unsigned NOT NULL,
  `room_number` int(10) unsigned NOT NULL,
  `key_id` int(10) unsigned DEFAULT NULL,
  `lodger_id` int(10) unsigned DEFAULT NULL,
  `hall` int(10) unsigned DEFAULT NULL,
  `due_time` datetime DEFAULT NULL,
  PRIMARY KEY (`npcid`,`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
