CREATE TABLE e_srut
(
   Id           INT          IDENTITY(1,1) PRIMARY KEY,
   -- Id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   DealerId     INT          NOT NULL,
   FileId       bigint                DEFAULT 0,
   FileName     varchar(155) NOT NULL,
   FilePath     varchar(175)          DEFAULT NULL,
   PeriodDate   datetime NULL DEFAULT NULL,
   Keterangan   varchar(175)          DEFAULT NULL,
   -- audit
   CreatedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    varchar(35)           DEFAULT NULL,
   ModifiedDate datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)           DEFAULT NULL,
   RowStatus    TINYINT      NOT NULL DEFAULT 1,
   CONSTRAINT fk_e_srut_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (Id)
);

INSERT INTO e_srut (Id, DealerId, FileId, FileName, FilePath, PeriodDate, Keterangan,
                    CreatedDate, CreatedBy, ModifiedDate, ModifiedBy, RowStatus)
VALUES ('0', '1', '0', '-', '-', now(), '-', now(), '-', now(), '-', '1');