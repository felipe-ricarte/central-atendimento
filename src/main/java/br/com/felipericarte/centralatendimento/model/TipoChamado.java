package br.com.felipericarte.centralatendimento.model;

import br.com.felipericarte.centralatendimento.controller.response.TipoAtendimentoResponse;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_chamado")
public class TipoChamado extends ApiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    public TipoAtendimentoResponse toResponse() {
        return TipoAtendimentoResponse.builder()
                .id(id)
                .nome(nome)
                .build();
    }

    @Override
    public String toString() {
        return "TipoAtendimento [id=" + id + ", nome=" + nome + "]";
    }

}
