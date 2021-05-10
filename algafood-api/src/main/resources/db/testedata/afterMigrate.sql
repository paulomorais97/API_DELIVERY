set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from  usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment =1;
alter table cozinha auto_increment =1;
alter table estado auto_increment =1;
alter table forma_pagamento auto_increment =1;
alter table grupo auto_increment =1;
alter table permissao auto_increment =1;
alter table produto auto_increment =1;
alter table restaurante auto_increment =1;
alter table  usuario auto_increment =1;

insert into cozinha (nome) values ('Nordestina');
insert into cozinha (nome) values ('Fast Food');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');
insert into cozinha (nome) values ('Francesa');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');


insert into cidade (id, nome, estado_id) values (1, 'Ferraz de Vasconcelos',2);
insert into cidade (id, nome, estado_id) values (2, 'São Paulo',2);
insert into cidade (id, nome, estado_id) values (3, 'Uberlândia',1);
insert into cidade (id, nome, estado_id) values (4, 'Belo Horizonte',1);
insert into cidade (id, nome, estado_id) values (5, 'Belford Roxo',3);
insert into cidade (id, nome, estado_id) values (6, 'Macaé',3);

insert into restaurante (id,nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (1,'Pé de Fava', 6.99, 1, utc_timestamp, utc_timestamp, 2, '08465-140', 'Rua Festa Chinesa', '467', 'Terreo', 'Juscelino');
insert into restaurante (id,nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (2,'Hero s Burguer', 8.99, 2, utc_timestamp, utc_timestamp, 1, '08454-540', 'Rua João Alves Aranha', '12 ', 'Fundos', 'Jd. Divino');
insert into restaurante (id,nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro) values (3,'Bawarchi', 5.99, 3, utc_timestamp, utc_timestamp, 2, '08000-541', 'Av. Antonio Fonseca', '5420', 'Terreo', 'Moema');
insert into restaurante (id,nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4,'Saia do Padre', 2.99, 4, utc_timestamp, utc_timestamp);
insert into restaurante (id,nome,taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (5,'ÇA-VA', 4.99, 5, utc_timestamp, utc_timestamp);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de Débito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de Crédito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Bisteca Assada', 'Bisteca suina assada ao forno com bata', 25.90 , 1, 4 );
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Triplo Bacon', 'Hamburguer com 3 camadas de carne e bacon', 17.90 ,1 , 2);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Esfiha de Carne', 'Esfiha assada com recheio de carne', 1.99 , 1, 3);

insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Baião de dois', 'Prato com arroz, feijão, carne seca e linguiça calabresa', 25.99, 1 , 1);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Petit Gateau', 'Bolo de chocolate com casca e recheio cremoso', 12.99, 1, 5);
insert into produto(nome, descricao, preco, ativo, restaurante_id) values('Feijoada', 'Feijao preto acompanhando de carne de porco e carne seca', 35.99, 1, 4);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS','Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS','Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1),(1, 2),(1, 3),(2, 3),(3, 2),(3, 3), (4, 1), (4, 2), (5, 1), (5, 2);
