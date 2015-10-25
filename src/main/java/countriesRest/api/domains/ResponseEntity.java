package countriesRest.api.domains;

import org.springframework.http.HttpStatus;

public class ResponseEntity {

	private final HttpStatus status;
	private final String message;

	public ResponseEntity(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
