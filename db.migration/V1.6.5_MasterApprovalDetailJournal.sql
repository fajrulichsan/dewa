CREATE TABLE master_approval_detail_journal
(
    -- PK fields
    Id                      INT IDENTITY(1,1)   PRIMARY KEY,
    -- Id                      INT            NOT NULL        PRIMARY KEY    AUTO_INCREMENT,

    -- FK fields
    MasterApprovalId        INT      NOT NULL,
    MasterApprovalJournalId INT      NOT NULL,
    MasterApprovalDetailId  INT      NOT NULL,
    UserId                  INT      NOT NULL,

    -- other fields
    ApprovalLevel           INT      NOT NULL,
    IsDefault               TINYINT  NOT NULL DEFAULT 0,

    -- audit fields
    RowStatus               TINYINT  NOT NULL DEFAULT 1,
    CreatedDate             DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CreatedBy               VARCHAR(35)       DEFAULT NULL,
    ModifiedDate            DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy              VARCHAR(35)       DEFAULT NULL,

    -- constraints
    -- CONSTRAINT fk_master_approval_detail_journal_MasterApprovalDetailId FOREIGN KEY (MasterApprovalDetailId) REFERENCES master_approval_detail (Id),
    -- CONSTRAINT fk_master_approval_detail_journal_MasterApprovalId FOREIGN KEY (MasterApprovalId) REFERENCES master_approval (Id)
)