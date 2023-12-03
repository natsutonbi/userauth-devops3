create table account(
    `username` varchar(50) not null primary key,
    `nickname` varchar(50) null,
    `email` varchar(50) null,
    `phone` varchar(20) null,
    `password` varchar(500) not null,
    `enabled` boolean not null);
create table role(
    `username` varchar(50) not null,
    `role` varchar(50) not null,
    constraint `fk_account_role` foreign key(`username`) references `account`(`username`));
create table permission(
    `username` varchar(50) not null,
    `permission` varchar(50) not null,
    constraint `fk_account_permission` foreign key(`username`) references `account`(`username`));
create unique index idx_role_username on `role` (`username`,`role`);
create unique index idx_perm_username on `permission` (`username`,`permission`);
create table group(
    `groupid` varchar(50) not null primary key,
    `groupname` varchar(50) null,
    `email` varchar(50) null,
    `phone` varchar(20) null,
    `password` varchar(500) not null,
    `enabled` boolean not null);