CREATE TABLE `User`.`user_login`
(
    `username` VARCHAR(32) NOT NULL,
    `createtime` DATE NOT NULL,
    `salt` CHAR(17) NOT NULL,
    `password` VARCHAR(32),
    `nickname` VARCHAR(32),
    PRIMARY KEY(`username`)
);