BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, species, pets, photos;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE species (
	species_id SERIAL,
	name varchar(100) NOT NULL,
	
	CONSTRAINT PK_species PRIMARY KEY (species_id)
);

CREATE TABLE pets (
	pet_id SERIAL,
	species_id integer NOT NULL,
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
	CONSTRAINT FK_pets_specie FOREIGN KEY(species_id) REFERENCES species(species_id)
);

CREATE TABLE photos (
	photo_id SERIAL,
	file_name varchar NOT NULL,
	pet_id integer NOT NULL,
	
	CONSTRAINT PK_photos PRIMARY KEY (photo_id),
	CONSTRAINT FK_photos_pet FOREIGN KEY(pet_id) REFERENCES pets(pet_id)
);

COMMIT TRANSACTION;
