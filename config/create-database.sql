-- mysql sql file
create
database bestpicture;

create
user 'bestpictureuser'@'localhost' identified by 'bestpicturepassword';

GRANT ALL PRIVILEGES ON bestpicture.* TO
'bestpictureuser'@'localhost';

CREATE TABLE `seq`
(
    `next_val` bigint(20) DEFAULT NULL
);

INSERT INTO `seq` (`next_val`)
VALUES (1);