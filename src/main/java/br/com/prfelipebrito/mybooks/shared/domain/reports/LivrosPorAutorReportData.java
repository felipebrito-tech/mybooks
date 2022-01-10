package br.com.prfelipebrito.mybooks.shared.domain.reports;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;


@Entity
@Immutable
public class LivrosPorAutorReportData {

	@Id
	private Long id;
	
	private Integer codAu;
	
	private String nome;
	
	private Integer codL;

	private String titulo;

	private String editora;

	private Integer edicao;

	private String anoPublicacao;
	
	private String assuntos;

	public Long getId() {
		return id;
	}

	public Integer getCodAu() {
		return codAu;
	}

	public String getNome() {
		return nome;
	}

	public Integer getCodL() {
		return codL;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getEditora() {
		return editora;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public String getAssuntos() {
		return assuntos;
	}
}
