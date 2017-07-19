drop table if exists attachment;
drop table if exists attach_config;
drop table if exists options;
drop table if exists suite_runner_log;
drop table if exists test_plan;
drop table if exists suite_runner_info;
drop table if exists project_foucs;
drop table if exists data_source_info;
drop table if exists field_locator;
drop table if exists page_field;
drop table if exists page_info;
drop table if exists project;
drop table if exists user_role_info;
drop table if exists role_info;
drop table if exists user_behavior;
drop table if exists group_info;
drop table if exists user_info;
drop table if exists sys_config;
drop view if exists project_user_view;

create table user_info (
	id varchar(36) not null,
	nick_name varchar(100) not null comment '昵称',
	login_name varchar(100) not null unique comment '登录用户',
	password varchar(100) not null comment '密码',
	email varchar(100) unique comment '电子邮件',
	regist_time timestamp comment '注册时间',
	primary key (id)
) default character set utf8 comment '用户信息';

create table user_behavior(
	id varchar(36) not null,
	user_id varchar(36) not null comment '用户主键',
	method varchar(10) comment '方法',
	user_agent varchar(200) comment '代理',
	server_ip varchar(20) comment '服务器ip',
	client_ip varchar(20) comment '客户端ip',
	operating_system varchar(20) comment '操作系统',
	request_uri varchar(200) comment '请求地址',
	query_info varchar(200) comment '查询信息',
	visit_time timestamp comment '访问时间',
	primary key(id),
	constraint user_behavior_2_user_info foreign key (user_id)
	references user_info (id) on delete restrict
) default character set utf8 comment '用户行为记录';

create table role_info (
	id varchar(36) not null,
	name varchar(100) not null unique comment '角色名称',
	primary key (id)
) default character set utf8 comment '角色记录';

create table user_role_info (
	id varchar(36) not null,
	user_id varchar(36) not null comment '用户主键',
	role_id varchar(36) not null comment '角色主键',
	primary key(id),
	constraint user_role_info_2_user foreign key (user_id)
	references user_info (id) on delete restrict,
	constraint user_role_info_2_role foreign key (role_id)
	references role_info (id) on delete restrict
) default character set utf8 comment '用户和角色的关联';

create table group_info (
	id varchar(36) not null,
	owner_id varchar(36) not null comment '用户主键',
	name varchar(100) not null comment '分组名称',
	remark varchar(300) comment '备注',
	primary key(id),
	constraint group_info_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict
) default character set utf8 comment '';

create table project (
	id varchar(36) not null,
	owner_id varchar(36) not null comment '用户主键',
	name varchar(100) unique not null comment '项目名称',
	pkg_name varchar(300) comment '包名',
	remark longtext comment '备注',
	create_time timestamp comment '创建时间',
	primary key (id),
	constraint project_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict
) default character set utf8 comment '项目信息';

create table project_foucs (
	id varchar(36) not null,
	project_id varchar(36) not null comment '项目主键',
	user_id varchar(36) not null comment '用户主键',
	focus_time timestamp not null comment '关注时间',
	primary key(id),
	constraint project_foucs_2_project foreign key (project_id)
	references project (id) on delete restrict,
	constraint project_foucs_2_user_info foreign key (user_id)
	references user_info (id) on delete restrict
) default character set utf8 comment '项目关注';

create table page_info (
	id varchar(36) not null,
	project_id varchar(36) not null comment '项目主键',
	name varchar(100) comment '页面名称',
	url varchar(1024) comment '地址',
	create_time DATETIME not null comment '创建时间',
	remark varchar(300) comment '备注',
	primary key (id),
	constraint page_info_2_project foreign key (project_id)
	references project (id) on delete restrict,
	constraint project_id_page_name_unique unique(project_id, name)
) default character set utf8 comment '页面信息';

