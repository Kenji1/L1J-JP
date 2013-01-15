SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `log_chat`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `log_chat` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `account_name` varchar(50) NOT NULL,
  `char_id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `clan_id` int(10) NOT NULL,
  `clan_name` varchar(50) DEFAULT NULL,
  `loc_x` int(10) NOT NULL,
  `loc_y` int(10) NOT NULL,
  `map_id` int(10) NOT NULL,
  `type` int(10) NOT NULL,
  `target_account_name` varchar(50) DEFAULT NULL,
  `target_id` int(10) DEFAULT '0',
  `target_name` varchar(50) DEFAULT NULL,
  `target_clan_id` int(10) DEFAULT NULL,
  `target_clan_name` varchar(50) DEFAULT NULL,
  `target_loc_x` int(10) DEFAULT NULL,
  `target_loc_y` int(10) DEFAULT NULL,
  `target_map_id` int(10) DEFAULT NULL,
  `content` varchar(256) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
