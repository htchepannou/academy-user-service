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
  first_name  VARCHAR(50),
  last_name   VARCHAR(50),
  language    CHAR(2),
  picture_url TEXT,
  website_url TEXT,
  title       VARCHAR(100),
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
  id          INT         NOT NULL AUTO_INCREMENT,
  person_fk   INT         NOT NULL REFERENCES T_PERSON(id),

  password    CHAR(32) NOT NULL,

  insert_timestamp    DATETIME    DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id)
) ENGINE = InnoDB;


CREATE TABLE T_SESSION(
  id          INT NOT NULL AUTO_INCREMENT,
  account_fk  INT NOT NULL REFERENCES T_ACCOUNT(id),
  role_fk     INT NOT NULL REFERENCES T_ROLE_TYPE(id),

  access_token      CHAR(36)    NOT NULL,
  expiry_datetime   DATETIME    NOT NULL,
  active            BIT,

  insert_timestamp    DATETIME    DEFAULT CURRENT_TIMESTAMP,
  update_timestamp    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  UNIQUE(access_token),
  PRIMARY KEY (id)
) ENGINE = InnoDB;


-- DATA
INSERT INTO T_ROLE_TYPE(id, name, rank) VALUE(1, 'student', 1);
INSERT INTO T_ROLE_TYPE(id, name, rank) VALUE(2, 'instructor', 2);
