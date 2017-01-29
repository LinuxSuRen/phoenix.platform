drop table if exists options;
drop table if exists suite_runner_info;
drop table if exists project_foucs;
drop table if exists data_source_info;
drop table if exists page_info;
drop table if exists project;
drop table if exists user_info;

create table user_info (
	id varchar(36) not null,
	nick_name varchar(100) not null,
	login_name varchar(100) not null,
	password varchar(100) not null,
	email varchar(100),
	regist_time timestamp,
	primary key (id)
);

create table project (
	id varchar(36) not null,
	owner_id varchar(36),
	name varchar(100),
	remark longtext,
	create_time timestamp,
	primary key (id),
	constraint project_2_user_info foreign key (owner_id)
	references user_info (id) on delete restrict
);

create table page_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	primary key (id),
	constraint page_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table data_source_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	primary key(id),
	constraint data_source_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table suite_runner_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	primary key(id),
	constraint suite_runner_info_2_project foreign key (project_id)
	references project (id) on delete restrict
);

create table options (
	id varchar(36) not null,
	opt_key varchar(100),
	opt_value varchar(300),
	primary key (id)
);

create table project_foucs (
	id varchar(36),
	project_id varchar(36),
	user_id varchar(36),
	primary key(id)
);