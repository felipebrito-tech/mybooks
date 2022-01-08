package br.com.prfelipebrito.mybooks.api.assunto;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class AssuntoFormMapper implements Mapper<AssuntoForm, Assunto> {

	@Override
	public Assunto map(AssuntoForm source) {
		return new Assunto(source.getDescricao());
	}

}
