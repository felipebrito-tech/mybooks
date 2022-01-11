package br.com.prfelipebrito.mybooks.api.reports;

public class LivrosPorAutorView {
	
	private Integer codL;

	private String titulo;

	private String editora;

	private Integer edicao;

	private String anoPublicacao;
	
	private String assuntos;

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

	public String getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(String assuntos) {
		this.assuntos = assuntos;
	}
}
