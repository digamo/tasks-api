package br.com.digamo.tasks.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty(message = "{required.task.field}")
	private String task;
	
	@Column(nullable = false)
	@NotNull(message = "{required.due.date.field}")
	@Future(message = "{invalid.due.date.field}")
	private LocalDate dueDate;
	
}
