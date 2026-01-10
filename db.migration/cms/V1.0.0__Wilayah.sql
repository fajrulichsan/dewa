-- MySQL
CREATE TABLE Wilayah
(
   Id           INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id           INT                    IDENTITY(1,1)  PRIMARY KEY,
   Name         VARCHAR(50)   NOT NULL,
   SK           VARCHAR(55)            DEFAULT NULL,
   Description  VARCHAR(255)           DEFAULT NULL,
   RowStatus    TINYINT                DEFAULT 1,
   CreatedDate  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    VARCHAR(35)            DEFAULT NULL,
   ModifiedDate DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   VARCHAR(35)            DEFAULT NULL
);

INSERT INTO Wilayah (Id, Name, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Jatim', now(), 'abdul', now(), 'abdul'),
       (2, 'DKI 1', now(), 'abdul', now(), 'abdul'),
       (3, 'Jateng', now(), 'abdul', now(), 'abdul'),
       (4, 'Jabar', now(), 'abdul', now(), 'abdul'),
       (5, 'Sumatera', now(), 'abdul', now(), 'abdul'),
       (6, 'Bali', now(), 'abdul', now(), 'abdul'),
       (7, 'IBT', now(), 'abdul', now(), 'abdul'),
       (8, 'Kalimantan', now(), 'abdul', now(), 'abdul');

-- SQL Server
INSERT INTO Wilayah (Name, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES ('Jatim', getdate(), 'abdul', getdate(), 'abdul'),
       ('DKI 1', getdate(), 'abdul', getdate(), 'abdul'),
       ('Jateng', getdate(), 'abdul', getdate(), 'abdul'),
       ('Jabar', getdate(), 'abdul', getdate(), 'abdul'),
       ('Sumatera', getdate(), 'abdul', getdate(), 'abdul'),
       ('Bali', getdate(), 'abdul', getdate(), 'abdul'),
       ('IBT', getdate(), 'abdul', getdate(), 'abdul'),
       ('Kalimantan', getdate(), 'abdul', getdate(), 'abdul');
