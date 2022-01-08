package br.com.prfelipebrito.mybooks.assunto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoForm;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoService;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoView;
import br.com.prfelipebrito.mybooks.shared.domain.Assunto;

@Transactional
public class AssuntoServiceTest extends MybooksApplicationTests {

	@Autowired
	private AssuntoService service;

	@Test
	public void whenList_thenReturnsAVoidAssuntoList() {
		List<AssuntoView> assuntos = this.service.listAll();

		assertNotNull(assuntos);
		assertEquals(0, assuntos.size());
	}

	@Test
	public void whenList_thenReturnsAllAssuntos() {
		this.persistNewAssunto("Tecnologia");
		this.persistNewAssunto("Psicologia");
		this.persistNewAssunto("Economia");

		List<AssuntoView> assuntos = this.service.listAll();

		assertNotNull(assuntos);
		assertEquals(3, assuntos.size());
	}
	
	@Test
	public void whenSaving_thenReturnsCreatedAssuntoView() throws Exception {
		var assuntoForm = new AssuntoForm("Ficção");
		
		AssuntoView createdAssuntoView = this.service.save(assuntoForm);
		
		assertNotNull(createdAssuntoView);
		assertNotNull(createdAssuntoView.getCodAs());
		assertEquals("Ficção", createdAssuntoView.getDescricao());
	}

	@Test
	public void whenDetails_thenShowsAssuntoDetails() throws Exception {
        var codAs = this.persistNewAssunto("Tecnologia").getCodAs();
        
        AssuntoView assuntoView = this.service.details(codAs);

        assertNotNull(assuntoView);
        assertEquals(codAs, assuntoView.getCodAs());
        assertEquals("Tecnologia", assuntoView.getDescricao());
	}
	
	@Test
	public void whenUpdating_thenReturnsUpdatedAssuntoView() throws Exception {
        var codAs = this.persistNewAssunto("Tecnologia").getCodAs();
		var assuntoForm = new AssuntoForm("Mundo Tech");
		
		AssuntoView updatedAssuntoView = this.service.update(codAs, assuntoForm);
		
		assertEquals(codAs, updatedAssuntoView.getCodAs());
		assertEquals(assuntoForm.getDescricao(), updatedAssuntoView.getDescricao());
	}
	
	@Test
	public void whenDeleting_thenSucceed() throws Exception {
        var codAs = this.persistNewAssunto("Tecnologia").getCodAs();

        this.service.removeBy(codAs);
        
        assertNull(this.entityManager.find(Assunto.class, codAs));
	}

	private Assunto persistNewAssunto(String descricao) {
		return this.entityManager.persist(new Assunto(null, descricao));
	}
}
