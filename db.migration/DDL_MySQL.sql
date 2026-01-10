-- MySQL DDL Conversion
-- Converted from SQL Server DDL

-- Application definition

CREATE TABLE `Application` (
	`Id` int NOT NULL,
	`Name` varchar(155) NOT NULL,
	`Description` varchar(255) NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApplicationAssign definition

CREATE TABLE `ApplicationAssign` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApplicationHeaderId` int NOT NULL,
	`ApplicationAssignStatusId` int NOT NULL,
	`ProfileId` bigint NOT NULL,
	`StartDateOn` datetime DEFAULT NULL,
	`CompletedDateOn` datetime DEFAULT NULL,
	`Notes` varchar(255) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApplicationAssignJournal definition

CREATE TABLE `ApplicationAssignJournal` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApplicationAssignId` int NOT NULL,
	`ApplicationHeaderId` int NOT NULL,
	`ApplicationAssignStatusId` int NOT NULL,
	`ProfileId` bigint NOT NULL,
	`StartDateOn` datetime DEFAULT NULL,
	`CompletedDateOn` datetime DEFAULT NULL,
	`Notes` varchar(255) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApplicationAssignStatus definition

CREATE TABLE `ApplicationAssignStatus` (
	`Id` int NOT NULL,
	`Name` varchar(155) NOT NULL,
	`Description` varchar(255) NOT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApplicationCategory definition

CREATE TABLE `ApplicationCategory` (
	`Id` int NOT NULL,
	`Name` varchar(155) NOT NULL,
	`Description` varchar(255) NOT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`CategoryBonus` int DEFAULT 0,
	PRIMARY KEY (`Id`)
);


-- ApplicationHeader definition

CREATE TABLE `ApplicationHeader` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApplicationId` int NOT NULL,
	`ApplicationHeaderStatusId` int NOT NULL,
	`ApplicationCategoryId` int NOT NULL,
	`DealerId` int NOT NULL,
	`TicketNo` varchar(55) DEFAULT NULL,
	`TicketCode` varchar(9) DEFAULT NULL,
	`ReqDate` datetime DEFAULT NULL,
	`ReqYear` int DEFAULT 0,
	`ReqMonth` int DEFAULT 0,
	`ReqScreenName` varchar(55) DEFAULT NULL,
	`ReqName` varchar(55) DEFAULT NULL,
	`ReqEmail` varchar(55) DEFAULT NULL,
	`ReqCCEmail` varchar(55) DEFAULT NULL,
	`ReqPhone` varchar(16) DEFAULT NULL,
	`NominalPengajuan` int NOT NULL,
	`ReqDesc` varchar(255) DEFAULT NULL,
	`BusinessBenefit` varchar(255) DEFAULT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(255) DEFAULT NULL,
	`FileUrl` varchar(255) DEFAULT NULL,
	`Notes` varchar(255) DEFAULT NULL,
	`NotesHistory` text DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApplicationHeaderJournal definition

CREATE TABLE `ApplicationHeaderJournal` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApplicationHeaderId` int NOT NULL,
	`ApplicationId` int NOT NULL,
	`ApplicationHeaderStatusId` int NOT NULL,
	`ApplicationCategoryId` int NOT NULL,
	`DealerId` int NOT NULL,
	`TicketNo` varchar(55) DEFAULT NULL,
	`TicketCode` varchar(9) DEFAULT NULL,
	`ReqDate` datetime DEFAULT NULL,
	`ReqYear` int NOT NULL,
	`ReqMonth` int NOT NULL,
	`ReqScreenName` varchar(55) DEFAULT NULL,
	`ReqName` varchar(55) DEFAULT NULL,
	`ReqEmail` varchar(55) DEFAULT NULL,
	`ReqCCEmail` varchar(55) DEFAULT NULL,
	`ReqPhone` varchar(16) DEFAULT NULL,
	`NominalPengajuan` int NOT NULL,
	`ReqDesc` varchar(255) DEFAULT NULL,
	`BusinessBenefit` varchar(255) DEFAULT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(255) DEFAULT NULL,
	`FileUrl` varchar(255) DEFAULT NULL,
	`Notes` varchar(255) DEFAULT NULL,
	`NotesHistory` text DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApplicationHeaderStatus definition

CREATE TABLE `ApplicationHeaderStatus` (
	`Id` int NOT NULL,
	`Name` varchar(155) NOT NULL,
	`Description` varchar(255) DEFAULT NULL NOT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- ApprovalDetailUser definition

CREATE TABLE `ApprovalDetailUser` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApprovalHeaderUserId` int NOT NULL,
	`RoleId` int NOT NULL,
	`RowStatus` tinyint NULL,
	`CreatedDate` datetime NULL,
	`CreatedBy` varchar(100) NULL,
	`ModifiedDate` datetime NULL,
	`ModifiedBy` varchar(100) NULL,
	PRIMARY KEY (`Id`)
);


-- ApprovalHeaderUser definition

CREATE TABLE `ApprovalHeaderUser` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApplicationAssignStatusId` int NOT NULL,
	`RejectReason` varchar(256) DEFAULT NULL,
	`RowStatus` tinyint NULL,
	`CreatedDate` datetime NULL,
	`CreatedBy` varchar(100) NULL,
	`ModifiedDate` datetime NULL,
	`ModifiedBy` varchar(100) NULL,
	`ApproverUserId` bigint NULL,
	`DealerId` int NULL,
	`CabangId` int NULL,
	`RequesterName` varchar(100) NULL,
	`RequesterEmail` varchar(100) NULL,
	PRIMARY KEY (`Id`)
);


-- ApprovalHistoryUser definition

CREATE TABLE `ApprovalHistoryUser` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`ApprovalHeaderUserId` int NOT NULL,
	`ApplicationAssignStatusId` int NOT NULL,
	`RejectReason` varchar(256) DEFAULT NULL,
	`RowStatus` tinyint NULL,
	`CreatedDate` datetime NULL,
	`CreatedBy` varchar(100) NULL,
	`ModifiedDate` datetime NULL,
	`ModifiedBy` varchar(100) NULL,
	`ApproverUserId` bigint NULL,
	`DealerId` int NULL,
	`CabangId` int NULL,
	`RequesterName` varchar(100) NULL,
	`RequesterEmail` varchar(100) NULL,
	PRIMARY KEY (`Id`)
);


-- Cabang definition

CREATE TABLE `Cabang` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Name` varchar(35) NULL,
	`Description` varchar(255) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- Configuration_ definition

CREATE TABLE `Configuration_` (
	`configurationId` varchar(255) NOT NULL,
	`dictionary` longtext NULL,
	PRIMARY KEY (`configurationId`)
);


-- Dealer definition

CREATE TABLE `Dealer` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Code` varchar(15) NOT NULL,
	`Name` varchar(75) NOT NULL,
	`KodeHo` varchar(15) DEFAULT NULL,
	`CabangId` int DEFAULT NULL,
	`WilayahId` int DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`GroupDealer` int DEFAULT 1,
	PRIMARY KEY (`Id`)
);


