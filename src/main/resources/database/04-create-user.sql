--liquibase formatted sql
--changeset wiciow:1


CREATE TABLE users (
                       id BIGINT  NOT NULL,
                       username varchar(255) UNIQUE not null,
                       password varchar(255) not null
);