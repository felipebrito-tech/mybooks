package br.com.prfelipebrito.mybooks.assunto;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoCreateForm;
import br.com.prfelipebrito.mybooks.shared.domain.Assunto;

public class AssuntoControllerTest extends MybooksApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;
	private Assunto assuntoCreated;

    @BeforeEach
    void setup() {
        assuntoCreated = this.entityManager.persist(new Assunto(null, "Tecnologia"));
    }
	
	@Test
	public void whenList_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/assuntos"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].descricao", equalTo("Tecnologia")));;
	}
	
	@Test
	public void whenSaving_thenReturns201() throws Exception {
		AssuntoCreateForm assuntoForm = new AssuntoCreateForm("Ficção");

		String request = this.objectMapper.writeValueAsString(assuntoForm);
		
		this.mockMvc.perform(post("/assuntos").header("Content-Type", "application/json").content(request))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.descricao", equalTo("Ficção")));
	}

	@Test
	public void whenDetails_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/assuntos/{codAs}", assuntoCreated.getCodAs()))
        			.andDo(log())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.descricao", equalTo("Tecnologia")));
	}
}
