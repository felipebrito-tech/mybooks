package br.com.prfelipebrito.mybooks.api.autor;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class AutorFormMapper implements Mapper<AutorForm, Autor> {

	@Override
	public Autor map(AutorForm source) {
		return new Autor(source.getNome());
	}
}
