package br.com.prfelipebrito.mybooks.api.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prfelipebrito.mybooks.shared.domain.reports.LivrosPorAutorReportData;

@Service
public class LivrosPorAutorReportService {

	@Autowired
	private LivrosPorAutorReportRepository repository;
	
	public List<LivrosPorAutorReportData> listAll() {
		return this.repository.findAll();
	}
}
