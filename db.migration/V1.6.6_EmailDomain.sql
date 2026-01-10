CREATE TABLE email_domain
(
	-- Id             INT IDENTITY(1,1)       PRIMARY KEY,
	-- Id             INT         NOT NULL    PRIMARY KEY AUTO_INCREMENT,
	DomainName     VARCHAR(55) NOT NULL,
	Category       VARCHAR(55) NOT NULL,
	RowStatus      TINYINT     NOT NULL    DEFAULT 1,
	CreatedBy      VARCHAR(35)             DEFAULT NULL,
	CreatedDate    DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP,
	ModifiedBy     VARCHAR(35)             DEFAULT NULL,
	ModifiedDate   DATETIME    NOT NULL    DEFAULT CURRENT_TIMESTAMP
);