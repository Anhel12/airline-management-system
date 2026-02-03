--liquibase formatted sql

--changeset dgilev:1
ALTER TABLE flight
ADD COLUMN departure_airport_id BIGINT
REFERENCES airport(id);

--changeset dgilev:2
ALTER TABLE flight
ADD COLUMN arrival_airport_id BIGINT
REFERENCES airport(id);

--changeset dgilev:3
ALTER TABLE flight DROP COLUMN aircraft_id;

--changeset dgilev:4
ALTER TABLE booking DROP CONSTRAINT booking_seat_id_fkey;

--changest dgilev:5
ALTER TABLE booking DROP COLUMN seat_id;

--chageset dgilev:6
DELETE FROM flight_crew WHERE id > 0;

--changeset dgilev:7
DELETE FROM booking_passenger where id > 0;

--changeset dgilev:8
DELETE FROM booking WHERE id > 0;

--chageset dgilev:9
DELETE FROM flight WHERE id > 0;

--changeset dgilev:10
CREATE TABLE booking_seat(
    id BIGSERIAL PRIMARY KEY ,
    booking_id BIGINT NOT NULL REFERENCES booking(id),
    seat_id BIGINT NOT NULL REFERENCES seat(id)
);
