CREATE TABLE roles (
	 Id				INT				NOT NULL 	PRIMARY KEY,
    Name			VARCHAR(50)		NOT NULL,
    Description		VARCHAR (255)			 	DEFAULT NULL,
    CreatedDate		TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    CreatedBy		VARCHAR(35)		NULL,
    ModifiedDate	TIMESTAMP		NOT NULL 	DEFAULT CURRENT_TIMESTAMP,
    ModifiedBy		VARCHAR(35)		NULL,
    RowStatus		TINYINT			NOT NULL	DEFAULT 1,
    RoleIdMember VARCHAR(255) NOT NULL
);

INSERT INTO roles (Id, Name)
VALUES (1, 'Administrator'),
       (2, 'HO Dealer'),
       (3, 'Dealer'),
       (4, 'DSO - FA Operation - Indirect Opr'),
       (5, 'DSO - FA Operation - BPD'),
       (6, 'DSO - FA Operation - Direct Opr'),
       (7, 'DSO - Finance - Legal'),
       (8, 'DSO - Treasury & Budget - Treasury'),
       (9, 'DSO - Marketing - Sales Program & Opr'),
       (10, 'DSO - Marketing - Marcom'),
       (11, 'DSO - Marketing - NFSD'),
       (12, 'DSO - Service - W/S Planning & Dev'),
       (13, 'DSO - Service - Service');
