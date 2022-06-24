--liquibase formatted sql
--changeset wiciow:1

create table movie
(
    id                    bigint       not null primary key,
    box_office_in_dollars bigint       null,
    category              varchar(255) null,
    imdbid                varchar(255) null,
    nominee               varchar(500) null,
    won_best_picture      bit          not null,
    year                  varchar(255) null
);
