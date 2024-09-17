create table atendente (
    id bigint auto_increment not null,
    nome varchar(255) not null,
    id_time_chamado bigint not null,
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    primary key (id)
);

alter table atendente
    add constraint fk_aten_time_chamado
        foreign key (id_time_chamado)
            references time_chamado (id);