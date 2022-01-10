package br.com.prfelipebrito.mybooks.api.livro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prfelipebrito.mybooks.shared.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

	Optional<Livro> findByCodL(Integer codL);
}
