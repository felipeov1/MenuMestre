CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE orders (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    tableCode INT NOT NULL,
    observation TEXT,
    orderHour TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);