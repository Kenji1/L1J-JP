SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE IF NOT EXISTS `shop` (
  `npc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `order_id` int(10) unsigned NOT NULL DEFAULT '0',
  `selling_price` int(10) NOT NULL DEFAULT '-1',
  `pack_count` int(10) unsigned NOT NULL DEFAULT '0',
  `purchasing_price` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`npc_id`,`item_id`,`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
