SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `shops`
-- ----------------------------
DROP TABLE IF EXISTS `shops`;
CREATE TABLE `shops` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `npc_name` varchar(255) NOT NULL DEFAULT '',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_name` varchar(255) NOT NULL DEFAULT '',
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `pack_count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`npc_id`,`item_id`,`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
