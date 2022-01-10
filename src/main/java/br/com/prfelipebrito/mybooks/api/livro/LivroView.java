package br.com.prfelipebrito.mybooks.api.livro;

import java.util.List;
import java.util.Objects;

import br.com.prfelipebrito.mybooks.api.assunto.AssuntoView;
import br.com.prfelipebrito.mybooks.api.autor.AutorView;

public class LivroView {

	private Integer codL;

	private String titulo;

	private String editora;

	private Integer edicao;

	private String anoPublicacao;

	private List<AutorView> autores;

	private List<AssuntoView> assuntos;
	
	public LivroView() {}

	public LivroView(Integer codL, String titulo, String editora, Integer edicao, String anoPublicacao,
			List<AutorView> autores, List<AssuntoView> assuntos) {
		this.codL = codL;
		this.titulo = titulo;
		this.editora = editora;
		this.edicao = edicao;
		this.anoPublicacao = anoPublicacao;
		this.autores = autores;
		this.assuntos = assuntos;
	}

	public Integer getCodL() {
		return codL;
	}

	public void setCodL(Integer codL) {
		this.codL = codL;
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

	public List<AutorView> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorView> autores) {
		this.autores = autores;
	}

	public List<AssuntoView> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<AssuntoView> assuntos) {
		this.assuntos = assuntos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoPublicacao, assuntos, autores, codL, edicao, editora, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivroView other = (LivroView) obj;
		return Objects.equals(anoPublicacao, other.anoPublicacao) && Objects.equals(assuntos, other.assuntos)
				&& Objects.equals(autores, other.autores) && Objects.equals(codL, other.codL)
				&& Objects.equals(edicao, other.edicao) && Objects.equals(editora, other.editora)
				&& Objects.equals(titulo, other.titulo);
	}
}
