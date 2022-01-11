package br.com.prfelipebrito.mybooks.api.reports;

import java.util.List;

import br.com.prfelipebrito.mybooks.api.autor.AutorView;

public class LivrosPorAutorReportDataView {

	private AutorView autor;

	private List<LivrosPorAutorView> livros;

	public AutorView getAutor() {
		return autor;
	}

	public void setAutor(AutorView autor) {
		this.autor = autor;
	}

	public List<LivrosPorAutorView> getLivros() {
		return livros;
	}

	public void setLivros(List<LivrosPorAutorView> livros) {
		this.livros = livros;
	}
}
