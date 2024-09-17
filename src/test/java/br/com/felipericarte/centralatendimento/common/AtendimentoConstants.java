package  br.com.felipericarte.centralatendimento.common;

import java.util.Optional;


public class AtendimentoConstants {
	
	public static final Optional<Atendente> ATENDENTE_VALIDO_MOCK = Optional.of(Atendente.builder()
			.id(1L)
			.nome("José")
			.timeAtendimento(TimeAtendimentoConstants.TIME_ATENDIMENTO_PROBLEMA_CARTAO_MOCK)
			.build());

}
