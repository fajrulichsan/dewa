CREATE TABLE bulan
(
   Id        varchar(25) NOT NULL,
   Name      varchar(25) NOT NULL,
   SortIndex int         NOT NULL,
   PRIMARY KEY (Id)
);

INSERT INTO bulan (Id, Name, SortIndex)
VALUES ('Januari', 'Januari', 1),
       ('Februari', 'Februari', 2),
       ('Maret', 'Maret', 3),
       ('April', 'April', 4),
       ('Mei', 'Mei', 5),
       ('Juni', 'Juni', 6),
       ('Juli', 'Juli', 7),
       ('Agustus', 'Agustus', 8),
       ('September', 'September', 9),
       ('Oktober', 'Oktober', 10),
       ('November', 'November', 11),
       ('Desember', 'Desember', 12);
