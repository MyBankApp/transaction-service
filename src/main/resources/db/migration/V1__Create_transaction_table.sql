CREATE TABLE IF NOT EXISTS transaction (
    id uuid PRIMARY KEY,
    amount NUMERIC NOT NULL,
    currency VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    description TEXT,
    category_id SERIAL NOT NULL
);

ALTER TABLE transaction ADD CONSTRAINT check_currency CHECK (currency IN ('RUB', 'USD', 'EUR'));

ALTER TABLE transaction ADD CONSTRAINT check_status CHECK (status IN ('PENDING', 'COMPLETED', 'FAILED', 'CANCELLED'));