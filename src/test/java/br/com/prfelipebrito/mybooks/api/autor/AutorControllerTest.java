package br.com.prfelipebrito.mybooks.api.autor;

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
import br.com.prfelipebrito.mybooks.shared.domain.Autor;

class AutorControllerTest extends MybooksApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	private Autor autorCreated;

    @BeforeEach
    void setup() {
        autorCreated = this.entityManager.persist(new Autor("Felipe Brito"));
    }
	
	@Test
	public void whenList_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/api/autores"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].nome", equalTo("Felipe Brito")));;
	}
	
	@Test
	public void whenSaving_thenReturns201() throws Exception {
		var autorForm = new AutorForm("Felipe Brito");

		String request = this.objectMapper.writeValueAsString(autorForm);
		
		this.mockMvc.perform(post("/api/autores").header("Content-Type", "application/json").content(request))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.nome", equalTo("Felipe Brito")));
	}
	
	@Test
	public void whenSavingWithEmptyNome_thenReturns400() throws Exception {
		AutorForm autorForm = new AutorForm("");

		String request = this.objectMapper.writeValueAsString(autorForm);
		
		this.mockMvc.perform(post("/api/autores").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithNullNome_thenReturns400() throws Exception {
		AutorForm autorForm = new AutorForm(null);

		String request = this.objectMapper.writeValueAsString(autorForm);
		
		this.mockMvc.perform(post("/api/autores").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithBiggerNome_thenReturns400() throws Exception {
		AutorForm autorForm = new AutorForm("Pedro de Alcântara Francisco Antônio João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim de Bragança e Bourbon");

		String request = this.objectMapper.writeValueAsString(autorForm);
		
		this.mockMvc.perform(post("/api/autores").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}

	@Test
	public void whenDetails_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/api/autores/{codAu}", this.autorCreated.getCodAu()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nome", equalTo("Felipe Brito")));
	}

	@Test
	public void whenDetailsAndAutorIsNotFound_thenReturns404() throws Exception {
		this.mockMvc.perform(get("/api/autores/{codAu}", 999))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenDetailsAndCodAuIsAString_thenReturns400() throws Exception {
		this.mockMvc.perform(get("/api/autores/teste"))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codAu")));
	}
	
	@Test
	public void whenUpdating_thenReturns200() throws Exception {
		var autorForm = new AutorForm("Felipe Brito");
		
		String request = this.objectMapper.writeValueAsString(autorForm);
		
		this.mockMvc.perform(put("/api/autores/{codAu}", this.autorCreated.getCodAu()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isOk());

		this.mockMvc.perform(get("/api/autores/{codAu}", this.autorCreated.getCodAu()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.nome", equalTo("Felipe Brito")));
	}

	@Test
	public void whenUpdatingAndAutorIsNotFound_thenReturns404() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AutorForm("Felipe Brito"));
		
		this.mockMvc.perform(put("/api/autores/{codAu}", 9999).header("Content-Type", "application/json").content(request))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenUpdatingAndCodAuIsAString_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AutorForm("Felipe Brito"));
		
		this.mockMvc.perform(put("/api/autores/teste").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codAu")));
	}
	
	@Test
	public void whenUpdatingWithEmptyNome_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AutorForm(""));
		
		this.mockMvc.perform(put("/api/autores/{codAu}", this.autorCreated.getCodAu()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdatingWithNullNome_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AutorForm(null));
		
		this.mockMvc.perform(put("/api/autores/{codAu}", this.autorCreated.getCodAu()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenUpdatingWithBiggerNome_thenReturns400() throws Exception {
		String request = this.objectMapper.writeValueAsString(new AutorForm("Pedro de Alcântara Francisco Antônio João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim de Bragança e Bourbon"));
		
		this.mockMvc.perform(put("/api/autores/{codAu}", this.autorCreated.getCodAu()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenDeleting_thenReturns200() throws Exception {
		this.mockMvc.perform(delete("/api/autores/{codAu}", this.autorCreated.getCodAu())).andExpect(status().isOk());
	}

	@Test
	public void whenDeletingAndAssuntoIsNotFound_thenReturns404() throws Exception {
		this.mockMvc.perform(delete("/api/autores/{codAu}", 9999))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenDeletingAndCodAuIsAString_thenReturns400() throws Exception {
		this.mockMvc.perform(delete("/api/autores/teste"))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codAu")));
	}
}