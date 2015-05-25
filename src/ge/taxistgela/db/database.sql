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
  companyCode VARCHAR(9) UNIQUE,
  username    VARCHAR(50) UNIQUE,
  email       VARCHAR(50) UNIQUE,
  password    TEXT,
  companyName VARCHAR(50) UNIQUE,
  phoneNumber VARCHAR(13) UNIQUE,
  facebookID  TEXT, -- TODO trigger davadot uniquesi
  googleID    TEXT -- TODO trigger davadot uniquesi
);

CREATE TABLE DriverPreferenes (
  ID        INT AUTO_INCREMENT PRIMARY KEY,
  rating    DOUBLE,
  timeLimit INT
);
CREATE TABLE Drivers (
  driverID    INT AUTO_INCREMENT PRIMARY KEY,
  personalID  VARCHAR(11) UNIQUE,
  username    VARCHAR(50) UNIQUE,
  password    TEXT,
  email       VARCHAR(50) UNIQUE,
  companyID   INT,
  firstName   TEXT,
  lastName    TEXT,
  phoneNumber VARCHAR(13) UNIQUE,
  carID       VARCHAR(8),
  facebookID  TEXT, -- TODO trigger davadot uniquesi
  googleID    TEXT, -- TODO trigger davadot uniquesi
  rating      DOUBLE,
  preferencesID INT,
  latitude    DECIMAL,
  longitude   DECIMAL,
  isActive    BOOLEAN,
  FOREIGN KEY (carID) REFERENCES Cars (carID),
  FOREIGN KEY (preferencesID) REFERENCES DriverPreferenes (ID),
  FOREIGN KEY (companyID) REFERENCES Companies (companyID)
);
CREATE TABLE UserPreferences (
  ID              INT AUTO_INCREMENT PRIMARY KEY,
  rating          DOUBLE,
  conditioning    BOOLEAN,
  carYear         TINYINT(4),
  passengersCount TINYINT(2),
  wantsAlone      BOOLEAN,
  timeLimit       INT
);
CREATE TABLE Users (
  userID      INT AUTO_INCREMENT PRIMARY KEY,
  username    VARCHAR(50) UNIQUE,
  password    TEXT,
  email       VARCHAR(50) UNIQUE,
  firstName   TEXT,
  lastName    TEXT,
  phoneNumber VARCHAR(13) UNIQUE,
  gender      ENUM("MALE", "FEMALE"),
  facebookID  TEXT,
  googleID    TEXT,
  prefences   INT,
  FOREIGN KEY (prefences) REFERENCES UserPreferences (ID)
);
CREATE TABLE Orders (
  orderID            INT AUTO_INCREMENT PRIMARY KEY,
  userID             INT, /*foreign key*/
  driverID           INT, /*foreign key*/
  numPassengers      INT,
  startLocation_long DECIMAL,
  startLocation_lat  DECIMAL,
  endLocation_long   DECIMAL,
  endLocation_lat    DECIMAL,
  startTime          DATETIME,
  endTime            DATETIME,
  paymentAmount      DECIMAL,
  callTime           DATETIME,
  FOREIGN KEY (userID) REFERENCES Users (userID)
);
CREATE TABLE Reviews (
  reviewID        INT AUTO_INCREMENT PRIMARY KEY,
  userID          INT,
  driverID        INT,
  orientationFlag BOOLEAN,
  timeRating      DOUBLE,
  comfortRating   DOUBLE,
  description     TEXT,
  FOREIGN KEY (userID) REFERENCES Users (userID),
  FOREIGN KEY (driverID) REFERENCES Drivers (driverID)
);