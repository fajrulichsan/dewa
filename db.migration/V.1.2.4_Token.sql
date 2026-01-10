CREATE TABLE token
(
   -- Id              int(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id          int IDENTITY(1,1) PRIMARY KEY,
   TokenNumber varchar(256) DEFAULT NULL,
   CreatedDate datetime     DEFAULT NULL,
   ExpiredDate datetime     DEFAULT NULL
)