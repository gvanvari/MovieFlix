package io.egen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Movie Not Found")
public class MovieNotFoundException extends Exception{

	private static final long serialVersionUID = -1118771158183783993L;

}
