package br.com.prfelipebrito.mybooks.api.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivrosPorAutorReportService {

	@Autowired
	private LivrosPorAutorReportRepository repository;

	@Autowired
	private LivrosPorAutorReportDataViewMapper viewMapper;

	public List<LivrosPorAutorReportDataView> listAll() {
		return this.viewMapper.map(this.repository.findAll());
	}
}
