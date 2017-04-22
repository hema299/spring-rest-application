package org.hema.spring_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundExeption extends RuntimeException {
	
	
	private static final long serialVersionUID = -8332466782929721541L;


	public NotFoundExeption(String message)
	{
		super(message);
	}


   public NotFoundExeption(String message,Throwable cause)
   {
	   super(message,cause);
   }

}
