CREATE TABLE diskon_others
(
   -- diskonOtherId           INT          IDENTITY(1,1) PRIMARY KEY,
   diskonOtherId           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
   dealerId     INT          NOT NULL,

   fileId       BIGINT                DEFAULT 0,
   fileName     VARCHAR(155) NOT NULL,
   filePath     VARCHAR(175)          DEFAULT NULL,

   tahun        INT                   DEFAULT NULL,
   kuartalId    INT(11)               DEFAULT NULL,
   keterangan   VARCHAR(175)          DEFAULT NULL,

   -- audit
   createdDate  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   createdBy    VARCHAR(35)           DEFAULT NULL,
   modifiedDate DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   modifiedBy   VARCHAR(35)           DEFAULT NULL,
   rowStatus    TINYINT      NOT NULL DEFAULT 1
);

INSERT INTO diskon_others (id, dealerId, fileName, keterangan, invoiceDate, uploadDate, createdDate, createdBy, modifiedDate, modifiedBy)
VALUES (1, 1, 'Diskon Others-Kuartal X-Tahun-Kode Dealer.xlsx', '-', now(), now(), now(), '-', now(), '-');