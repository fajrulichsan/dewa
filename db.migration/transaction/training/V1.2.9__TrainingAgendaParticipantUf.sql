CREATE TABLE training_agenda_participant_uf
(
   -- Id           int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id               int IDENTITY(1,1) PRIMARY KEY,
   TrainingAgendaId int          NOT NULL,
   DealerId         int          NOT NULL,
   FileId           bigint                DEFAULT 0,
   FileName         varchar(255) NOT NULL,
   FilePath         varchar(255) NOT NULL,
   -- audit
   RowStatus        tinyint               DEFAULT 1,
   CreatedDate      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   CreatedBy        varchar(35)           DEFAULT NULL,
   ModifiedDate     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   ModifiedBy       varchar(35)           DEFAULT NULL
--    PRIMARY KEY (Id),
--    CONSTRAINT fk_training_agenda_participant_uf_DealerId FOREIGN KEY (DealerId) REFERENCES Dealer (id),
--    CONSTRAINT fk_training_agenda_participant_uf_TrainingAgendaId FOREIGN KEY (TrainingAgendaId) REFERENCES training_agenda (id)
);
