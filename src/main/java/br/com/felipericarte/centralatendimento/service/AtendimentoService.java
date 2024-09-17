package br.com.felipericarte.centralatendimento.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoService {
	
	@Autowired
	private AtendenteService atendenteService;
	
	@Autowired
	private TipoAtendimentoService tipoAtendimentoService;
	
	@Autowired
	private AtendimentoRepository AtendimentoRepository;
	
	public List<Atendimento> buscarTodos() {
		return AtendimentoRepository.findAll();
	}

	public Atendimento buscarPorId(Long id) {
		Atendimento atendimento = AtendimentoRepository.findById(id).orElseThrow(
			()-> new AtendimentooNotFoundException("Atendimento " + id + " não encontrada")
		);
		return atendimento;
	}
	
	@Transactional
	public Atendimento criar(Atendimento request) {
		
		Optional<TipoChamado> tipoAtendimento = tipoAtendimentoService.buscarPorId(request.getTipoAtendimento().getId());
		Optional<Atendente> atendente = atendenteService.buscarProximoPorTipoAtendimento(tipoAtendimento.get());
		
		Atendimento atendimento = Atendimento.builder()
				.texto(request.getTexto())
				.statusAtendimento(atendente.isPresent() ? StatusAtendimentoEnum.EM_ATENDIMENTO : StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO)
				.atendente(atendente.isPresent() ? atendente.get() : null)
				.tipoAtendimento(tipoAtendimento.get())
				.build();
		
		return AtendimentoRepository.save(atendimento);
	}

	@Transactional
	public Atendimento finalizar(Long id) {
		Optional<Atendimento> Atendimento = AtendimentoRepository.findById(id);
		validarFinalizacao(Atendimento);
		atualizarStatus(Atendimento, StatusAtendimentoEnum.FINALIZADA);
		iniciarProxima(Atendimento.get().getAtendente());
		return Atendimento.get();
	}

	private void validarFinalizacao(Optional<Atendimento> Atendimento) {
		if(!Atendimento.isPresent()) {
			throw new AtendimentoNotFoundException("Atendimento não encontrada");
		}
		if(!Atendimento.get().getStatusAtendimento().equals(StatusAtendimentoEnum.EM_ATENDIMENTO)) {
			throw new AtendimentoStatusAtendimentoException("Não é possível finalizar uma Atendimento que não está Em Atendimento");
		}
	}
	
	private void atualizarStatus(Optional<Atendimento> Atendimento, StatusAtendimentoEnum statusAtendimento) {
		if(Atendimento.get().getStatusAtendimento().equals(statusAtendimento)) {
			throw new AtendimentoStatusAtendimentoException("Não é possível alterar o Status de uma Atendimento para o mesmo Status");
		}
		Atendimento.get().setStatusAtendimento(statusAtendimento);
	}

	private void iniciarProxima(Atendente atendente) {
		Optional<Atendimento> Atendimento =
			AtendimentoRepository.findTop1ByTipoAtendimentoAndStatusAtendimentoOrderByIdAsc(atendente.getTimeAtendimento().getTipoAtendimento(), StatusAtendimentoEnum.AGUARDANDO_ATENDIMENTO);
		if(Atendimento.isPresent()) {
			Atendimento.get().setAtendente(atendente);
			Atendimento.get().setStatusAtendimento(StatusAtendimentoEnum.EM_ATENDIMENTO);
		}
	}
	
}
