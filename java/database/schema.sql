BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, people, species, pets, photos;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE people (
	person_id SERIAL,
	user_id integer DEFAULT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	email varchar(150) NOT NULL UNIQUE,
	is_available_weekdays boolean NOT NULL DEFAULT 'false',
	is_available_weekends boolean NOT NULL DEFAULT 'false',
	volunteering_interest varchar(500),
	is_approved boolean DEFAULT null,
	token varchar(250) NOT NULL UNIQUE,
	
	CONSTRAINT PK_people PRIMARY KEY (person_id),
	CONSTRAINT FK_people_user FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE species (
	species_id SERIAL,
	name varchar(100) NOT NULL,
	
	CONSTRAINT PK_species PRIMARY KEY (species_id)
);

CREATE TABLE pets (
	pet_id SERIAL,
	species_id integer NOT NULL,
	main_photo varchar,
	name varchar(200) NOT NULL,
	age integer NOT NULL,
	has_special_needs boolean DEFAULT 'false',
	color varchar NOT NULL,
	breed varchar NOT NULL,
	size varchar NOT NULL,
	gender varchar NOT NULL,
	is_fixed boolean DEFAULT 'false',
	is_adopted boolean DEFAULT 'false',
	description varchar (5000),
	
	CHECK (size IN ('XS', 'S', 'M','L', 'XL')),
	CHECK (gender IN ('Female', 'Male')),
	
	CONSTRAINT PK_pets PRIMARY KEY (pet_id),
	CONSTRAINT FK_pets_species FOREIGN KEY(species_id) REFERENCES species(species_id)
);

CREATE TABLE photos (
	photo_id SERIAL PRIMARY KEY ,
	file_name varchar NOT NULL,
	pet_id integer REFERENCES pets(pet_id)
);

COMMIT TRANSACTION;
