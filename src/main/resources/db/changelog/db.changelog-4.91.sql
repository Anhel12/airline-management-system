--liquibase formatted sql

--changeset dgilev:1
alter table passenger drop constraint passenger_phone_number_key;
