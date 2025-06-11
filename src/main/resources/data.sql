INSERT INTO HAIKUUSER (id, username, password, email)
VALUES (1, 'u', 'p', 'u@u.com');

INSERT INTO HAIKU (user_id, text, created_at)
VALUES (1, 'Detta är en fin dikt', NOW());
