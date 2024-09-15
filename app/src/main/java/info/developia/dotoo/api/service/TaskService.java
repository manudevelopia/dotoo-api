package info.developia.dotoo.api.service;

import info.developia.dotoo.api.exception.DotooException;
import info.developia.dotoo.api.model.Task;
import info.developia.dotoo.api.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

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
        return Optional.ofNullable(taskRepository.getById(id))
                .orElseThrow(() -> new DotooException("Task with id: " + id + " cannot be found"));
    }

    public Task create(Task task) {
        return taskRepository.create(task);
    }

    public Task update(Task task) {
        return taskRepository.update(task);
    }

    public int deleteById(String id) {
        return taskRepository.deleteById(id);
    }
}
