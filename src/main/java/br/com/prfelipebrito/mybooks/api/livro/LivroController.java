package br.com.prfelipebrito.mybooks.api.livro;

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
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService service;

	@GetMapping
	public List<LivroView> list() {
		return this.service.listAll();
	}

	@PostMapping
	public ResponseEntity<LivroView> create(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
		LivroView livroView = this.service.save(form);

		URI uri = uriBuilder.path("/livros/{codL}").buildAndExpand(livroView.getCodL()).toUri();

		return ResponseEntity.created(uri).header("Content-Type", "application/json;charset=UTF-8").body(livroView);
	}

	@GetMapping("{codL}")
	public ResponseEntity<LivroView> details(@PathVariable Integer codL) {
		try {
			LivroView livroView = this.service.details(codL);
			
			return ResponseEntity.ok(livroView);
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("{codL}")
	public ResponseEntity<LivroView> update(@PathVariable @Valid Integer codL, @RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
		try {
			return ResponseEntity.ok(this.service.update(codL, form));			
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("{codL}")
	public ResponseEntity<?> remove(@PathVariable Integer codL, UriComponentsBuilder uriBuilder) {
		try {
			this.service.removeBy(codL);
			
			return ResponseEntity.ok().build();
		}
		catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
