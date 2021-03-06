package br.com.prfelipebrito.mybooks.api.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prfelipebrito.mybooks.shared.domain.reports.LivrosPorAutorReportData;

@RestController
@RequestMapping("/api/reports/livros-por-autor")
public class LivrosPorAutorReportController {

	@Autowired
	private LivrosPorAutorReportService service;

	@GetMapping
	public List<LivrosPorAutorReportData> list() {
		return this.service.listAll();
	}
}
