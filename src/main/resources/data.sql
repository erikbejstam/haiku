INSERT INTO haikuusers (username, password, email)
VALUES ('erik', '$2y$10$k9LVr7aIYvy0/JNL/9odAOs4AIiK/kjmLDNQSBpjBns9RIixXNfZ2', 'erik@erik.com');

INSERT INTO haikuusers (username, password, email)
VALUES ('josefin', '$2y$10$k9LVr7aIYvy0/JNL/9odAOs4AIiK/kjmLDNQSBpjBns9RIixXNfZ2', 'josefin@josefin.com');

INSERT INTO haikus (user_id, timestamp, text)
VALUES
(
    1,
    NOW(),
    'An old silent pond\nA frog jumps into the pond—\nSplash! Silence again.'
);

INSERT INTO haikus (user_id, timestamp, text)
VALUES
    (
        2,
        NOW(),
        'Pruttarna\nVanilj-\nTystnad igen.'
    );
