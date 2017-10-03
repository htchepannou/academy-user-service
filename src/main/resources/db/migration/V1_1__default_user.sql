INSERT INTO T_PERSON(id, email, first_name, last_name, picture_url) VALUES(1, 'herve.tchepannou@gmail.com', 'Herve', 'Tchepannou', 'https://media.licdn.com/mpr/mpr/shrinknp_100_100/p/6/000/1ed/117/09eecf4.jpg');
INSERT INTO T_ACCOUNT(id, person_fk, password, insert_timestamp) VALUES(1, 1, 'ffbe21e2ede3f9fa3b61ab9448d7a36c', '2017-01-02 10:30:00');
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(1, 1, 1);

INSERT INTO T_PERSON(id, email, first_name, last_name, picture_url, biography) VALUES(2, 'ntchepannou@gmail.com', 'Nadine', 'Tchepannou', 'https://media.licdn.com/media/AAEAAQAAAAAAAAcsAAAAJGE4YzgzNWNmLTQ2ZGMtNGVlYS1hYjM3LTdjM2VjZTA2MjkxNQ.jpg',
'I am a Marketer who can code, love designing pretty things and I crunche numbers for fun. I am a lifelong learner: I like to learn from others, experiment and share what I have learned. I believe Marketers shouldn’t have to chose between being creative or analytical. They HAVE TO be both.');
INSERT INTO T_ACCOUNT(id, person_fk, password, insert_timestamp) VALUES(2, 2, 'feb1026f376530f9a63b3d9681c71485', '2017-01-02 10:30:00');
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(2, 1, 2);
INSERT INTO T_ROLE(id, type_fk, person_fk) VALUES(3, 2, 2);

