CREATE TABLE faktur_indirect
(
    -- PK fields
    --    Id           INT          IDENTITY(1,1) PRIMARY KEY,
    Id           INT          NOT NULL AUTO_INCREMENT  PRIMARY KEY,

    -- FK fields
    DealerId     INT          NOT NULL,

    -- Other fields
    Keterangan   VARCHAR(175)          DEFAULT NULL,
    InvoiceDate  DATETIME     NOT NULL,
    UploadDate   DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- File fields
    FileId       BIGINT                DEFAULT 0,
    FileName     VARCHAR(155) NOT NULL,
    FilePath     VARCHAR(175)          DEFAULT NULL,

    -- Audit fields
    CreatedDate  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CreatedBy    VARCHAR(35)           DEFAULT NULL,
    ModifiedDate DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy   VARCHAR(35)           DEFAULT NULL,
    RowStatus    TINYINT      NOT NULL DEFAULT 1,

    -- Constraints
    -- CONSTRAINT fk_faktur_indirect_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (id)
);

INSERT INTO faktur_indirect (Id, DealerId, FileName, Keterangan, InvoiceDate, UploadDate, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (0, 1, 'Rekap Faktur Kendaraan-Tanggal Faktur-Kode Dealer.pdf', '-', now(), now(), now(), 'fajri', now(), 'fajri');

