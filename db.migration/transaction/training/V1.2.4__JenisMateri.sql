CREATE TABLE jenis_materi
(
   -- Id           int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id   int IDENTITY(1,1) PRIMARY KEY,
   Name varchar(255) NOT NULL,
   ImageId       bigint                DEFAULT 0,
   ImageName     varchar(255) NOT NULL,
   ImagePath     varchar(255)          DEFAULT NULL,
   -- audit
   RowStatus     tinyint               DEFAULT 1,
   CreatedDate   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy     varchar(35)           DEFAULT NULL,
   ModifiedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy    varchar(35)           DEFAULT NULL
);

INSERT INTO jenis_materi (Id, Name)
VALUES (1, 'Training Admin Unit'),
       (2, 'Training Kasir');
