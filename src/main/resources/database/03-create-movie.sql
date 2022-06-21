--liquibase formatted sql
--changeset wiciow:1


CREATE TABLE movie (
    id BIGINT  NOT NULL,
    category varchar(255) null,
    nominee varchar(500) null,
    won_best_picture bit not null,
    year varchar(255) null
);