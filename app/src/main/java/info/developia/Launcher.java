package info.developia;

import info.developia.dotoo.api.persistence.Persistence;
import info.developia.dotoo.api.server.GraphqlService;
import info.developia.dotoo.api.service.TaskService;

public class Launcher {
    public static final Persistence persistence = new Persistence("info.developia.dotoo.api.repository.mapper");

    public static void main(String[] args) {
        var taskService = new TaskService();
        var server = new GraphqlService(taskService);
        server.start();
    }
}
