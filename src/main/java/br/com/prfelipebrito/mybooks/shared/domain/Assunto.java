package br.com.prfelipebrito.mybooks.shared.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="assunto")
public class Assunto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codAs;

	@Column(length = 20)
	private String descricao;

	public Assunto() {}
	
	public Assunto(String descricao) {
		this(null, descricao);		
	}

	public Assunto(Integer codAs, String descricao) {
		this.codAs = codAs;
		this.descricao = descricao;
	}

	public Integer getCodAs() {
		return codAs;
	}

	public void setCodAs(Integer codAs) {
		this.codAs = codAs;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
