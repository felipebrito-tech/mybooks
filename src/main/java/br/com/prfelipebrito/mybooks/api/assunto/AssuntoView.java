package br.com.prfelipebrito.mybooks.api.assunto;

public class AssuntoView {

	private Integer codAs;
	private String descricao;

	public AssuntoView(Integer codAs, String descricao) {
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
