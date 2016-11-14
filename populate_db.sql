drop database crm;
create database crm;
grant all privileges on crm.* to 'crm'@'localhost' identified by 'crm';
insert into categoria (codigo,nombre,descripcion) values (1,'Tecnologia','Dispositivos electronicos, celulares, audifonos, etc');
insert into categoria (codigo,nombre,descripcion) values (2,'Arte','Libros, obras, etc');
insert into categoria (codigo,nombre,descripcion) values (3,'Musica','Instrumentos musicales, discografias, etc');
insert into categoria (codigo,nombre,descripcion) values (4,'Moda','Ropa, articulos de belleza, etc');





insert into tipo_empleado (codigo,nombre,descripcion) values (1,'Administrador','Permisos para crear empleados y registrar productos');
insert into tipo_empleado (codigo,nombre,descripcion) values (2,'Agente de Servicio','Atiende a clientes');
insert into tipo_empleado (codigo,nombre,descripcion) values (3,'Agente de Marketing','Prepara ofertas');




insert into empleado (codigo,nombre,telefono,email,username,password,tipo_id) values ('56549','Samuel Nu;ez','911','samuel@samuel.com','samuel','asdf',2);
insert into empleado (codigo,nombre,telefono,email,username,password,tipo_id) values ('8658','Antonio Juares','911','samuel@samuel.com','Antonio','asdf',3);
insert into empleado (codigo,nombre,telefono,email,username,password,tipo_id) values ('56549','Nuria Nu;ez','911','samuel@samuel.com','nuria','asdf',2);
insert into empleado (codigo,nombre,telefono,email,username,password,tipo_id) values ('56549','Carlos Nu;ez','911','samuel@samuel.com','carlos','asdf',2);
insert into empleado (codigo,nombre,telefono,email,username,password,tipo_id) values ('56549','Ana Castillo','911','samuel@samuel.com','ana','asdf',1);
