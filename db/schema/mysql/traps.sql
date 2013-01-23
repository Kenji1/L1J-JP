SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `traps`
-- ----------------------------
DROP TABLE IF EXISTS `traps`;
CREATE TABLE IF NOT EXISTS `traps` (
  `id` int(10) NOT NULL,
  `note` varchar(64) DEFAULT NULL,
  `type` varchar(64) NOT NULL,
  `gfx_id` int(10) NOT NULL,
  `is_detectionable` tinyint(1) NOT NULL,
  `base` int(10) NOT NULL,
  `dice` int(10) NOT NULL,
  `dice_count` int(10) NOT NULL,
  `poison_type` varchar(1) NOT NULL DEFAULT 'n',
  `poison_delay` int(10) NOT NULL DEFAULT '0',
  `poison_time` int(10) NOT NULL DEFAULT '0',
  `poison_damage` int(10) NOT NULL DEFAULT '0',
  `monster_npc_id` int(10) NOT NULL DEFAULT '0',
  `monster_count` int(10) NOT NULL DEFAULT '0',
  `teleport_x` int(10) NOT NULL DEFAULT '0',
  `teleport_y` int(10) NOT NULL DEFAULT '0',
  `teleport_map_id` int(10) NOT NULL DEFAULT '0',
  `skill_id` int(10) NOT NULL DEFAULT '0',
  `skill_time_seconds` int(10) NOT NULL DEFAULT '0',
  `switch_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
