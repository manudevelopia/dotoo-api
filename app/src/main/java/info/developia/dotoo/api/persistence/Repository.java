package info.developia.dotoo.api.persistence;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

public abstract class Repository<T> {
    private final SqlSessionFactory sqlSessionFactory;
    private final Class<T> typeParameterClass;

    public Repository(Persistence persistence) {
        sqlSessionFactory = persistence.session();
        typeParameterClass = getTypeParameterClass();
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTypeParameterClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    protected <R> R repository(Function<T, R> function) {
        return applyFunction(function);
    }

    private <R> R applyFunction(Function<T, R> function) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            var result = function.apply(session.getMapper(typeParameterClass));
            session.commit();
            return result;
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage());
        }
    }
}
