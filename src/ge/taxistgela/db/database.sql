DROP DATABASE IF EXISTS taxistGela;
CREATE DATABASE taxistGela
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
USE taxistGela;

CREATE TABLE Cars (
  carID         VARCHAR(8) PRIMARY KEY,
  carDescription TEXT,
  carYear       INT NOT NULL,
  conditioning  BOOLEAN    NOT NULL,
  numPassengers INT NOT NULL
);

CREATE TABLE Companies (
  companyID   INT AUTO_INCREMENT PRIMARY KEY,
  companyCode VARCHAR(9) UNIQUE  NOT NULL,
  email       VARCHAR(50) UNIQUE NOT NULL,
  password    TEXT               NOT NULL,
  companyName VARCHAR(50) NOT NULL,
  phoneNumber VARCHAR(13) UNIQUE NOT NULL,
  facebookID  VARCHAR(255) UNIQUE,
  googleID   VARCHAR(255) UNIQUE,
  isVerified BOOLEAN NOT NULL
);

CREATE TABLE DriverPreferences(
  driverPreferenceID        INT AUTO_INCREMENT PRIMARY KEY,
  minimumUserRating    DOUBLE NOT NULL,
  coefficientPer       DOUBLE NOT NULL
);

CREATE TABLE Drivers (
  driverID      INT AUTO_INCREMENT PRIMARY KEY,
  personalID    VARCHAR(11) UNIQUE NOT NULL,
  password      TEXT               NOT NULL,
  email         VARCHAR(50) UNIQUE NOT NULL,
  companyID     INT,
  firstName     VARCHAR(20)               NOT NULL,
  lastName      VARCHAR(20)               NOT NULL,
  gender        ENUM('MALE', 'FEMALE') NOT NULL,
  phoneNumber   VARCHAR(13) UNIQUE NOT NULL,
  carID         VARCHAR(8)  UNIQUE     NOT NULL,
  facebookID  VARCHAR(255) UNIQUE,
  googleID   VARCHAR(255) UNIQUE,
  rating        DOUBLE             NOT NULL,
  driverPreferenceID INT                NOT NULL,
  latitude      DECIMAL            NOT NULL,
  longitude     DECIMAL            NOT NULL,
  isActive      BOOLEAN            NOT NULL,
  isVerified BOOLEAN NOT NULL,
  FOREIGN KEY (carID) REFERENCES Cars (carID),
  FOREIGN KEY (driverPreferenceID) REFERENCES DriverPreferences (driverPreferenceID),
  FOREIGN KEY (companyID) REFERENCES Companies (companyID)
);

CREATE TABLE UserPreferences (
  userPreferenceID              INT AUTO_INCREMENT PRIMARY KEY,
  minimumDriverRating          DOUBLE     NOT NULL,
  conditioning    BOOLEAN    NOT NULL,
  carYear         INT NOT NULL,
  passengersCount INT NOT NULL,
  wantsAlone      BOOLEAN    NOT NULL,
  timeLimit       INT        NOT NULL
);

CREATE TABLE Users (
  userID        INT AUTO_INCREMENT PRIMARY KEY,
  password      TEXT                   NOT NULL,
  email         VARCHAR(50) UNIQUE     NOT NULL,
  firstName     VARCHAR(20)                   NOT NULL,
  lastName      VARCHAR(20)                   NOT NULL,
  phoneNumber   VARCHAR(13) UNIQUE     NOT NULL,
  gender        ENUM('MALE', 'FEMALE') NOT NULL,
  rating DOUBLE NOT NULL ,
  facebookID  VARCHAR(255) UNIQUE,
  googleID   VARCHAR(255) UNIQUE,
  userPreferenceID INT                    NOT NULL,
  isVerified BOOLEAN NOT NULL,
  FOREIGN KEY (userPreferenceID) REFERENCES UserPreferences (userPreferenceID)
);

CREATE TABLE Orders (
  orderID            INT AUTO_INCREMENT PRIMARY KEY,
  userID             INT      NOT NULL, /*foreign key*/
  driverID           INT      NOT NULL, /*foreign key*/
  numPassengers      INT      NOT NULL,
  startLocation_long DECIMAL  NOT NULL,
  startLocation_lat  DECIMAL  NOT NULL,
  endLocation_long   DECIMAL  NOT NULL,
  endLocation_lat    DECIMAL  NOT NULL,
  startTime          DATETIME,
  endTime            DATETIME,
  paymentAmount      DECIMAL  NOT NULL,
  callTime           DATETIME NOT NULL,
  FOREIGN KEY (userID) REFERENCES Users (userID),
  FOREIGN KEY (driverID) REFERENCES Drivers (driverID)
);

CREATE TABLE Reviews (
  reviewID        INT AUTO_INCREMENT PRIMARY KEY,
  orderID          INT     NOT NULL,
  orientationFlag BOOLEAN NOT NULL,
  rating      DOUBLE  NOT NULL,
  description     TEXT    NOT NULL,
  FOREIGN KEY (orderID) REFERENCES Orders (orderID)
);
