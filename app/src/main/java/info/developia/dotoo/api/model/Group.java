package info.developia.dotoo.api.model;

import java.util.List;

public record Group(
        String id,
        String title,
        List<Task> tasks
) {
}
