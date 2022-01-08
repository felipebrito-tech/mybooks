package br.com.prfelipebrito.mybooks.api.autor;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class AutorViewMapper implements Mapper<Autor, AutorView> {

	@Override
	public AutorView map(Autor source) {
		return new AutorView(source.getCodAu(), source.getNome());
	}
}
