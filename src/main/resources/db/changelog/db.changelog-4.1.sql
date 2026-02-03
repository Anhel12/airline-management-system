--liquibase formated sql

--changeset dgilev:1
alter table passenger add column password varchar;

--changeset dgilev:2
alter table passenger alter column passport_number drop not null;
alter table passenger alter column date_of_birth drop not null;
alter table passenger alter column first_name drop not null;
alter table passenger alter column middle_name drop not null;
alter table passenger alter column sex drop not null;
alter table passenger alter column role drop not null;
