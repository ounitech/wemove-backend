ALTER TABLE member
MODIFY COLUMN active BOOLEAN;

ALTER TABLE staff
MODIFY COLUMN active BOOLEAN;

ALTER TABLE subscription
MODIFY COLUMN active BOOLEAN;