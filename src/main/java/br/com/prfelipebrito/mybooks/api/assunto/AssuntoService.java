package br.com.prfelipebrito.mybooks.api.assunto;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

	public Assunto findByCodAs(Integer codAs) throws ResourceNotFoundException {
		return this.repository.findByCodAs(codAs).orElseThrow(() -> new ResourceNotFoundException(String.join(" ", "Assunto não encontrado! -> codAs =", codAs.toString())));
	}

	public AssuntoView details(Integer codAs) throws ResourceNotFoundException {
		Assunto assunto = this.findByCodAs(codAs);

		return this.viewMapper.map(assunto);
	}

	@Transactional
	public AssuntoView update(Integer codAs, AssuntoForm assuntoForm) throws ResourceNotFoundException {
		Assunto assunto = this.findByCodAs(codAs);
		
		assunto.setDescricao(assuntoForm.getDescricao());
		
		return this.viewMapper.map(assunto);
	}

	@Transactional
	public void removeBy(Integer codAs) throws DataIntegrityViolationException {
		this.findByCodAs(codAs);

		this.repository.deleteById(codAs);
	}
}
