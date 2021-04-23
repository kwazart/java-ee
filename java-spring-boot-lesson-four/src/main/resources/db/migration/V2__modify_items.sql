ALTER TABLE items ADD COLUMN cost INT;

UPDATE items SET cost = id * 20;