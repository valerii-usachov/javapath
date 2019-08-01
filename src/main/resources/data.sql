INSERT INTO account_type (id, name, url_exp_period)
VALUES ('free', 'Free', 30);
INSERT INTO account_type (id, name, url_exp_period)
VALUES ('pro', 'Pro', 365);
INSERT INTO account_type (id, name, url_exp_period)
VALUES ('platinum', 'Platinum', 0);

INSERT INTO users (username, account_type_id, first_name, last_name, gender)
VALUES ('test@user.com', 'pro', 'Test', 'User', 'MALE')