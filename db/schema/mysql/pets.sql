SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pets`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `pets` (
  `item_obj_id` int(10) unsigned NOT NULL DEFAULT '0',
  `obj_id` int(10) unsigned NOT NULL DEFAULT '0',
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `level` int(10) unsigned NOT NULL DEFAULT '0',
  `hp` int(10) unsigned NOT NULL DEFAULT '0',
  `mp` int(10) unsigned NOT NULL DEFAULT '0',
  `exp` int(10) unsigned NOT NULL DEFAULT '0',
  `lawful` int(10) unsigned NOT NULL DEFAULT '0',
  `food` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
