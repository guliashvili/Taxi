drop database if exists taxistGela;
create database taxistGela
	default character set utf8
	default collate utf8_general_ci;
use taxistGela;

create table Cars(
	carID varchar(8) primary key,
	carDescrption text,
	carYear TINYINT(4),
	conditioning boolean,
	numPassengers tinyint(2)
);
create table Companies(
	companyID int auto_increment primary key,
	companyCode varchar(9) unique,
	username varchar(50) unique,
	email varchar(50) unique,
	password text,
	companyName varchar(50) unique,
	phoneNumber varchar(13) unique,
	facebookID text,
	googleID text
);
create table DriverPreferenes(
	ID int auto_increment primary key,
	rating double,
	timeLimit int
);
create table Drivers(
	driverID int auto_increment primary key,
	presonalID varchar(11) unique,
	username varchar(50) unique,
	password text,
	email varchar(50) unique,
	companyID int,
	firstName text,
	lastName text,
	phoneNumber varchar(13) unique,
	carID varchar(8),
	facebookID text,
	googleID text,
	rating double,
	preferences int,
	latitude decimal,
	longitude decimal,
	isActive boolean,
	foreign key(carID) references Cars(carID),
	foreign key(preferences) references DriverPreferenes(ID),
	foreign key(companyID) references Companies(companyID)
);
create table UserPreferences(
	ID int auto_increment primary key,
	rating double,
	conditioning boolean,
	carYear tinyint(4),
	passengersCount tinyint(2),
	wantsAlone boolean,
	timeLimit int
);
create table Users(
	userID int auto_increment primary key,
	username varchar(50) unique,
	password text,
	email varchar(50) unique,
	firstName text,
	lastName text,
	phoneNumber varchar(13) unique,
	gender enum("MALE","FEMALE"),
	facebookID text,
	googleID text,
	prefences int,
	foreign key(prefences) references UserPreferences(ID)
);
create table Orders(
	orderID int auto_increment primary key,
	userID int,/*foreign key*/
	driverID int,/*foreign key*/
	numPassengers int,
	startLocation_long decimal,
	startLocation_lat decimal,
	endLocation_long decimal,
	endLocation_lat decimal,
	startTime datetime,
	endTime datetime,
	paymentAmount decimal,
	callTime datetime,
	foreign key(userID) references Users(userID)
);
create table Reviews(
	reviewID int auto_increment primary key,
	userID int,
	driverID int,
	orientationFlag boolean,
	timeRating double,
	comfortRating double,
	description text,
	foreign key(userID) references Users(userID),
	foreign key(driverID) references Drivers(driverID)
);