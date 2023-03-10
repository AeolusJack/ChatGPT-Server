/*
 Navicat Premium Data Transfer

 Source Server         : dddddddd
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : chatgpt

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 08/03/2023 23:46:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_data
-- ----------------------------
DROP TABLE IF EXISTS `chat_data`;
CREATE TABLE `chat_data` (
                             `TENANT_ID` int(11) DEFAULT NULL COMMENT '租户号',
                             `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
                             `CREATED_BY` varchar(255) DEFAULT NULL COMMENT '创建人',
                             `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                             `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
                             `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                             `USER_ID` varchar(255) DEFAULT NULL COMMENT '归属用户id',
                             `CONTENT` longtext COMMENT '数据内容',
                             `CONTENT_TYPE` varchar(255) DEFAULT NULL COMMENT '数据类型;Q,A',
                             `TOKEN` varchar(255) DEFAULT NULL COMMENT '令牌号;区分同一个终端发起',
                             `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键;主键',
                             `THEME_ID` int(11) DEFAULT NULL COMMENT '主题ID',
                             PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COMMENT='交流数据';

-- ----------------------------
-- Table structure for chat_theme
-- ----------------------------
DROP TABLE IF EXISTS `chat_theme`;
CREATE TABLE `chat_theme` (
                              `TENANT_ID` int(32) DEFAULT NULL COMMENT '租户号',
                              `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
                              `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
                              `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                              `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
                              `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                              `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `USER_ID` varchar(255) DEFAULT NULL COMMENT '用户ID',
                              `THEME_DESCRIBE` varchar(3000) DEFAULT NULL COMMENT '主题描述',
                              `THEME_NAME` varchar(255) DEFAULT NULL COMMENT '主题名称',
                              `Parent_ID` varchar(255) DEFAULT NULL COMMENT '父主题id序列，1-231-221，最多只能有三级主题',
                              PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='主题表';

-- ----------------------------
-- Table structure for sys_suer
-- ----------------------------
DROP TABLE IF EXISTS `sys_suer`;
CREATE TABLE `sys_suer` (
                            `TENANT_ID` int(32) DEFAULT NULL COMMENT '租户号',
                            `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
                            `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
                            `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                            `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
                            `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                            `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `USER_NAME` varchar(255) DEFAULT NULL COMMENT '用户名',
                            `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
                            `PHONE` varchar(255) DEFAULT NULL COMMENT '电话',
                            `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮箱',
                            `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
                            `SEX` varchar(255) DEFAULT NULL COMMENT '性别',
                            `ICON` varchar(255) DEFAULT NULL COMMENT '头像',
                            `GROUP_ID` int(11) DEFAULT NULL COMMENT '机构id',
                            PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
