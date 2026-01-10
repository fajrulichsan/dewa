CREATE TABLE sp3d
(
   Id           int IDENTITY(1,1) PRIMARY KEY,
   -- Id           int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   DealerId     int       NOT NULL,
   DealerName   varchar(50)  NOT Null,
   FileId       bigint                DEFAULT 0,
   FileName     varchar(155) NOT NULL,
   FilePath     varchar(175)          DEFAULT NULL,
   Tahun        int NOT NULL,
   Bulan        varchar(50)  NOT NULL,
   -- audit
   RowStatus    tinyint NOT NULL DEFAULT 1,
   CreatedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    varchar(35)           DEFAULT NULL,
   ModifiedDate datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)           DEFAULT NULL,
   CONSTRAINT fk_surat_penalty_stock_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (id)
);

-- INSERT INTO sp3d (Id, DealerName, FileName, FilePath, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy) VALUES
--     (5200005726, 'CV.KARUNIA MOTOR','Diskon Fleet-Kuartal X-Tahun-Kode Dealer.xlsx', '-', now(), 'abdul', now(), 'abdul');
--
