-- ----------------------------
-- Table structure for `COOKING_RECIPES`
-- ----------------------------
DROP TABLE IF EXISTS `COOKING_RECIPES`;
CREATE TABLE `COOKING_RECIPES` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `DISH_ID` INT NOT NULL,
  `DISH_AMOUNT` INT NOT NULL,
  `FANTASY_DISH_ID` INT NOT NULL,
  `FANTASY_DISH_AMOUNT` INT NOT NULL
);
-- ----------------------------
-- Index for `COOKING_RECIPES`
-- ----------------------------
CREATE PRIMARY KEY `COOKING_RECIPES_PK` ON `COOKING_RECIPES`(`ID`);
