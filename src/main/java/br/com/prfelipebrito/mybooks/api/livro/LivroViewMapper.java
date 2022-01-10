package br.com.prfelipebrito.mybooks.api.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.prfelipebrito.mybooks.api.assunto.AssuntoViewMapper;
import br.com.prfelipebrito.mybooks.api.autor.AutorViewMapper;
import br.com.prfelipebrito.mybooks.shared.domain.Livro;
import br.com.prfelipebrito.mybooks.shared.infra.Mapper;

@Component
public class LivroViewMapper implements Mapper<Livro, LivroView> {

	@Autowired
	private AutorViewMapper autorViewMapper;

	@Autowired
	private AssuntoViewMapper assuntoViewMapper;

	@Override
	public LivroView map(Livro source) {
		var livroView = new LivroView();
		livroView.setCodL(source.getCodL());
		livroView.setTitulo(source.getTitulo());
		livroView.setEdicao(source.getEdicao());
		livroView.setEditora(source.getEditora());
		livroView.setAnoPublicacao(source.getAnoPublicacao());
		livroView.setAutores(source.getAutores().stream().map(this.autorViewMapper::map).toList());
		livroView.setAssuntos(source.getAssuntos().stream().map(this.assuntoViewMapper::map).toList());
		
		return livroView;
	}
}
