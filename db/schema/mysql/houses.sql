SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `houses`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `houses` (
  `house_id` int(10) unsigned NOT NULL DEFAULT '0',
  `house_name` varchar(45) NOT NULL DEFAULT '',
  `house_area` int(10) unsigned NOT NULL DEFAULT '0',
  `location` varchar(45) NOT NULL DEFAULT '',
  `keeper_id` int(10) unsigned NOT NULL DEFAULT '0',
  `is_on_sale` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `is_purchase_basement` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `tax_deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
