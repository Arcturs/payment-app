CREATE TABLE IF NOT EXISTS statistics
(
    id     BIGSERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL UNIQUE,
    amount INT         NOT NULL DEFAULT 0
);
