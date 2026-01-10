-- MySQL
CREATE TABLE Cabang
(
   Id           INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id           INT                    IDENTITY(1,1)  PRIMARY KEY,
   Name         VARCHAR(50)   NOT NULL,
   Description  VARCHAR(255)           DEFAULT NULL,
   RowStatus    TINYINT                DEFAULT 1,
   CreatedDate  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    VARCHAR(35)            DEFAULT NULL,
   ModifiedDate DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   VARCHAR(35)            DEFAULT NULL
);

INSERT INTO Cabang (Id, Name, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Surabaya', now(), 'abdul', now(), 'abdul'),
       (2, 'Pamekasan', now(), 'abdul', now(), 'abdul'),
       (3, 'Banyuwangi', now(), 'abdul', now(), 'abdul');

-- SQL Server
INSERT INTO Cabang (Name, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES ('Surabaya', getdate(), 'abdul', getdate(), 'abdul'),
       ('Pamekasan', getdate(), 'abdul', getdate(), 'abdul'),
       ('Banyuwangi', getdate(), 'abdul', getdate(), 'abdul');
