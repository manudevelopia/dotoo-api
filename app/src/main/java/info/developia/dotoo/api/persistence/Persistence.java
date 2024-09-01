package info.developia.dotoo.api.persistence;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class Persistence {
    private final SqlSessionFactory sqlSessionFactory;
    private final Dotenv dotenv = Dotenv.load();

    public Persistence(String packageName) {
        sqlSessionFactory = buildSqlSessionFactory(packageName);
    }

    private SqlSessionFactory buildSqlSessionFactory(String mappersPackageName) {
        DataSource dataSource = new PooledDataSource(
                "org.postgresql.Driver",
                dotenv.get("DATABASE_URL"),
                dotenv.get("DATABASE_USER"),
                dotenv.get("DATABASE_PASSWORD"));
        Environment environment = new Environment("Default", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMappers(mappersPackageName);
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    public SqlSessionFactory session() {
        return sqlSessionFactory;
    }
}
