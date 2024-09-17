create table time_chamado (
    id bigint auto_increment not null,
    nome varchar(255) not null,
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    id_tipo_atendimento bigint not null,
    primary key (id)
);

alter table time_atendimento
    add constraint fk_timate_tipo_atendimento
        foreign key (id_tipo_atendimento)
            references tipo_atendimento (id);