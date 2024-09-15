package info.developia.dotoo.api.repository;

import info.developia.dotoo.api.exception.DotooException;
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
            throw new DotooException("Error counting tasks, %s".formatted(e.getMessage()));
        }
    }

    public List<Task> getTasks(int offset, int limit) {
        try {
            return repository(repository -> repository.getTasks(offset, limit));
        } catch (PersistenceException e) {
            throw new DotooException("Error retrieving task list, %s".formatted(e.getMessage()));
        }
    }

    public Task getById(String id) {
        try {
            return repository(repository -> repository.getById(id));
        } catch (PersistenceException e) {
            throw new DotooException("Error retrieving task by id, %s".formatted(e.getMessage()));
        }
    }

    public Task create(Task task) {
        try {
            return repository(repository -> repository.create(task));
        } catch (PersistenceException e) {
            throw new DotooException("Error creating task, %s".formatted(e.getMessage()));
        }
    }

    public int deleteById(String id) {
        try {
            return repository(repository -> repository.deleteById(id));
        } catch (PersistenceException e) {
            throw new DotooException("Error deleting task, %s".formatted(e.getMessage()));
        }
    }

    public Task update(Task task) {
        try {
            return repository(repository -> repository.update(task));
        } catch (PersistenceException e) {
            throw new DotooException("Error updating task, %s".formatted(e.getMessage()));
        }
    }
}
