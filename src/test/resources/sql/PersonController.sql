-- findByRole
INSERT INTO T_PERSON(id, email, first_name, last_name, language, picture_url, biography) VALUES(100, 'ray.sponsible@gmail.com', 'Ray', 'Sponsible', 'en', 'http://img.com/ray.sponsible', 'Bio...');
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(100, 1, 100);
