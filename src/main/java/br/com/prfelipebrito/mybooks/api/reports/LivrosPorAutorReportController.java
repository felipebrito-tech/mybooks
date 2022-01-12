package br.com.prfelipebrito.mybooks.api.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livros-por-autor-report")
public class LivrosPorAutorReportController {

	@Autowired
	private LivrosPorAutorReportService service;

	@GetMapping
	public List<LivrosPorAutorReportDataView> list() {
		return this.service.listAll();
	}
}
