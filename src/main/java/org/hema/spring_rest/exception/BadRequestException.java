package org.hema.spring_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	
	
	private static final long serialVersionUID = -5653630770400980531L;


	public BadRequestException(String message)
	{
		super(message);
	}


   public BadRequestException(String message,Throwable cause)
   {
	   super(message,cause);
   }



}



