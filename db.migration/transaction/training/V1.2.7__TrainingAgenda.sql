CREATE TABLE training_agenda
(
   -- Id           int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id                         int IDENTITY(1,1) PRIMARY KEY,
   Judul                      varchar(555) NOT NULL,
   JenisAgenda                int          NOT NULL,
   StatusAgenda               int          NOT NULL,
   Location                   varchar(255)          DEFAULT NULL,
   LinkMeeting                varchar(255)          DEFAULT NULL,
   StartDate                  date         not null,
   StartDateHours             varchar(7)   not null,
   EndDate                    date         not null,
   EndDateHours               varchar(7)   not null,
   RegistrationLimitDate      date         not null,
   RegistrationLimitDateHours varchar(7)   not null,
   Deskripsi                  text         NOT NULL,
   ImageId                    bigint                DEFAULT 0,
   ImageName                  varchar(255) NOT NULL,
   ImagePath                  varchar(255) NOT NULL,
   -- audit
   RowStatus                  tinyint               DEFAULT 1,
   CreatedDate                datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy                  varchar(35)           DEFAULT NULL,
   ModifiedDate               datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy                 varchar(35)           DEFAULT NULL
--    CONSTRAINT fk_training_agenda_JenisAgendaId FOREIGN KEY (JenisAgendaId) REFERENCES jenis_agenda (id),
--    CONSTRAINT fk_training_agenda_StatusAgendaId FOREIGN KEY (StatusAgendaId) REFERENCES status_agenda (id)
);
