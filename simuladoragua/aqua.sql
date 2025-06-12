create database gerenciadorAgua;
use gerenciadorAgua;

create table usuario
(
    Id int primary key,
    nome varchar(50) Not null,
	email varchar(100) not null,
    senha varchar(100) not null
);

create table consumoChuveiro
(
	Id int primary key,
	banhoDia int not null,
	tempoGasto int not null,
    UsuarioId int not null,
    foreign key (UsuarioId) references usuario(Id)
);

create table consumoEscovaDente
(
	Id int primary key,
	quantEscovacao int not null,
	tempoEscovacao  int not null,
    UsuarioId int not null,
    foreign key (UsuarioId) references usuario(Id)
);

create table consumoLavagemRoupa
(
	Id int primary key,
	quantLavagens int not null,
	tempoLavagens int not null,
    UsuarioId int not null,
     foreign key (UsuarioId) references usuario(Id)
);

insert into usuario (Id, nome, email)
values (1, 'Henrique Lopes', 'henrique@email.com');

insert into usuario (Id, nome, email, senha)
values (2, 'admin', 'henrique2@email.com',"admin");

ALTER TABLE usuario
ADD senha VARCHAR(100) NOT NULL;

SELECT * FROM USUARIO;

select * from consumoChuveiro

