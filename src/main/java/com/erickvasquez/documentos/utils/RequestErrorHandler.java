package com.erickvasquez.documentos.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

//component -> genera un bin
@Component
public class RequestErrorHandler {
	Map<String, List<String>> mapErrors(List<FieldError> fielderrors) {
		Map<String, List<String>> mappedErrors = new HashMap<>();
    	fielderrors.getFieldErrors()
    		.stream()
    		.forEach(error -> {
    			String key = error.getField() + "_errors";
    			List<String> errors = mappedErrors.get(key);
    			
    			if (errors == null) {
    				errors = new ArrayList<>();
    			}
    			
    			errors.add(error.getDefaultMessage());
    			
    			mappedErrors.put(key, errors);
    		});
    	return mappedErrors;
	}
	
}
