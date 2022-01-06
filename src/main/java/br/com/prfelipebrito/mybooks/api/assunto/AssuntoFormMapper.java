package br.com.prfelipebrito.mybooks.api.assunto;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class AssuntoFormMapper implements Mapper<AssuntoCreateForm, Assunto> {

	@Override
	public Assunto map(AssuntoCreateForm source) {
		return new Assunto(null, source.getDescricao());
	}

}
