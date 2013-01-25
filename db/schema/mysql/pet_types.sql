SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pet_types`
-- ----------------------------
DROP TABLE IF EXISTS `pet_types`;
CREATE TABLE IF NOT EXISTS `pet_types` (
  `base_npc_id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `tame_item_id` int(10) NOT NULL,
  `min_hpup` int(10) NOT NULL,
  `max_hpup` int(10) NOT NULL,
  `min_mpup` int(10) NOT NULL,
  `max_mpup` int(10) NOT NULL,
  `transform_item_id` int(10) NOT NULL,
  `transform_npc_id` int(10) NOT NULL,
  `message_id1` int(10) NOT NULL,
  `message_id2` int(10) NOT NULL,
  `message_id3` int(10) NOT NULL,
  `message_id4` int(10) NOT NULL,
  `message_id5` int(10) NOT NULL,
  `defy_message_id` int(10) NOT NULL,
  `use_equipment` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`base_npc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
