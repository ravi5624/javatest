DROP TABLE oauth_refresh_token;
DROP TABLE oauth_access_token;

SELECT * FROM oauth_access_token;
SELECT * FROM oauth_refresh_token;

DELETE FROM oauth_access_token;
DELETE FROM oauth_refresh_token;

CREATE TABLE oauth_access_token (
  token_id          VARCHAR(256) NOT NULL,
  token             BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name         VARCHAR(256) NOT NULL,
  client_id         VARCHAR(256) NOT NULL,
  authentication    BYTEA,
  refresh_token     VARCHAR(256) NOT NULL
);

CREATE TABLE oauth_refresh_token (
  token_id       VARCHAR(256) DEFAULT NULL,
  token          BYTEA,
  authentication BYTEA
);

CREATE SEQUENCE locale_seq INCREMENT 1 START 1;
CREATE SEQUENCE log_activity_seq INCREMENT 1 START 1;

CREATE SEQUENCE states_seq INCREMENT 1 START 1;
CREATE SEQUENCE districts_seq INCREMENT 1 START 1;
CREATE TABLE states
(
  id         BIGINT DEFAULT nextval('states_seq') PRIMARY KEY,
  state_name VARCHAR(255)
);
CREATE TABLE districts (
  id            BIGINT DEFAULT nextval('districts_seq') PRIMARY KEY,
  branch_code   VARCHAR(255),
  district_code VARCHAR(255),
  district_name VARCHAR(255),
  state_id      VARCHAR(255)
);