CREATE TABLE banner
(
    Id           int IDENTITY(1,1) NOT NULL,
    FileId       bigint                DEFAULT '0',
    FileName     varchar(155) NOT NULL,
    FilePath     varchar(175)          DEFAULT NULL,
    IsShow       tinyint               DEFAULT '0',
    CreatedDate  datetime     NOT NULL DEFAULT getdate(),
    CreatedBy    varchar(35)           DEFAULT NULL,
    ModifiedDate datetime     NOT NULL DEFAULT getdate(),
    ModifiedBy   varchar(35)           DEFAULT NULL,
    RowStatus    tinyint               DEFAULT '1',
    PRIMARY KEY (Id)
)