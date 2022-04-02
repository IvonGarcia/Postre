
create database postres_db;
use postres_db;

create table ingredientes(
    id_ingrediente int primary key not null auto_increment ,
    cantidad varchar(100),
    nombre varchar(255),
    fecha_caducidad varchar(20)
);


create table postres(
    id_postre int primary key not null auto_increment,
    nombre varchar(255),
    cantidad_ingredientes int,
    piezas int,
    imagen varchar(255)
);
select * from postres;
create table ingredientes_postres(
    id int primary key not null auto_increment,
    id_postre int not null,
    id_ingrediente int not null,
    foreign key(id_postre) references postres(id_postre) on delete cascade,
    foreign key(id_ingrediente) references ingredientes(id_ingrediente) on delete cascade
);