--liquibase formatted sql
--changeset wiciow:1

create table movie_rating
(
    id       bigint not null
        primary key,
    rating   int    not null,
    movie_id bigint null,
    user_id  bigint null,
    constraint UK_useridandmovieid
        unique (user_id, movie_id),
    constraint FK_movieid
        foreign key (movie_id) references movie (id),
    constraint FK_userid
        foreign key (user_id) references users (id)
);

