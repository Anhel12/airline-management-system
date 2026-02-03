--liquibase formated sql

--changeset dgilev:1
INSERT INTO booking (number, booking_date, flight_id, total_amount, status)
VALUES   ('BK-000102', '2026-01-26', 388, 15000, 'CONFIRMED'),
         ('BK-000103', '2026-01-26', 389, 15000, 'CONFIRMED'),
         ('BK-000104', '2026-01-27', 390, 25000, 'CONFIRMED'),
         ('BK-000105', '2026-01-28', 391, 12000, 'CANCELED'),
         ('BK-000106', '2026-01-27', 392, 12000, 'CONFIRMED'),
         ('BK-000107', '2026-01-29', 393, 18000, 'CONFIRMED'),
         ('BK-000108', '2026-01-30', 394, 18000, 'CONFIRMED'),
         ('BK-000109', '2026-01-20', 395, 22000, 'CANCELED'),
         ('BK-000110', '2026-01-15', 396, 22000, 'CONFIRMED');
