drop table if exists options;
drop table if exists suite_runner_log;
drop table if exists suite_runner_info;
drop table if exists project_foucs;
drop table if exists data_source_info;
drop table if exists page_info;
drop table if exists project;
drop table if exists user_role_info;
drop table if exists role_info;
drop table if exists user_info;

create table user_info (
	id varchar(36) not null,
	nick_name varchar(100) not null,
	login_name varchar(100) not null unique,
	password varchar(100) not null,
	email varchar(100) unique,
	regist_time timestamp,
	primary key (id)
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
	primary key (id),
	constraint page_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table data_source_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100) unique,
	content longtext,
	primary key(id),
	constraint data_source_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table suite_runner_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100) unique,
	content longtext,
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

create table options (
	id varchar(36) not null,
	opt_key varchar(100) not null unique,
	opt_value varchar(300),
	primary key (id)
);