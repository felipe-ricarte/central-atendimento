package br.com.felipericarte.centralatendimento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AtendenteRepository extends JpaRepository<Atendente, Long>{
	
	@Query("SELECT a FROM Atendente a "
		+ "LEFT JOIN a.atendimentos s "
		+ "WHERE a.timeAtendimento.tipoAtendimento = :tipoAtendimento")
	public List<Atendente> findByTipoAtendimento(TipoChamado tipoChamado);
	
}
