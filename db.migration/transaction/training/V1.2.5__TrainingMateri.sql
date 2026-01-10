CREATE TABLE training_materi
(
   -- Id           int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id            int IDENTITY(1,1) PRIMARY KEY,
   JenisMateriId int          NOT NULL,
   TopikMateriId int          DEFAULT 0,
   JudulMateri   varchar(155) NOT NULL,
   ImageId       bigint                DEFAULT 0,
   ImageName     varchar(255) DEFAULT NULL,
   ImagePath     varchar(255)          DEFAULT NULL,
   -- audit
   RowStatus     tinyint               DEFAULT 1,
   CreatedDate   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy     varchar(35)           DEFAULT NULL,
   ModifiedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy    varchar(35)           DEFAULT NULL
--    PRIMARY KEY (Id),
--    CONSTRAINT fk_training_materi_JenisMateriId FOREIGN KEY (JenisMateriId) REFERENCES jenis_materi (id)
);


