SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `droplist`
-- ----------------------------
DROP TABLE IF EXISTS `droplist`;
CREATE TABLE IF NOT EXISTS `droplist`(
  `mob_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `min` int(10) unsigned NOT NULL DEFAULT '0',
  `max` int(10) unsigned NOT NULL DEFAULT '0',
  `chance` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY(`mob_id`,`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
