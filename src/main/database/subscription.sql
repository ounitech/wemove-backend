ALTER TABLE subscription
    ADD COLUMN duration ENUM('daily', 'monthly', 'year') NOT NULL;

INSERT INTO `wemove`.`subscription`
(`name`,`price`,`duration`,`active`)
VALUES ("GOLD",300,"YEAR",1);

INSERT INTO `wemove`.`subscription`
(`name`,`price`,`duration`,`active`)
VALUES ("SILVER",30,"MONTHLY",1);

INSERT INTO `wemove`.`subscription`
(`name`,`price`,`duration`,`active`)
VALUES ("BRONZE",7,"DAILY",1);

SELECT * FROM subscription
