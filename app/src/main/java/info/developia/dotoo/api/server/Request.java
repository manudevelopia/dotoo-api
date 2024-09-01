package info.developia.dotoo.api.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Request(
        String query,
        Map<String, Object> variables
) {
    public Request(String query, Map<String, Object> variables) {
        this.query = query;
        this.variables = variables == null ? Map.of() : variables;
    }
}
