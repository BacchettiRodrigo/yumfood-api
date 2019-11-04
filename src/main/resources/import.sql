insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');

insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thaifood', 7.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Yoga Fire', 5.40, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento (descricao) values ('À Vista');
insert into forma_pagamento (descricao) values ('Cartão de Crédito');
insert into forma_pagamento (descricao) values ('Cartão de Débito');

insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Rio de Janeiro');
insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('Espírito Santo');

insert into cidade (nome, estado_id) values ('Vitória', 4);
insert into cidade (nome, estado_id) values ('Vila Velha', 4);
insert into cidade (nome, estado_id) values ('Campinas', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 1);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 3);
insert into cidade (nome, estado_id) values ('Niterói', 2);