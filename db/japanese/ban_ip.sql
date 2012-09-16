SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ban_ip`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ban_ip` (
  `ip` varchar(255) NOT NULL DEFAULT '',
  `mask` int(255) DEFAULT '32',
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ban_ip
-- ----------------------------
