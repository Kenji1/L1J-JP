SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `race_tickets`
-- ----------------------------
DROP TABLE IF EXISTS `race_tickets`;
CREATE TABLE IF NOT EXISTS `race_tickets` (
  `item_obj_id` int(11) NOT NULL,
  `round` int(7) NOT NULL,
  `allotment_percentage` double NOT NULL,
  `victory` int(1) NOT NULL,
  `runner_num` int(2) NOT NULL,
  PRIMARY KEY (`item_obj_id`,`round`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
