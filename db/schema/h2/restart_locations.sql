-- ----------------------------
-- Table structure for `RESTART_LOCATIONS`
-- ----------------------------
DROP TABLE IF EXISTS `RESTART_LOCATIONS`;
CREATE TABLE `RESTART_LOCATIONS` (
  `AREA` INT NOT NULL DEFAULT '0',
  `NOTE` VARCHAR(255) DEFAULT NULL,
  `LOC_X` INT NOT NULL DEFAULT '0',
  `LOC_Y` INT NOT NULL DEFAULT '0',
  `MAP_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `RESTART_LOCATIONS`
-- ----------------------------
CREATE PRIMARY KEY `RESTART_LOCATIONS_PK` ON `RESTART_LOCATIONS`(`AREA`);
