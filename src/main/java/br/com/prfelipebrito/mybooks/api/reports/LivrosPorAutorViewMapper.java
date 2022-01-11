package br.com.prfelipebrito.mybooks.api.reports;

import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.shared.domain.reports.LivrosPorAutorReportData;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class LivrosPorAutorViewMapper implements Mapper<LivrosPorAutorReportData, LivrosPorAutorView> {

	@Override
	public LivrosPorAutorView map(LivrosPorAutorReportData source) {
		if (source.getCodL() == null)
			return null;
		
		var livrosPorAutorView = new LivrosPorAutorView();
		livrosPorAutorView.setCodL(source.getCodL());
		livrosPorAutorView.setTitulo(source.getTitulo());
		livrosPorAutorView.setEditora(source.getEditora());
		livrosPorAutorView.setEdicao(source.getEdicao());
		livrosPorAutorView.setAnoPublicacao(source.getAnoPublicacao());
		livrosPorAutorView.setAssuntos(source.getAssuntos());

		return livrosPorAutorView;
	}
}
