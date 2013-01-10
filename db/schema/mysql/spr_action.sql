SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for spr_action
-- ----------------------------
DROP TABLE IF EXISTS `spr_action`;
CREATE TABLE IF NOT EXISTS `spr_action` (
`spr_id` int(10) unsigned NOT NULL,
`act_id` int(10) unsigned NOT NULL,
`framecount` int(10) unsigned NOT NULL DEFAULT '0',
`framerate` int(10) unsigned NOT NULL DEFAULT '24',
PRIMARY KEY (`spr_id`,`act_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
