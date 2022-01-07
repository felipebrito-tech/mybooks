package br.com.prfelipebrito.mybooks.api.assunto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;

public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {

	Optional<Assunto> findByCodAs(Integer codAs);

}
