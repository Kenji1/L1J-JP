SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_skills`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `character_skills` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_obj_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_name` varchar(45) NOT NULL DEFAULT '',
  `is_active` tinyint(1) unsigned DEFAULT NULL,
  `active_time_left` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`char_obj_id`,`skill_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
