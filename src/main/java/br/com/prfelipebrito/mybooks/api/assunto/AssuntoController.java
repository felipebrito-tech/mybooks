package br.com.prfelipebrito.mybooks.api.assunto;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/assuntos")
public class AssuntoController {

	@Autowired
	private AssuntoService service;

	@GetMapping
	public List<AssuntoView> list() {
		return this.service.listAll();
	}

	@PostMapping
	public ResponseEntity<AssuntoView> create(UriComponentsBuilder uriBuilder, @RequestBody AssuntoCreateForm form) {
		AssuntoView assuntoView = this.service.save(form);

		URI uri = uriBuilder.path("/assuntos/{codAs}").buildAndExpand(assuntoView.getCodAs()).toUri();

		return ResponseEntity.created(uri).header("Content-Type", "application/json;charset=UTF-8").body(assuntoView);
	}

	@GetMapping("{codAs}")
	public AssuntoView details(@PathVariable Integer codAs) {
		AssuntoView assuntoView = this.service.details(codAs);
		
		return assuntoView;
	}
}
