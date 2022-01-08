package br.com.prfelipebrito.mybooks.api.autor;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prfelipebrito.mybooks.shared.domain.Autor;
import br.com.prfelipebrito.mybooks.shared.infra.ResourceNotFoundException;

@Service
public class AutorService {

	@Autowired
	private AutorRepository repository;

	@Autowired
	private AutorViewMapper viewMapper;

	@Autowired
	private AutorFormMapper formMapper;

	public List<AutorView> listAll() {
		return this.repository.findAll().stream().map(this.viewMapper::map).collect(toList());
	}

	@Transactional
	public AutorView save(AutorForm autorForm) {
		var autor = this.formMapper.map(autorForm);
		
		this.repository.save(autor);
		
		return this.viewMapper.map(autor);
	}

	public AutorView details(Integer codAu) throws ResourceNotFoundException {
		Autor autor = this.repository.findByCodAu(codAu).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));

		return this.viewMapper.map(autor);
	}

	@Transactional
	public AutorView update(Integer codAu, AutorForm autorForm) {
		Autor autor = this.repository.findByCodAu(codAu).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));
		
		autor.setNome(autorForm.getNome());
		
		return this.viewMapper.map(autor);
	}

	@Transactional
	public void removeBy(Integer codAu) {
		this.repository.findByCodAu(codAu).orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));

		this.repository.deleteById(codAu);
	}
}
