--Account Types
INSERT INTO account_type (id, name, url_exp_period)
VALUES  ('free', 'Free', 30),
        ('pro', 'Pro', 365),
        ('platinum', 'Platinum', 0);

--Users
INSERT INTO users (username, account_type_id, first_name, last_name, gender)
VALUES ('test@user.com', 'pro', 'Test', 'User', 'MALE');

--Tags
INSERT INTO tag (name, created_at)
VALUES  ('programming', current_timestamp),
        ('java', current_timestamp),
        ('education', current_timestamp);