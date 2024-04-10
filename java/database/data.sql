BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO people (user_id, first_name, last_name, email, is_available_weekdays, is_available_weekends, volunteering_interest)
	VALUES (1, 'John', 'Smith', 'JohnSmith@volunteer.com', false, true, null);
INSERT INTO people (user_id, first_name, last_name, email, is_available_weekdays, is_available_weekends, volunteering_interest)
	VALUES (2, 'Jane', 'Doe', 'JaneTheAdmin@boss.com', true, true, 'I love animals and cannot wait to make a difference in the world!');

INSERT INTO species (name) VALUES ('cat');
INSERT INTO species (name) VALUES ('dog');
INSERT INTO species (name) VALUES ('bunny');

INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (1, 'Cat1.JPG', 'Pippin', 3, false, 'orange, white', 'house cat', 'S', 'Male', true, false, 'He loves to eat fabric but is cute...');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (1, 'Cat2.JPG', 'Dori', 3, false, 'orange', 'house cat', 'S', 'Female', true, false, 'Super cute cat!');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (1, 'Cat3.JPG', 'Callie', 8, true, 'calico', 'stray', 'XS', 'Female', false, true, 'This stray stumbled in and never left.');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (2, 'Dog1.JPG', 'Daisy', 5, false, 'cream', 'lab/husky', 'M', 'Female', true, true, 'Sweet girl that loves to be loved!');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (2, 'Dog2.JPG', 'Smokey', 4, true, 'grey', 'puli', 'S', 'Male', false, false, 'Smokey has allergies.');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (2, 'Dog3.JPG', 'Butters', 6, true, 'white, brown', 'cattle dog', 'M', 'Female', true, false, 'Poor girl has a lot of anxiety.');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (3, 'Rabbit1.JPG', 'Arwin', 2, false, 'grey', 'angora', 'S', 'Female', false, true, 'You have to watch her as she likes to bite ankles.');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (3, 'Rabbit2.JPG', 'Carrots', 5, false, 'white, black', 'lionhead', 'XS', 'Female', false, false, 'Believe it or not, Carrots LOVES... carrots!');
INSERT INTO pets (species_id, main_photo, name, age, has_special_needs, color, breed, size, gender, is_fixed, is_adopted, description) 
	VALUES (3, 'Rabbit3.JPG', 'Sir Hops-A-Lot', 1, true, 'white', 'mini lop', 'L', 'Male', true, false, 'Gets around great but is a blind boy.');

COMMIT TRANSACTION;
