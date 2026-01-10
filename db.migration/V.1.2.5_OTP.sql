CREATE TABLE otp
(
   -- Id              int(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY,
   Id           int IDENTITY(1,1) PRIMARY KEY,
   OTPNumber    int          DEFAULT NULL,
   EmailNewUser varchar(256) DEFAULT NULL,
   CreatedDate  datetime     DEFAULT NULL,
   ExpiredDate  datetime     DEFAULT NULL
)