package br.com.felipericarte.centralatendimento.model;

import br.com.felipericarte.centralatendimento.controller.response.AtendimentoResponse;
import br.com.felipericarte.centralatendimento.enums.StatusDemandaEnum;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atendimento")
public class Atendimento extends ApiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto", length = 1000, nullable = false)
    private String texto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_demanda", nullable = false)
    private StatusDemandaEnum statusDemandaEnum;

    @ManyToOne
    @JoinColumn(name = "id_tipo_Atendimento", nullable = false)
    private TipoChamado tipoChamado;

    @ManyToOne
    @JoinColumn(name = "id_atendente", nullable = true)
    private Atendente atendente;

    public AtendimentoResponse toResponse() {
        return AtendimentoResponse.builder()
                .id(id)
                .texto(texto)
                .statusDemanda(statusDemandaEnum)
                .tipoAtendimento(tipoChamado.toResponse())
                .atendente(!Objects.isNull(atendente) ? atendente.toDTO() : null).build();
    }

    @Override
    public String toString() {
        return "Atendimentos [id=" + id + ", texto=" + texto + ", statusAtendimento=" + statusDemandaEnum
                + ", tipoAtendimento=" + tipoChamado + ", atendente=" + (!Objects.isNull(atendente) ? atendente.getNome() : "") + "]";
    }

}
