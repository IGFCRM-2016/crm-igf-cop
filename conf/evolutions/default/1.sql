# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aplicacion_oferta (
  id                            bigint auto_increment not null,
  oferta_id                     bigint,
  categoria_id                  bigint,
  constraint pk_aplicacion_oferta primary key (id)
);

create table categoria (
  id                            bigint auto_increment not null,
  codigo                        integer,
  nombre                        varchar(255),
  descripcion                   varchar(255),
  constraint pk_categoria primary key (id)
);

create table cliente (
  id                            bigint auto_increment not null,
  codigo                        varchar(255),
  nombre                        varchar(255),
  genero                        integer,
  telefono                      varchar(255),
  email                         varchar(255),
  username                      varchar(255),
  password                      varchar(255),
  tarjeta_id                    bigint,
  constraint uq_cliente_tarjeta_id unique (tarjeta_id),
  constraint pk_cliente primary key (id)
);

create table compra (
  id                            bigint auto_increment not null,
  fecha                         datetime(6),
  total                         double,
  cliente_id                    bigint,
  constraint pk_compra primary key (id)
);

create table empleado (
  id                            bigint auto_increment not null,
  codigo                        varchar(255),
  nombre                        varchar(255),
  telefono                      varchar(255),
  email                         varchar(255),
  username                      varchar(255),
  password                      varchar(255),
  tipo_id                       bigint,
  constraint pk_empleado primary key (id)
);

create table gusto (
  id                            bigint auto_increment not null,
  cliente_id                    bigint,
  categoria_id                  bigint,
  constraint pk_gusto primary key (id)
);

create table incidencia (
  id                            bigint auto_increment not null,
  problema                      varchar(255),
  resolucion                    varchar(255),
  cerrada                       tinyint(1) default 0,
  empleado_id                   bigint,
  cliente_id                    bigint,
  constraint pk_incidencia primary key (id)
);

create table linea_oferta (
  id                            bigint auto_increment not null,
  oferta_id                     bigint,
  compra_id                     bigint,
  cantidad                      integer,
  precio_compra                 double,
  subtotal                      double,
  constraint pk_linea_oferta primary key (id)
);

create table linea_producto (
  id                            bigint auto_increment not null,
  producto_id                   bigint,
  compra_id                     bigint,
  cantidad                      integer,
  precio_compra                 double,
  subtotal                      double,
  constraint pk_linea_producto primary key (id)
);

create table oferta (
  id                            bigint auto_increment not null,
  fecha_inicio                  datetime(6),
  fecha_fin                     datetime(6),
  precio                        double,
  genero                        integer,
  compras_minimas               integer,
  constraint pk_oferta primary key (id)
);

create table producto (
  id                            bigint auto_increment not null,
  codigo                        varchar(255),
  nombre                        varchar(255),
  descripcion                   varchar(255),
  precio                        double,
  existencias                   integer,
  categoria_id                  bigint,
  imagen                        longblob,
  constraint pk_producto primary key (id)
);

create table producto_join_oferta (
  id                            bigint auto_increment not null,
  producto_id                   bigint,
  oferta_id                     bigint,
  cantidad                      integer,
  constraint pk_producto_join_oferta primary key (id)
);

create table tarjeta (
  id                            bigint auto_increment not null,
  numero                        varchar(255),
  saldo                         double,
  cliente_id                    bigint,
  constraint uq_tarjeta_cliente_id unique (cliente_id),
  constraint pk_tarjeta primary key (id)
);

create table tipo_empleado (
  id                            bigint auto_increment not null,
  codigo                        integer,
  nombre                        varchar(255),
  descripcion                   varchar(255),
  constraint pk_tipo_empleado primary key (id)
);

alter table aplicacion_oferta add constraint fk_aplicacion_oferta_oferta_id foreign key (oferta_id) references oferta (id) on delete restrict on update restrict;
create index ix_aplicacion_oferta_oferta_id on aplicacion_oferta (oferta_id);

alter table aplicacion_oferta add constraint fk_aplicacion_oferta_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_aplicacion_oferta_categoria_id on aplicacion_oferta (categoria_id);

alter table cliente add constraint fk_cliente_tarjeta_id foreign key (tarjeta_id) references tarjeta (id) on delete restrict on update restrict;

alter table compra add constraint fk_compra_cliente_id foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_compra_cliente_id on compra (cliente_id);

alter table empleado add constraint fk_empleado_tipo_id foreign key (tipo_id) references tipo_empleado (id) on delete restrict on update restrict;
create index ix_empleado_tipo_id on empleado (tipo_id);

alter table gusto add constraint fk_gusto_cliente_id foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_gusto_cliente_id on gusto (cliente_id);

