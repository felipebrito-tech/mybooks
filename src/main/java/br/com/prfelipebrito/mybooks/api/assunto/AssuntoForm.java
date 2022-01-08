package br.com.prfelipebrito.mybooks.api.assunto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AssuntoForm {

	@NotNull @NotEmpty @Length(min = 1, max = 20)
	private String descricao;

	public AssuntoForm() {}
	
	public AssuntoForm(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
