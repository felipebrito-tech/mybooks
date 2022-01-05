package br.com.prfelipebrito.mybooks.api.assunto;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class AssuntoViewMapper implements Mapper<Assunto, AssuntoView> {

	@Override
	public AssuntoView map(Assunto source) {
		return new AssuntoView(source.getCodAs(), source.getDescricao());
	}
}
