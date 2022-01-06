package br.com.prfelipebrito.mybooks.api.assunto;

public class AssuntoCreateForm {

	private String descricao;

	public AssuntoCreateForm() {}
	
	public AssuntoCreateForm(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
