--CREATE SEQUENCE country_id_seq;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS organization;
DROP SEQUENCE IF EXISTS organization_id_seq;
DROP SEQUENCE IF EXISTS employee_id_seq;

CREATE SEQUENCE organization_id_seq START WITH 100 INCREMENT 1;
CREATE TABLE organization
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('organization_id_seq'),
    name varchar        NOT NULL,
    inn  varchar UNIQUE NOT NULL
);

CREATE SEQUENCE employee_id_seq START WITH 100 INCREMENT 1;
CREATE TABLE account
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('employee_id_seq'),
    sum             varchar NOT NULL,
    account         varchar not null,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organization (id)
);

CREATE SEQUENCE check_payment_id_seq START WITH 100 INCREMENT 1;
CREATE TABLE payment
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('check_payment_id_seq'),
    sum             INTEGER NOT NULL,
    senderInn       varchar not null,
    receiverInn     varchar not null,
    senderAccount   varchar not null,
    receiverAccount varchar not null,
    status          varchar
)

