package info.developia.dotoo.server;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import info.developia.dotoo.TaskService;
import info.developia.dotoo.model.Task;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

public class SchemaGenerator {
    private TaskService taskService;

    public SchemaGenerator(TaskService taskService) {
        this.taskService = taskService;
    }

    public GraphQLSchema generate() {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(getSchema());
        RuntimeWiring runtimeWiring = getRuntimeWiring();
        graphql.schema.idl.SchemaGenerator schemaGenerator = new graphql.schema.idl.SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    }

    private static String getSchema() {
        try (InputStream is = ClassLoader.getSystemResourceAsStream("schema.ql")) {
            return new String(Objects.requireNonNull(is).readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RuntimeWiring getRuntimeWiring() {
        return newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("task", getTaskById))
                .type("Query", builder -> builder.dataFetcher("tasks", getAllTasksPaged))
                .type("Mutation", builder -> builder.dataFetcher("create", createTask))
                .build();
    }

    private final DataFetcher<Task> getTaskById = environment -> {
        String id = environment.getArgument("id");
        return taskService.getById(id);
    };

    private final DataFetcher<Paged<Task>> getAllTasksPaged = environment -> {
        int page = environment.getArgumentOrDefault("page", 1);
        int limit = environment.getArgumentOrDefault("limit", 25);
        int offset = (page - 1) * limit;
        int taskSize = taskService.getSize();
//        items - list of items paginated items.
//        limit - number of items per page.
//        offset - number of skipped items.
//        total - total number of items.
        var results = taskService.getTasks(offset, offset + limit);
        return new Paged<>(page, results, taskSize / limit, taskSize);
    };


    private final DataFetcher<Task> createTask = environment -> {
        String title = environment.getArgument("title");
        var task = new Task(null, title, false);
        return taskService.add(task);
    };
}
