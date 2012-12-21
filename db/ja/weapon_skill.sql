SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `weapon_skill`
-- ----------------------------
DROP TABLE IF EXISTS `weapon_skill`;
CREATE TABLE IF NOT EXISTS `weapon_skill` (
  `weapon_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `probability` int(10) unsigned NOT NULL DEFAULT '0',
  `fix_damage` int(10) unsigned NOT NULL DEFAULT '0',
  `random_damage` int(10) unsigned NOT NULL DEFAULT '0',
  `area` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_time` int(10) unsigned NOT NULL DEFAULT '0',
  `effect_id` int(10) unsigned NOT NULL DEFAULT '0',
  `effect_target` int(10) unsigned NOT NULL DEFAULT '0',
  `arrow_type` int(10) unsigned NOT NULL DEFAULT '0',
  `attr` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`weapon_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=259 ;

-- ----------------------------
-- Records of weapon_skill
-- ----------------------------
INSERT INTO `weapon_skill` VALUES(47, 'サイレンス ソード', 2, 0, 0, 0, 64, 16, 2177, 0, 0, 0);
INSERT INTO `weapon_skill` VALUES(54, 'カーツ ソード', 15, 35, 25, 0, 0, 0, 10, 0, 0, 8);
INSERT INTO `weapon_skill` VALUES(58, 'デスナイト フレイムブレード', 7, 75, 15, 0, 0, 0, 1811, 0, 0, 2);
INSERT INTO `weapon_skill` VALUES(76, 'ロンドゥ デュアル ブレード', 15, 35, 25, 0, 0, 0, 1805, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(121, 'アイスクイーン スタッフ', 25, 95, 55, 0, 0, 0, 1810, 0, 0, 4);
INSERT INTO `weapon_skill` VALUES(203, 'バルログのツーハンド ソード', 15, 90, 90, 2, 0, 0, 762, 0, 0, 2);
INSERT INTO `weapon_skill` VALUES(205, 'ルナ ロング ボウ', 5, 8, 0, 0, 0, 0, 6288, 0, 1, 0);
INSERT INTO `weapon_skill` VALUES(256, 'ハロウィン パンプキン ロングソード', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(257, 'ハロウィン ロングソード', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(258, 'アルティメット ハロウィン ロングソード', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(303, 'ハロウィン パンプキン スタッフ', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(304, 'ハロウィン パンプキン ロングソード 2011', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(305, 'ハロウィン パンプキン ツーハンドソード 2011', 8, 0, 0, 0, 167, 0, 7849, 0, 0, 0);
INSERT INTO `weapon_skill` VALUES(306, 'ハロウィン パンプキン スタッフ 2011', 8, 0, 0, 0, 167, 0, 7849, 0, 0, 0);
INSERT INTO `weapon_skill` VALUES(307, 'ハロウィン パンプキン ロングボウ 2011', 8, 0, 0, 0, 167, 0, 7849, 0, 0, 0);
INSERT INTO `weapon_skill` VALUES(308, 'ハロウィン パンプキン ロングソード 2012', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(309, 'ハロウィン パンプキン ツーハンドソード 2012', 8, 35, 25, 0, 0, 0, 2750, 0, 0, 1);
INSERT INTO `weapon_skill` VALUES(310, 'ハロウィン パンプキン スタッフ 2012', 8, 0, 0, 0, 167, 0, 7849, 0, 0, 0);
INSERT INTO `weapon_skill` VALUES(311, 'ハロウィン パンプキン ロングボウ 2012', 8, 0, 0, 0, 167, 0, 7849, 0, 0, 0);