create table page_field (
	id varchar(36) not null,
	page_id varchar(36) not null comment '',
	name varchar(100) not null comment '',
	field_type varchar(36) not null comment '',
	strategy varchar(36) not null comment '',
	timeout int comment '',
	primary key (id),
	constraint page_field_2_page foreign key (page_id)
	references page_info (id) on delete restrict,
	constraint page_id_name_unique unique (page_id, name)
) default character set utf8 comment '';

create table field_locator (
	id varchar(36) not null,
	field_id varchar(36) not null comment '',
	name varchar(100) comment '',
	value varchar(100) comment '',
	locator_order int default 0 comment '',
	timeout bigint default 0 comment '',
	primary key (id),
	constraint field_locator_2_field foreign key (field_id)
	references page_field (id) on delete restrict,
	constraint name_value_unique unique (name, value)
) default character set utf8 comment '';

create table data_source_info (
	id varchar(36) not null,
	project_id varchar(36) not null comment '项目主键',
	name varchar(100) comment '',
	type varchar(100) comment '',
	resource varchar(1024) comment '',
	create_time timestamp not null comment '',
	remark varchar(300) comment '',
	primary key(id),
	constraint data_source_info_2_project foreign key (project_id)
	references project (id) on delete restrict,
	constraint project_id_name_unique unique (project_id, name)
) default character set utf8 comment '';

create table suite_runner_info (
	id varchar(36) not null,
	project_id varchar(36) not null comment '项目主键',
	name varchar(100) comment '',
	content longtext comment '',
	create_time timestamp not null comment '',
	remark varchar(300) comment '',
	primary key(id),
	constraint suite_runner_info_2_project foreign key (project_id)
	references project (id) on delete restrict,
	constraint project_id_name_unique unique (project_id, name)
) default character set utf8 comment '';

create table test_plan (
	id varchar(36) not null,
	name varchar(100) not null comment '',
	owner_id varchar(36) not null comment '用户主键',
	suite_runner_id varchar(36) not null comment '',
	cron_exp varchar(300) comment '',
	create_time timestamp not null comment '',
	remark varchar(300) comment '',
	primary key(id),
	constraint test_plan_2_suite_runner foreign key (suite_runner_id)
	references suite_runner_info (id) on delete restrict
) default character set utf8 comment '';

create table suite_runner_log (
	id varchar(36) not null,
	suite_runner_info_id varchar(36) not null comment '',
	trigger_user_id varchar(36) not null comment '',
	message longtext comment '',
	begin_time timestamp not null comment '开始时间',
	end_time timestamp not null comment '结束时间',
	primary key(id),
	constraint suite_runner_log_2_info foreign key (suite_runner_info_id)
	references suite_runner_info (id) on delete restrict,
	constraint suite_runner_log_2_user_info foreign key (trigger_user_id)
	references user_info (id) on delete restrict
) default character set utf8 comment '';

create table attach_config (
	id varchar(36) not null,
	name varchar(100) not null unique comment '',
	remark varchar(200) comment '备注',
	primary key(id)
) default character set utf8 comment '';

create table attachment (
	id varchar(36) not null,
	owner_id varchar(36) not null comment '用户主键',
	belong_id varchar(36) not null comment '',
	config_id varchar(36) not null comment '',
	file_name varchar(200) not null comment '',
	relative_path varchar(512) not null comment '',
	remark varchar(200) comment '备注',
	create_time timestamp not null comment '',
	constraint attach_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict,
	constraint attach_2_config foreign key (config_id)
	references attach_config (id) on delete restrict,
	primary key(id)
) default character set utf8 comment '';

create table options (
	id varchar(36) not null,
	opt_key varchar(100) not null unique comment '',
	opt_value varchar(300) comment '',
	remark varchar(300) comment '',
	primary key (id)
) default character set utf8 comment '';

create table sys_config (
	id varchar(36) not null,
	attach_root varchar(300) comment '',
	primary key(id)
) default character set utf8 comment '';

create view project_user_view as
	select project.id, project.name, project.owner_id, user_info.nick_name as userName
	from project left join user_info
	on project.owner_id = user_info.id;