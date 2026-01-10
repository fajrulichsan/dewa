-- MySQL
CREATE TABLE ApplicationAssignJournal
(
   Id                        int IDENTITY(1,1) PRIMARY KEY,
   ApplicationAssignId       int      NOT NULL,
   ApplicationHeaderId       int      NOT NULL,
   ApplicationAssignStatusId int      NOT NULL,
   ProfileId                 bigint   NOT NULL,
   StartDateOn               datetime          DEFAULT NULL,
   CompletedDateOn           datetime          DEFAULT NULL,
   Notes                     varchar(255)      DEFAULT NULL,
   RowStatus                 tinyint DEFAULT 1,
   CreatedBy                 varchar(35)       DEFAULT NULL,
   CreatedDate               datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy                varchar(35)       DEFAULT NULL,
   ModifiedDate              datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);
