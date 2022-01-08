package br.com.prfelipebrito.mybooks.api.autor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prfelipebrito.mybooks.shared.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	Optional<Autor> findByCodAu(Integer codAu);

}
