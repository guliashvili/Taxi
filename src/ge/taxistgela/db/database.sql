DROP DATABASE IF EXISTS taxistGela;
CREATE DATABASE taxistGela
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
USE taxistGela;

CREATE TABLE Cars (
  carID         VARCHAR(8) PRIMARY KEY,
  carDescrption TEXT,
  carYear       TINYINT(4) NOT NULL,
  conditioning  BOOLEAN    NOT NULL,
  numPassengers TINYINT(2) NOT NULL
);

CREATE TABLE Companies (
  companyID   INT AUTO_INCREMENT PRIMARY KEY,
  companyCode VARCHAR(9) UNIQUE  NOT NULL,
  email       VARCHAR(50) UNIQUE NOT NULL,
  password    TEXT               NOT NULL,
  companyName VARCHAR(50) UNIQUE NOT NULL,
  phoneNumber VARCHAR(13) UNIQUE NOT NULL,
  facebookID  TEXT, -- TODO trigger davadot uniquesi
  googleID    TEXT -- TODO trigger davadot uniquesi
);

CREATE TABLE DriverPreferences(
  DriverPreferenceID        INT AUTO_INCREMENT PRIMARY KEY,
  minimumUserRating    DOUBLE NOT NULL,
  coefficientPer       DOUBLE NOT NULL
);

CREATE TABLE Drivers (
  driverID      INT AUTO_INCREMENT PRIMARY KEY,
  personalID    VARCHAR(11) UNIQUE NOT NULL,
  password      TEXT               NOT NULL,
  email         VARCHAR(50) UNIQUE NOT NULL,
  companyID     INT,
  firstName     TEXT               NOT NULL,
  lastName      TEXT               NOT NULL,
  gender        ENUM('MALE', 'FEMALE') NOT NULL,
  phoneNumber   VARCHAR(13) UNIQUE NOT NULL,
  carID         VARCHAR(8)         NOT NULL,
  facebookID    TEXT, -- TODO trigger davadot uniquesi
  googleID      TEXT, -- TODO trigger davadot uniquesi
  rating        DOUBLE             NOT NULL,
  DriverPreferenceID INT                NOT NULL,
  latitude      DECIMAL            NOT NULL,
  longitude     DECIMAL            NOT NULL,
  isActive      BOOLEAN            NOT NULL,
  FOREIGN KEY (carID) REFERENCES Cars (carID),
  FOREIGN KEY (DriverPreferenceID) REFERENCES DriverPreferences (DriverPreferenceID),
  FOREIGN KEY (companyID) REFERENCES Companies (companyID)
);

CREATE TABLE UserPreferences (
  ID              INT AUTO_INCREMENT PRIMARY KEY,
  rating          DOUBLE     NOT NULL,
  conditioning    BOOLEAN    NOT NULL,
  carYear         TINYINT(4) NOT NULL,
  passengersCount TINYINT(2) NOT NULL,
  wantsAlone      BOOLEAN    NOT NULL,
  timeLimit       INT        NOT NULL
);

CREATE TABLE Users (
  userID        INT AUTO_INCREMENT PRIMARY KEY,
  password      TEXT                   NOT NULL,
  email         VARCHAR(50) UNIQUE     NOT NULL,
  firstName     TEXT                   NOT NULL,
  lastName      TEXT                   NOT NULL,
  phoneNumber   VARCHAR(13) UNIQUE     NOT NULL,
  gender        ENUM('MALE', 'FEMALE') NOT NULL,
  facebookID    TEXT,
  googleID      TEXT,
  preferenceID INT                    NOT NULL,
  FOREIGN KEY (preferenceID) REFERENCES UserPreferences (ID)
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
