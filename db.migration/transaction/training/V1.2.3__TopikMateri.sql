CREATE TABLE topik_materi
(
   -- Id           int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id   int IDENTITY(1,1) PRIMARY KEY,
   Name varchar(155) NOT NULL,
   -- audit
   RowStatus     tinyint               DEFAULT 1,
   CreatedDate   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy     varchar(35)           DEFAULT NULL,
   ModifiedDate  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy    varchar(35)           DEFAULT NULL
);
