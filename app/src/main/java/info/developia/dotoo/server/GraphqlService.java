package info.developia.dotoo.server;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import info.developia.dotoo.TaskService;
import ratpack.core.handling.Context;
import ratpack.core.http.MediaType;
import ratpack.core.server.BaseDir;
import ratpack.core.server.RatpackServer;

import static ratpack.core.jackson.Jackson.json;

public class GraphqlService {
    private final GraphQL graphQL;

    public GraphqlService(TaskService taskService) {
        GraphQLSchema graphQLSchema = getGraphQLSchema(taskService);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public void start() {
        try {
            RatpackServer.start(server -> server
                    .serverConfig(builder -> builder.baseDir(BaseDir.find("static/.ratpack.base.dir").toAbsolutePath()))
                    .handlers(chain -> chain.files(files -> files.dir("resources"))
                            .post("graphql", this::handle)
                            .get(ctx -> ctx.render(ctx.file("playground/index.html"))))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static GraphQLSchema getGraphQLSchema(TaskService taskService) {
        return new SchemaGenerator(taskService).generate();
    }

    public Object query(Request request) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(request.query())
                .variables(request.variables())
                .build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        return executionResult.toSpecification();
    }

    private void handle(Context context) {
        context.parse(Request.class).then(request -> {
            var result = query(request);
            context.getResponse().contentType(MediaType.APPLICATION_JSON);
            context.render(json(result));
        });
    }
}
