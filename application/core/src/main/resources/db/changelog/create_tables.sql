CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users
(
    id                  UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email               TEXT NOT NULL,
    created             TIMESTAMP,
    last_connection     TIMESTAMP,
    online              BOOLEAN,
    user_name           TEXT NOT NULL,
    blocked             BOOLEAN DEFAULT FALSE,
    user_role           TEXT NOT NULL,
    CONSTRAINT unique_email UNIQUE (email),
    CONSTRAINT unique_user_name UNIQUE (user_name)
);

CREATE INDEX idx_user_name ON users(user_name);
CREATE INDEX idx_email ON users(email);

CREATE TABLE IF NOT EXISTS email_code
(
    id                  UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    code                TEXT NOT NULL,
    email               TEXT NOT NULL,
    sending_time        TIMESTAMP,
    used                BOOLEAN DEFAULT FALSE
);

