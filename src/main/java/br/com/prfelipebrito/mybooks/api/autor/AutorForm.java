package br.com.prfelipebrito.mybooks.api.autor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AutorForm {

	@NotNull @NotEmpty @Length(min = 1, max = 40)
	private String nome;
	
	public AutorForm() {}

	public AutorForm(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
