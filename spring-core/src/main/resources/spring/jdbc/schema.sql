CREATE TABLE IF NOT EXISTS account (
    id      INT PRIMARY KEY,
    name    VARCHAR(50)    NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);

INSERT INTO account VALUES (1, 'Alice',   1000.00);
INSERT INTO account VALUES (2, 'Bob',      500.00);
INSERT INTO account VALUES (3, 'Charlie',  200.00);
