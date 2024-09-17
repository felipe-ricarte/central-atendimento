package br.com.felipericarte.centralatendimento.enums;

import lombok.Getter;

@Getter
public enum StatuDemandaEnum {

    AGUARDANDO_DEMANDA(1),
    ATENDENDO_DEMANDA(2),
    FINALIZADA(3);

    private  StatusDemandaEnum(Integer codigo){
        this.codigo = codigo;
    }

    private Integer codigo;

    @Override
    public String toString() {
        return super.toString();
    }
}
