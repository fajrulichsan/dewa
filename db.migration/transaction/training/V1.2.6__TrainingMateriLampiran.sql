CREATE TABLE training_materi_lampiran
(
   -- Id           int         NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id   int IDENTITY(1,1) PRIMARY KEY,
   TrainingMateriId int  NOT NULL,
   FileId           bigint       DEFAULT 0,
   FileName         varchar(155) NOT NULL,
   FilePath         varchar(175) DEFAULT NULL
--    CONSTRAINT fk_training_materi_lampiran_TrainingMateriId FOREIGN KEY (TrainingMateriId) REFERENCES training_materi (id)
);
