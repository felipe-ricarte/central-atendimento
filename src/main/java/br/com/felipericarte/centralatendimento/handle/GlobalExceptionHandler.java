package br.com.felipericarte.centralatendimento.handle;


import br.com.felipericarte.centralatendimento.controller.response.Response;
import br.com.felipericarte.centralatendimento.controller.response.ResponseStatusEnum;
import br.com.felipericarte.centralatendimento.exception.AtendimentoNotFoundException;
import br.com.felipericarte.centralatendimento.exception.AtendimentoStatusAtendimentoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({AtendimentoNotFoundException.class})
    public ResponseEntity<Response<?>> handleAtendimentoNotFoundException(AtendimentoNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Response.builder()
            		.message(Arrays.asList(exception.getMessage()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
    }
	
	@ExceptionHandler({AtendimentoStatusAtendimentoException.class})
    public ResponseEntity<Response<?>> handleAtendimentoStatusAtendimentoException(AtendimentoStatusAtendimentoException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.builder()
            		.message(Arrays.asList(exception.getMessage()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
    }
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.builder()
            		.message(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Response<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		BindingResult bindingResult = exception.getBindingResult();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Response.builder()
            		.message(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()))
            		.status(ResponseStatusEnum.ERROR.getDescription())
        		.build())
        ;
    }

}
