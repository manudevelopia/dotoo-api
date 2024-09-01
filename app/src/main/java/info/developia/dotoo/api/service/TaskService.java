package info.developia.dotoo.api.service;

import info.developia.dotoo.api.model.Task;
import info.developia.dotoo.api.repository.TaskRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static info.developia.Launcher.persistence;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();
    private final TaskRepository taskRepository = new TaskRepository(persistence);

    public TaskService() {
        IntStream.rangeClosed(1, 5234).forEach(item -> {
                    var id = String.valueOf(item);
                    tasks.put(id, new Task(id, "my task " + id, false));
                }
        );
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
        return taskRepository.getById(id);
    }

    public Task create(Task task) {
        taskRepository.create(task);
        return task;
    }

    public void update(Task task) {
        tasks.put(task.id(), task);
    }

    public Task deleteById(String id) {
        var task = taskRepository.getById(id);
        taskRepository.deleteById(id);
        return task;
    }
}
