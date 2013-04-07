-- ----------------------------
-- Table structure for `AUCTION_HOUSES`
-- ----------------------------
CREATE TABLE IF NOT EXISTS `AUCTION_HOUSES` (
  `HOUSE_ID` INT NOT NULL DEFAULT '0',\
  `DEADLINE` DATETIME DEFAULT NULL,
  `PRICE` INT NOT NULL DEFAULT '0',
  `OWNER_ID` INT NOT NULL DEFAULT '0',
  `BIDDER_ID` INT NOT NULL DEFAULT '0'
);
-- ----------------------------
-- Index for `AUCTION_HOUSES`
-- ----------------------------
CREATE PRIMARY KEY IF NOT EXISTS `AUCTION_HOUSES_PK` ON `AUCTION_HOUSES`(`HOUSE_ID`);
