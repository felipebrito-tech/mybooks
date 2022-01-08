package br.com.prfelipebrito.mybooks.assunto;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.api.assunto.AssuntoForm;
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
		AssuntoForm assuntoForm = new AssuntoForm("Ficção");

		String request = this.objectMapper.writeValueAsString(assuntoForm);
		
		this.mockMvc.perform(post("/assuntos").header("Content-Type", "application/json").content(request))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.descricao", equalTo("Ficção")));
	}
	
	@Test
	public void whenSavingWithEmptyDescricao_thenReturns400() throws Exception {
		AssuntoForm assuntoForm = new AssuntoForm("");

		String request = this.objectMapper.writeValueAsString(assuntoForm);
		
		this.mockMvc.perform(post("/assuntos").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithNullDescricao_thenReturns400() throws Exception {
		AssuntoForm assuntoForm = new AssuntoForm(null);

		String request = this.objectMapper.writeValueAsString(assuntoForm);
		
		this.mockMvc.perform(post("/assuntos").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithBiggerDescricao_thenReturns400() throws Exception {
		AssuntoForm assuntoForm = new AssuntoForm("Desenvolvimento Pessoal");

		String request = this.objectMapper.writeValueAsString(assuntoForm);
		
		this.mockMvc.perform(post("/assuntos").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}

	@Test
	public void whenDetails_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/assuntos/{codAs}", this.assuntoCreated.getCodAs()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.descricao", equalTo("Tecnologia")));
	}

	@Test
	public void whenDetailsAndAssuntoIsNotFound_thenReturns404() throws Exception {
		this.mockMvc.perform(get("/assuntos/{codAs}", 999))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenDetailsAndCodAsIsAString_thenReturns400() throws Exception {
		this.mockMvc.perform(get("/assuntos/teste"))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codAs")));
	}
	
	@Test
	public void whenUpdating_thenReturns200() throws Exception {
		var assuntoForm = new AssuntoForm("Mundo Tech");
		
		String request = this.objectMapper.writeValueAsString(assuntoForm);
		
		this.mockMvc.perform(put("/assuntos/{codAs}", this.assuntoCreated.getCodAs()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isOk());

		this.mockMvc.perform(get("/assuntos/{codAs}", this.assuntoCreated.getCodAs()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.descricao", equalTo("Mundo Tech")));
	}

	@Test
	public void whenUpdatingAndAssuntoIsNotFound_thenReturns404() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AssuntoForm("Mundo Tech"));
		
		this.mockMvc.perform(put("/assuntos/{codAs}", 9999).header("Content-Type", "application/json").content(request))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenUpdatingAndCodAsIsAString_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AssuntoForm("Mundo Tech"));
		
		this.mockMvc.perform(put("/assuntos/teste").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codAs")));
	}
	
	@Test
	public void whenUpdatingWithEmptyDescricao_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AssuntoForm(""));
		
		this.mockMvc.perform(put("/assuntos/{codAs}", this.assuntoCreated.getCodAs()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdatingWithNullDescricao_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AssuntoForm(null));
		
		this.mockMvc.perform(put("/assuntos/{codAs}", this.assuntoCreated.getCodAs()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdatingWithBiggerDescricao_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AssuntoForm("Desenvolvimento Pessoal"));
		
		this.mockMvc.perform(put("/assuntos/{codAs}", this.assuntoCreated.getCodAs()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenDeleting_thenReturns200() throws Exception {
		this.mockMvc.perform(delete("/assuntos/{codAs}", this.assuntoCreated.getCodAs())).andExpect(status().isOk());
	}

	@Test
	public void whenDeletingAndAssuntoIsNotFound_thenReturns404() throws Exception {
		this.mockMvc.perform(delete("/assuntos/{codAs}", 9999))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenDeletingAndCodAsIsAString_thenReturns400() throws Exception {
		this.mockMvc.perform(delete("/assuntos/teste"))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codAs")));
	}
}