-- SystemEvent definition

CREATE TABLE `SystemEvent` (
	`mvccVersion` bigint DEFAULT 0 NOT NULL,
	`systemEventId` bigint NOT NULL,
	`groupId` bigint NULL,
	`companyId` bigint NULL,
	`userId` bigint NULL,
	`userName` varchar(75) NULL,
	`createDate` datetime NULL,
	`classNameId` bigint NULL,
	`classPK` bigint NULL,
	`classUuid` varchar(75) NULL,
	`referrerClassNameId` bigint NULL,
	`parentSystemEventId` bigint NULL,
	`systemEventSetKey` bigint NULL,
	`type_` int NULL,
	`extraData` longtext NULL,
	PRIMARY KEY (`systemEventId`)
);


-- Team definition

CREATE TABLE `Team` (
	`mvccVersion` bigint DEFAULT 0 NOT NULL,
	`uuid_` varchar(75) NULL,
	`teamId` bigint NOT NULL,
	`companyId` bigint NULL,
	`userId` bigint NULL,
	`userName` varchar(75) NULL,
	`createDate` datetime NULL,
	`modifiedDate` datetime NULL,
	`groupId` bigint NULL,
	`name` varchar(75) NULL,
	`description` varchar(4000) NULL,
	`lastPublishDate` datetime NULL,
	PRIMARY KEY (`teamId`)
);


