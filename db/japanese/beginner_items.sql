SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `beginner_items`
-- ----------------------------
DROP TABLE IF EXISTS `beginner_items`;
CREATE TABLE IF NOT EXISTS `beginner_items` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `item_id` int(10) unsigned NOT NULL DEFAULT '0',
  `item_count` int(10) unsigned NOT NULL DEFAULT '0',
  `charge_count` int(10) unsigned NOT NULL DEFAULT '0',
  `enchant_level` tinyint(3) NOT NULL DEFAULT '0',
  `note` varchar(50) NOT NULL DEFAULT '',
  `class_initial` varchar(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  KEY `class_initial` (`class_initial`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=40 ;

-- ----------------------------
-- Records of beginner_items
-- ----------------------------
INSERT INTO `beginner_items` VALUES(1, 20028, 1, 0, 0, '象牙の塔のヘルム', 'A');
INSERT INTO `beginner_items` VALUES(2, 20082, 1, 0, 0, '象牙の塔のTシャツ', 'A');
INSERT INTO `beginner_items` VALUES(3, 20126, 1, 0, 0, '象牙の塔のアーマー', 'A');
INSERT INTO `beginner_items` VALUES(4, 20173, 1, 0, 0, '象牙の塔のグローブ', 'A');
INSERT INTO `beginner_items` VALUES(5, 20206, 1, 0, 0, '象牙の塔のブーツ', 'A');
INSERT INTO `beginner_items` VALUES(6, 20232, 1, 0, 0, '象牙の塔のシールド', 'A');
INSERT INTO `beginner_items` VALUES(7, 21168, 1, 0, 0, '象牙の塔のクローク', 'A');
INSERT INTO `beginner_items` VALUES(8, 7, 1, 0, 0, '象牙の塔のダガー', 'P');
INSERT INTO `beginner_items` VALUES(9, 35, 1, 0, 0, '象牙の塔のソード', 'P');
INSERT INTO `beginner_items` VALUES(10, 48, 1, 0, 0, '象牙の塔のツーハンド ソード', 'P');
INSERT INTO `beginner_items` VALUES(11, 147, 1, 0, 0, '象牙の塔のアックス', 'P');
INSERT INTO `beginner_items` VALUES(12, 7, 1, 0, 0, '象牙の塔のダガー', 'K');
INSERT INTO `beginner_items` VALUES(13, 35, 1, 0, 0, '象牙の塔のソード', 'K');
INSERT INTO `beginner_items` VALUES(14, 48, 1, 0, 0, '象牙の塔のツーハンド ソード', 'K');
INSERT INTO `beginner_items` VALUES(15, 147, 1, 0, 0, '象牙の塔のアックス', 'K');
INSERT INTO `beginner_items` VALUES(16, 105, 1, 0, 0, '象牙の塔のスピアー', 'K');
INSERT INTO `beginner_items` VALUES(17, 174, 1, 0, 0, '象牙の塔のクロスボウ', 'K');
INSERT INTO `beginner_items` VALUES(18, 50540, 1000, 0, 0, '象牙の塔のアロー', 'K');
INSERT INTO `beginner_items` VALUES(19, 7, 1, 0, 0, '象牙の塔のダガー', 'E');
INSERT INTO `beginner_items` VALUES(20, 35, 1, 0, 0, '象牙の塔のソード', 'E');
INSERT INTO `beginner_items` VALUES(21, 174, 1, 0, 0, '象牙の塔のクロスボウ', 'E');
INSERT INTO `beginner_items` VALUES(22, 175, 1, 0, 0, '象牙の塔のボウ', 'E');
INSERT INTO `beginner_items` VALUES(23, 50540, 1000, 0, 0, '象牙の塔のアロー', 'E');
INSERT INTO `beginner_items` VALUES(24, 7, 1, 0, 0, '象牙の塔のダガー', 'W');
INSERT INTO `beginner_items` VALUES(25, 120, 1, 0, 0, '象牙の塔のスタッフ', 'W');
INSERT INTO `beginner_items` VALUES(26, 7, 1, 0, 0, '象牙の塔のダガー', 'D');
INSERT INTO `beginner_items` VALUES(27, 35, 1, 0, 0, '象牙の塔のソード', 'D');
INSERT INTO `beginner_items` VALUES(28, 73, 1, 0, 0, '象牙の塔のデュアルブレード', 'D');
INSERT INTO `beginner_items` VALUES(29, 156, 1, 0, 0, '象牙の塔のクロウ', 'D');
INSERT INTO `beginner_items` VALUES(30, 174, 1, 0, 0, '象牙の塔のクロスボウ', 'D');
INSERT INTO `beginner_items` VALUES(31, 50540, 1000, 0, 0, '象牙の塔のアロー', 'D');
INSERT INTO `beginner_items` VALUES(32, 35, 1, 0, 0, '象牙の塔のソード', 'R');
INSERT INTO `beginner_items` VALUES(33, 48, 1, 0, 0, '象牙の塔のツーハンド ソード', 'R');
INSERT INTO `beginner_items` VALUES(34, 147, 1, 0, 0, '象牙の塔のアックス', 'R');
INSERT INTO `beginner_items` VALUES(35, 120, 1, 0, 0, '象牙の塔のスタッフ', 'I');
INSERT INTO `beginner_items` VALUES(36, 147, 1, 0, 0, '象牙の塔のアックス', 'I');
INSERT INTO `beginner_items` VALUES(37, 174, 1, 0, 0, '象牙の塔のクロスボウ', 'I');
INSERT INTO `beginner_items` VALUES(38, 175, 1, 0, 0, '象牙の塔のボウ', 'I');
INSERT INTO `beginner_items` VALUES(39, 50540, 1000, 0, 0, '象牙の塔のアロー', 'I');
