package br.com.felipericarte.centralatendimento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AtendenteService {

	@Autowired
	private AtendenteRepository atendenteRepository;

	public Optional<Atendente> buscarProximoPorTipoAtendimenti(TipoAtendimento tipoAtendimento) {
		List<Atendente> list = atendenteRepository.findByTipoAtendimento(tipoAtendimento);
		
		return list.stream().filter(s -> s.getAtendimentos().size() < 3).findFirst();
	}

}
