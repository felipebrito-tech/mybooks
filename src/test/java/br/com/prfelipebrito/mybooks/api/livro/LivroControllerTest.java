package br.com.prfelipebrito.mybooks.api.livro;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.prfelipebrito.mybooks.MybooksApplicationTests;
import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.domain.Livro;

class LivroControllerTest extends MybooksApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;

	private Livro livroCreated;

	private List<Autor> autores;

	private ArrayList<Assunto> assuntos;

    @BeforeEach
    void setup() {
    	this.initData();
    	
        this.livroCreated = this.entityManager.persist(new Livro("Essencialismo", "Editora Sextante", 1, "2015", this.autores, this.assuntos));
    }
	
	@Test
	public void whenList_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/api/livros"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$[0].titulo", equalTo("Essencialismo")))
					.andExpect(jsonPath("$[0].editora", equalTo("Editora Sextante")))
					.andExpect(jsonPath("$[0].edicao", equalTo(1)))
					.andExpect(jsonPath("$[0].anoPublicacao", equalTo("2015")))
					.andExpect(jsonPath("$[0].autores[0].nome", equalTo("Greg Mckeown")))
					.andExpect(jsonPath("$[0].assuntos[0].descricao", equalTo("Administração")))
					.andExpect(jsonPath("$[0].assuntos[1].descricao", equalTo("Negócios")))
					.andExpect(jsonPath("$[0].assuntos[2].descricao", equalTo("Economia")));
	}
	
	@Test
	public void whenSaving_thenReturns201() throws Exception {		
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$.titulo", equalTo("Sem esforço")))
					.andExpect(jsonPath("$.editora", equalTo("Editora Sextante")))
					.andExpect(jsonPath("$.edicao", equalTo(1)))
					.andExpect(jsonPath("$.anoPublicacao", equalTo("2021")))
					.andExpect(jsonPath("$.autores[0].nome", equalTo("Greg Mckeown")))
					.andExpect(jsonPath("$.assuntos[0].descricao", equalTo("Administração")))
					.andExpect(jsonPath("$.assuntos[1].descricao", equalTo("Negócios")))
					.andExpect(jsonPath("$.assuntos[2].descricao", equalTo("Economia")));
	}
	
	@Test
	public void whenSavingWithEmptyTitulo_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm("", "Editora Sextante", 1, "2021");

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithNullTitulo_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm(null, "Editora Sextante", 1, "2021");

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithBiggerTitulo_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm("O Guia Definitivo Do Mochileiro Das Galaxias", "Editora Sextante", 1, "2021");

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithEmptyAssuntos_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");
		livroForm.setAssuntos(new ArrayList<Integer>());

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithNullAssuntos_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");
		livroForm.setAssuntos(null);

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithEmptyAutores_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");
		livroForm.setAutores(new ArrayList<Integer>());

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenSavingWithNullAutores_thenReturns400() throws Exception {
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");
		livroForm.setAutores(null);

		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(post("/api/livros").header("Content-Type", "application/json").content(request))
					.andExpect(status().isBadRequest());
	}

	@Test
	public void whenDetails_thenReturns200() throws Exception {
		this.mockMvc.perform(get("/api/livros/{codL}", this.livroCreated.getCodL()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.titulo", equalTo("Essencialismo")))
					.andExpect(jsonPath("$.editora", equalTo("Editora Sextante")))
					.andExpect(jsonPath("$.edicao", equalTo(1)))
					.andExpect(jsonPath("$.anoPublicacao", equalTo("2015")))
					.andExpect(jsonPath("$.autores[0].nome", equalTo("Greg Mckeown")))
					.andExpect(jsonPath("$.assuntos[0].descricao", equalTo("Administração")))
					.andExpect(jsonPath("$.assuntos[1].descricao", equalTo("Negócios")))
					.andExpect(jsonPath("$.assuntos[2].descricao", equalTo("Economia")));
	}

	@Test
	public void whenDetailsAndLivroIsNotFound_thenReturns404() throws Exception {
		this.mockMvc.perform(get("/api/livros/{codL}", 999))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenDetailsAndCodLIsAString_thenReturns400() throws Exception {
		this.mockMvc.perform(get("/api/livros/teste"))
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$[0].field", equalTo("codL")));
	}
	
	@Test
	public void whenUpdating_thenReturns200() throws Exception {
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");
		
		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(put("/api/livros/{codL}", this.livroCreated.getCodL()).header("Content-Type", "application/json").content(request))
					.andExpect(status().isOk());

		this.mockMvc.perform(get("/api/livros/{codL}", this.livroCreated.getCodL()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.titulo", equalTo("Sem esforço")))
					.andExpect(jsonPath("$.editora", equalTo("Editora Sextante")))
					.andExpect(jsonPath("$.edicao", equalTo(1)))
					.andExpect(jsonPath("$.anoPublicacao", equalTo("2021")))
					.andExpect(jsonPath("$.autores[0].nome", equalTo("Greg Mckeown")))
					.andExpect(jsonPath("$.assuntos[0].descricao", equalTo("Administração")))
					.andExpect(jsonPath("$.assuntos[1].descricao", equalTo("Negócios")))
					.andExpect(jsonPath("$.assuntos[2].descricao", equalTo("Economia")));
	}

	@Test
	public void whenUpdatingAndLivroIsNotFound_thenReturns404() throws Exception {
		var livroForm = this.buildLivroForm("Sem esforço", "Editora Sextante", 1, "2021");
	
		String request = this.objectMapper.writeValueAsString(livroForm);
		
		this.mockMvc.perform(put("/api/livros/{codL}", 9999).header("Content-Type", "application/json").content(request))
					.andExpect(status().isNotFound());
	}

	@Test
	public void whenDeleting_thenReturns200() throws Exception {
		this.mockMvc.perform(delete("/api/livros/{codL}", this.livroCreated.getCodL())).andExpect(status().isOk());
	}

	@Test
	public void whenDeletingAndAssuntoIsNotFound_thenReturns404() throws Exception {
		this.mockMvc.perform(delete("/api/livros/{codL}", 9999))
					.andExpect(status().isNotFound());
	}

	private void initData() {
		if (this.autores == null)
			this.autores = Arrays.asList(this.entityManager.persist(new Autor("Greg Mckeown")));

		if (this.assuntos == null) {
			this.assuntos = new ArrayList<Assunto>();
			this.assuntos.add(this.entityManager.persist(new Assunto("Administração")));
			this.assuntos.add(this.entityManager.persist(new Assunto("Negócios")));
			this.assuntos.add(this.entityManager.persist(new Assunto("Economia")));
		}
	}

	private LivroForm buildLivroForm(String titulo, String editora, Integer edicao, String anoPublicacao) {
		this.initData();

		var codAuList = this.autores.stream().map(autor -> autor.getCodAu()).toList();
		var codAsList = this.assuntos.stream().map(assunto -> assunto.getCodAs()).toList();

		var livroForm = new LivroForm(titulo, editora, edicao, anoPublicacao, codAuList, codAsList);

		return livroForm;
	}
}