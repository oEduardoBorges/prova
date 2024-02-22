INSERT INTO tb_user (name, email, username, password) VALUES ('Admin', 'admin@email.com', 'admin', '$2a$10$52rafkMziVkhI5bEYGDR5eUV2IVKAj8DzVXWHH2isbgsKnxE5Vm9a');
INSERT INTO tb_user (name, email, username, password) VALUES ('root', 'root@email.com', 'root', '$2a$10$52rafkMziVkhI5bEYGDR5eUV2IVKAj8DzVXWHH2isbgsKnxE5Vm9a');
INSERT INTO tb_user (name, email, username, password) VALUES ('user', 'user@email.com', 'user', '$2a$10$52rafkMziVkhI5bEYGDR5eUV2IVKAj8DzVXWHH2isbgsKnxE5Vm9a');


INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_CLIENT');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);

INSERT INTO tb_tela (name, description) VALUES ('Tela Principal', 'Tela principal do sistema');
INSERT INTO tb_tela (name, description) VALUES ('Tela Secundário', 'Tela secundária do sistema');
