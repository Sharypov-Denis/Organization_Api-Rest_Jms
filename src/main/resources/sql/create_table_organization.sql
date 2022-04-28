--CREATE SEQUENCE country_id_seq;
DROP TABLE IF EXISTS organization;
DROP SEQUENCE IF EXISTS organization_id_seq;

CREATE SEQUENCE organization_id_seq START WITH 100 INCREMENT 1;
CREATE TABLE organization
(
    id INTEGER PRIMARY KEY DEFAULT nextval('organization_id_seq'),
    name varchar NOT NULL,
    inn varchar UNIQUE NOT NULL
);

/*CREATE TABLE country
(
    id integer NOT NULL DEFAULT nextval('country_id_seq'::regclass),
    name character varying(50) NOT NULL,
    CONSTRAINT country_id_pk PRIMARY KEY (id)
);

CREATE SEQUENCE city_id_seq;

CREATE TABLE city
(
    id integer NOT NULL DEFAULT nextval('city_id_seq'::regclass),
    name character varying(50) NOT NULL,
    country_id integer NOT NULL
);*/

