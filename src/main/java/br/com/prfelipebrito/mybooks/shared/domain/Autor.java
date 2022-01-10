package br.com.prfelipebrito.mybooks.shared.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codAu;

	@Column(length = 40)
	private String nome;
	
	public Autor() {}

	public Autor(String nome) {
		this(null, nome);
	}

	public Autor(Integer codAu, String nome) {
		this.codAu = codAu;
		this.nome = nome;
	}

	public Integer getCodAu() {
		return codAu;
	}

	public void setCodAu(Integer codAu) {
		this.codAu = codAu;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