-- Ticket definition

CREATE TABLE `Ticket` (
	`mvccVersion` bigint DEFAULT 0 NOT NULL,
	`ticketId` bigint NOT NULL,
	`companyId` bigint NULL,
	`createDate` datetime NULL,
	`classNameId` bigint NULL,
	`classPK` bigint NULL,
	`key_` varchar(75) NULL,
	`type_` int NULL,
	`extraInfo` longtext NULL,
	`expirationDate` datetime NULL,
	PRIMARY KEY (`ticketId`)
);


-- UserRoleType definition

CREATE TABLE `UserRoleType` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`UserId` bigint NOT NULL,
	`RoleId` int NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
	`ModifiedBy` varchar(35) NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`Id`)
);


-- UsersDealers definition

CREATE TABLE `UsersDealers` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`UserId` bigint DEFAULT 0 NOT NULL,
	`DealerId` int DEFAULT NULL,
	`RoleId` int NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`IsDefault` tinyint DEFAULT 0,
	`Ordinal` int DEFAULT 0,
	`FullName` varchar(100) DEFAULT NULL,
	`PhotoFileId` bigint DEFAULT 0,
	`ADB2CId` varchar(100) DEFAULT NULL,
	`userPrincipalName` varchar(100) DEFAULT NULL,
	`ApprovedDate` datetime DEFAULT NULL,
	`UserEmail` varchar(255) DEFAULT NULL,
	`ScreenName` varchar(35) NULL,
	PRIMARY KEY (`Id`)
);


-- Wilayah definition

CREATE TABLE `Wilayah` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Name` varchar(35) NOT NULL,
	`SK` varchar(55) DEFAULT NULL,
	`Description` varchar(255) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- banner definition

CREATE TABLE `banner` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`IsShow` tinyint DEFAULT 0,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	PRIMARY KEY (`Id`)
);


-- bulan definition

CREATE TABLE `bulan` (
	`Id` varchar(25) NOT NULL,
	`Name` varchar(25) NOT NULL,
	`SortIndex` int NOT NULL,
	PRIMARY KEY (`Id`)
);


-- calender_event definition

CREATE TABLE `calender_event` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Judul` varchar(555) NULL,
	`JenisAcara` int NOT NULL,
	`StatusAcara` int NOT NULL,
	`Location` varchar(255) DEFAULT NULL,
	`LinkMeeting` varchar(255) DEFAULT NULL,
	`StartDate` datetime DEFAULT NULL,
	`StartDateHours` varchar(7) DEFAULT NULL,
	`EndDate` datetime DEFAULT NULL,
	`EndDateHours` varchar(7) DEFAULT NULL,
	`RegistrationLimitDate` datetime DEFAULT NULL,
	`RegistrationLimitDateHours` varchar(7) DEFAULT NULL,
	`Deskripsi` text NOT NULL,
	`ImageId` bigint DEFAULT 0,
	`ImageName` varchar(255) NOT NULL,
	`ImagePath` varchar(255) NOT NULL,
	`JenisAcaraName` varchar(255) NULL,
	`StatusAcaraName` varchar(255) NULL,
	`Lokasi` varchar(255) NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- calender_event_participant definition

CREATE TABLE `calender_event_participant` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`FullName` varchar(155) NOT NULL,
	`Email` varchar(255) NOT NULL,
	`Phone` varchar(255) NOT NULL,
	`CalenderEventId` int NOT NULL,
	`DealerId` int DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- common definition

CREATE TABLE `common` (
	`CommonKey` varchar(75) NOT NULL,
	`CommonCode` varchar(9) NOT NULL,
	`CommonDesc1` text DEFAULT NULL,
	`CommonDesc2` text DEFAULT NULL,
	`CommonDesc3` text DEFAULT NULL,
	`CommonDesc4` text DEFAULT NULL,
	`CommonDesc5` text DEFAULT NULL,
	`IsActive` tinyint DEFAULT 1,
	`Sequence` int DEFAULT NULL,
	`CreatedBy` varchar(250) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT NULL,
	`ModifiedBy` varchar(250) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT NULL,
	PRIMARY KEY (`CommonKey`)
);


-- diskon_fakpol definition

