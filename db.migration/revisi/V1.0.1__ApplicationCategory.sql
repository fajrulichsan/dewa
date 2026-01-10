CREATE TABLE ApplicationCategory
(
   Id           int NOT NULL,
   Name         varchar(155) NOT NULL,
   Description  varchar(255) NOT NULL,
   CategoryBonus  int NOT NULL default 0,
   RowStatus    tinyint                   DEFAULT 1,
   CreatedBy    varchar(35)           DEFAULT NULL,
   CreatedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)           DEFAULT NULL,
   ModifiedDate datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (Id)
);

-- MySQL
INSERT INTO ApplicationCategory (Id, Name, Description, CategoryBonus, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Back Bonus', '-', 0, now(), 'abdul', now(), 'abdul'),
       (2, 'Achievement Bonus', '-', 0, now(), 'abdul', now(), 'abdul'),
       (3, 'Yearly Back Bonus', '-', 0, now(), 'abdul', now(), 'abdul'),
       (4, 'Claim Test Car', '-', 1, now(), 'abdul', now(), 'abdul');


-- SQL Server
INSERT INTO ApplicationCategory (Id, Name, Description, CategoryBonus, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Back Bonus', '-', 0, getdate(), 'abdul', getdate(), 'abdul'),
       (2, 'Achievement Bonus', '-', 0, getdate(), 'abdul', getdate(), 'abdul'),
       (3, 'Yearly Back Bonus', '-', 0, getdate(), 'abdul', getdate(), 'abdul'),
       (4, 'Claim Test Car', '-', 1, getdate(), 'abdul', getdate(), 'abdul');
