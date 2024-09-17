package br.com.felipericarte.centralatendimento.common;

import java.util.Optional;

public class TipoAtendimentoConstants {
	
	public static final Optional<TipoChamado> TIPO_Atendimento_PROBLEMA_CARTAO_MOCK =
			Optional.of(TipoChamado.builder()
				.id(1L)
				.nome("Problemas com cartão")
				.build()
			);

}
