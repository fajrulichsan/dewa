CREATE TABLE diskon_test_car
(
   Id           INT          IDENTITY(1,1) PRIMARY KEY,
   -- Id                INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   DealerId          INT          NOT NULL,

   FileId            BIGINT                DEFAULT 0,
   FileName          VARCHAR(155) NOT NULL,
   FilePath          VARCHAR(175)          DEFAULT NULL,

   Tahun             INT                   DEFAULT NULL,
   KuartalId         VARCHAR(25)           DEFAULT NULL,
   TipeKendaraanId   INT           DEFAULT NULL,
   Keterangan        VARCHAR(175)          DEFAULT NULL,
   InvoiceDate       DATETIME     NOT NULL,
   UploadDate        DATETIME     NOT NULL,

   -- audit
   CreatedDate       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy         VARCHAR(35)           DEFAULT NULL,
   ModifiedDate      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy        VARCHAR(35)           DEFAULT NULL,
   RowStatus         TINYINT      NOT NULL DEFAULT 1,

   CONSTRAINT fk_diskon_test_car_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (Id),
   CONSTRAINT fk_diskon_test_car_TipeKendaraanId FOREIGN KEY (TipeKendaraanId) REFERENCES tipe_kendaraan (id)
);

INSERT INTO diskon_test_car (Id, DealerCode, FileName, FilePath, TipeKendaraan, Keterangan, InvoiceDate, UploadDate, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (0, 1, 'Diskon Test Car Tipe Mobil-Kuartal X-Tahun-Kode Dealer.xlsx', '-', 'Xenia', '-', now(), now(), now(), 'abdul', now(), 'abdul');

