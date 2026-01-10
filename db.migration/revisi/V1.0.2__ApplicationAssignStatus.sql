CREATE TABLE ApplicationAssignStatus
(
    Id           INT          NOT NULL PRIMARY KEY,
    Name         VARCHAR(155) NOT NULL,
    Description  VARCHAR(255) NOT NULL,
    RowStatus    TINYINT               DEFAULT 1,
    CreatedBy    VARCHAR(35)           DEFAULT NULL,
    CreatedDate  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy   VARCHAR(35)           DEFAULT NULL,
    ModifiedDate DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
);

-- MySQL
INSERT INTO ApplicationAssignStatus (Id, Name, Description, RowStatus, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Waiting', '-', 1, now(), 'abdul', now(), 'abdul'),
       (2, 'Approved', '-', 1, now(), 'abdul', now(), 'abdul'),
       (3, 'Rejected', '-', 1, now(), 'abdul', now(), 'abdul'),
       (4, 'Revised', '-', 1, now(), 'abdul', now(), 'abdul'),
       (5, 'Completed', '-', 1, now(), 'abdul', now(), 'abdul'),
       (6, 'Submitted', '-', 1, now(), 'abdul', now(), 'abdul'),
       (7, 'Assigned', '-', 1, now(), 'abdul', now(), 'abdul');

-- SQL Server
INSERT INTO ApplicationAssignStatus (Id, Name, Description, RowStatus, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Waiting', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (2, 'Approved', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (3, 'Rejectet', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (4, 'Revised', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (5, 'Completed', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (6, 'Submitted', '-', 1, getdate(), 'fajri', getdate(), 'fajri'),
       (7, 'Assigned', '-', 1, getdate(), 'fajri', getdare(), 'fajri');
