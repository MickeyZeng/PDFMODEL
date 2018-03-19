/*Table structure for table 'mod_times' */

DROP TABLE IF EXISTS `mod_times`;

CREATE TABLE `mod_times`(
	`id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
	`company_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '归属公司',
	`user_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '公司负责人',
	`times` varchar(2) COLLATE utf8_bin NOT NULL COMMENT '次数',
	`create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  	`create_date` datetime NOT NULL COMMENT '创建时间',
  	`update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
 	`update_date` datetime NOT NULL COMMENT '更新时间',
  	`remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  	`del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  	PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模版使用次数表';

/*Data for the table 'mod_times' */

insert into `mod_times`(`id`,`company_id`,`user_id`,`times`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values('1','3','7','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0');
insert into `mod_times`(`id`,`company_id`,`user_id`,`times`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values('2','3','7','2','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0');

/*Tbale structure for table 'sys_modElement' */
DROP TABLE IF EXISTS `sys_modElement`;

CREATE TABLE `sys_modElement`(
	`id` varchar(64) COLLATE utf8_bin not null COMMENT '编号',
	`elementName` varchar(100) COLLATE utf8_bin not null COMMENT '元素名字',
	`PDFelementName` varchar(100) COLLATE utf8_bin not null COMMENT '元素PDF里的名字',
	`company_id` varchar(64) COLLATE utf8_bin not null COMMENT '归属公司',
	`office_id` varchar(64) COLLATE utf8_bin not null COMMENT '归属部门',
	`use_flag` varchar(64) COLLATE utf8_bin DEFAULT null COMMENT '是否可以使用',
	`create_by` varchar(64) COLLATE utf8_bin not null COMMENT '创建者',
	`create_date` datetime not null COMMENT '创建时间',
	`update_by` varchar(64) COLLATE utf8_bin not null COMMENT '更新者',
	`update_date` datetime not null COMMENT '更新时间',
	`remarks` varchar(255) COLLATE utf8_bin null COMMENT '备注信息',
	`del_flag` char(1) COLLATE utf8_bin default '0' not null COMMENT '删除标记',
	PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='模版元素表';

/*Data for the table 'mod_times' */

insert into `sys_modElement`(`id`,`elementName`,`PDFelementName`,`company_id`,`office_id`,`use_flag`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`del_flag`) values ('1','名字','Name','3','5','1','1','2013-05-27 08:00:00','1','2013-05-27 08:00:00',NULL,'0');


