package br.com.prfelipebrito.mybooks.shared.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "livro")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codL;

	@Column(length = 40)
	private String titulo;

	@Column(length = 40)
	private String editora;

	private Integer edicao;

	@Column(length = 4)
	private String anoPublicacao;

	@ManyToMany
	@JoinTable(name = "livro_autor",
				joinColumns = {@JoinColumn(referencedColumnName = "codL")},
				inverseJoinColumns = {@JoinColumn(referencedColumnName = "codAu")})
	private List<Autor> autores;

	@ManyToMany
	@JoinTable(name = "livro_assunto",
				joinColumns = {@JoinColumn(referencedColumnName = "codL")},
				inverseJoinColumns = {@JoinColumn(referencedColumnName = "codAs")})
	private List<Assunto> assuntos;
	
	public Livro() {}

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

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}
}
