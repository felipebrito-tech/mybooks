package br.com.prfelipebrito.mybooks.api.livro;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.domain.Livro;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class LivroFormMapper implements Mapper<LivroForm, Livro> {

	@Override
	public Livro map(LivroForm source) {
		var assuntos = source.getAssuntos()
								.stream()
								.map(assuntoCodAs -> new Assunto(assuntoCodAs, null))
								.toList();

		var autores = source.getAutores()
								.stream()
								.map(autorCodAu -> new Autor(autorCodAu, null))
								.toList();
		
		return new Livro(source.getTitulo(), source.getEditora(), source.getEdicao(), source.getAnoPublicacao(), autores, assuntos);
	}

}
