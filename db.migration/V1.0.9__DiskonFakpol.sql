CREATE TABLE diskon_fakpol
(
   -- key fields
   Id           INT          IDENTITY(1,1) PRIMARY KEY,
   -- Id           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   DealerId     INT          NOT NULL,

   -- file fields
   FileId       BIGINT                DEFAULT 0,
   FileName     VARCHAR(155) NOT NULL,
   FilePath     VARCHAR(175)          DEFAULT NULL,

   -- period fields
   Tahun        INT                   DEFAULT NULL,
   Periode      VARCHAR(25)           DEFAULT NULL,
   InvoiceDate  DATETIME     NOT NULL,
   UploadDate   DATETIME     NOT NULL,

   -- other fields
   Keterangan   VARCHAR(175)          DEFAULT NULL,

   -- audit fields
   CreatedDate  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    VARCHAR(35)           DEFAULT NULL,
   ModifiedDate DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   VARCHAR(35)           DEFAULT NULL,
   RowStatus    TINYINT      NOT NULL DEFAULT 1

   -- constraint fields
   CONSTRAINT fk_diskon_fakpol_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (Id)
);

INSERT INTO diskon_fakpol (Id, DealerId, FileName, FilePath, Periode, Keterangan, InvoiceDate, UploadDate, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (0, 1, 'Diskon Fakpol-Bulan-Tahun-Kode Dealer.xlsx', '-', 'Januari', '-', now(), now(), now(), 'abdul', now(), 'abdul');

