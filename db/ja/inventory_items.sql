SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `inventory_items`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `inventory_items` (
  `id` int(11) unsigned NOT NULL,
  `owner_id` int(11) unsigned NOT NULL,
  `location` int(11) NOT NULL,
  `item_id` int(7) unsigned NOT NULL,
  `item_count` int(11) unsigned NOT NULL,
  `is_equipped` tinyint(1) unsigned NOT NULL,
  `enchant_level` tinyint(3) NOT NULL,
  `is_identified` tinyint(1) unsigned NOT NULL,
  `durability` int(11) NOT NULL,
  `charge_count` int(11) unsigned NOT NULL,
  `charge_time` int(11) unsigned NOT NULL,
  `expiration_time` datetime DEFAULT NULL,
  `last_used` datetime DEFAULT NULL,
  `is_sealed` tinyint(1) unsigned NOT NULL,
  `attr_enchant_kind` tinyint(3) NOT NULL,
  `attr_enchant_level` tinyint(3) NOT NULL,
  `ac` tinyint(3) DEFAULT '0',
  `str` tinyint(3) NOT NULL DEFAULT '0',
  `con` tinyint(3) NOT NULL DEFAULT '0',
  `dex` tinyint(3) NOT NULL DEFAULT '0',
  `wis` tinyint(3) NOT NULL DEFAULT '0',
  `cha` tinyint(3) NOT NULL DEFAULT '0',
  `int` tinyint(3) NOT NULL DEFAULT '0',
  `hp` int(5) NOT NULL DEFAULT '0',
  `hpr` int(5) NOT NULL DEFAULT '0',
  `mp` int(5) NOT NULL DEFAULT '0',
  `mpr` int(5) NOT NULL DEFAULT '0',
  `mr` tinyint(3) NOT NULL DEFAULT '0',
  `sp` tinyint(3) NOT NULL DEFAULT '0',
  `hit_modifier` tinyint(3) NOT NULL DEFAULT '0',
  `dmg_modifier` tinyint(3) NOT NULL DEFAULT '0',
  `defense_earth` tinyint(3) NOT NULL DEFAULT '0',
  `defense_water` tinyint(3) NOT NULL DEFAULT '0',
  `defense_fire` tinyint(3) NOT NULL DEFAULT '0',
  `defense_wind` tinyint(3) NOT NULL DEFAULT '0',
  `resist_stun` tinyint(3) NOT NULL DEFAULT '0',
  `resist_stone` tinyint(3) NOT NULL DEFAULT '0',
  `resist_sleep` tinyint(3) NOT NULL DEFAULT '0',
  `resist_freeze` tinyint(3) NOT NULL DEFAULT '0',
  `resist_hold` tinyint(3) NOT NULL DEFAULT '0',
  `resist_blind` tinyint(3) NOT NULL DEFAULT '0',
  `exp_bonus` tinyint(3) NOT NULL DEFAULT '0',
  `is_haste` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `can_be_dmg` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `is_unique` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `potion_recovery_rate` tinyint(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_index` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventory_items
-- ----------------------------