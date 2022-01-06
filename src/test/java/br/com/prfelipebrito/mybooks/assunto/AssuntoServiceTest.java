package br.com.prfelipebrito.mybooks.assunto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoCreateForm;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoService;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoView;

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
		this.service.save(new AssuntoCreateForm("Economia"));
		this.service.save(new AssuntoCreateForm("Tecnologia"));
		this.service.save(new AssuntoCreateForm("Psicologia"));
		
		List<AssuntoView> assuntos = this.service.listAll();

		assertNotNull(assuntos);
		assertEquals(3, assuntos.size());
	}
	
	@Test
	public void whenSaving_thenReturnsCreatedAssuntoView() throws Exception {
		AssuntoCreateForm assuntoForm = new AssuntoCreateForm("Ficção");
		AssuntoView assuntoView = new AssuntoView(1, "Ficção");
		
		AssuntoView createdAssuntoView = this.service.save(assuntoForm);
		
		assertEquals(assuntoView, createdAssuntoView);
	}
}
