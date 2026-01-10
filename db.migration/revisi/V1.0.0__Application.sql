CREATE TABLE Application
(
   Id           int NOT NULL,
   Name         varchar(155) NOT NULL,
   Description  varchar(255) NOT NULL,
   RowStatus    tinyint                   DEFAULT 1,
   CreatedBy    varchar(35)           DEFAULT NULL,
   CreatedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)           DEFAULT NULL,
   ModifiedDate datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (Id)
);

-- MySQL
INSERT INTO Application (Id, Name, Description, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Upload Kuitansi Bonus', '-', now(), 'abdul', now(), 'abdul'),
       (2, 'Upload Copy STNK Test Car', '-', now(), 'abdul', now(), 'abdul');

-- SQL Server
INSERT INTO Application (Id, Name, Description, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Upload Kuitansi Bonus', '-', getdate(), 'abdul', getdate(), 'abdul'),
       (2, 'Upload Copy STNK Test Car', '-', getdate(), 'abdul', getdate(), 'abdul');
