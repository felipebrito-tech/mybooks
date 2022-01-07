package br.com.prfelipebrito.mybooks.api.assunto;

public class AssuntoForm {

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
