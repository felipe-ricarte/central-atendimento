package br.com.felipericarte.centralatendimento.repository;

import java.util.Optional;


public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>{

	Optional<Atendimento> findTop1ByTipoAtendimenoAndStatusAtendimentoOrderByIdAsc(TipoChamado tipoChamado,
																					StatusAtendimentoEnum aguardandoAtendimento);

}
