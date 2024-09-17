package br.com.felipericarte.centralatendimento.common;

public class AtendimentoRequestConstants {
	
	public static final AtendimentoRequest Atendimento_REQUEST_VALIDA_MOCK = AtendimentoRequest.builder()
		.texto("Problemas durante a consulta de saldo do cartão")
		.idTipoAtendimento(1L)
		.build();
	
	public static final AtendimentoRequest Atendimento_REQUEST_INVALIDA_MOCK = AtendimentoRequest.builder()
			.idTipoAtendimento(1L)
			.build();

}
