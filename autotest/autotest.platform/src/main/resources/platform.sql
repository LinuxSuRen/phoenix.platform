drop table if exists page_info;
create table page_info (
	id varchar(36),
	name varchar(100),
	content longtext
);

drop table if exists project;
create table project (
	id varchar(36),
	owner_id varchar(36),
	name varchar(100),
	remark longtext,
	create_time timestamp,
	primary key (id)
);

drop table if exists options;
create table options (
	id varchar(36),
	opt_key varchar(100),
	opt_value varchar(300),
	primary key (id)
);

drop table if exists project_foucs
create table project_foucs (
	id varchar(36),
	project_id varchar(36),
	user_id varchar(36),
	primary key(id)
);