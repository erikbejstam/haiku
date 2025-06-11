INSERT INTO HAIKUUSER (id, username, password, email)
VALUES (1, 'u', '$2y$10$k9LVr7aIYvy0/JNL/9odAOs4AIiK/kjmLDNQSBpjBns9RIixXNfZ2', 'u@u.com');

INSERT INTO HAIKU (user_id, text, created_at)
VALUES (1, 'Detta är en fin dikt', NOW());
