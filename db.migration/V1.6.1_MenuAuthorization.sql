CREATE TABLE menuAuthorization
(
    -- PK fields
    Id           INT IDENTITY(1,1)   PRIMARY KEY,
    -- Id          INT                 NOT NULL        PRIMARY KEY     AUTO_INCREMENT,

    -- FK fields
    RoleId       INT          NOT NULL,
    MenuIds      VARCHAR(255) NOT NULL,

    -- audit fields
    RowStatus    TINYINT      NOT NULL DEFAULT 1,
    CreatedDate  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CreatedBy    VARCHAR(35)           DEFAULT NULL,
    ModifiedDate TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy   VARCHAR(35)           DEFAULT NULL,

    -- constraints
    -- CONSTRAINT fk_menu_authorization_RoleId FOREIGN KEY (RoleId) REFERENCES Roles (Id)
);