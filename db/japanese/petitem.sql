SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `petitem`
-- ----------------------------
DROP TABLE IF EXISTS `petitem`;
CREATE TABLE IF NOT EXISTS `petitem` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `note` varchar(45) NOT NULL DEFAULT '',
  `hitmodifier` int(3) NOT NULL DEFAULT '0',
  `dmgmodifier` int(3) NOT NULL DEFAULT '0',
  `ac` int(3) NOT NULL DEFAULT '0',
  `add_str` int(2) NOT NULL DEFAULT '0',
  `add_con` int(2) NOT NULL DEFAULT '0',
  `add_dex` int(2) NOT NULL DEFAULT '0',
  `add_int` int(2) NOT NULL DEFAULT '0',
  `add_wis` int(2) NOT NULL DEFAULT '0',
  `add_hp` int(10) NOT NULL DEFAULT '0',
  `add_mp` int(10) NOT NULL DEFAULT '0',
  `add_sp` int(10) NOT NULL DEFAULT '0',
  `m_def` int(2) NOT NULL DEFAULT '0',
  `use_type` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=40767 ;

-- ----------------------------
-- Records of petitem
-- ----------------------------
INSERT INTO `petitem` VALUES(40749, 'ハンター ファング', 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `petitem` VALUES(40750, 'ルーイン ファング', -3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `petitem` VALUES(40751, 'コンバット ファング', 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `petitem` VALUES(40752, 'ゴールド ファング', 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1);
INSERT INTO `petitem` VALUES(40756, 'ディバイン ファング', 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 1);
INSERT INTO `petitem` VALUES(40757, 'スチール ファング', 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `petitem` VALUES(40758, 'ヴィクトリー ファング', 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `petitem` VALUES(40761, 'レザー ペットアーマー', 0, 0, -4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `petitem` VALUES(40762, 'ボーン ペットアーマー', 0, 0, -7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `petitem` VALUES(40763, 'スチール ペットアーマー', 0, 0, -8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `petitem` VALUES(40764, 'ミスリル ペットアーマー', 0, 0, -12, 0, 0, 0, 1, 1, 0, 0, 0, 10, 0);
INSERT INTO `petitem` VALUES(40765, 'クロス ペットアーマー', 0, 0, -13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `petitem` VALUES(40766, 'チェーン ペットアーマー', 0, 0, -20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
