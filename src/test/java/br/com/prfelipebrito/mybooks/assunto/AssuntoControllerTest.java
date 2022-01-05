package br.com.prfelipebrito.mybooks.assunto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;

public class AssuntoControllerTest extends MybooksApplicationTests {
	
	@Test
	public void whenList_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/assuntos")).andExpect(status().isOk());
	}

}
