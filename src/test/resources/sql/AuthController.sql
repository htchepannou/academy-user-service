-- login
INSERT INTO T_PERSON(id, email) VALUES(100, 'ray.sponsible@gmail.com');
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(100, 1, 100);
INSERT INTO T_ACCOUNT(id, person_fk, password, insert_timestamp) VALUES(100, 100, 'd0270be76661234d1b461df838b338a2', '2017-01-02 10:30:00');

INSERT INTO T_PERSON(id, email) VALUES(101, 'john.doe@gmail.com');
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(101, 1, 101);

-- findByAccessToken
INSERT INTO T_PERSON(id, email) VALUES(201, 'ray201.sponsible@gmail.com');
INSERT INTO T_SESSION(id, account_fk, role_fk, access_token, expiry_datetime, active) VALUES(201, 201, 1, '12345678901234567890123456789012', '2030-01-02 10:30:00', 1);

INSERT INTO T_PERSON(id, email) VALUES(202, 'ray202.sponsible@gmail.com');
INSERT INTO T_SESSION(id, account_fk, role_fk, access_token, expiry_datetime, active) VALUES(202, 202, 1, '12345678901234567890123456789000', '2030-01-02 10:30:00', 0);

INSERT INTO T_PERSON(id, email) VALUES(203, 'ray203.sponsible@gmail.com');
INSERT INTO T_SESSION(id, account_fk, role_fk, access_token, expiry_datetime, active) VALUES(203, 203, 1, '12345678901234567890123456789099', '2015-01-02 10:30:00', 1);
