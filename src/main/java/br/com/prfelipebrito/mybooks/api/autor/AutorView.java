package br.com.prfelipebrito.mybooks.api.autor;

import java.util.Objects;

public class AutorView {

	private Integer codAu;
	
	private String nome;

	public AutorView(Integer codAu, String nome) {
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

	@Override
	public int hashCode() {
		return Objects.hash(codAu, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutorView other = (AutorView) obj;
		return Objects.equals(codAu, other.codAu) && Objects.equals(nome, other.nome);
	}
}
