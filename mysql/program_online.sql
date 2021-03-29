# 创建数据库
create database if not exists program_online;

# 访问数据库
use program_online;

# 创建用户表
create table if not exists users(
    id integer primary key auto_increment,
    email varchar(50) not null unique,
    name varchar(50) not null unique,
    password varchar(50) not null
);

# 创建仓库表
create table if not exists workspaces(
    id integer primary key auto_increment,
    name varchar(50) not null unique,
    authority_type enum('public', 'private') not null,
    directory_code varchar(50) not null unique
);

# 创建仓库表
create table if not exists projects(
    id integer primary key auto_increment,
    name varchar(50) not null unique,
    authority_type enum('public', 'private') not null,
    directory_code varchar(50) not null unique
);

# 创建仓库-项目关系表
create table if not exists workspace_project_relations(
    id integer primary key auto_increment,
    workspace_id integer not null,
    project_id integer not null,
    foreign key(workspace_id) references workspaces(id),
    foreign key(project_id) references projects(id)
);

# 创建仓库-环境关系表
create table if not exists workspace_environment_relations(
    id integer primary key auto_increment,
    workspace_id integer not null,
    environment_name varchar(20) not null,
    environment_version varchar(20) not null,
    foreign key(workspace_id) references workspaces(id)
);

# 创建用户-仓库权限关系表
create table if not exists user_workspace_relations(
    id integer primary key auto_increment,
    user_id integer not null,
    workspace_id integer not null,
    authority_read integer not null,
    authority_write integer not null,
    authority_manage integer not null,
    authority_owner integer not null,
    foreign key(workspace_id) references workspaces(id),
    foreign key(user_id) references users(id)
);

# 创建用户-项目权限关系表
create table if not exists user_project_relations(
    id integer primary key auto_increment,
    user_id integer not null,
    project_id integer not null,
    authority_read integer not null,
    authority_write integer not null,
    authority_manage integer not null,
    authority_owner integer not null,
    foreign key(project_id) references projects(id),
    foreign key(user_id) references users(id)
);