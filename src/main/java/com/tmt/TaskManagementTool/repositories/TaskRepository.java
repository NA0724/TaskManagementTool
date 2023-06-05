package com.tmt.TaskManagementTool.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.TaskManagementTool.models.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, ObjectId> {
    List<Task> findTasksByCreatedBy(String username);

    List<Task> findTasksByStatus(String status);

    List<Task> findTasksByAssignedTo(String username);

    Optional<Task> findTaskByTid(String tid);

    List<Task> findTaskByDueDate(LocalDate dueDate);

    List<Task> findTasksByStatusAndDueDate(String status, LocalDate dueDate);

}
