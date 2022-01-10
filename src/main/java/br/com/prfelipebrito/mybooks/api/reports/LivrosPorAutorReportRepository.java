package br.com.prfelipebrito.mybooks.api.reports;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prfelipebrito.mybooks.shared.domain.reports.LivrosPorAutorReportData;

public interface LivrosPorAutorReportRepository extends JpaRepository<LivrosPorAutorReportData, Long> {

}
