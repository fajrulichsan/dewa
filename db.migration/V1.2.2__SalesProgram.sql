CREATE TABLE sales_program
(
   Id           bigint IDENTITY(1,1) PRIMARY KEY,
   -- Id           bigint                        NOT NULL AUTO_INCREMENT PRIMARY KEY,
   FileId       bigint      DEFAULT 0 NULL,
   FileName     varchar(155)                  NOT NULL,
   rowStatus    tinyint     DEFAULT NULL NULL,
   Tahun        int         DEFAULT 0 NULL,
   Periode      varchar(75) DEFAULT NULL NULL,
   CreatedDate  datetime    DEFAULT getdate() NOT NULL,
   CreatedBy    varchar(35) DEFAULT NULL NULL,
   ModifiedDate datetime    DEFAULT getdate() NOT NULL,
   ModifiedBy   varchar(35) DEFAULT NULL NULL
);

INSERT INTO sales_program (Id, DealerCode, FileName, FilePath, Periode, Tahun, CreatedDate, CreatedBy, ModifiedDate,
                           ModifiedBy)
VALUES ('5200005726', '5200005726', 'Diskon Fleet-Kuartal X-Tahun-Kode Dealer.xlsx', '-', 'Januari', 2022, now(),
        'abdul', now(), 'abdul');

