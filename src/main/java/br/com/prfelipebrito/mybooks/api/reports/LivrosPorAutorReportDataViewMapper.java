package br.com.prfelipebrito.mybooks.api.reports;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.api.autor.AutorView;
import br.com.prfelipebrito.mybooks.shared.domain.reports.LivrosPorAutorReportData;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class LivrosPorAutorReportDataViewMapper implements Mapper<List<LivrosPorAutorReportData>, List<LivrosPorAutorReportDataView>> {

	@Autowired
	private LivrosPorAutorViewMapper livrosPorAutorViewMapper;

	@Override
	public List<LivrosPorAutorReportDataView> map(List<LivrosPorAutorReportData> source) {
		return this.groupDataByAutor(source)
					.stream()
					.map(this::buildLivrosPorAutorReportDataView)
					.toList();
	}
	
	private Collection<List<LivrosPorAutorReportData>> groupDataByAutor(List<LivrosPorAutorReportData> source) {
		return source.stream().collect(Collectors.groupingBy(LivrosPorAutorReportData::getCodAu)).values();
	}

	private LivrosPorAutorReportDataView buildLivrosPorAutorReportDataView(List<LivrosPorAutorReportData> elements) {
		List<LivrosPorAutorView> livrosPorAutor = elements.stream().map(this.livrosPorAutorViewMapper::map).toList();
		
		var livrosPorAutorReportDataView = new LivrosPorAutorReportDataView();
		var autor = new AutorView(elements.get(0).getCodAu(), elements.get(0).getNome());

		livrosPorAutorReportDataView.setAutor(autor);
		livrosPorAutorReportDataView.setLivros(livrosPorAutor.get(0) == null ? null : livrosPorAutor);
		
		return livrosPorAutorReportDataView;
	}
}
