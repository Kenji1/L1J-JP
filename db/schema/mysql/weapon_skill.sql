SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `weapon_skill`
-- ----------------------------
DROP TABLE IF EXISTS `weapon_skill`;
CREATE TABLE IF NOT EXISTS `weapon_skill` (
  `weapon_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `probability` int(10) unsigned NOT NULL DEFAULT '0',
  `prob_enchant` int(10) NOT NULL DEFAULT '0',
  `fix_damage` int(10) unsigned NOT NULL DEFAULT '0',
  `random_damage` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `arrow_type` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `enable_mr` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `enable_attr_mr` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`weapon_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=259 ;
