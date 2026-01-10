DROP TABLE IF EXISTS ApprovalHistoryUser;
CREATE TABLE ApprovalHistoryUser
(
   -- PK fields
   -- Id                         INT IDENTITY(1,1) PRIMARY KEY,
   Id                         INT(11)     NOT NULL    AUTO_INCREMENT    PRIMARY KEY,

   -- FK fields
   ApprovalHeaderUserId       INT         NOT NULL,
   ApplicationAssignStatusId  INT         NOT NULL,
   ApproverUserId             BIGINT      NOT NULL,
   DealerId                   INT         NOT NULL,
   CabangId                   INT         NOT NULL,

   -- Requester fields
   RequesterName        VARCHAR(255)   NOT NULL,
   RequesterEmail       VARCHAR(255)   NOT NULL,

   -- Other fields
   RejectReason               VARCHAR (255) NULL,

   -- Audit fields
   RowStatus                  TINYINT     NOT NULL    DEFAULT  1,
   CreatedDate                DATETIME    NOT NULL    DEFAULT  CURRENT_TIMESTAMP,
   CreatedBy                  VARCHAR(35) NULL,
   ModifiedDate               DATETIME    NOT NULL    DEFAULT  CURRENT_TIMESTAMP,
   ModifiedBy                 VARCHAR(35) NULL
)