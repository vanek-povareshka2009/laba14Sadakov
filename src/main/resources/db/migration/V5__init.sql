-- ALTER TABLE hotelresident
--     DROP CONSTRAINT room_range_check;
--
-- ALTER TABLE hotelresident
--     ALTER COLUMN room TYPE INT USING (room::INT);
--
-- ALTER TABLE hotelresident
--     ADD CONSTRAINT room_range_check CHECK (room >= 0 AND room <= 1000);
--
-- UPDATE hotelresident
-- SET room = 123
-- WHERE name = 'Титова В. Е.';