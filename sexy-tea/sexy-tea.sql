/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : sexy-tea

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 23/09/2020 16:57:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for beverage
-- ----------------------------
DROP TABLE IF EXISTS `beverage`;
CREATE TABLE `beverage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '饮料表id',
  `beverage_id` int(0) NULL DEFAULT NULL COMMENT '饮料编号',
  `beverage_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '饮料名称',
  `beverage_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '饮料图片',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主分类',
  `sub_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子分类',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of beverage
-- ----------------------------

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '星礼卡表id',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `card_id` int(0) NULL DEFAULT NULL COMMENT '星礼卡卡号',
  `card_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '星礼卡名称',
  `card_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '星礼卡图片',
  `balance` decimal(5, 2) NULL DEFAULT NULL COMMENT '余额',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card
-- ----------------------------

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int(0) NOT NULL COMMENT '城市表id',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市名',
  `center_longitude` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市经度',
  `center_latitude` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市纬度',
  `count` int(0) NULL DEFAULT NULL COMMENT '门店数量',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (0, '北京市', '116.407526', '39.904030', 234, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (1, '廊坊市', '116.683752', '39.538047', 5, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (2, '天津市', '117.200983', '39.084158', 58, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (3, '保定市', '115.464806', '38.873891', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (4, '唐山市', '118.180193', '39.630867', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (5, '张家口', '114.887543', '40.824418', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (6, '承德市', '117.962410', '40.954071', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (7, '石家庄', '114.514860', '38.042307', 7, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (8, '秦皇岛', '119.600492', '39.935385', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (9, '邢台市', '114.504844', '37.070589', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (10, '邯郸', '114.538961', '36.625657', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (11, '太原市', '112.548879', '37.870590', 8, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (12, '包头市', '109.840347', '40.657449', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (13, '呼和浩特', '111.749180', '40.842585', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (14, '大连市', '121.614682', '38.914003', 25, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (15, '抚顺市', '123.957208', '41.880872', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (16, '沈阳市', '123.431474', '41.805698', 24, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (17, '盘锦市', '122.070714', '41.119997', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (18, '营口市', '122.235417', '40.667012', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (19, '鞍山市', '122.994329', '41.108647', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (20, '吉林市', '126.549576', '43.837883', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (21, '延吉市', '129.508946', '42.891255', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (22, '长春市', '125.323544', '43.817071', 10, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (23, '哈尔滨', '126.534967', '45.803775', 14, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (24, '大庆市', '125.103784', '46.589309', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (25, '上海市', '121.473701', '31.230416', 539, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (26, '宁波市', '121.550357', '29.874556', 59, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (27, '南京市', '118.796877', '32.060255', 73, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (28, '南通市', '120.894291', '31.980171', 21, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (29, '吴江市', '120.641602', '31.160404', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (30, '太仓市', '121.130550', '31.457735', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (31, '姜堰市', '115.508797', '32.830109', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (32, '宜兴市', '119.823308', '31.340637', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (33, '宿迁市', '118.275198', '33.963232', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (34, '常州市', '119.973987', '31.810689', 26, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (35, '常熟市', '120.752481', '31.654376', 6, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (36, '张家港', '120.768611', '31.446084', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (37, '张家港市', '120.479033', '31.899861', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (38, '徐州市', '117.284124', '34.205768', 7, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (39, '扬中市', '119.797635', '32.234831', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (40, '扬州市', '119.412966', '32.394210', 12, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (41, '无锡市', '120.311910', '31.491169', 40, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (42, '昆山市', '120.980737', '31.385598', 13, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (43, '江苏省', '118.763232', '32.061707', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (44, '江阴市', '120.284939', '31.920658', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (45, '泰州市', '119.923116', '32.455778', 7, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (46, '海门市', '121.181615', '31.871173', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (47, '淮安市', '119.015285', '33.610353', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (48, '盐城市', '120.163561', '33.347382', 6, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (49, '苏州市', '120.585315', '31.298886', 90, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (50, '连云港', '119.221611', '34.596653', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (51, '连云港市', '119.221611', '34.596653', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (52, '镇江市', '119.425836', '32.187849', 9, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (53, '靖江市', '120.277138', '31.982751', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (54, '高邮市', '119.459161', '32.781659', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (55, '上虞市', '118.897163', '32.466564', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (56, '东阳市', '120.241566', '29.289648', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (57, '丽水市', '119.922796', '28.467630', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (58, '临海市', '121.145047', '28.858457', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (59, '义乌市', '120.075058', '29.306841', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (60, '余姚市', '121.154594', '30.037858', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (61, '兰溪市', '119.460526', '29.208919', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (62, '台州市', '121.420757', '28.656386', 11, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (63, '嘉兴', '120.755486', '30.746129', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (64, '嘉兴市', '120.755486', '30.746129', 14, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (65, '嘉善市', '120.921867', '30.841352', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (66, '奉化市', '121.406728', '29.656560', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (67, '慈溪市', '121.266579', '30.169665', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (68, '杭州市', '120.155070', '30.274084', 117, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (69, '江山市', '118.626926', '28.737245', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (70, '海宁市', '120.680757', '30.510659', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (71, '温岭市', '121.385604', '28.372506', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (72, '温州市', '120.699366', '27.994267', 20, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (73, '湖州市', '120.086823', '30.894348', 12, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (74, '绍兴市', '120.580232', '30.029752', 18, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (75, '舟山市', '122.207215', '29.985295', 5, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (76, '萧山市', '120.264253', '30.183806', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (77, '衢州市', '118.859457', '28.970079', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (78, '诸暨市', '120.246863', '29.708692', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (79, '金华市', '119.647444', '29.079059', 11, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (80, '合肥市', '117.227239', '31.820586', 18, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (81, '芜湖市', '118.432941', '31.352859', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (82, '蚌埠市', '117.389719', '32.916287', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (83, '马鞍山', '118.506759', '31.670452', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (84, '厦门市', '118.089425', '24.479833', 31, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (85, '宁德市', '119.547932', '26.665617', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (86, '泉州市', '118.675675', '24.874132', 13, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (87, '漳州市', '117.647481', '24.512948', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (88, '福州市', '119.296494', '26.074507', 23, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (89, '莆田市', '119.007777', '25.454084', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (90, '龙岩市', '117.017536', '25.075123', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (91, '九江市', '116.001930', '29.705077', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (92, '南昌市', '115.858197', '28.682892', 11, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (93, '东营市', '118.674767', '37.434751', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (94, '临沂市', '118.356448', '35.104672', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (95, '威海市', '122.120419', '37.513068', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (96, '德州市', '116.357464', '37.434092', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (97, '日照市', '119.526888', '35.416377', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (98, '泰安市', '117.087614', '36.200252', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (99, '济南市', '117.119999', '36.651216', 16, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (100, '济宁市', '116.587098', '35.414921', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (101, '淄博市', '118.054927', '36.813487', 5, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (102, '潍坊市', '119.161755', '36.706774', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (103, '烟台市', '121.447935', '37.463822', 8, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (104, '青岛市', '120.382639', '36.067082', 28, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (105, '洛阳', '112.454040', '34.619682', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (106, '郑州市', '113.625368', '34.746599', 18, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (107, '十堰市', '110.797990', '32.629397', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (108, '宜昌市', '111.286471', '30.691967', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (109, '武汉市', '114.305392', '30.593098', 67, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (110, '襄樊市', '112.144150', '32.042427', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (111, '襄阳市', '112.122414', '32.008986', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (112, '黄石市', '115.038520', '30.199652', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (113, '岳阳市', '113.128958', '29.357104', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (114, '株洲市', '113.134002', '27.827550', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (115, '湘潭市', '112.944049', '27.829738', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (116, '衡阳市', '112.571997', '26.893230', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (117, '长沙市', '112.938814', '28.228209', 26, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (118, '东莞市', '113.751765', '23.020536', 31, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (119, '中山市', '113.392782', '22.517645', 11, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (120, '佛山市', '113.121416', '23.021548', 33, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (121, '广州市', '113.264434', '23.129162', 106, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (122, '惠州市', '114.416196', '23.111847', 7, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (123, '梅州市', '116.122238', '24.288615', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (124, '汕头市', '116.681972', '23.354091', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (125, '江门市', '113.081901', '22.578738', 6, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (126, '深圳市', '114.057868', '22.543099', 112, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (127, '清远市', '113.056031', '23.681763', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (128, '湛江市', '110.359377', '21.270707', 4, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (129, '珠海市', '113.576726', '22.270715', 14, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (130, '肇庆市', '112.465091', '23.047191', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (131, '阳江市', '111.982232', '21.857958', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (132, '北海市', '109.119927', '21.481254', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (133, '南宁市', '108.366543', '22.817002', 12, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (134, '柳州', '109.415953', '24.325502', 6, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (135, '桂林', '110.290194', '25.273566', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (136, '三亚市', '109.511909', '18.252847', 6, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (137, '儋州市', '109.580811', '19.521134', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (138, '海口市', '110.198293', '20.044001', 9, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (139, '重庆市', '106.551556', '29.563009', 41, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (140, '乐山市', '103.765568', '29.552106', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (141, '德阳市', '104.397894', '31.126855', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (142, '成都市', '104.066541', '30.572269', 98, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (143, '绵阳市', '104.679114', '31.467450', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (144, '贵阳', '106.630153', '26.647661', 8, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (145, '贵阳市', '106.630153', '26.647661', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (146, '丽江市', '100.227750', '26.855047', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (147, '大理市', '100.267638', '25.606486', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (148, '昆明市', '102.832891', '24.880095', 21, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (149, '咸阳市', '108.708991', '34.329605', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (150, '宝鸡市', '107.237974', '34.361979', 1, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (151, '西安市', '108.940174', '34.341568', 40, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (152, '兰州市', '103.834303', '36.061089', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (153, '西宁市', '101.778228', '36.617144', 3, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');
INSERT INTO `city` VALUES (154, '银川市', '106.230909', '38.487193', 2, 1, '2020-09-23 16:54:28', '2020-09-23 16:54:28');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '优惠券表id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优惠券标题',
  `start_date` date NULL DEFAULT NULL COMMENT '优惠券开始日期',
  `expire_date` date NULL DEFAULT NULL COMMENT '优惠券结束日期',
  `use_date` date NULL DEFAULT NULL COMMENT '优惠券使用日期',
  `use_status` int(0) NULL DEFAULT NULL COMMENT '优惠券使用状态',
  `goods_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品类型',
  `require_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '使用类型',
  `require` int(0) NULL DEFAULT NULL COMMENT '使用数量',
  `coupon_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优惠券类型',
  `coupon_num` int(0) NULL DEFAULT NULL COMMENT '优惠券数量',
  `coupon_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优惠券图片',
  `rules` json NULL COMMENT '优惠券规则',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for expense_record
-- ----------------------------
DROP TABLE IF EXISTS `expense_record`;
CREATE TABLE `expense_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '费用记录表id',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `consume_date` datetime(0) NULL DEFAULT NULL COMMENT '消费时间',
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商店名称',
  `goods` json NULL COMMENT '消费商品',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '消费总价',
  `star_gain` int(0) NULL DEFAULT NULL COMMENT '获得星数',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expense_record
-- ----------------------------

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '食品表id',
  `food_id` int(0) NULL DEFAULT NULL COMMENT '食品id',
  `food_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '食品名字',
  `food_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '食品图片',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主分类',
  `sub_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子分类',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '日志表id',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名字',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录地址',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `device` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备',
  `operation_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------

-- ----------------------------
-- Table structure for membership
-- ----------------------------
DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '会员表id',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户表id',
  `star_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员等级',
  `star_level_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员等级名称',
  `star_number` int(0) NULL DEFAULT NULL COMMENT '会员星数',
  `next_lev` int(0) NULL DEFAULT NULL COMMENT '下级会员需要星数',
  `privileges` json NULL COMMENT '会员特权',
  `expire_date` date NULL DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of membership
-- ----------------------------

-- ----------------------------
-- Table structure for merchandise
-- ----------------------------
DROP TABLE IF EXISTS `merchandise`;
CREATE TABLE `merchandise`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品表id',
  `product_id` int(0) NULL DEFAULT NULL COMMENT '商品编号',
  `product_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '主分类',
  `sub_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '子分类',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchandise
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单表id',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `order_number` bigint(0) NULL DEFAULT NULL COMMENT '订单编号',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际支付价格',
  `is_pay` tinyint(1) NULL DEFAULT 0 COMMENT '是否已经支付：1 已支付，0 未支付，-1 订单失效',
  `pay_id` bigint(0) NULL DEFAULT NULL COMMENT '支付单号',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_goods
-- ----------------------------
DROP TABLE IF EXISTS `order_goods`;
CREATE TABLE `order_goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单商品表',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `goods_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `count` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `type` int(0) NULL DEFAULT NULL COMMENT '商品类型：1 饮品，2 周边',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_goods
-- ----------------------------

-- ----------------------------
-- Table structure for shopping_record
-- ----------------------------
DROP TABLE IF EXISTS `shopping_record`;
CREATE TABLE `shopping_record`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '购物车记录表id',
  `uid` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `goods_id` int(0) NULL DEFAULT NULL COMMENT '商品id',
  `type` int(0) NULL DEFAULT NULL COMMENT '商品类型：1 饮品，2 周边',
  `count` int(0) NULL DEFAULT NULL COMMENT '商品数量',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_record
-- ----------------------------

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商店表id',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌',
  `country` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国家',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `center_longitude` double NULL DEFAULT NULL COMMENT '城市经度 ',
  `center_latitude` double NULL DEFAULT NULL COMMENT '城市纬度',
  `store_number` int(0) NULL DEFAULT NULL COMMENT '商店编号',
  `store_serial` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商店序列号',
  `store_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商店名称',
  `store_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商店地址',
  `longitude` double NULL DEFAULT NULL COMMENT '门店经度',
  `latitude` double NULL DEFAULT NULL COMMENT '门店纬度',
  `phone_number` int(0) NULL DEFAULT NULL COMMENT '门店电话号码',
  `post_code` int(0) NULL DEFAULT NULL COMMENT '邮编',
  `owner_ship_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '所有权类型',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(0) NULL DEFAULT NULL COMMENT '性别',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '记录状态：1 正常，0 禁用，-1 删除',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
