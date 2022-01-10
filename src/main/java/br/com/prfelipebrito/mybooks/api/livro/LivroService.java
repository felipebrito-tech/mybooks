package br.com.prfelipebrito.mybooks.api.livro;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prfelipebrito.mybooks.api.assunto.AssuntoService;
import br.com.prfelipebrito.mybooks.api.autor.AutorService;
import br.com.prfelipebrito.mybooks.shared.domain.Livro;
import br.com.prfelipebrito.mybooks.shared.infra.ResourceNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private AssuntoService assuntoService;
	
	@Autowired
	private AutorService autorService;

	@Autowired
	private LivroViewMapper viewMapper;

	@Autowired
	private LivroFormMapper formMapper;

	public List<LivroView> listAll() {
		return this.repository.findAll().stream().map(this.viewMapper::map).collect(toList());
	}

	@Transactional
	public LivroView save(LivroForm livroForm) throws ResourceNotFoundException {
		var livro = this.formMapper.map(livroForm);
		
		var assuntos = livro.getAssuntos()
							.stream()
							.map(assunto -> this.assuntoService.findByCodAs(assunto.getCodAs()))
							.toList();
		
		var autores = livro.getAutores()
							.stream()
							.map(autor -> this.autorService.findByCodAu(autor.getCodAu()))
							.toList();
		
		livro.setAssuntos(assuntos);
		livro.setAutores(autores);
		
		this.repository.save(livro);
		
		return this.viewMapper.map(livro);
	}

	public Livro findByCodL(Integer codL) throws ResourceNotFoundException {
		return this.repository.findByCodL(codL).orElseThrow(() -> new ResourceNotFoundException(String.join(" ", "Livro não encontrado! -> codL =", codL.toString())));
	}

	public LivroView details(Integer codL) throws ResourceNotFoundException {
		Livro livro = this.findByCodL(codL);

		return this.viewMapper.map(livro);
	}

	@Transactional
	public LivroView update(Integer codL, LivroForm livroForm) throws ResourceNotFoundException {
		Livro livro = this.findByCodL(codL);

		livro.setTitulo(livroForm.getTitulo());
		livro.setEditora(livroForm.getEditora());
		livro.setEdicao(livroForm.getEdicao());
		livro.setAnoPublicacao(livroForm.getAnoPublicacao());

		var assuntos = livroForm.getAssuntos()
								.stream()
								.map(assuntoCodAs -> this.assuntoService.findByCodAs(assuntoCodAs))
								.toList();
		
		livro.setAssuntos(assuntos);

		var autores = livroForm.getAutores()
								.stream()
								.map(autorCodAu -> this.autorService.findByCodAu(autorCodAu))
								.toList();

		livro.setAutores(autores);

		return this.viewMapper.map(livro);
	}

	@Transactional
	public void removeBy(Integer codL) {
		this.findByCodL(codL);

		this.repository.deleteById(codL);
	}
}
