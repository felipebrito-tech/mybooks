package br.com.prfelipebrito.mybooks.assunto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
	public void whenList_theReturnsAVoidAssuntoList() {
		List<AssuntoView> assuntos = this.service.listAll();

		assertNotNull(assuntos);
		assertEquals(0, assuntos.size());
	}

	@Test
	public void whenList_theReturnsAllAssuntos() {
        this.entityManager.persist(new Assunto(null, "Tecnologia"));
        this.entityManager.persist(new Assunto(null, "Psicologia"));
        this.entityManager.persist(new Assunto(null, "Economia"));

		List<AssuntoView> assuntos = this.service.listAll();

		assertNotNull(assuntos);
		assertEquals(3, assuntos.size());
	}
	
	@Test
	public void whenSaving_thenReturnsCreatedAssuntoView() throws Exception {
		AssuntoForm assuntoForm = new AssuntoForm("Ficção");
		
		AssuntoView createdAssuntoView = this.service.save(assuntoForm);
		
		assertNotNull(createdAssuntoView);
		assertNotNull(createdAssuntoView.getCodAs());
		assertEquals("Ficção", createdAssuntoView.getDescricao());
	}
	
	@Test
	public void whenUpdating_thenReturns200() throws Exception {
        var codAs = this.entityManager.persist(new Assunto(null, "Tecnologia")).getCodAs();
		var assuntoForm = new AssuntoForm("Mundo Tech");
		
		AssuntoView updatedAssuntoView = this.service.update(codAs, assuntoForm);
		
		assertEquals(codAs, updatedAssuntoView.getCodAs());
		assertEquals(assuntoForm.getDescricao(), updatedAssuntoView.getDescricao());
	}
}
