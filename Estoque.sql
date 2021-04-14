create database estagio;

create table estoque(
   codigo varchar(30),
   produto varchar(30),
   valor float not null,
   qtdeProduto integer not null,
   primary key(codigo)
);

insert into estoque values('1234','Valvula','1000','4');
insert into estoque values('4321','Parafuso','5','10');

select * from estoque;