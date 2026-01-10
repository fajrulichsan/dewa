CREATE TABLE master_approval
(
    -- PK fields
    Id               INT IDENTITY(1,1)   PRIMARY KEY,
    -- Id                INT            NOT NULL          PRIMARY KEY    AUTO_INCREMENT,

    -- FK fields
    RoleId           INT          NOT NULL,
    MenuId           INT          NOT NULL,

    -- other fields
    ApprovalGroup    VARCHAR(100) NOT NULL,
    ApprovalWorkflow TINYINT      NOT NULL,

    -- audit fields
    RowStatus        TINYINT      NOT NULL DEFAULT 1,
    CreatedDate      DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CreatedBy        VARCHAR(35)           DEFAULT NULL,
    ModifiedDate     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy       VARCHAR(35)           DEFAULT NULL,

    -- constraints
    -- CONSTRAINT fk_master_approval_RoleId FOREIGN KEY (RoleId) REFERENCES Roles(Id),
    -- CONSTRAINT fk_master_approval_MenuId FOREIGN KEY (MenuId) REFERENCES Application(Id)
);