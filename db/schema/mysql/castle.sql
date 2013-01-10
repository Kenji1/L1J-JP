SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `castle`
-- ----------------------------
DROP TABLE IF EXISTS `castle`;
CREATE TABLE IF NOT EXISTS `castle` (
  `castle_id` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `war_time` datetime DEFAULT NULL,
  `tax_rate` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `public_money` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`castle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
