SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `door_gfxs`
-- ----------------------------
DROP TABLE IF EXISTS `door_gfxs`;
CREATE TABLE `door_gfxs` (
  `gfx_id` int(10) unsigned NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `direction` tinyint(1) unsigned NOT NULL,
  `left_edge_offset` int(10) NOT NULL,
  `right_edge_offset` int(10) NOT NULL,
  PRIMARY KEY (`gfx_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
