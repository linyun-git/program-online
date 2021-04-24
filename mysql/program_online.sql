# 创建数据库
create database if not exists program_online;

# 访问数据库
use program_online;

# 创建用户表
create table if not exists user(
    id integer primary key auto_increment,
    email varchar(50) not null unique,
    name varchar(50) not null,
    password varchar(50) not null,
    description varchar(250),
    profile varchar(50)
);

# 创建仓库表
create table if not exists workspace(
    id integer primary key auto_increment,
    name varchar(50) not null,
    description varchar(250) not null,
    authority_type enum('public', 'private') not null,
    directory_code varchar(50) not null unique,
    creator integer not null,
    create_date varchar(50) not null,
    foreign key(creator) references user(id)
);

# 创建项目表
create table if not exists project(
    id integer primary key auto_increment,
    name varchar(50) not null,
    description varchar(250) not null,
    authority_type enum('public', 'private') not null,
    directory_code varchar(50) not null unique,
    creator integer not null,
    create_date varchar(50) not null,
    foreign key(creator) references user(id)
);

# 创建仓库-项目关系表
create table if not exists workspace_project_relation(
    id integer primary key auto_increment,
    workspace_id integer not null,
    project_id integer not null,
    foreign key(workspace_id) references workspace(id),
    foreign key(project_id) references project(id)
);

# 创建仓库-环境关系表
create table if not exists workspace_environment_relation(
    id integer primary key auto_increment,
    workspace_id integer not null,
    environment_name varchar(20) not null,
    environment_version varchar(20) not null,
    foreign key(workspace_id) references workspace(id)
);

# 创建用户-仓库权限关系表
create table if not exists user_workspace_relation(
    id integer primary key auto_increment,
    user_id integer not null,
    workspace_id integer not null,
    authority_read integer not null,
    authority_write integer not null,
    authority_manage integer not null,
    authority_owner integer not null,
    foreign key(workspace_id) references workspace(id),
    foreign key(user_id) references user(id)
);

# 创建用户-项目权限关系表
create table if not exists user_project_relation(
    id integer primary key auto_increment,
    user_id integer not null,
    project_id integer not null,
    authority_read integer not null,
    authority_write integer not null,
    authority_manage integer not null,
    authority_owner integer not null,
    foreign key(project_id) references project(id),
    foreign key(user_id) references user(id)
);