CREATE TABLE `User`.`user_login`
(
    `username` VARCHAR(32) NOT NULL,
    `createtime` DATE NOT NULL,
    `salt` CHAR(17) NOT NULL,
    `password` VARCHAR(32),
    `nickname` VARCHAR(32),
    PRIMARY KEY(`username`)
);
CREATE TABLE `User`.`message_recieve`
(
    `username` VARCHAR(32) NOT NULL,
    `email` VARCHAR(64),
    `tel` VARCHAR(16),
    PRIMARY KEY(`username`)
);

CREATE TABLE `User`.`group_info`
(
    `groupid` VARCHAR(32) NOT NULL,
    `owner` VARCHAR(32) NOT NULL,
    `groupname` VARCHAR(32),
    PRIMARY KEY(`groupid`)
);

CREATE TABLE `User`.`group_member`
(
    `groupid` VARCHAR(32) NOT NULL,
    `username` VARCHAR(32) NOT NULL,
    `grouprole` VARCHAR(32) NOT NULL
);