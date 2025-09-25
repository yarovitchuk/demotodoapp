--liquibase formatted sql

--changeset user:1
CREATE TABLE todo
(
    id           UUID PRIMARY KEY,
    description  TEXT    NOT NULL,
    is_completed BOOLEAN NOT NULL
)