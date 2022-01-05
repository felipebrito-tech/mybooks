package br.com.prfelipebrito.mybooks.api.assunto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assuntos")
public class AssuntoController {

	@Autowired
	private AssuntoService service;

	@GetMapping
	public List<AssuntoView> list() {
		return this.service.listAll();
	}

}
