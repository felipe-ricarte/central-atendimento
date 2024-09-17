package br.com.felipericarte.centralatendimento.model;


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
    @Column(name = "status_atendimento", nullable = false)
    private StatusAtendimentoEnum statusAtendimento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_Atendimento", nullable = false)
    private TipoAtendimento tipoAtendimento;

    @ManyToOne
    @JoinColumn(name = "id_atendente", nullable = true)
    private Atendente atendente;

    public AtendimentoResponse toResponse() {
        return AtendimentoResponse.builder()
                .id(id)
                .texto(texto)
                .statusAtendimento(statusAtendimento)
                .tipoAtendimento(tipoAtendimento.toResponse())
                .atendente(!Objects.isNull(atendente) ? atendente.toDTO() : null).build();
    }

    @Override
    public String toString() {
        return "Atendimentos [id=" + id + ", texto=" + texto + ", statusAtendimento=" + statusAtendimento
                + ", tipoAtendimento=" + tipoAtendimento + ", atendente=" + (!Objects.isNull(atendente) ? atendente.getNome() : "") + "]";
    }

}
