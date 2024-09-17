package br.com.felipericarte.centralatendimento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoAtendimentoService {
	
	@Autowired
	private TipoAtendimentoRepository tipoAtendimentoRepository;

	public Optional<TipoAtendimento> buscarPorId(Long id) {
		return tipoAtendimentoRepository.findById(id);
	}

	public List<TipoAtendimento> buscarTodos() {
		return tipoAtendimentoRepository.findAll();
	}

}
