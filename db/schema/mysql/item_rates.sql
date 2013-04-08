SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `item_rates`
-- ----------------------------
DROP TABLE IF EXISTS `item_rates`;
CREATE TABLE `item_rates` (
  `item_id` int(10) unsigned NOT NULL,
  `selling_price` int(10) NOT NULL DEFAULT '-1',
  `purchasing_price` int(10) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
