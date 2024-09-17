create table tipo_atendimento (
    id bigint auto_increment not null,
    nome varchar(255) not null,
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    primary key (id)
);