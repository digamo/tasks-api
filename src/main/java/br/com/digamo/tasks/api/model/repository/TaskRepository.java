package br.com.digamo.tasks.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digamo.tasks.api.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
