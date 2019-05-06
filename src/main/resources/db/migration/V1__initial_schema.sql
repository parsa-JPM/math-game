CREATE TABLE game.`user` (
	id INT NOT NULL AUTO_INCREMENT,
	username varchar(100) NOT NULL,
	password varchar(30) NOT NULL,
	token varchar(150) NULL,
	invited_by INT NULL,
	device VARCHAR(50) NULL,
	phone_number varchar(30) NULL,
	email varchar(100) NULL,
	gender varchar(20) NULL,
	created_at timestamp DEFAULT current_timestamp() NOT NULL,
	CONSTRAINT user_PK PRIMARY KEY (id)
)

ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;