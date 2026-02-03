--liquibase formatted sql

--changeset dgilev:1
ALTER TABLE booking
    DROP CONSTRAINT booking_seat_id_fkey;

--changeset dgilev:2
ALTER TABLE booking
    ALTER COLUMN seat_id TYPE BIGINT[]
        USING ARRAY[seat_id::bigint]::bigint[];

--changeset dgilev:4
ALTER TABLE booking
    ALTER COLUMN seat_id TYPE bigint
        USING seat_id[1]::bigint;

--changeset dgilev:3
ALTER TABLE booking
    ADD CONSTRAINT booking_seat_id_fkey
        FOREIGN KEY (seat_id)
            REFERENCES seat(id);