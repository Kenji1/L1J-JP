-- ----------------------------
-- Table structure for BAN_IPS
-- ----------------------------
CREATE TABLE IF NOT EXISTS `BAN_IPS` (
  `IP` VARCHAR(255) NOT NULL DEFAULT '',
  `HOST` VARCHAR(255) NOT NULL DEFAULT '',
  `MASK` INT DEFAULT '32'
);
-- ----------------------------
-- Index for `BAN_IPS`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `BAN_IPS_PK` ON `BAN_IPS`(`IP`);
