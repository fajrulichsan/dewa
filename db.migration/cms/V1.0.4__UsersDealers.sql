CREATE TABLE UsersDealers
(
    -- PK fields
    Id                INT IDENTITY(1,1) PRIMARY KEY,
    --Id                INT      NOT NULL PRIMARY KEY AUTO_INCREMENT,

    -- FK fields
    UserId            BIGINT   NOT NULL DEFAULT 0,
    DealerId          INT               DEFAULT NULL,
    RoleId            INT      NOT NULL,

    -- Other fields
    IsDefault         TINYINT  NOT NULL DEFAULT 0,
    Ordinal           INT      NOT NULL DEFAULT 0,
    FullName          VARCHAR(35)       DEFAULT NULL,
    PhotoFileId       BIGINT            DEFAULT 0,
    ADB2CId           VARCHAR(100)      DEFAULT NULL,
    userPrincipalName VARCHAR(100)      DEFAULT NULL,
    ApprovedDate      DATETIME          DEFAULT NULL,

    -- Audit fields
    RowStatus         TINYINT  NOT NULL DEFAULT 1,
    CreatedDate       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CreatedBy         VARCHAR(35)       DEFAULT NULL,
    ModifiedDate      DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy        VARCHAR(35)       DEFAULT NULL
);
