package info.developia.dotoo.api.service;

import info.developia.dotoo.api.model.Task;
import info.developia.dotoo.api.repository.TaskRepository;

import java.util.List;

import static info.developia.Launcher.persistence;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository(persistence);

    public List<Task> getTasks(int offset, int limit) {
        int taskSize = getSize();
        if (offset > taskSize) offset = taskSize;
        if (limit > taskSize) limit = taskSize;
        return taskRepository.getTasks(offset, limit);
    }

    public int getSize() {
        return taskRepository.countTasks();
    }

    public Task getById(String id) {
        return taskRepository.getById(id);
    }

    public int create(Task task) {
        return taskRepository.create(task);
    }

    public int update(Task task) {
        return taskRepository.update(task);
    }

    public int deleteById(String id) {
        return taskRepository.deleteById(id);
    }
}
