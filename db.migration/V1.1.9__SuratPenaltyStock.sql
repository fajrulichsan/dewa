CREATE TABLE surat_penalty_stock
(
   Id           bigint IDENTITY(1,1) PRIMARY KEY,
   -- Id           bigint                     NOT NULL AUTO_INCREMENT PRIMARY KEY,
   DealerId     int                        NOT NULL,
   FileId       bigint NULL,
   FileName     varchar(155)               NOT NULL,
   Judul        varchar(255) NULL,
   Periode      varchar(35) NULL,
   Tahun        int NULL,
   CreatedDate  datetime DEFAULT getdate() NOT NULL,
   CreatedBy    varchar(35) NULL,
   ModifiedDate datetime DEFAULT getdate() NOT NULL,
   ModifiedBy   varchar(35) NULL,
   RowStatus    tinyint NULL
);

INSERT INTO surat_penalty_stock (Id, DealerCode, FileName, FilePath, Judul, PeriodeReviewId, Tahun, CreatedDate,
                                 CreatedBy, ModifiedDate, ModifiedBy)
VALUES ('001', '5200005726', 'Report Plafond-Tanggal Faktur-Kode Dealer.xlsx', '-', 'Judul 1', 'periode1', 2023, now(),
        'abdul', now(), 'abdul');

