CREATE TABLE haikuusers
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(30) UNIQUE  NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE haikus
(
    id        INTEGER PRIMARY KEY,
    author_id SERIAL       NOT NULL,
    timestamp TIMESTAMP    NOT NULL,
    text      VARCHAR(255) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES haikuusers (id)
        ON DELETE CASCADE /* In prod I think the haikus should not be deleted when the user is */
);
