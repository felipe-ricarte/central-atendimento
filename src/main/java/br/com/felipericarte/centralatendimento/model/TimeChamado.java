package br.com.felipericarte.centralatendimento.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "time_atendimento", schema = "public")
public class TimeChamado extends ApiModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 255, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo_chma", nullable = false)
    private TipoChamado tipoChamado;

    @Override
    public String toString() {
        return "TimeAtendimento [id=" + id + ", nome=" + nome + ", tipoAtendimento=" + (!Objects.isNull(tipoChamado) ? tipoChamado.getNome() : "") + "]";
    }

}
