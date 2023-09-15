INSERT INTO role(authority) VALUES('ROLE_SUPERADMIN')
INSERT INTO role(authority) VALUES('ROLE_ADMIN')
INSERT INTO role(authority) VALUES('ROLE_USER')

INSERT INTO usuarios(nombres,apellidos,email,enabled,dni,telefono,username,password,nacionalidad,fecha_ingreso,foto) VALUES('Hector17','Apellido01 Apellido02','user1@gmail.com',true,'78021293','995707476','user123','$2a$10$DDNB/kjudd5GvexibmBfk.IsI5l6KTmXPLZdx3O7i3Q5v8m7xbPg6','Perú','2020/10/01','image.png');
INSERT INTO usuarios(nombres,apellidos,email,enabled,dni,telefono,username,password,nacionalidad,fecha_ingreso) VALUES('Antonio','Apellido01 Apellido02','user2@gmail.com',true,'78021293','995707476','user234','$2a$10$DDNB/kjudd5GvexibmBfk.IsI5l6KTmXPLZdx3O7i3Q5v8m7xbPg6','Perú','2020/10/01');
INSERT INTO usuarios(nombres,apellidos,email,enabled,dni,telefono,username,password,nacionalidad,fecha_ingreso) VALUES('Josaf','Reaños Arcilla','user3@gmail.com',true,'78021293','995707476','user254','$2a$10$DDNB/kjudd5GvexibmBfk.IsI5l6KTmXPLZdx3O7i3Q5v8m7xbPg6','Perú','2020/10/01');
INSERT INTO usuarios(nombres,apellidos,email,enabled,dni,telefono,username,password,nacionalidad,fecha_ingreso) VALUES('Yuliana','Alvarez Caballero','user4@gmail.com',true,'78021293','995707476','user789','$2a$10$DDNB/kjudd5GvexibmBfk.IsI5l6KTmXPLZdx3O7i3Q5v8m7xbPg6','Perú','2020/10/01');


INSERT INTO usuarios_roles(usuario_id,roles_id) VALUES(1,1);
INSERT INTO usuarios_roles(usuario_id,roles_id) VALUES(2,2);
INSERT INTO usuarios_roles(usuario_id,roles_id) VALUES(2,3);
INSERT INTO usuarios_roles(usuario_id,roles_id) VALUES(3,3);


INSERT INTO account(id,user_id,balance) VALUES(1,1,5000.00);
INSERT INTO account(id,user_id,balance) VALUES(2,2,5000.00);
INSERT INTO account(id,user_id,balance) VALUES(3,3,5000.00);

INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'EUR', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'JPY', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'GBP', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'AUD', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'CAD', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('EUR', 'JPY', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('EUR', 'GBP', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('EUR', 'AUD', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('EUR', 'CAD', random() + 1);
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('JPY', 'GBP', random() + 1);

	