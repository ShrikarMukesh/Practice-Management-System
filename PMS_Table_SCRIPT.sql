create schema PMS;
use PMS;
CREATE TABLE `visit` (
  `visitid` bigint NOT NULL AUTO_INCREMENT,
  `visited_at` date DEFAULT NULL,
  `vitalsign_id` bigint DEFAULT NULL,
  PRIMARY KEY (`visitid`)
 
);
CREATE TABLE `vital_signs` (
  `vitalsignid` bigint NOT NULL AUTO_INCREMENT,
  `height` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `bp_systolic` float DEFAULT NULL,
  `bp_diastolic` float DEFAULT NULL,
  `respiration_rate` float DEFAULT NULL,
  PRIMARY KEY (`vitalsignid`)
  
);
------->
INSERT INTO `patint-visit`.`medication` (`appl_no`, `active_ingredient`, `drug_name`, `form`, `product_no`, `reference_drug`, `reference_standard`, `strength`) VALUES ('000159', 'SULFAPYRIDINE', 'SULFAPYRIDINE', 'TABLET;ORAL', '001', '0', '0', '500MG');
INSERT INTO `patint-visit`.`medication` (`appl_no`, `active_ingredient`, `drug_name`, `form`, `product_no`, `reference_drug`, `reference_standard`, `strength`) VALUES ('000552', 'HEPARIN SODIUM', 'LIQUAEMIN SODIUM', 'INJECTABLE;INJECTION', '001', '0', '0', '20,000 UNITS/ML');
------->
CREATE TABLE `medication` (
  `appl_no` varchar(20) NOT NULL,
  `product_no` varchar(20) DEFAULT NULL,
  `form` varchar(20) DEFAULT NULL,
  `strength` varchar(20) DEFAULT NULL,
  `reference_drug` varchar(20) DEFAULT NULL,
  `drug_name` varchar(20) DEFAULT NULL,
  `active_ingredient` varchar(20) DEFAULT NULL,
  `reference_standard` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`appl_no`)
);
---->
INSERT INTO `pms1`.`procedures` (`procedure_code`, `approach_type`, `procedure_description`, `procedure_is_depricated`) VALUES ('001607A', 'Open Approach', 'Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute', b'1');
INSERT INTO `pms1`.`procedures` (`procedure_code`, `approach_type`, `procedure_description`, `procedure_is_depricated`) VALUES ('001607B', 'New Approach', 'Bypass Cerebral Ventricle to Subgaleal Space with Autologous Tissue Substitute', b'1');
----->

create table procedures(
    procedure_code varchar(25),
	procedure_description varchar(200),
    procedure_is_depricated Boolean,
    approach_type varchar(45),
	PRIMARY KEY (procedure_code)
);
------>
	INSERT INTO `pms1`.`diagnosis` (`diagnosis_code`, `diagnosis_description`, `diagnosis_is_depricated`) VALUES ('A00', 'Cholera', b'1');
	INSERT INTO `pms1`.`diagnosis` (`diagnosis_code`, `diagnosis_description`, `diagnosis_is_depricated`) VALUES ('A00.1', 'Cholera due to Vibrio cholerae 01', b'1');
------>

create table `diagnosis`(
    diagnosis_code varchar(25),
	diagnosis_description varchar(200),
    diagnosis_is_depricated Boolean,
	PRIMARY KEY (diagnosis_code)
);

CREATE TABLE `allergy` (
  `allergy_id` varchar(30) NOT NULL,
  `allergy_type` varchar(30) ,
  `allergy_name` varchar(30) ,
  `allergen_source` varchar(30) ,
  `isoforms` varchar(50) ,
  `allergini_city` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`allergy_id`)
);


--------manyTomany mapping---------https://grokonez.com/sql/sql-tutorial-mysql-many-to-many-relationship
CREATE TABLE visit_medication (
	visitid bigint NOT NULL,
	appl_no varchar(20) NOT NULL,
	FOREIGN KEY (visitid) REFERENCES visit (visitid) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (appl_no) REFERENCES medication (appl_no) ON DELETE RESTRICT ON UPDATE CASCADE,
	PRIMARY KEY (visitid, appl_no)
);

-------------------------------------------------------------
CREATE TABLE `patients` (
  `patientid` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `race` varchar(50) DEFAULT NULL,
  `ethnicity` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `sameasabove` tinyint(1) DEFAULT NULL,
  `contactnumber` bigint DEFAULT NULL,
  `emergencycontact_id` bigint DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `language_id` bigint DEFAULT NULL,
  PRIMARY KEY (`patientid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `emergencycontact` (
  `emergencycontactid` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `relationship` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contactnumber` bigint DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  PRIMARY KEY (`emergencycontactid`),
  KEY `fk_addressid` (`address_id`),
  CONSTRAINT `fk_addressid` FOREIGN KEY (`address_id`) REFERENCES `address` (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `address` (
  `addressid` bigint NOT NULL AUTO_INCREMENT,
  `street` varchar(50) DEFAULT NULL,
  `addressline` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `district` varchar(20) DEFAULT NULL,
  `state` varchar(25) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `pincode` varchar(15) DEFAULT NULL,
  `emergencycontact_id` bigint DEFAULT NULL,
  PRIMARY KEY (`addressid`),
  KEY `fk_emergencycontactid` (`emergencycontact_id`),
  CONSTRAINT `fk_emergencycontactid` FOREIGN KEY (`emergencycontact_id`) REFERENCES `emergencycontact` (`emergencycontactid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

=====================================================================================
appointment
id 
creator varchar(40),
tiitle varchar(10),
starttime DateAndTime,
Endtime DateAndTime,
Desciption varchar(500),
patient_id 
---CRUD---5---methods
Controller
Apponitment Service

attendies
name
email

=====================================================================================
/*
ALTER TABLE address
ADD CONSTRAINT `fk_emergencycontactid` FOREIGN KEY (`emergencycontact_id`) REFERENCES `emergencycontact` (`emergencycontactid`);

*/

/*
ALTER TABLE emergencycontact
ADD CONSTRAINT `fk_addressid` FOREIGN KEY (`address_id`) REFERENCES `address` (`addressid`);

*/

/*KEY `VSfk_visitdetails_id` (`visitdetails_id`),
  CONSTRAINT `VSfk_visitdetails_id` FOREIGN KEY (`visitdetails_id`) REFERENCES `visit_details` (`visitdetailsid`)*/
  /* KEY `VSfk_vitalsign_id` (`vitalsign_id`),
  CONSTRAINT `VSfk_vitalsign_id` FOREIGN KEY (`vitalsign_id`) REFERENCES `vital_signs` (`vitalsignid`)*/