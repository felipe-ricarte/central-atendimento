package br.com.felipericarte.centralatendimento.common;

public class TimeAtendimentoConstants {
	
	public static final TimeChamado TIME_ATENDIMENTO_PROBLEMA_CARTAO_MOCK = TimeChamado.builder()
			.id(1L)
			.nome("Cartões")
			.tipoChamado(TipoAtendimentoConstants.TIPO_SOLICITACAO_PROBLEMA_CARTAO_MOCK.get())
			.build();

}
