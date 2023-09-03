CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users
(
    id                  UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email               TEXT,
    created             TIMESTAMP,
    last_connection     TIMESTAMP,
    online              BOOLEAN,
    user_name           TEXT,
    CONSTRAINT unique_email UNIQUE (email),
    CONSTRAINT unique_user_name UNIQUE (user_name)
    );

CREATE INDEX idx_user_name ON users(user_name);
CREATE INDEX idx_email ON users(email);