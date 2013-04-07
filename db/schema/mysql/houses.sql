SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `houses`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `houses` (
  `id` int(10) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL DEFAULT '',
  `area` int(10) unsigned NOT NULL DEFAULT '0',
  `location` varchar(255) NOT NULL DEFAULT '',
  `keeper_id` int(10) unsigned NOT NULL DEFAULT '0',
  `is_on_sale` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `is_purchase_basement` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `tax_deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
