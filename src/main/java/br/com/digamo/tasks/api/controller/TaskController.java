package br.com.digamo.tasks.api.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.digamo.tasks.api.model.Task;
import br.com.digamo.tasks.api.model.repository.TaskRepository;
import br.com.digamo.tasks.api.utils.DateUtils;
import br.com.digamo.tasks.api.utils.ValidationException;

@RestController
@RequestMapping(value ="/todo")
public class TaskController {

	@Autowired
	private TaskRepository todoRepository;
	
	@GetMapping
	public List<Task> findAll() {
		return todoRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Task> save(@RequestBody @Valid Task todo) throws ValidationException {
		if(todo.getName() == null || todo.getName() == "") {
			throw new ValidationException("Fill the task description");
		}
		if(todo.getDueDate() == null) {
			throw new ValidationException("Fill the due date");
		}
		if(!DateUtils.isEqualOrFutureDate(todo.getDueDate())) {
			throw new ValidationException("Due date must not be in past");
		}
		Task saved = todoRepository.save(todo);
		return new ResponseEntity<Task>(saved, HttpStatus.CREATED);
	}
	 
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		todoRepository.deleteById(id);
	}
}
