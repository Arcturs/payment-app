CREATE TABLE IF NOT EXISTS payments
(
    id              BIGSERIAL PRIMARY KEY,
    payer           VARCHAR(250)   NOT NULL,
    recipient       VARCHAR(250)   NOT NULL,
    amount          NUMERIC(20, 2) NOT NULL,
    status          VARCHAR(50)    NOT NULL,
    comment         VARCHAR(5000)
);