CREATE TABLE `diskon_fakpol` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`Tahun` int DEFAULT NULL,
	`Periode` varchar(25) DEFAULT NULL,
	`Keterangan` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	PRIMARY KEY (`Id`)
);


-- diskon_fleet definition

CREATE TABLE `diskon_fleet` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`Tahun` int DEFAULT NULL,
	`Kuartal` varchar(25) DEFAULT NULL,
	`Keterangan` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	PRIMARY KEY (`Id`)
);


-- diskon_others definition

CREATE TABLE `diskon_others` (
	`diskonOtherId` bigint NOT NULL AUTO_INCREMENT,
	`dealerId` int NOT NULL,
	`fileId` bigint DEFAULT 0,
	`fileName` varchar(155) NOT NULL,
	`filePath` varchar(175) DEFAULT NULL,
	`tahun` int DEFAULT NULL,
	`kuartalId` int DEFAULT NULL,
	`keterangan` varchar(175) DEFAULT NULL,
	`createdDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`createdBy` varchar(35) DEFAULT NULL,
	`modifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`modifiedBy` varchar(35) DEFAULT NULL,
	`rowStatus` bit DEFAULT 1 NOT NULL,
	PRIMARY KEY (`diskonOtherId`)
);


-- diskon_test_car definition

CREATE TABLE `diskon_test_car` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`Tahun` int DEFAULT NULL,
	`KuartalId` varchar(25) DEFAULT NULL,
	`TipeKendaraanId` varchar(25) DEFAULT NULL,
	`Keterangan` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	PRIMARY KEY (`Id`)
);


-- dwg_setting definition

CREATE TABLE `dwg_setting` (
	`Id` int NOT NULL,
	`Key` varchar(50) NULL,
	`Code` varchar(50) NULL,
	`Value` varchar(50) NULL,
	`RowStatus` bit NULL,
	`CreatedDate` datetime NULL,
	`CreatedBy` varchar(500) NULL,
	`ModifiedDate` datetime NULL,
	`ModifiedBy` varchar(500) NULL,
	PRIMARY KEY (`Id`)
);


-- e_srut definition

CREATE TABLE `e_srut` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`PeriodDate` datetime DEFAULT NULL,
	`Keterangan` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	PRIMARY KEY (`Id`)
);


-- email_domain definition

CREATE TABLE `email_domain` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DomainName` varchar(55) NOT NULL,
	`Category` varchar(55) NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	PRIMARY KEY (`Id`)
);


-- faktur_indirect definition

CREATE TABLE `faktur_indirect` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`InvoiceDate` date DEFAULT NULL,
	`Keterangan` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`UploadDate` datetime NULL,
	PRIMARY KEY (`Id`)
);


-- faktur_pajak definition

CREATE TABLE `faktur_pajak` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`Keterangan` varchar(175) DEFAULT NULL,
	`InvoiceDate` datetime NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`UploadDate` datetime DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- jenis_materi definition

CREATE TABLE `jenis_materi` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`CreatedDate` datetime NULL,
	`RowStatus` int NULL,
	`ModifiedDate` datetime NULL,
	`ModifiedBy` varchar(50) NULL,
	`CreatedBy` varchar(50) NULL,
	`ImageId` bigint DEFAULT 0,
	`ImageName` varchar(255) NULL,
	`ImagePath` varchar(255) NULL,
	`Name` varchar(255) NULL,
	PRIMARY KEY (`Id`)
);


-- kategori_dealer definition

CREATE TABLE `kategori_dealer` (
	`Id` bigint NOT NULL,
	`DealerId` int NOT NULL,
	`FileId` bigint NULL,
	`FileName` varchar(155) NOT NULL,
	`Judul` varchar(255) NULL,
	`PeriodeReview` varchar(35) NOT NULL,
	`Tahun` int NULL,
	`CreatedDate` datetime NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedDate` datetime NOT NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint NULL,
	PRIMARY KEY (`Id`)
);


-- kuartal definition

CREATE TABLE `kuartal` (
	`Id` varchar(25) NOT NULL,
	`Name` varchar(25) NOT NULL,
	PRIMARY KEY (`Id`)
);


-- master_approval definition

CREATE TABLE `master_approval` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`RoleId` int NOT NULL,
	`MenuId` int NOT NULL,
	`ApprovalGroup` varchar(100) NOT NULL,
	`ApprovalWorkflow` tinyint NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- master_approval_detail definition

