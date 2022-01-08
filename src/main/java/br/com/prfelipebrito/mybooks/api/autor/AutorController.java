package br.com.prfelipebrito.mybooks.api.autor;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/autores")
public class AutorController {

	@Autowired
	private AutorService service;

	@GetMapping
	public List<AutorView> list() {
		return this.service.listAll();
	}

	@PostMapping
	public ResponseEntity<AutorView> create(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder) {
		AutorView autorView = this.service.save(form);

		URI uri = uriBuilder.path("/autores/{codAu}").buildAndExpand(autorView.getCodAu()).toUri();

		return ResponseEntity.created(uri).header("Content-Type", "application/json;charset=UTF-8").body(autorView);
	}

	@GetMapping("{codAu}")
	public ResponseEntity<AutorView> details(@PathVariable Integer codAu) {
		try {
			AutorView autorView = this.service.details(codAu);
			
			return ResponseEntity.ok(autorView);
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("{codAu}")
	public ResponseEntity<AutorView> update(@PathVariable @Valid Integer codAu, @RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder) {
		try {
			return ResponseEntity.ok(this.service.update(codAu, form));			
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{codAu}")
	public ResponseEntity<?> remove(@PathVariable Integer codAu, UriComponentsBuilder uriBuilder) {
		try {
			this.service.removeBy(codAu);
			
			return ResponseEntity.ok().build();
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
