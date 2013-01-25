SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `drop_rates`
-- ----------------------------
DROP TABLE IF EXISTS `drop_rates`;
CREATE TABLE IF NOT EXISTS `drop_rates` (
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `drop_rate` float unsigned NOT NULL DEFAULT '1',
  `drop_amount` float unsigned NOT NULL DEFAULT '1',
  `unique_rate` float unsigned NOT NULL DEFAULT '1',
  `note` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
