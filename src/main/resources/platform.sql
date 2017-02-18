drop table if exists attachment;
drop table if exists attach_config;
drop table if exists options;
drop table if exists suite_runner_log;
drop table if exists suite_runner_info;
drop table if exists project_foucs;
drop table if exists data_source_info;
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
	nick_name varchar(100) not null,
	login_name varchar(100) not null unique,
	password varchar(100) not null,
	email varchar(100) unique,
	regist_time timestamp,
	primary key (id)
);

create table user_behavior(
	id varchar(36) not null,
	user_id varchar(36) not null,
	method varchar(10),
	user_agent varchar(200),
	server_ip varchar(20),
	client_ip varchar(20),
	operating_system varchar(20),
	request_uri varchar(200),
	query_info varchar(200),
	visit_time timestamp,
	primary key(id),
	constraint user_behavior_2_user_info foreign key (user_id)
	references user_info (id) on delete restrict
);

create table role_info (
	id varchar(36) not null,
	name varchar(100) not null unique,
	primary key (id)
);

create table user_role_info (
	id varchar(36) not null,
	user_id varchar(36) not null,
	role_id varchar(36) not null,
	primary key(id),
	constraint user_role_info_2_user foreign key (user_id)
	references user_info (id) on delete restrict,
	constraint user_role_info_2_role foreign key (role_id)
	references role_info (id) on delete restrict
);

create table group_info (
	id varchar(36) not null,
	owner_id varchar(36) not null,
	name varchar(100) not null,
	remark varchar(300),
	primary key(id),
	constraint group_info_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict
);

create table project (
	id varchar(36) not null,
	owner_id varchar(36),
	name varchar(100) unique,
	pkg_name varchar(300),
	remark longtext,
	create_time timestamp,
	primary key (id),
	constraint project_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict
);

create table project_foucs (
	id varchar(36) not null,
	project_id varchar(36) not null,
	user_id varchar(36) not null,
	focus_time timestamp not null,
	primary key(id),
	constraint project_foucs_2_project foreign key (project_id)
	references project (id) on delete restrict,
	constraint project_foucs_2_user_info foreign key (user_id)
	references user_info (id) on delete restrict
);

create table page_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100) unique,
	content longtext,
	create_time timestamp not null,
	remark varchar(300),
	primary key (id),
	constraint page_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table data_source_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	create_time timestamp not null,
	remark varchar(300),
	primary key(id),
	constraint data_source_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table suite_runner_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100) unique,
	content longtext,
	create_time timestamp not null,
	remark varchar(300),
	primary key(id),
	constraint suite_runner_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table suite_runner_log (
	id varchar(36) not null,
	suite_runner_info_id varchar(36) not null,
	trigger_user_id varchar(36) not null,
	message longtext,
	begin_time timestamp not null,
	end_time timestamp not null,
	primary key(id),
	constraint suite_runner_log_2_info foreign key (suite_runner_info_id)
	references suite_runner_info (id) on delete restrict,
	constraint suite_runner_log_2_user_info foreign key (trigger_user_id)
	references user_info (id) on delete restrict
);

create table attach_config (
	id varchar(36) not null,
	name varchar(100) not null unique,
	remark varchar(200),
	primary key(id)
);

create table attachment (
	id varchar(36) not null,
	owner_id varchar(36) not null,
	belong_id varchar(36) not null,
	config_id varchar(36) not null,
	file_name varchar(200) not null,
	relative_path varchar(512) not null,
	remark varchar(200),
	create_time timestamp not null,
	constraint attach_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict,
	constraint attach_2_config foreign key (config_id)
	references attach_config (id) on delete restrict,
	primary key(id)
);

create table options (
	id varchar(36) not null,
	opt_key varchar(100) not null unique,
	opt_value varchar(300),
	remark varchar(300),
	primary key (id)
);

create table sys_config (
	id varchar(36) not null,
	attach_root varchar(300),
	primary key(id)
);

create view project_user_view as
	select project.id, project.name, project.owner_id, user_info.nick_name as userName
	from project left join user_info
	on project.owner_id = user_info.id;