alter table gusto add constraint fk_gusto_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_gusto_categoria_id on gusto (categoria_id);

alter table incidencia add constraint fk_incidencia_empleado_id foreign key (empleado_id) references empleado (id) on delete restrict on update restrict;
create index ix_incidencia_empleado_id on incidencia (empleado_id);

alter table incidencia add constraint fk_incidencia_cliente_id foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;
create index ix_incidencia_cliente_id on incidencia (cliente_id);

alter table linea_oferta add constraint fk_linea_oferta_oferta_id foreign key (oferta_id) references oferta (id) on delete restrict on update restrict;
create index ix_linea_oferta_oferta_id on linea_oferta (oferta_id);

alter table linea_oferta add constraint fk_linea_oferta_compra_id foreign key (compra_id) references compra (id) on delete restrict on update restrict;
create index ix_linea_oferta_compra_id on linea_oferta (compra_id);

alter table linea_producto add constraint fk_linea_producto_producto_id foreign key (producto_id) references producto (id) on delete restrict on update restrict;
create index ix_linea_producto_producto_id on linea_producto (producto_id);

alter table linea_producto add constraint fk_linea_producto_compra_id foreign key (compra_id) references compra (id) on delete restrict on update restrict;
create index ix_linea_producto_compra_id on linea_producto (compra_id);

alter table producto add constraint fk_producto_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_producto_categoria_id on producto (categoria_id);

alter table producto_join_oferta add constraint fk_producto_join_oferta_producto_id foreign key (producto_id) references producto (id) on delete restrict on update restrict;
create index ix_producto_join_oferta_producto_id on producto_join_oferta (producto_id);

alter table producto_join_oferta add constraint fk_producto_join_oferta_oferta_id foreign key (oferta_id) references oferta (id) on delete restrict on update restrict;
create index ix_producto_join_oferta_oferta_id on producto_join_oferta (oferta_id);

alter table tarjeta add constraint fk_tarjeta_cliente_id foreign key (cliente_id) references cliente (id) on delete restrict on update restrict;


# --- !Downs

alter table aplicacion_oferta drop foreign key fk_aplicacion_oferta_oferta_id;
drop index ix_aplicacion_oferta_oferta_id on aplicacion_oferta;

alter table aplicacion_oferta drop foreign key fk_aplicacion_oferta_categoria_id;
drop index ix_aplicacion_oferta_categoria_id on aplicacion_oferta;

alter table cliente drop foreign key fk_cliente_tarjeta_id;

alter table compra drop foreign key fk_compra_cliente_id;
drop index ix_compra_cliente_id on compra;

alter table empleado drop foreign key fk_empleado_tipo_id;
drop index ix_empleado_tipo_id on empleado;

alter table gusto drop foreign key fk_gusto_cliente_id;
drop index ix_gusto_cliente_id on gusto;

alter table gusto drop foreign key fk_gusto_categoria_id;
drop index ix_gusto_categoria_id on gusto;

alter table incidencia drop foreign key fk_incidencia_empleado_id;
drop index ix_incidencia_empleado_id on incidencia;

alter table incidencia drop foreign key fk_incidencia_cliente_id;
drop index ix_incidencia_cliente_id on incidencia;

alter table linea_oferta drop foreign key fk_linea_oferta_oferta_id;
drop index ix_linea_oferta_oferta_id on linea_oferta;

alter table linea_oferta drop foreign key fk_linea_oferta_compra_id;
drop index ix_linea_oferta_compra_id on linea_oferta;

alter table linea_producto drop foreign key fk_linea_producto_producto_id;
drop index ix_linea_producto_producto_id on linea_producto;

alter table linea_producto drop foreign key fk_linea_producto_compra_id;
drop index ix_linea_producto_compra_id on linea_producto;

alter table producto drop foreign key fk_producto_categoria_id;
drop index ix_producto_categoria_id on producto;

alter table producto_join_oferta drop foreign key fk_producto_join_oferta_producto_id;
drop index ix_producto_join_oferta_producto_id on producto_join_oferta;

alter table producto_join_oferta drop foreign key fk_producto_join_oferta_oferta_id;
drop index ix_producto_join_oferta_oferta_id on producto_join_oferta;

alter table tarjeta drop foreign key fk_tarjeta_cliente_id;

drop table if exists aplicacion_oferta;

drop table if exists categoria;

drop table if exists cliente;

drop table if exists compra;

drop table if exists empleado;

drop table if exists gusto;

drop table if exists incidencia;

drop table if exists linea_oferta;

drop table if exists linea_producto;

drop table if exists oferta;

drop table if exists producto;

drop table if exists producto_join_oferta;

drop table if exists tarjeta;

drop table if exists tipo_empleado;

