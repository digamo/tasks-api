package br.com.digamo.tasks.api.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
	
	private String field;
	private String message;
	
}

/*
@AllArgsConstructor
@Data
public class Error {

    private final String message;
    private final String field;
    private final Object parameter;
}*/