CREATE TABLE `master_approval_detail` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`MasterApprovalId` int NOT NULL,
	`UserId` bigint NOT NULL,
	`ApprovalLevel` int NOT NULL,
	`IsDefault` tinyint DEFAULT 0 NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- master_approval_detail_journal definition

CREATE TABLE `master_approval_detail_journal` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`MasterApprovalDetailId` int NOT NULL,
	`MasterApprovalId` int NOT NULL,
	`UserId` bigint NOT NULL,
	`ApprovalLevel` int NOT NULL,
	`IsDefault` tinyint DEFAULT 0 NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`MasterApprovalJournalId` int NOT NULL,
	PRIMARY KEY (`Id`)
);


-- master_approval_journal definition

CREATE TABLE `master_approval_journal` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`MasterApprovalId` int NOT NULL,
	`RoleId` int NOT NULL,
	`MenuId` int NOT NULL,
	`ApprovalGroup` varchar(100) NOT NULL,
	`ApprovalWorkflow` tinyint NOT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- menuAuthorization definition

CREATE TABLE `menuAuthorization` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`RoleId` int NOT NULL,
	`Menus` varchar(500) NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`IsRssp` tinyint DEFAULT 0 NOT NULL,
	`IsCmsDso` bit DEFAULT 0 NOT NULL,
	PRIMARY KEY (`Id`)
);


-- otp definition

CREATE TABLE `otp` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`OTPNumber` int DEFAULT NULL,
	`EmailNewUser` varchar(256) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT NULL,
	`ExpiredDate` datetime DEFAULT NULL,
	`IsVerified` bit DEFAULT 0,
	PRIMARY KEY (`Id`)
);


-- periode_review definition

CREATE TABLE `periode_review` (
	`Id` varchar(25) NOT NULL,
	`Name` varchar(25) NOT NULL,
	PRIMARY KEY (`Id`)
);


-- report_plafond definition

CREATE TABLE `report_plafond` (
	`Id` bigint NOT NULL AUTO_INCREMENT,
	`FileId` bigint NULL,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) NULL,
	`Periode` date NULL,
	`Keterangan` varchar(175) NULL,
	`CreatedDate` datetime NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedDate` datetime NOT NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint NOT NULL,
	`DealerId` int NULL,
	PRIMARY KEY (`Id`)
);


-- roles definition

CREATE TABLE `roles` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Name` varchar(50) NOT NULL,
	`Description` varchar(255) DEFAULT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`RoleIdMember` varchar(255) NULL,
	PRIMARY KEY (`Id`)
);


-- sales_program definition

CREATE TABLE `sales_program` (
	`Id` bigint NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`rowStatus` tinyint DEFAULT NULL,
	`Tahun` int DEFAULT 0,
	`Periode` varchar(75) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- sales_report definition

CREATE TABLE `sales_report` (
	`Id` bigint NOT NULL AUTO_INCREMENT,
	`FileId` bigint NULL,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) NULL,
	`Periode` date NULL,
	`Keterangan` varchar(175) NULL,
	`CreatedDate` datetime NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedDate` datetime NOT NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint NOT NULL,
	`DealerId` int NULL,
	PRIMARY KEY (`Id`)
);


-- setting definition

CREATE TABLE `setting` (
	`Id` int NOT NULL,
	`KeySetting` varchar(50) DEFAULT NULL,
	`Code` varchar(50) DEFAULT NULL,
	`Value` varchar(700) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	PRIMARY KEY (`Id`)
);


-- sk_iris definition

CREATE TABLE `sk_iris` (
	`Id` bigint NOT NULL AUTO_INCREMENT,
	`FileId` bigint NULL,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) NULL,
	`Tahun` int NULL,
	`Periode` varchar(75) NULL,
	`WilayahId` varchar(20) NULL,
	`DealerId` int NOT NULL,
	`CreatedDate` datetime NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedDate` datetime NOT NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint NOT NULL,
	`Kategori` varchar(50) NULL,
	PRIMARY KEY (`Id`)
);


-- sp3d definition

