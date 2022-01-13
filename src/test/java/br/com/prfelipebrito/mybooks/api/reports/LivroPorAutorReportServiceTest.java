package br.com.prfelipebrito.mybooks.api.reports;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.domain.Livro;
import br.com.prfelipebrito.mybooks.shared.domain.reports.LivrosPorAutorReportData;

class LivroPorAutorReportServiceTest extends MybooksApplicationTests {

	@Autowired
	private LivrosPorAutorReportService service;

	@Test
	public void whenList_thenReturnsAVoidList() {
		List<LivrosPorAutorReportData> reportDataList = this.service.listAll();

		assertNotNull(reportDataList);
		assertEquals(0, reportDataList.size());
	}

	@Test
	public void whenList_thenReturnsAllLivros() {
		this.setUp();

		var livrosPorAutorData = this.service.listAll();
		
		assertNotNull(livrosPorAutorData);
		assertEquals(8, livrosPorAutorData.size());

		var livrosGreg = this.getLivrosPorAutorBy(livrosPorAutorData, "Greg Mckeown");

		assertNotNull(livrosGreg);
		assertEquals(3, livrosGreg.size());

		var livrosFelipe = this.getLivrosPorAutorBy(livrosPorAutorData, "Felipe Brito");

		assertNotNull(livrosFelipe);
		assertEquals(1, livrosFelipe.size());
		assertNull(livrosFelipe.get(0).getTitulo());
	}

	private List<LivrosPorAutorReportData> getLivrosPorAutorBy(List<LivrosPorAutorReportData> livrosPorAutorData, String nomeAutor) {
		return livrosPorAutorData.stream().filter(livrosPorAutor -> livrosPorAutor.getNome().equals(nomeAutor)).toList();
	}

	private void setUp() {
		Autor greg = new Autor("Greg Mckeown");
		Autor duhigg = new Autor("Charles Duhigg");
		Autor lewis = new Autor("C. S. Lewis");
		Autor keller = new Autor("Timothy Keller");
		Autor eu = new Autor("Felipe Brito");
		Autor wiseman = new Autor("Liz Wiseman");

		this.entityManager.persist(greg);
		this.entityManager.persist(duhigg);
		this.entityManager.persist(lewis);
		this.entityManager.persist(keller);
		this.entityManager.persist(eu);
		this.entityManager.persist(wiseman);

		Assunto adm = new Assunto("Administração");
		Assunto negocios = new Assunto("Negócios");
		Assunto economia = new Assunto("Economia");
		Assunto teologia = new Assunto("Teologia Cristã");
		Assunto vidaCrista = new Assunto("Vida Cristã");
		Assunto lideranca = new Assunto("Liderança");

		this.entityManager.persist(adm);
		this.entityManager.persist(negocios);
		this.entityManager.persist(economia);
		this.entityManager.persist(teologia);
		this.entityManager.persist(vidaCrista);
		this.entityManager.persist(lideranca);

		Livro livro1 = new Livro("Essencialismo", "Editora Sextante", 1, "2015", Arrays.asList(greg), Arrays.asList(adm, negocios, economia));
		Livro livro2 = new Livro("Sem esforço", "Editora Sextante", 1, "2021", Arrays.asList(greg), Arrays.asList(adm, negocios, economia));
		Livro livro3 = new Livro("O poder do hábito", "Objetiva", 1, "2012", Arrays.asList(duhigg), Arrays.asList(adm, negocios, economia));
		Livro livro4 = new Livro("Cristianismo puro e simples", "Thomas Nelson Brasil", 1, "2017", Arrays.asList(lewis), Arrays.asList(teologia));
		Livro livro5 = new Livro("Ego transformado", "Vida Nova", 1, "2014", Arrays.asList(keller), Arrays.asList(vidaCrista));
		Livro livro6 = new Livro("Multiplicadores", "Rocco Digital", 1, "2012", Arrays.asList(greg, wiseman), Arrays.asList(lideranca, negocios, economia));
		
		this.entityManager.persist(livro1);
		this.entityManager.persist(livro2);
		this.entityManager.persist(livro3);
		this.entityManager.persist(livro4);
		this.entityManager.persist(livro6);
		this.entityManager.persist(livro5);
		
		this.entityManager.flush();
	}
}
