package info.developia.dotoo;

import info.developia.dotoo.model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    public TaskService() {
        IntStream.rangeClosed(1, 500).forEach(item -> {
                    var id = String.valueOf(item);
                    tasks.put(id, new Task(id, "my task " + id, false));
                }
        );
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getById(String id) {
        return tasks.get(id);
    }

    public Task add(Task task) {
        var id = String.valueOf(tasks.size() + 1);
        var newTask = new Task(id, task.title(), false);
        tasks.put(id, newTask);
        return newTask;
    }

    public List<Task> getTasks(int offset, int limit) {
//        items - list of items paginated items.
//        limit - number of items per page.
//        offset - number of skipped items.
//        total - total number of items.
        if (offset > getSize()) offset = getSize();
        if (limit > getSize()) limit = getSize();
        return tasks.values().stream().toList().subList(offset, limit);
    }
}
