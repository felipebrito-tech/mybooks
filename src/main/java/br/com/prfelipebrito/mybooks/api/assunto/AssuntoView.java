package br.com.prfelipebrito.mybooks.api.assunto;

import java.util.Objects;

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
	
	@Override
	public int hashCode() {
		return Objects.hash(codAs, descricao);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssuntoView other = (AssuntoView) obj;
		return Objects.equals(codAs, other.codAs) && Objects.equals(descricao, other.descricao);
	}
}
