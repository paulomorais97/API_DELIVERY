insert into cozinha (nome) values ('Nordestina');
insert into cozinha (nome) values ('Fast Food');
insert into cozinha (nome) values ('indiana');

insert into restaurante (id,nome,taxa_frete, cozinha_id) values (1,'Pé de Fava', 6.99, 1);
insert into restaurante (id, nome,taxa_frete, cozinha_id) values (2,'Hero s Burguer', 8.99, 2);
insert into restaurante (id, nome,taxa_frete, cozinha_id) values (3,'Bawarchi', 5.99, 3);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');


insert into cidade (id, nome, estado_id) values (1, 'Ferraz de Vasconcelos',2);
insert into cidade (id, nome, estado_id) values (2, 'São Paulo',2);
insert into cidade (id, nome, estado_id) values (3, 'Uberlândia',1);
insert into cidade (id, nome, estado_id) values (4, 'Belo Horizonte',1);
insert into cidade (id, nome, estado_id) values (5, 'Belford Roxo',3);
insert into cidade (id, nome, estado_id) values (6, 'Macaé',3);


insert into forma_pagamento (id, descricao) values (1, 'Cartão de Débito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de Crédito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');
insert into forma_pagamento (id, descricao) values (4, 'Boleto');


insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS','Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS','Permite editar cozinhas');
