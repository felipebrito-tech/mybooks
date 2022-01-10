package br.com.prfelipebrito.mybooks.api.livro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.domain.Livro;
import br.com.prfelipebrito.mybooks.shared.infra.ResourceNotFoundException;

class LivroServiceTest extends MybooksApplicationTests {

	@Autowired
	private LivroService service;

	private ArrayList<Assunto> assuntos;

	private List<Autor> autores;

	@Test
	public void whenList_thenReturnsAVoidAutoresList() {
		List<LivroView> livros = this.service.listAll();

		assertNotNull(livros);
		assertEquals(0, livros.size());
	}

	@Test
	public void whenList_thenReturnsAllLivros() {
		this.persistNewLivro("Essencialismo", "2015");
		this.persistNewLivro("Sem esforço", "2021");

		List<LivroView> livros = this.service.listAll();

		assertNotNull(livros);
		assertEquals(2, livros.size());
	}

	@Test
	public void whenSaving_thenReturnsCreatedLivroView() {
		var livroForm = this.buildLivroForm("Essencialismo", "2015");

		LivroView createdLivroView = this.service.save(livroForm);

		assertNotNull(createdLivroView);
		assertNotNull(createdLivroView.getCodL());
		assertEquals("Essencialismo", createdLivroView.getTitulo());
	}

	@Test
	public void whenDetails_thenShowsLivroDetails() {
        var codL = this.persistNewLivro("Essencialismo", "2015").getCodL();
        
        LivroView livroView = this.service.details(codL);

        assertNotNull(livroView);
        assertEquals(codL, livroView.getCodL());
        assertEquals("Essencialismo", livroView.getTitulo());
	}

	@Test
	public void whenDetailsAndLivroIsNotFound_thenThrowsResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class, () -> this.service.details(9999));
	}
	
	@Test
	public void whenUpdating_thenReturnsUpdatedLivroView() {
        var codL = this.persistNewLivro("Essencialismo", "2015").getCodL();
		var livroForm = this.buildLivroForm("Sem esforço", "2021");
		
		LivroView updatedLivroView = this.service.update(codL, livroForm);
		
		assertEquals(codL, updatedLivroView.getCodL());
		assertEquals(livroForm.getTitulo(), updatedLivroView.getTitulo());
	}
	
	@Test
	public void whenUpdatingAndLivroIsNotFound_thenThrowsResourceNotFoundException() {
		var livroForm = this.buildLivroForm("Sem esforço", "2021");

		assertThrows(ResourceNotFoundException.class,
						() -> this.service.update(9999, livroForm));
	}
	
	@Test
	public void whenDeleting_thenSucceed() {
		var codL = this.persistNewLivro("Essencialismo", "2015").getCodL();

        this.service.removeBy(codL);
        
        assertNull(this.entityManager.find(Livro.class, codL));
	}
	
	@Test
	public void whenDeletingAndAutorIsNotFound_thenThrowsResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class,
						() -> this.service.removeBy(9999));
	}

	private Livro persistNewLivro(String titulo, String anoPublicacao) {
		this.setUp();

		var livro = new Livro(titulo, "Editora Sextante", 1, anoPublicacao, this.autores, this.assuntos);

		return this.entityManager.persist(livro);
	}

	private LivroForm buildLivroForm(String titulo, String anoPublicacao) {
		this.setUp();

		var codAuList = this.autores.stream().map(autor -> autor.getCodAu()).toList();

		var codAsList = this.assuntos.stream().map(assunto -> assunto.getCodAs()).toList();

		return new LivroForm(titulo, "Editora Sextante", 1, anoPublicacao, codAuList, codAsList);
	}

	private void setUp() {
		if (autores == null)
			this.autores = Arrays.asList(this.entityManager.persist(new Autor("Greg Mckeown")));

		if (this.assuntos == null) {
			this.assuntos = new ArrayList<Assunto>();
			this.assuntos.add(this.entityManager.persist(new Assunto("Administração")));
			this.assuntos.add(this.entityManager.persist(new Assunto("Negócios")));
			this.assuntos.add(this.entityManager.persist(new Assunto("Economia")));
		}
	}
}
