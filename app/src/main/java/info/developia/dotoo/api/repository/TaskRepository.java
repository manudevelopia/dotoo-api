package info.developia.dotoo.api.repository;

import info.developia.dotoo.api.model.Task;
import info.developia.dotoo.api.persistence.Persistence;
import info.developia.dotoo.api.persistence.PersistenceException;
import info.developia.dotoo.api.persistence.Repository;
import info.developia.dotoo.api.repository.mapper.TaskMapper;

import java.util.List;

public class TaskRepository extends Repository<TaskMapper> {

    public TaskRepository(Persistence persistence) {
        super(persistence);
    }

    public int countTasks() {
        try {
            return repository(TaskMapper::countTasks);
        } catch (PersistenceException e) {
            throw new RuntimeException("Error counting tasks, %s".formatted(e.getMessage()));
        }
    }

    public List<Task> getTasks(int offset, int limit) {
        try {
            return repository(repository -> repository.getTasks(offset, limit));
        } catch (PersistenceException e) {
            throw new RuntimeException("Error retrieving task list, %s".formatted(e.getMessage()));
        }
    }

    public Task getById(String id) {
        try {
            return repository(repository -> repository.getById(Integer.parseInt(id)));
        } catch (PersistenceException e) {
            throw new RuntimeException("Error retrieving task by id, %s".formatted(e.getMessage()));
        }
    }

    public void deleteById(String id) {
        try {
            repository(repository -> repository.deleteById(Integer.parseInt(id)));
        } catch (PersistenceException e) {
            throw new RuntimeException("Error deleting task, %s".formatted(e.getMessage()));
        }
    }

    public void create(Task task) {
        try {
            repository(repository -> repository.create(task));
        } catch (PersistenceException e) {
            throw new RuntimeException("Error creating task, %s".formatted(e.getMessage()));
        }
    }
}
