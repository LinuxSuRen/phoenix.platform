drop table if exists project;
create table project (
	id varchar(36) not null,
	owner_id varchar(36),
	name varchar(100),
	remark longtext,
	create_time timestamp,
	primary key (id)
);

drop table if exists page_info;
create table page_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	primary key (id)
);

drop table if exists data_source_info;
create table data_source_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	primary key(id)
);

drop table if exists suite_runner_info;
create table suite_runner_info (
	id varchar(36) not null,
	project_id varchar(36) not null,
	name varchar(100),
	content longtext,
	primary key(id)
);

drop table if exists options;
create table options (
	id varchar(36) not null,
	opt_key varchar(100),
	opt_value varchar(300),
	primary key (id)
);

drop table if exists project_foucs;
create table project_foucs (
	id varchar(36),
	project_id varchar(36),
	user_id varchar(36),
	primary key(id)
);