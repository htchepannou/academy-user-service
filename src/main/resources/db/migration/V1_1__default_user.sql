INSERT INTO T_PERSON(id, email) VALUES(1, 'herve.tchepannou@gmail.com');
INSERT INTO T_ACCOUNT(id, person_fk, password, insert_timestamp) VALUES(1, 100, 'ffbe21e2ede3f9fa3b61ab9448d7a36c', '2017-01-02 10:30:00');

INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(1, 1, 1);
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(2, 2, 1);