CREATE TABLE `sp3d` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`FileId` bigint NULL,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) NULL,
	`Tahun` int NULL,
	`CreatedDate` datetime NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedDate` datetime NOT NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint NOT NULL,
	`DealerId` bigint NOT NULL,
	`Bulan` varchar(50) NULL,
	PRIMARY KEY (`Id`)
);


-- surat_penalty_stock definition

CREATE TABLE `surat_penalty_stock` (
	`Id` bigint NOT NULL,
	`DealerId` int NOT NULL,
	`FileId` bigint NULL,
	`FileName` varchar(155) NOT NULL,
	`Judul` varchar(255) NULL,
	`Periode` varchar(35) NULL,
	`Tahun` int NULL,
	`CreatedDate` datetime NOT NULL,
	`CreatedBy` varchar(35) NULL,
	`ModifiedDate` datetime NOT NULL,
	`ModifiedBy` varchar(35) NULL,
	`RowStatus` tinyint NULL,
	PRIMARY KEY (`Id`)
);


-- tahun definition

CREATE TABLE `tahun` (
	`Id` int NOT NULL,
	`Name` int NOT NULL,
	PRIMARY KEY (`Id`)
);


-- tipe_kendaraan definition

CREATE TABLE `tipe_kendaraan` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Name` varchar(35) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	PRIMARY KEY (`Id`)
);


-- token definition

CREATE TABLE `token` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`TokenNumber` varchar(256) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT NULL,
	`ExpiredDate` datetime DEFAULT NULL,
	PRIMARY KEY (`Id`)
);


-- topik_materi definition

CREATE TABLE `topik_materi` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`RowStatus` tinyint DEFAULT 1,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`Name` varchar(155) NULL,
	PRIMARY KEY (`Id`)
);


-- training_agenda definition

CREATE TABLE `training_agenda` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`Judul` varchar(555) NOT NULL,
	`JenisAgenda` int NOT NULL,
	`StatusAgenda` int NOT NULL,
	`Location` varchar(255) DEFAULT NULL,
	`LinkMeeting` varchar(255) DEFAULT NULL,
	`StartDate` date NOT NULL,
	`StartDateHours` varchar(7) NOT NULL,
	`EndDate` date NOT NULL,
	`EndDateHours` varchar(7) NOT NULL,
	`RegistrationLimitDate` date NOT NULL,
	`RegistrationLimitDateHours` varchar(7) NOT NULL,
	`Deskripsi` text NOT NULL,
	`ImageId` bigint DEFAULT 0,
	`ImageName` varchar(255) NOT NULL,
	`ImagePath` varchar(255) NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	PRIMARY KEY (`Id`)
);


-- training_agenda_participant definition

CREATE TABLE `training_agenda_participant` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`FullName` varchar(155) NOT NULL,
	`Email` varchar(255) NOT NULL,
	`Phone` varchar(255) NOT NULL,
	`TrainingAgendaId` int NOT NULL,
	`DealerId` int NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	PRIMARY KEY (`Id`)
);


-- training_agenda_participant_uf definition

CREATE TABLE `training_agenda_participant_uf` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`TrainingAgendaId` int NOT NULL,
	`DealerId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(255) NOT NULL,
	`FilePath` varchar(255) NOT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	PRIMARY KEY (`Id`)
);


-- training_materi definition

CREATE TABLE `training_materi` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`JenisMateriId` int NOT NULL,
	`JudulMateri` varchar(155) NOT NULL,
	`ImageId` bigint DEFAULT 0,
	`ImagePath` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1,
	`TopikMateriId` int NULL,
	`ImageName` varchar(255) NULL,
	PRIMARY KEY (`Id`)
);


-- training_materi_lampiran definition

CREATE TABLE `training_materi_lampiran` (
	`Id` int NOT NULL AUTO_INCREMENT,
	`TrainingMateriId` int NOT NULL,
	`FileId` bigint DEFAULT 0,
	`FileName` varchar(155) NOT NULL,
	`FilePath` varchar(175) DEFAULT NULL,
	`CreatedDate` datetime DEFAULT CURRENT_TIMESTAMP NOT NULL,
	`CreatedBy` varchar(35) DEFAULT NULL,
	`ModifiedDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
	`ModifiedBy` varchar(35) DEFAULT NULL,
	`RowStatus` tinyint DEFAULT 1 NOT NULL,
	PRIMARY KEY (`Id`)
);
