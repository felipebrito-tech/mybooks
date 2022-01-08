package br.com.prfelipebrito.mybooks.api.assunto;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.prfelipebrito.mybooks.shared.infra.ResourceNotFoundException;

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
	public ResponseEntity<AssuntoView> create(@RequestBody AssuntoForm form, UriComponentsBuilder uriBuilder) {
		AssuntoView assuntoView = this.service.save(form);

		URI uri = uriBuilder.path("/assuntos/{codAs}").buildAndExpand(assuntoView.getCodAs()).toUri();

		return ResponseEntity.created(uri).header("Content-Type", "application/json;charset=UTF-8").body(assuntoView);
	}

	@GetMapping("{codAs}")
	public ResponseEntity<AssuntoView> details(@PathVariable Integer codAs) {
		try {
			AssuntoView assuntoView = this.service.details(codAs);
			
			return ResponseEntity.ok(assuntoView);
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("{codAs}")
	public ResponseEntity<AssuntoView> update(@PathVariable Integer codAs, @RequestBody AssuntoForm form, UriComponentsBuilder uriBuilder) {
		try {
			return ResponseEntity.ok(this.service.update(codAs, form));			
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{codAs}")
	public ResponseEntity<?> remove(@PathVariable Integer codAs, UriComponentsBuilder uriBuilder) {
		try {
			this.service.removeBy(codAs);
			
			return ResponseEntity.ok().build();
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
