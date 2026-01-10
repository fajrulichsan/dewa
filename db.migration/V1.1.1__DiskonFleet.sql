CREATE TABLE diskon_fleet
(
   Id           INT          IDENTITY(1,1) PRIMARY KEY,
   -- Id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   DealerId     INT          NOT NULL,

   FileId       BIGINT                DEFAULT 0,
   FileName     VARCHAR(155) NOT NULL,
   FilePath     VARCHAR(175)          DEFAULT NULL,

   Tahun        INT                   DEFAULT NULL,
   Kuartal      VARCHAR(25)           DEFAULT NULL,
   InvoiceDate  DATETIME     NOT NULL,
   UploadDate   DATETIME     NOT NULL,
   Keterangan   VARCHAR(175)          DEFAULT NULL,


   -- audit
   CreatedDate  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    VARCHAR(35)           DEFAULT NULL,
   ModifiedDate DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   VARCHAR(35)           DEFAULT NULL,
   RowStatus    TINYINT      NOT NULL DEFAULT 1
   CONSTRAINT fk_diskon_fleet_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (Id)
);

INSERT INTO diskon_fleet (Id, DealerId, FileName, Keterangan, InvoiceDate, UploadDate, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (0, 1, 'Diskon Fleet-Kuartal X-Tahun-Kode Dealer.xlsx', '-', now(), now(), now(), 'abdul', now(), 'abdul');