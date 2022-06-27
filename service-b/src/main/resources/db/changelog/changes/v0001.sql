CREATE TABLE IF NOT EXISTS balance
(
    balance     numeric(10, 2) NOT NULL DEFAULT 0.00,
    update_date date           NOT NULL
);

CREATE TABLE IF NOT EXISTS balance_change
(
    id          SERIAL PRIMARY KEY,
    amount      numeric(10, 2) NOT NULL,
    create_date timestamp      NOT NULL
);

INSERT INTO balance (balance, update_date)
SELECT 0.00, NOW()
WHERE NOT EXISTS(SELECT balance FROM balance)
