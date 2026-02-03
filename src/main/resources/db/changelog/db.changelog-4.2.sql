--liquibase formatted sql

--changeset dgilev:1
update passenger set password = '123' where id = '1' or id = '3';