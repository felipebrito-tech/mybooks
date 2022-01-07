package br.com.prfelipebrito.mybooks.api.assunto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prfelipebrito.mybooks.shared.domain.Assunto;

@Service
public class AssuntoService {

	@Autowired
	private AssuntoRepository repository;

	@Autowired
	private AssuntoViewMapper viewMapper;

	@Autowired
	private AssuntoFormMapper formMapper;

	public List<AssuntoView> listAll() {
		return this.repository.findAll().stream().map(this.viewMapper::map).collect(Collectors.toList());
	}

	public AssuntoView save(AssuntoCreateForm assuntoForm) {
		var assunto = this.formMapper.map(assuntoForm);
		
		this.repository.save(assunto);
		
		return this.viewMapper.map(assunto);
	}

	public AssuntoView details(Integer codAs) {
		Assunto assunto = this.repository.findByCodAs(codAs).orElse(null);

		return this.viewMapper.map(assunto);
	}
}
