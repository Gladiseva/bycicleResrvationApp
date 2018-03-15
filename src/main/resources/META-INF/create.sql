CREATE SEQUENCE employee_seq;

CREATE SEQUENCE bicycle_seq;

CREATE SEQUENCE reservation_seq;

CREATE TABLE employee (id BIGINT NOT NULL CONSTRAINT employee_pkey PRIMARY KEY, name VARCHAR(150) NOT NULL, surname VARCHAR(200) NOT NULL);

CREATE TABLE bicycle (id BIGINT NOT NULL CONSTRAINT bicycle_pkey PRIMARY KEY, manufacturer  VARCHAR(100) NOT NULL, model VARCHAR(100) NOT NULL, year_produced INTEGER NOT NULL, breakages VARCHAR(300));

CREATE TABLE bicycle_reservation (id BIGINT NOT NULL CONSTRAINT bicycle_reservation_pkey PRIMARY KEY, employee_id BIGINT NOT NULL CONSTRAINT reservation_emloyee_fk REFERENCES employee ON DELETE CASCADE, bicycle_id BIGINT NOT NULL CONSTRAINT reservation_bicycle_fk REFERENCES bicycle ON DELETE CASCADE, start_usage_date TIMESTAMP NOT NULL, end_usage_date TIMESTAMP NOT NULL);

CREATE INDEX fki_reservation_emloyee_fk ON bicycle_reservation (employee_id);

CREATE INDEX fki_reservation_bicycle_fk ON bicycle_reservation (bicycle_id);

