-- ----------------------------
-- Table structure for `COOKING_INGREDIENTS`
-- ----------------------------
DROP TABLE IF EXISTS `COOKING_INGREDIENTS`;
CREATE TABLE `COOKING_INGREDIENTS` (
  `ID` INT NOT NULL,
  `COOKING_RECIPE_ID` INT NOT NULL,
  `ITEM_ID` INT NOT NULL,
  `AMOUNT` INT NOT NULL
);
-- ----------------------------
-- Index for `COOKING_INGREDIENTS`
-- ----------------------------
CREATE PRIMARY KEY `COOKING_INGREDIENTS_PK` ON `COOKING_INGREDIENTS`(`ID`);
