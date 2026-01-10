CREATE TABLE sk_iris
(
   Id           bigint IDENTITY(1,1) PRIMARY KEY,
   -- Id           bigint       NOT NULL PRIMARY KEY,
   FileId       bigint                DEFAULT 0,
   FileName     varchar(155) NOT NULL,
   FilePath     varchar(175)          DEFAULT NULL,
   Tahun        int                   DEFAULT 0,
   Periode      varchar(75)           DEFAULT NULL,
   WilayahId    int                   DEFAULT NULL,
   DealerId     int          NOT NULL,
   Kategori     varchar(50)  NOT NULL,
   -- audit
   RowStatus    tinyint NOT NULL DEFAULT 1,
   CreatedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy    varchar(35)           DEFAULT NULL,
   ModifiedDate datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy   varchar(35)           DEFAULT NULL,
   CONSTRAINT fk_sk_iris_WilayahId FOREIGN KEY (WilayahId) REFERENCES Wilayah (id)
);

-- INSERT INTO sk_iris (Id, FileName, FilePath, Periode, Tahun, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy) VALUES
--     ('5200005726', 'Diskon Fleet-Kuartal X-Tahun-Kode Dealer.xlsx', '-', 'Januari', 2022, now(), 'abdul', now(), 'abdul');

