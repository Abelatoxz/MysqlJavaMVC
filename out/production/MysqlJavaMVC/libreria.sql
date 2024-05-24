CREATE DATABASE Libreria;

USE Libreria;

CREATE TABLE Llibres(
  ID_Llibre int NOT NULL AUTO_INCREMENT,
  Titol VARCHAR(100) NOT NULL,
  Autor VARCHAR(100) NOT NULL,
  ISBN VARCHAR(10) NOT NULL,
  Editorial VARCHAR(100) NOT NULL,
  Any_Publicacio int(4) NOT NULL,
  Categoria VARCHAR(100) NOT NULL,
  Estat BOOLEAN,
  PRIMARY KEY(ID_Llibre)
);

CREATE TABLE Usuaris(
  ID_Usuari int NOT NULL AUTO_INCREMENT,
  Nom VARCHAR(100) NOT NULL,
  Cognoms VARCHAR(100) NOT NULL,
  Email VARCHAR(100) NOT NULL,
  Telefon VARCHAR(100) NOT NULL,
  Data_Registre datetime NOT NULL,
  PRIMARY KEY(ID_Usuari)
);

CREATE TABLE Prestecs(
  ID_Prestec int NOT NULL AUTO_INCREMENT,
  ID_Llibre int NOT NULL,
  ID_Usuari int NOT NULL,
  Data_Prestec date NOT NULL,
  Data_Retorn_Prevista date NOT NULL,
  Data_Retorn_Real date NOT NULL,
  Estat ENUM('actiu','completat','retardat'),
  PRIMARY KEY(ID_Prestec),
  FOREIGN KEY (ID_Llibre) REFERENCES Llibres(ID_Llibre),
  FOREIGN KEY (ID_Usuari) REFERENCES Usuaris(ID_Usuari)
);
