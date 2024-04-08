BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO species (name) VALUES ('cat');
INSERT INTO species (name) VALUES ('dog');
INSERT INTO species (name) VALUES ('bunny');

INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (1, 'Pippin', 3, false, 'orange, white', 'house cat', 'S', 'Male', true, false, 'He loves to eat fabric but is cute...');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (1, 'Dori', 3, false, 'orange', 'house cat', 'S', 'Female', true, false, 'Super cute cat!');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (1, 'Callie', 8, true, 'calico', 'stray', 'XS', 'Female', false, true, 'This stray stumbled in and never left.');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (2, 'Daisy', 5, false, 'cream', 'lab/husky', 'M', 'Female', true, true, 'Sweet girl that loves to be loved!');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (2, 'Smokey', 4, true, 'grey', 'puli', 'S', 'Male', false, false, 'Smokey has allergies.');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (2, 'Butters', 6, true, 'white, brown', 'cattle dog', 'M', 'Female', true, false, 'Poor girl has a lot of anxiety.');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (3, 'Arwin', 2, false, 'grey', 'angora', 'S', 'Female', false, true, 'You have to watch her as she likes to bite ankles.');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (3, 'Carrots', 5, false, 'white, black', 'lionhead', 'XS', 'Female', false, false, 'Believe it or not, Carrots LOVES... carrots!');
INSERT INTO pets (species_id, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (3, 'Sir Hops-A-Lot', 1, true, 'white', 'mini lop', 'L', 'Male', true, false, 'Gets around great but is a blind boy.');

COMMIT TRANSACTION;
