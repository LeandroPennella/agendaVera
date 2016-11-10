-- password is 'admin' hashed con http://www.nitrxgen.net/hashgen/ - Ojo que tal vez haya que agregar un '*' adelante
-- CREATE USER 'admin'@'localhost' IDENTIFIED BY PASSWORD '*4acfe3202a5ff5cf467898fc58aab1d615029441';

CREATE SCHEMA IF NOT EXISTS agenda;

GRANT ALL PRIVILEGES ON `agenda`.* TO 'admin'@'localhost'
IDENTIFIED BY PASSWORD '*4acfe3202a5ff5cf467898fc58aab1d615029441';

USE agenda;

DROP TABLE IF EXISTS evento;
DROP TABLE IF EXISTS eventoprivado;
DROP TABLE IF EXISTS invitado;
DROP TABLE IF EXISTS reunion;
DROP TABLE IF EXISTS salareunion;
DROP TABLE IF EXISTS usuario;


CREATE TABLE usuario (    
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
username varchar(255) NOT NULL,
password varchar(255) NOT NULL,
nombre varchar(255),
apellido varchar(255),
idioma varchar(2) NOT NULL
);


CREATE TABLE evento (
id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(500),
fecha DATETIME NOT NULL,
inicio VARCHAR(5) NOT NULL,
fin VARCHAR(5) NOT NULL,
owner BIGINT NOT NULL
);


CREATE TABLE eventoprivado (
id BIGINT NOT NULL PRIMARY KEY,
descripcion VARCHAR (5000),
direccion VARCHAR (1000)
);


CREATE TABLE salareunion (
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR (500) NOT NULL
);


CREATE TABLE reunion (
id BIGINT NOT NULL PRIMARY KEY,
id_sala INT NOT NULL,
temario VARCHAR (5000),
FOREIGN KEY (id_sala) REFERENCES salareunion(id)
);


CREATE TABLE invitado (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
id_reunion BIGINT NOT NULL,
id_usuario BIGINT NOT NULL,
estado INT NOT NULL,
FOREIGN KEY (id_reunion) REFERENCES reunion(id),
FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);


INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('admin', 'admin', 'Administrador', 'Root', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('alan', 'alan', 'Alan', 'Vera', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('pablo', 'pablo', 'Pablo', 'Bonillo', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('diego', 'diego', 'Diego', 'Mornacco', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('martina', 'martina', 'Martina', 'Fernandez', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('leandro', 'leandro', 'Leandro', 'Pennella', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('betiana', 'betiana', 'Betiana', 'Arias', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('juan', 'juan', 'Juan', 'Carlos', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('pepe', 'pepe', 'Jose', 'Fernandez', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('alberto', 'alberto', 'Alberto', 'Perez', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('flavio', 'flavio', 'Flavio', 'De la Puente', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('carlos', 'carlos', 'Juan Carlos', 'Orosco', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('anto', 'anto', 'Antonella', 'Ocorzo', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('dani', 'dani', 'Daniela', 'Lopez Iglesias', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('rodri', 'rodri', 'Rodrigo', 'Menchaca', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('enzo', 'enzo', 'Enzo', 'Falciglia', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('seba', 'seba', 'Sebastian', 'Capano', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('ariel', 'ariel', 'Ariel', 'Gorocito', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('nicolas', 'nicolas', 'Nicolas', 'Stazkiw', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('nico', 'nico', 'Nicolas', 'Vignati', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('franco', 'franco', 'Franco', 'Vera', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('bruno', 'bruno', 'Bruno', 'Vera', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('vanina', 'vanina', 'Vanina', 'Dursi', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('pepi', 'pepi', 'Estefania', 'Dursi', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('quique', 'quique', 'Enrique', 'Vera Roa', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('blanca', 'blanca', 'Rosalia', 'Galvano', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('maradona', 'maradona', 'Diego', 'Armando', 'en');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('lionel', 'lionel', 'Lionel', 'Messi', 'es');
INSERT INTO usuario (username, password, nombre, apellido, idioma)
VALUES('lucho', 'lucho', 'Luciano', 'Carracedo', 'en');


INSERT INTO salareunion (nombre) VALUES('Auditorio');
INSERT INTO salareunion (nombre) VALUES('Conferencias');
INSERT INTO salareunion (nombre) VALUES('Prensa');
INSERT INTO salareunion (nombre) VALUES('Sala Mediana');
INSERT INTO salareunion (nombre) VALUES('Sala Grande');





