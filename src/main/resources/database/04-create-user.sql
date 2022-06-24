--liquibase formatted sql
--changeset wiciow:1


CREATE TABLE users (
                       id BIGINT  NOT NULL primary key,
                       username varchar(255) UNIQUE not null,
                       password varchar(255) not null,
                       constraint UK_uniqueusername unique (username)
);