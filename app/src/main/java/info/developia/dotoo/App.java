package info.developia.dotoo;


import info.developia.dotoo.server.GraphqlService;

public class App {

    public static void main(String[] args) {
        var taskService = new TaskService();
        var server = new GraphqlService(taskService);
        server.start();
    }
}
