--liquibase formated sql

--changeset dgilev:1
alter table booking add column number_of_passengers BIGINT;