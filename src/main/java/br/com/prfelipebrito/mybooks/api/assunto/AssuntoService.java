package br.com.prfelipebrito.mybooks.api.assunto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
