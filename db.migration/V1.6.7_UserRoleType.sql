CREATE TABLE UserRoleType (
   -- PK fields
   Id             INT            NOT NULL    PRIMARY KEY AUTO_INCREMENT,

   -- FK fields
   UserId         BIGINT         NOT NULL,
   RoleId         INT            NOT NULL,

   -- Audit fields
   RowStatus      TINYINT        NOT NULL    DEFAULT  1,
   CreatedBy      VARCHAR(35)    NULL,
   CreatedDate    DATETIME                   DEFAULT  CURRENT_TIMESTAMP,
   ModifiedBy     VARCHAR(35)    NULL,
   ModifiedDate   DATETIME                   DEFAULT  CURRENT_TIMESTAMP
);