-- ----------------------------
-- Table structure for BAN_IP
-- ----------------------------
CREATE TABLE IF NOT EXISTS `BAN_IP` (
  `IP` VARCHAR(255) NOT NULL DEFAULT '',
  `HOST` VARCHAR(255) NOT NULL DEFAULT '',
  `MASK` INT DEFAULT '32'
);
-- ----------------------------
-- Index for `BAN_IP`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `BAN_IP_PK` ON `BAN_IP`(`IP`);
