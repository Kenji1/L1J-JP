SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `etc_items`
-- ----------------------------
DROP TABLE IF EXISTS `etc_items`;
CREATE TABLE `etc_items` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `unidentified_name_id` varchar(255) NOT NULL DEFAULT '',
  `identified_name_id` varchar(255) NOT NULL DEFAULT '',
  `item_type` varchar(255) NOT NULL DEFAULT '',
  `use_type` varchar(255) NOT NULL DEFAULT '',
  `material` varchar(255) NOT NULL DEFAULT '',
  `weight` int(10) unsigned NOT NULL DEFAULT '0',
  `inv_gfx_id` int(10) unsigned NOT NULL DEFAULT '0',
  `grd_gfx_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_desc_id` int(10) unsigned NOT NULL DEFAULT '0',
  `stackable` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `max_charge_count` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `dmg_small` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `dmg_large` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `min_level` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `max_level` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `loc_x` int(10) unsigned NOT NULL DEFAULT '0',
  `loc_y` int(10) unsigned NOT NULL DEFAULT '0',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `bless` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `tradable` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `deletable` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `sealable` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `delay_id` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `delay_time` int(10) unsigned NOT NULL DEFAULT '0',
  `delay_effect` int(10) unsigned NOT NULL DEFAULT '0',
  `food_volume` int(10) unsigned NOT NULL DEFAULT '0',
  `save_at_once` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `charge_time` int(10) unsigned NOT NULL DEFAULT '0',
  `expiration_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
