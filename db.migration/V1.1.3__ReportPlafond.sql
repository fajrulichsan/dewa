CREATE TABLE report_plafond
(
   Id           INT IDENTITY(1,1) PRIMARY KEY,
   DealerCode   varchar(50)  NOT NULL,
   Dealername   varchar(50)  NOT NULL,
   FileId       bigint                DEFAULT 0,
   FileName     varchar(155) NOT NULL,
   FilePath     varchar(175)          DEFAULT NULL,
   Periode      date                  DEFAULT NULL,
   Keterangan   varchar(175)          DEFAULT NULL,
   -- audit
   RowStatus    tinyint      NOT NULL DEFAULT 1,
   CreatedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    varchar(35)           DEFAULT NULL,
   ModifiedDate datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)           DEFAULT NULL,
   CONSTRAINT fk_report_plafond_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (id)
);

INSERT INTO report_plafond (Id, DealerCode, FileName, FilePath, Periode, Keterangan, CreatedDate, CreatedBy,
                            ModifiedDate, ModifiedBy)
VALUES ('001', '5200005726', 'Report Plafond-Tanggal Faktur-Kode Dealer.xlsx', '-', now(), '-', now(), 'abdul', now(),
        'abdul');

