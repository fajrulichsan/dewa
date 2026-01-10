CREATE TABLE calender_event_participant
(
   -- Id                        int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id              int IDENTITY(1,1) PRIMARY KEY,
   FullName        varchar(155) NOT NULL,
   Email           varchar(255) NOT NULL,
   Phone           varchar(255) NOT NULL,
   CalenderEventId int          NOT NULL,
   DealerId        int                   DEFAULT NULL,
   -- audit
   RowStatus       tinyint               DEFAULT 1,
   CreatedDate     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy       varchar(35)           DEFAULT NULL,
   ModifiedDate    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy      varchar(35)           DEFAULT NULL
--    CONSTRAINT fk_calender_event_participant_CalenderEventId FOREIGN KEY (CalenderEventId) REFERENCES calender_event (id),
--    CONSTRAINT fk_calender_event_participant_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (id)
);

INSERT INTO calender_event_participant (Id, FullName, Email, Phone, CalenderEventId, DealerId, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES (1, 'Muhammad', 'Muhammad@gmail.com', '082137306191', 1, 1, now(), 'abdul', now(), 'abdul'),
       (2, 'Abdul', 'Abdul@gmail.com', '082137306192', 1, 1, now(), 'abdul', now(), 'abdul'),
       (3, 'Honi', 'Honi@gmail.com', '082137306193', 1, 1, now(), 'abdul', now(), 'abdul'),
       (4, 'Sura', 'Sura@gmail.com', '082137306194', 1, 1, now(), 'abdul', now(), 'abdul'),
       (5, 'Karta', 'Karta@gmail.com', '082137306195', 1, 1, now(), 'abdul', now(), 'abdul');