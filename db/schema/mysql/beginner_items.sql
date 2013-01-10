SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `beginner_items`
-- ----------------------------
DROP TABLE IF EXISTS `beginner_items`;
CREATE TABLE IF NOT EXISTS `beginner_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_count` int(10) unsigned NOT NULL DEFAULT '0',
  `charge_count` int(10) unsigned NOT NULL DEFAULT '0',
  `enchant_level` tinyint(3) NOT NULL DEFAULT '0',
  `note` varchar(50) NOT NULL DEFAULT '',
  `class_initial` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  KEY `class_initial` (`class_initial`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=40 ;
