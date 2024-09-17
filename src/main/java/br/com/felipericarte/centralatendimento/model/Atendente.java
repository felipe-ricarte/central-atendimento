package br.com.felipericarte.centralatendimento.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;
import org.hibernate.annotations.Where;



@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atendente")
public class Atendente extends ApiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_time_chamado", nullable = false)
    private TimeChamado timeChamado;

    @OneToMany(mappedBy = "atendente")
    @Where(clause = "status_atendimento = 'EM_ATENDIMENTO'")
    private List<Atendimento> atendimentos;

    @Override
    public String toString() {
        return "Atendente [id=" + id + ", nome=" + nome + ", timeChamado=" + (!Objects.isNull(timeChamado) ? timeChamado.getNome() : "") + "]";
    }

}
