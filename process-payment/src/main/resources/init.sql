CREATE TABLE IF NOT EXISTS payments
(
    id              BIGSERIAL PRIMARY KEY,
    payer           VARCHAR(250)   NOT NULL,
    recipient       VARCHAR(250)   NOT NULL,
    amount          NUMERIC(20, 2) NOT NULL,
    status          VARCHAR(50)    NOT NULL,
    comment         VARCHAR(5000)
);

CREATE ROLE debezium_reader WITH LOGIN PASSWORD 'oj98gh' VALID UNTIL '2030-01-01' REPLICATION;
CREATE ROLE reader_group;
GRANT reader_group TO postgres;
GRANT reader_group TO debezium_reader;
ALTER TABLE payments OWNER TO reader_group;
