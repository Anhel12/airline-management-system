--liquibase formatted sql

--changeset dgilev:1
alter table passenger drop constraint passenger_passport_number_key;

--changeset dgilev:2
update passenger set password = '$2a$10$kkh.BzrTK9Dcu191VkroCue2P1zVtAQSex5WBBbSfCmeRQxk8CtAG' where email = 'asdf@mail.ru';
