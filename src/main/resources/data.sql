use springauth;
INSERT INTO USER(name, email, password) VALUES('user', 'user@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USER(name, email, password) VALUES('Moderador', 'moderador@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

INSERT INTO ROLE(id, name) VALUES(1, 'ROLE_USER');
INSERT INTO ROLE(id, name) VALUES(2, 'ROLE_MODERADOR');

INSERT INTO user_roles(user_id, roles_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, roles_id) VALUES(2, 2);