CREATE TABLE tipe_kendaraan
(
   Id           INT        IDENTITY(1,1) PRIMARY KEY,
   -- Id           INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Name         varchar(35)       DEFAULT NULL,
   -- audit
   CreatedDate  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    varchar(35)       DEFAULT NULL,
   ModifiedDate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)       DEFAULT NULL,
   RowStatus    tinyint           DEFAULT 1
);

INSERT INTO tipe_kendaraan (Id, Name, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Xenia', now(), 'abdul', now(), 'abdul'),
       (2, 'Rocky', now(), 'abdul', now(), 'abdul'),
       (3, 'Terios', now(), 'abdul', now(), 'abdul');

-- SQL Server
INSERT INTO tipe_kendaraan (Id, Name, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Xenia', getdate(), 'abdul', getdate(), 'abdul'),
       (2, 'Rocky', getdate(), 'abdul', getdate(), 'abdul'),
       (3, 'Terios', getdate(), 'abdul', getdate(), 'abdul');




