CREATE TABLE calender_event
(
   -- Id                        int NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id                         int IDENTITY(1,1) PRIMARY KEY,
   Judul                      varchar(555),
   JenisAcara                 int          NOT NULL,
   StatusAcara                int          NOT NULL,
   Location                   varchar(255)          DEFAULT NULL,
   LinkMeeting                varchar(255)          DEFAULT NULL,
   StartDate                  datetime              DEFAULT NULL,
   StartDateHours             varchar(7)            DEFAULT NULL,
   EndDate                    datetime              DEFAULT NULL,
   EndDateHours               varchar(7)            DEFAULT NULL,
   RegistrationLimitDate      datetime              DEFAULT NULL,
   RegistrationLimitDateHours varchar(7)            DEFAULT NULL,
   Deskripsi                  text         NOT NULL,
   ImageId                    bigint                DEFAULT 0,
   ImageName                  varchar(255) NOT NULL,
   ImagePath                  varchar(255) NOT NULL,
   JenisAcaraName             varchar(255) NULL,
   StatusAcaraName            varchar(255) NULL,
   Lokasi                     varchar(255) NULL,
   -- audit
   RowStatus                  tinyint               DEFAULT 1,
   CreatedDate                datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy                  varchar(35)           DEFAULT NULL,
   ModifiedDate               datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy                 varchar(35)           DEFAULT NULL
--    PRIMARY KEY (Id),
--    CONSTRAINT fk_calender_event_JenisAcaraId FOREIGN KEY (JenisAcaraId) REFERENCES jenis_acara (id),
--    CONSTRAINT fk_calender_event_StatusAcaraId FOREIGN KEY (StatusAcaraId) REFERENCES status_acara (id)
);

INSERT INTO calender_event (JenisAcaraId, JenisAcaraName, StatusAcaraId, StatusAcaraName, Lokasi, Deskripsi, ImageId,
                            ImageName, ImagePath, CreatedDate, CreatedBy, ModifiedDate, ModifiedBy)
VALUES ('offline', 'Offline', 'belum_terlaksana', 'Belum Terlaksana', 'Jakarta', '-', 0, '-', '-', now(), 'abdul',
        now(), 'abdul');

