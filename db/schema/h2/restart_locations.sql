-- ----------------------------
-- Table structure for `RESTART_LOCATIONS`
-- ----------------------------
DROP TABLE IF EXISTS `RESTART_LOCATIONS`;
CREATE TABLE `RESTART_LOCATIONS` (
  `AREA` INT NOT NULL,
  `LOC_X` INT NOT NULL,
  `LOC_Y` INT NOT NULL,
  `MAP_ID` INT NOT NULL,
  `NOTE` VARCHAR(255) DEFAULT NULL
);
-- ----------------------------
-- Index for `RESTART_LOCATIONS`
-- ----------------------------
CREATE PRIMARY KEY `RESTART_LOCATIONS_PK` ON `RESTART_LOCATIONS`(`AREA`);
