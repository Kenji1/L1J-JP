-- ----------------------------
-- Table structure for `GETBACK_RESTART`
-- ----------------------------
DROP TABLE IF EXISTS `GETBACK_RESTART`;
CREATE TABLE `GETBACK_RESTART` (
  `AREA` INT NOT NULL DEFAULT '0',
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `LOCX` INT NOT NULL DEFAULT '0',
  `LOCY` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `GETBACK_RESTART`
-- ----------------------------
CREATE PRIMARY KEY `GETBACK_RESTART_PK` ON `GETBACK_RESTART`(`AREA`);
