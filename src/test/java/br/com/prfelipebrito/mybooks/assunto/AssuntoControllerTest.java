package br.com.prfelipebrito.mybooks.assunto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoForm;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoView;

public class AssuntoControllerTest extends MybooksApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void whenList_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/assuntos")).andExpect(status().isOk());
	}
	
	@Test
	public void whenSaving_thenReturns201() throws Exception {
		AssuntoForm assuntoForm = new AssuntoForm("Ficção");
		AssuntoView assuntoView = new AssuntoView(1, "Ficção");

		String request = this.objectMapper.writeValueAsString(assuntoForm);
		String response = this.objectMapper.writeValueAsString(assuntoView);
		
		MvcResult result = this.mockMvc.perform(post("/assuntos").header("Content-Type", "application/json").content(request))
										.andExpect(status().isCreated())
										.andReturn();

		assertThat(response)
			.isEqualToIgnoringWhitespace(result.getResponse().getContentAsString());
	}

}
