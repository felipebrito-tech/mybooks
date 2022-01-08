package br.com.prfelipebrito.mybooks.api.autor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.infra.ResourceNotFoundException;

class AutorServiceTest extends MybooksApplicationTests {

	@Autowired
	private AutorService service;

	@Test
	public void whenList_thenReturnsAVoidAutoresList() {
		List<AutorView> autores = this.service.listAll();

		assertNotNull(autores);
		assertEquals(0, autores.size());
	}

	@Test
	public void whenList_thenReturnsAllAutores() {
		this.persistNewAutor("Felipe Brito");
		this.persistNewAutor("Luciano S.");
		this.persistNewAutor("Uncle Bob");

		List<AutorView> autores = this.service.listAll();

		assertNotNull(autores);
		assertEquals(3, autores.size());
	}
	
	@Test
	public void whenSaving_thenReturnsCreatedAutorView() {
		var autorForm = new AutorForm("Felipe Brito");
		
		AutorView createdAutorView = this.service.save(autorForm);
		
		assertNotNull(createdAutorView);
		assertNotNull(createdAutorView.getCodAu());
		assertEquals("Felipe Brito", createdAutorView.getNome());
	}

	@Test
	public void whenDetails_thenShowsAutorDetails() {
        var codAu = this.persistNewAutor("Felipe Brito").getCodAu();
        
        AutorView autorView = this.service.details(codAu);

        assertNotNull(autorView);
        assertEquals(codAu, autorView.getCodAu());
        assertEquals("Felipe Brito", autorView.getNome());
	}

	@Test
	public void whenDetailsAndAutorIsNotFound_thenThrowsResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class, () -> this.service.details(9999));
	}
	
	@Test
	public void whenUpdating_thenReturnsUpdatedAutorView() {
        var codAu = this.persistNewAutor("Felipe Brito").getCodAu();
		var autorForm = new AutorForm("Felipe Brito - Tech");
		
		AutorView updatedAutorView = this.service.update(codAu, autorForm);
		
		assertEquals(codAu, updatedAutorView.getCodAu());
		assertEquals(autorForm.getNome(), updatedAutorView.getNome());
	}
	
	@Test
	public void whenUpdatingAndAutorIsNotFound_thenThrowsResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class,
						() -> this.service.update(9999, new AutorForm("Felipe Brito")));
	}
	
	@Test
	public void whenDeleting_thenSucceed() {
        var codAu = this.persistNewAutor("Felipe Brito").getCodAu();

        this.service.removeBy(codAu);
        
        assertNull(this.entityManager.find(Autor.class, codAu));
	}
	
	@Test
	public void whenDeletingAndAutorIsNotFound_thenThrowsResourceNotFoundException() {
		assertThrows(ResourceNotFoundException.class,
						() -> this.service.removeBy(9999));
	}

	private Autor persistNewAutor(String nome) {
		return this.entityManager.persist(new Autor(nome));
	}
}
