package br.com.prfelipebrito.mybooks.api.assunto;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;
import br.com.prfelipebrito.mybooks.shared.infra.ResourceNotFoundException;

@Service
public class AssuntoService {

	@Autowired
	private AssuntoRepository repository;

	@Autowired
	private AssuntoViewMapper viewMapper;

	@Autowired
	private AssuntoFormMapper formMapper;

	public List<AssuntoView> listAll() {
		return this.repository.findAll().stream().map(this.viewMapper::map).collect(toList());
	}

	@Transactional
	public AssuntoView save(AssuntoForm assuntoForm) {
		var assunto = this.formMapper.map(assuntoForm);
		
		this.repository.save(assunto);
		
		return this.viewMapper.map(assunto);
	}

	public AssuntoView details(Integer codAs) throws ResourceNotFoundException {
		Assunto assunto = this.repository.findByCodAs(codAs).orElseThrow(() -> new ResourceNotFoundException("Assunto não encontrado!"));

		return this.viewMapper.map(assunto);
	}

	@Transactional
	public AssuntoView update(Integer codAs, AssuntoForm assuntoForm) {
		Assunto assunto = this.repository.findByCodAs(codAs).orElseThrow(() -> new ResourceNotFoundException("Assunto não encontrado!"));
		
		assunto.setDescricao(assuntoForm.getDescricao());
		
		return this.viewMapper.map(assunto);
	}

	@Transactional
	public void removeBy(Integer codAs) {
		this.repository.findByCodAs(codAs).orElseThrow(() -> new ResourceNotFoundException("Assunto não encontrado!"));

		this.repository.deleteById(codAs);
	}
}
