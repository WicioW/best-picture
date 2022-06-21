--liquibase formatted sql
--changeset wiciow:1

CREATE TABLE `seq`
(
    `next_val` bigint(20) DEFAULT NULL
);

INSERT INTO `seq` (`next_val`)
VALUES (1);