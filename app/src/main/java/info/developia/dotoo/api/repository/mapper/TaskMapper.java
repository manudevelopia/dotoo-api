package info.developia.dotoo.api.repository.mapper;

import info.developia.dotoo.api.model.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    List<Task> getTasks(int offset, int limit);

    @Select("""
            select t_id, t_title, t_done
            from dotoo.tasks
            where t_id = #{arg0}
            """)
    Task getById(int id);

    @Update("""
            insert into dotoo.tasks(t_title, t_created_on)
            values(#{title}, now())
            """)
    int create(Task task);

    @Delete("""
            delete
            from dotoo.tasks
            where t_id = #{arg0}
            """)
    int deleteById(int id);

    @Update("""
            update dotoo.tasks
            set t_title = #{title},
                t_done = #{done},
                t_updated_on = now()
            where t_id = #{id}
            """)
    int update(Task task);
}
