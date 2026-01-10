-- MySQL
CREATE TABLE ApplicationAssign
(
    Id                        INT      NOT NULL IDENTITY(1,1)       PRIMARY KEY,
    -- Id                        INT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ApplicationHeaderId       INT      NOT NULL,
    ApplicationAssignStatusId INT      NOT NULL,
    ProfileId                 BIGINT   NOT NULL,
    StartDateOn               DATETIME          DEFAULT NULL,
    CompletedDateOn           DATETIME          DEFAULT NULL,
    Notes                     VARCHAR(255)      DEFAULT NULL,
    RowStatus                 TINYINT           DEFAULT 1,
    CreatedBy                 VARCHAR(35)       DEFAULT NULL,
    CreatedDate               DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy                VARCHAR(35)       DEFAULT NULL,
    ModifiedDate              DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
