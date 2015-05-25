DROP DATABASE IF EXISTS taxistGela;
CREATE DATABASE taxistGela
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
USE taxistGela;

CREATE TABLE Cars (
  carID         VARCHAR(8) PRIMARY KEY,
  carDescrption TEXT,
  carYear       TINYINT(4) not null,
  conditioning  BOOLEAN not null,
  numPassengers TINYINT(2) not null
);
CREATE TABLE Companies (
  companyID   INT AUTO_INCREMENT PRIMARY KEY,
  companyCode VARCHAR(9) UNIQUE not null,
  username    VARCHAR(50) UNIQUE not null,
  email       VARCHAR(50) UNIQUE not null,
  password    TEXT not null,
  companyName VARCHAR(50) UNIQUE not null,
  phoneNumber VARCHAR(13) UNIQUE not null,
  facebookID  TEXT, -- TODO trigger davadot uniquesi
  googleID    TEXT -- TODO trigger davadot uniquesi
);

CREATE TABLE DriverPreferenes (
  ID        INT AUTO_INCREMENT PRIMARY KEY,
  rating    DOUBLE not null,
  timeLimit INT not null
);
CREATE TABLE Drivers (
  driverID    INT AUTO_INCREMENT PRIMARY KEY,
  personalID  VARCHAR(11) UNIQUE not null,
  username    VARCHAR(50) UNIQUE not null,
  password    TEXT not null,
  email       VARCHAR(50) UNIQUE not null,
  companyID   INT,
  firstName   TEXT not null,
  lastName    TEXT not null,
  phoneNumber VARCHAR(13) UNIQUE not null,
  carID       VARCHAR(8) not null,
  facebookID  TEXT, -- TODO trigger davadot uniquesi
  googleID    TEXT, -- TODO trigger davadot uniquesi
  rating      DOUBLE not null,
  preferencesID INT not null,
  latitude    DECIMAL not null,
  longitude   DECIMAL not null,
  isActive    BOOLEAN not null,
  FOREIGN KEY (carID) REFERENCES Cars (carID),
  FOREIGN KEY (preferencesID) REFERENCES DriverPreferenes (ID),
  FOREIGN KEY (companyID) REFERENCES Companies (companyID)
);
CREATE TABLE UserPreferences (
  ID              INT AUTO_INCREMENT PRIMARY KEY,
  rating          DOUBLE not null,
  conditioning    BOOLEAN not null,
  carYear         TINYINT(4) not null,
  passengersCount TINYINT(2) not null,
  wantsAlone      BOOLEAN not null,
  timeLimit       INT not null
);
CREATE TABLE Users (
  userID      INT AUTO_INCREMENT PRIMARY KEY,
  username    VARCHAR(50) UNIQUE not null,
  password    TEXT not null,
  email       VARCHAR(50) UNIQUE not null,
  firstName   TEXT not null,
  lastName    TEXT not null,
  phoneNumber VARCHAR(13) UNIQUE not null,
  gender      ENUM("MALE", "FEMALE") not null,
  facebookID  TEXT,
  googleID    TEXT,
  preferencesID   INT not null,
  FOREIGN KEY (preferencesID) REFERENCES UserPreferences (ID)
);
CREATE TABLE Orders (
  orderID            INT AUTO_INCREMENT PRIMARY KEY,
  userID             INT not null, /*foreign key*/
  driverID           INT not null, /*foreign key*/
  numPassengers      INT not null,
  startLocation_long DECIMAL not null,
  startLocation_lat  DECIMAL not null,
  endLocation_long   DECIMAL not null,
  endLocation_lat    DECIMAL not null,
  startTime          DATETIME,
  endTime            DATETIME,
  paymentAmount      DECIMAL not null,
  callTime           DATETIME not null,
  FOREIGN KEY (userID) REFERENCES Users (userID)
);
CREATE TABLE Reviews (
  reviewID        INT AUTO_INCREMENT PRIMARY KEY,
  userID          INT not null,
  driverID        INT not null,
  orientationFlag BOOLEAN not null,
  timeRating      DOUBLE not null,
  comfortRating   DOUBLE not null,
  description     TEXT not null,
  FOREIGN KEY (userID) REFERENCES Users (userID),
  FOREIGN KEY (driverID) REFERENCES Drivers (driverID)
);