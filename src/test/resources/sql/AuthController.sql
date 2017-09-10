INSERT INTO T_PERSON(id, email) VALUES(100, 'ray.sponsible@gmail.com');
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(100, 1, 100);
INSERT INTO T_ACCOUNT(id, person_fk, password, insert_timestamp) VALUES(100, 100, 'b96ce7dbbd279bd1dd3e92dbac904fdc', '2017-01-02 10:30:00');
