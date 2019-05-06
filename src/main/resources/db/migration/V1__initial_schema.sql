CREATE TABLE game.`user` (
	id INT NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(30) NOT NULL,
	invited_by INT NULL,
	device VARCHAR(50) NULL,
	phonenumber varchar(30) NULL,
	email varchar(100) NULL,
	gender varchar(20) NULL,
	CONSTRAINT NewTable_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;