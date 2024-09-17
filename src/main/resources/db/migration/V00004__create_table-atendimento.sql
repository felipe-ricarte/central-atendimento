create table atendimento (
    id bigint auto_increment not null,
    texto varchar(1000) not null,
    id_tipo_atendimento bigint not null,
    id_atendente bigint,
    status_atendimento ENUM (
		'AGUARDANDO_ATENDIMENTO',
    	'EM_ATENDIMENTO',
		'FINALIZADA'
	),
    data_criacao timestamp not null,
    data_atualizacao timestamp,
    primary key (id)
);

alter table atendimento
    add constraint fk_sol_tipo_atendimento
        foreign key (id_tipo_atendimento)
            references tipo_atendimento (id);
            
alter table atendimento
    add constraint fk_sol_atendente
        foreign key (id_atendente)
            references atendente (id);