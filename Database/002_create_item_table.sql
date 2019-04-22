-- The "item" table is being created as a TEMPORARY table to enable the back-end developers
-- somewhere to establish patterns related to their interacting with the database.

-- This table will be dropped in the future!

USE shmoozed;

CREATE TABLE item (
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name text NOT NULL,
    quantity int
);


-- Insert some dummy data for testing

INSERT INTO item (name, quantity)
VALUES ('First Item', 5),
       ('Second Item', 15),
       ('First Secret Item', 100),
       ('The green thing', 5),
       ('Special Edition thing', 2),
       ('Special extra secret thing', 0),
       ('Final extra secret item', 63);
