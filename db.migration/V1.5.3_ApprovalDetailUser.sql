DROP TABLE IF EXISTS ApprovalDetailUser;
CREATE TABLE ApprovalDetailUser
(
   -- PK fields
   -- Id                   INT   IDENTITY(1,1)  PRIMARY KEY,
   Id                   INT   NOT NULL       PRIMARY KEY    AUTO_INCREMENT,

   -- FK fields
   ApprovalHeaderUserId INT   NOT NULL,
   RoleId               INT   NOT NULL,

   -- Audit fields
   RowStatus            TINYINT        NOT NULL    DEFAULT 1,
   CreatedDate          DATETIME       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
   CreatedBy            VARCHAR(35)    NULL,
   ModifiedDate         DATETIME       NOT NULL    DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy           VARCHAR(35)    NULL
)