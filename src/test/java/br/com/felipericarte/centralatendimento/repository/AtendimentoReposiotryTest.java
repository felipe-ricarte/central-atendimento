package br.com.felipericarte.centralatendimento.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class AtendimentoReposiotryTest {
	
	@Autowired
	private AtendimentoRepository AtendimentoRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void criarAtendimento_ComDadosValidosSemAtendente() {
		Atendimento atendimento = AtendimentoRepository.save(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK);
		Atendimento sut = testEntityManager.find(Atendimento.class, atendimento.getId());
		assertThat(sut).isNotNull();
		assertThat(sut.getTexto()).isEqualTo(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK.getTexto());
		assertThat(sut.getStatusAtendimento()).isEqualTo(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK.getStatusAtendimento());
		assertThat(sut.getTipoAtendimento()).isEqualTo(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK.getTipoAtendimento());
	}
	
	@Test
	public void criarAtendimento_ComDadosValidosComAtendente() {
		Atendimento atendimento = AtendimentoRepository.save(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK);
		Atendimento sut = testEntityManager.find(Atendimento.class, atendimento.getId());
		assertThat(sut).isNotNull();
		assertThat(sut.getTexto()).isEqualTo(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK.getTexto());
		assertThat(sut.getStatusAtendimento()).isEqualTo(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK.getStatusAtendimento());
		assertThat(sut.getTipoAtendimento()).isEqualTo(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK.getTipoAtendimento());
		assertThat(sut.getAtendente().getId()).isEqualTo(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK.getAtendente().getId());
	}
	
	@Test
	public void criarAtendimento_ComDadosInvalidos_LancandoExcecao() {
		Atendimento atendimentoVazia = new Atendimento();
		Atendimento atendimentoInvalida = Atendimento.builder()
				.texto("")
				.build();
		
		assertThatThrownBy(() -> AtendimentoRepository.save(atendimentoVazia));
		assertThatThrownBy(() -> AtendimentoRepository.save(atendimentoInvalida));
		
	}
	
}
