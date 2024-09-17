package br.com.felipericarte.centralatendimento.service;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {
	
	@Mock
	private AtendimentoRepository AtendimentoRepository;
	
	@Mock
	private TipoAtendimentoService tipoAtendimentoService;
	
	@Mock
	private AtendenteService atendenteService;
	
	@InjectMocks
	private AtendimentoService AtendimentoService;
	
	@Test
	public void criarAtendimento_ComDadosValidosSemAtendente_RetornandoAtendimento() {
		when(tipoAtendimentoService.buscarPorId(TIPO_Atendimento_PROBLEMA_CARTAO_MOCK.get().getId())).thenReturn(TIPO_Atendimento_PROBLEMA_CARTAO_MOCK);
		when(AtendimentoRepository.save(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK)).thenReturn(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK);
		
		Atendimento sut = AtendimentoService.criar(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK);
		assertThat(sut).isEqualTo(ATENDIMENTO_VALIDA_SEM_ATENDENTE_MOCK);
	}
	
	@Test
	public void criarAtendimento_ComDadosValidosComAtendente_RetornandoAtendimento() {
		when(tipoAtendimentoService.buscarPorId(TIPO_Atendimento_PROBLEMA_CARTAO_MOCK.get().getId())).thenReturn(TIPO_Atendimento_PROBLEMA_CARTAO_MOCK);
		when(atendenteService.buscarProximoPorTipoAtendimento(TIPO_Atendimento_PROBLEMA_CARTAO_MOCK.get())).thenReturn(ATENDENTE_VALIDO_MOCK);
		when(AtendimentoService.criar(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK)).thenReturn(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK);
		
		Atendimento sut = AtendimentoService.criar(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK);
		assertThat(sut).isEqualTo(ATENDIMENTO_VALIDA_COM_ATENDENTE_MOCK);
	}
	
	@Test
	public void criarAtendimento_ComTipoAtendimentoInvalida_LancandoExcecao() {
		when(tipoAtendimentoService.buscarPorId(TIPO_Atendimento_PROBLEMA_CARTAO_MOCK.get().getId())).thenReturn(TipoAtendimentoConstants.TIPO_Atendimento_PROBLEMA_CARTAO_MOCK);
		when(AtendimentoRepository.save(ATENDIMENTO_INVALIDA_MOCK)).thenThrow(RuntimeException.class);
		
		assertThatThrownBy(() -> AtendimentoService.criar(ATENDIMENTO_INVALIDA_MOCK)).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void finalizarAtendimento_ComDadosValidos_RetornandoAtendimento() {
		when(AtendimentoRepository.findById(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getId())).thenReturn(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK);
		
		Atendimento sut = AtendimentoService.finalizar(Atendimento_VALIDA_EM_ATENDIMENTO_MOCK.get().getId());
		assertThat(sut).isEqualTo(ATENDIMENTO_VALIDA_FINALIZADA_MOCK);
	}
	
	@Test
	public void finalizarAtendimento_ComAtendimentoInexistente_LancandoExcecao() {
		assertThatThrownBy(() -> AtendimentoService.finalizar(999L)).isInstanceOf(AtendimentoNotFoundException.class);
	}
	
	@Test
	public void finalizarAtendimento_ComStatusAtendimentoInvalido_LancandoExcecao() {
		when(AtendimentoRepository.findById(Atendimento_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK.get().getId())).thenReturn(Atendimento_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK);
		
		assertThatThrownBy(() -> AtendimentoService.finalizar(Atendimento_VALIDA_AGUARDANDO_ATENDIMENTO_MOCK.get().getId())).isInstanceOf(AtendimentoStatusAtendimentoException.class);
	}

}
