package com.romankrivtsov.tms.dao;

import com.romankrivtsov.tms.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer> {

}
