package br.com.prfelipebrito.mybooks.shared.infra;

public class ResourceNotFoundException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}