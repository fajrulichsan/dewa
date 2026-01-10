CREATE TABLE ApplicationHeaderStatus
(
    Id           INT NOT NULL PRIMARY KEY,
    Name         VARCHAR(155) NOT NULL,
    Description  VARCHAR(255) NOT NULL,
    RowStatus    TINYINT               DEFAULT 1,
    CreatedBy    VARCHAR(35)           DEFAULT NULL,
    CreatedDate  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy   VARCHAR(35)           DEFAULT NULL,
    ModifiedDate DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- MySQL
INSERT INTO ApplicationHeaderStatus (Id, Name, Description, RowStatus, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Waiting for Approval', '-', 1, now(), 'abdul', now(), 'abdul'),
       (2, 'Approved', '-', 1, now(), 'abdul', now(), 'abdul'),
       (3, 'Rejected', '-', 1, now(), 'abdul', now(), 'abdul'),
       (4, 'Revised', '-', 1, now(), 'abdul', now(), 'abdul'),
       (5, 'Draft', '-', 1, now(), 'abdul', now(), 'abdul'),
       (6, 'Submitted', '-', 1, now(), 'fajri', now(), 'fajri'),
       (7, 'Waiting for Approval Level 1', '-', 1, now(), 'fajri', now(), 'fajri'),
       (8, 'Waiting for Approval Level 2', '-', 1, now(), 'fajri', now(), 'fajri'),
       (9, 'Waiting for Approval Level 3', '-', 1, now(), 'fajri', now(), 'fajri'),
       (10, 'Waiting for Approval Level 4', '-', 1, now(), 'fajri', now(), 'fajri'),
       (11, 'Waiting for Approval Level 5', '-', 1, now(), 'fajri', now(), 'fajri');

-- SQL Server
INSERT INTO ApplicationHeaderStatus (Id, Name, Description, RowStatus, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Waiting for Approval', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (2, 'Approve', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (3, 'Reject', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (4, 'Revise', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (5, 'Draft', '-', 1, getdate(), 'abdul', getdate(), 'abdul'),
       (6, 'Submitted', '-', 1, getdate(), 'fajri', getdate(), 'fajri'),
       (7, 'Waiting for Approval Level 1', '-', 1, getdate(), 'fajri', getdate(), 'fajri'),
       (8, 'Waiting for Approval Level 2', '-', 1, getdate(), 'fajri', getdate(), 'fajri'),
       (9, 'Waiting for Approval Level 3', '-', 1, getdate(), 'fajri', getdate(), 'fajri'),
       (10, 'Waiting for Approval Level 4', '-', 1, getdate(), 'fajri', getdate(), 'fajri'),
       (11, 'Waiting for Approval Level 5', '-', 1, getdate(), 'fajri', getdate(), 'fajri');
