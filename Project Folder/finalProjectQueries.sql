CREATE SCHEMA FinalProject;

USE FinalProject;

CREATE TABLE DepartmentTable
(
DepID INT AUTO_INCREMENT,
Preference VARCHAR(10),
Synchronizable VARCHAR(10),
Changeable VARCHAR(10),
HourDelta INT,
PRIMARY KEY(DepID)
)
ENGINE = InnoDB;

CREATE TABLE RoleTable
(
RoleID INT AUTO_INCREMENT,
Preference VARCHAR(10),
Synchronizable VARCHAR(10),
Changeable VARCHAR(10),
HourDelta INT,
DepID INT,
PRIMARY KEY(RoleID),
FOREIGN KEY (DepID) REFERENCES DepartmentTable(DepID)
ON UPDATE CASCADE
ON DELETE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE EmployeeTable
(
EmpID INT AUTO_INCREMENT,
Preference VARCHAR(10),
HourDelta INT,
RoleID INT,
empType VARCHAR(255),
PRIMARY KEY(EmpID),
FOREIGN KEY (RoleID) REFERENCES RoleTable(RoleID)
ON UPDATE CASCADE
ON DELETE CASCADE
)
ENGINE = InnoDB;


CREATE TABLE BaseSalesEmployeeTable
(
EmpID INT AUTO_INCREMENT,
Salary INT,
SalesPercentage FLOAT,
PRIMARY KEY(EmpID),
FOREIGN KEY (EmpID) REFERENCES EmployeeTable(EmpID)
ON UPDATE CASCADE
ON DELETE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE BaseEmployeeTable
(
EmpID INT AUTO_INCREMENT,
Salary INT,
PRIMARY KEY(EmpID),
FOREIGN KEY (EmpID) REFERENCES EmployeeTable(EmpID)
ON UPDATE CASCADE
ON DELETE CASCADE
)
ENGINE = InnoDB;

CREATE TABLE HourlyEmployeeTable
(
EmpID INT AUTO_INCREMENT,
Wage INT,
PRIMARY KEY(EmpID),
FOREIGN KEY (EmpID) REFERENCES EmployeeTable(EmpID)
ON UPDATE CASCADE
ON DELETE CASCADE
)
ENGINE = InnoDB;






SELECT * FROM departmenttable;
SELECT * FROM roletable;
SELECT * FROM employeetable;
SELECT * FROM hourlyemployeetable;
SELECT * FROM baseemployeetable;
SELECT * FROM basesalesemployeetable;


insert into departmenttable values
(1 , 'HOME' , 'false' , 'true' , 0),
(2 , 'EARLIER' , 'true' , 'false' , 2),
(3 , 'LATER' , 'true' , 'true' , 3),
(4 , 'SAME' , 'false' , 'false' , 0);


insert into roletable values
(1 , 'SAME' , 'false' , 'true' , 0 ,1),
(2 , 'HOME' , 'false' , 'false' , 0 ,1),
(3 , 'EARLIER' , 'true' , 'true' , 2 ,2),
(4 , 'LATER' , 'true' , 'false' , 3 ,2),
(5 , 'LATER' , 'false' , 'true' , 3 ,3);



insert into employeetable values
(1 , 'LATER' , 2 , 1 , 'Sales'),
(2 , 'EARLIER' , 2 , 1 , 'Base'),
(3 , 'HOME' , 0 , 1 , 'Base'),
(4 , 'HOME' , 0 , 2 , 'Hourly'),
(5 , 'SAME' , 0 , 2 , 'Sales'),
(6 , 'EARLIER' , 5 , 3 , 'Hourly'),
(7 , 'LATER' , 4 , 4 , 'Base'),
(8 , 'SAME' , 0 , 5 , 'Sales'),
(9 , 'LATER' , 2 , 5 , 'Hourly');


insert into employeetable values
(10 , 'LATER' , 2 , 5 , 'Sales');


insert into baseemployeetable values
(2,7000),
(3,5500),
(7,10000);


insert into basesalesemployeetable values
(1,6000,12.3),
(5,12000,33.3),
(8,9999,0.2);

insert into hourlyemployeetable values
(4,100),
(6,70),
(9,95);


