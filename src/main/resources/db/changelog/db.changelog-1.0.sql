--liquibase formatted sql

--changeset dgilev:1
CREATE TABLE passenger(
    id BIGSERIAL PRIMARY KEY,
    phone_number VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    passport_number VARCHAR(20) NOT NULL UNIQUE,
    date_of_birth DATE NOT NULL,
    first_name VARCHAR NOT NULL,
    middle_name VARCHAR NOT NULL,
    last_name VARCHAR,
    sex VARCHAR(6) NOT NULL,
    role VARCHAR NOT NULL
);

--changeset dgilev:2
CREATE TABLE seat_class(
    id BIGSERIAL PRIMARY KEY,
    class VARCHAR NOT NULL
);

--changeset dgilev:3
CREATE TABLE airport(
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR NOT NULL UNIQUE,
    city VARCHAR NOT NULL,
    country VARCHAR NOT NULL,
    timezone VARCHAR NOT NULL
);

--changeset dgilev:4
CREATE TABLE aircraft(
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR NOT NULL,
    registration_number VARCHAR NOT NULL UNIQUE,
    total_seats INTEGER NOT NULL
);

--changeset dgilev:5
CREATE TABLE position(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

--changeset dgilev:6
CREATE TABLE crew(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    middle_name VARCHAR NOT NULL,
    last_name VARCHAR,
    position_id BIGINT NOT NULL REFERENCES position(id),
    certification VARCHAR NOT NULL
);

--changeset dgilev:7
CREATE TABLE seat(
    id BIGSERIAL PRIMARY KEY,
    aircraft_id BIGINT NOT NULL REFERENCES aircraft(id),
    number VARCHAR NOT NULL,
    seat_class_id BIGINT NOT NULL REFERENCES seat_class(id)
);

--changeset dgilev:8
CREATE TABLE flight(
    id BIGSERIAL PRIMARY KEY,
    number VARCHAR NOT NULL UNIQUE,
    departure_date_time TIMESTAMP NOT NULL,
    arrival_date_time TIMESTAMP NOT NULL,
    aircraft_id BIGINT NOT NULL REFERENCES aircraft(id),
    crew_id NUMERIC NOT NULL REFERENCES crew(id)
);

--changeset dgilev:9
CREATE TABLE booking(
    id BIGSERIAL PRIMARY KEY,
    number VARCHAR NOT NULL,
    booking_date DATE NOT NULL,
    flight_id BIGINT NOT NULL REFERENCES flight(id),
    seat_id BIGINT NOT NULL REFERENCES seat(id),
    passenger_id BIGINT NOT NULL REFERENCES passenger(id),
    total_amount INTEGER NOT NULL,
    status VARCHAR NOT NULL
);

--changeset dgilev:10
CREATE TABLE flight_crew(
    id BIGSERIAL PRIMARY KEY,
    flight_id BIGINT NOT NULL REFERENCES flight(id),
    crew_id BIGINT NOT NULL REFERENCES crew(id)
);

--changeset dgilev:11
CREATE TABLE booking_passenger(
    id BIGSERIAL PRIMARY KEY,
    booking_id BIGINT NOT NULL REFERENCES booking(id),
    passenger BIGINT NOT NULL REFERENCES passenger(id)
);
