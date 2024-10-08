package info.developia.dotoo.api.repository.mapper;

import info.developia.dotoo.api.model.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TaskMapper {
    @Select("""
            select count(t_id)
            from dotoo.tasks
            """)
    int countTasks();

    @Select("""
            select t_id, t_title, t_done
            from dotoo.tasks
            offset #{arg0} limit #{arg1}
            """)
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    List<Task> getTasks(int offset, int limit);

    @Select("""
            select t_id, t_title, t_done
            from dotoo.tasks
            where t_id = #{arg0}::uuid
            """)
    Task getById(String id);

    @Select("""
            insert into dotoo.tasks(t_title, t_created_on)
            values(#{title}, now())
            returning t_id, t_title, t_done
            """)
    Task create(Task task);

    @Delete("""
            delete
            from dotoo.tasks
            where t_id = #{arg0}::uuid
            """)
    int deleteById(String id);

    @Select("""
            update dotoo.tasks
            set t_title = #{title},
                t_done = #{done},
                t_updated_on = now()
            where t_id = #{id}::uuid
            returning t_id, t_title, t_done
            """)
    Task update(Task task);
}
