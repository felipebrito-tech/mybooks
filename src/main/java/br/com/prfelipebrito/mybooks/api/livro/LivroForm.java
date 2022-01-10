package br.com.prfelipebrito.mybooks.api.livro;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LivroForm {

	@NotNull @NotEmpty @Length(min = 1, max = 40)
	private String titulo;

	@NotNull @NotEmpty @Length(min = 1, max = 40)
	private String editora;

	@NotNull
	private Integer edicao;

	@NotNull @NotEmpty @Length(min = 4, max = 4)
	private String anoPublicacao;

	@NotNull @NotEmpty
	private List<Integer> autores;

	@NotNull @NotEmpty
	private List<Integer> assuntos;
	
	public LivroForm() {}

	public LivroForm(String titulo, String editora, Integer edicao, String anoPublicacao, List<Integer> autores,
			List<Integer> assuntos) {
		this.titulo = titulo;
		this.editora = editora;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
		this.autores = autores;
		this.assuntos = assuntos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public List<Integer> getAutores() {
		return autores;
	}

	public void setAutores(List<Integer> autores) {
		this.autores = autores;
	}

	public List<Integer> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Integer> assuntos) {
		this.assuntos = assuntos;
	}
}
