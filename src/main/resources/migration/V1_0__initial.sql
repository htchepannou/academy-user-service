CREATE TABLE T_ROLE_TYPE(
  id          INT         NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20) NOT NULL,
  rank        INT         NOT NULL,

  UNIQUE(name),
  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE T_PERSON(
  id          INT           NOT NULL AUTO_INCREMENT,

  email       VARCHAR(200)  NOT NULL,
  firstname   VARCHAR(50),
  lastname    VARCHAR(50),
  language    VARCHAR(2),
  picture_url TEXT,
  biography   TEXT,


  insert_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  UNIQUE(email),
  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE T_ROLE(
  id          INT     NOT NULL AUTO_INCREMENT,
  person_fk   INT     NOT NULL REFERENCES T_PERSON(id),
  type_fk     INT     NOT NULL REFERENCES T_ROLE_TYPE(id),

  insert_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE T_ACCOUNT(
  id          INT     NOT NULL AUTO_INCREMENT,
  person_fk   INT     NOT NULL REFERENCES T_PERSON(id),

  insert_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
) ENGINE = InnoDB;


-- DATA
INSERT INTO T_ROLE_TYPE(id, name, rank) VALUE(1, 'student', 1);
INSERT INTO T_ROLE_TYPE(id, name, rank) VALUE(2, 'teacher', 2);
