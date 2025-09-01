// 代码生成时间: 2025-09-02 02:12:05
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import javax.sql.DataSource;
import io.micronaut.sql.DataSourceFactory;
import io.micronaut.sql.DataSourceResolver;
import io.micronaut.transaction.SynchronousTransactionManager;
import jakarta.inject.Singleton;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.Transaction;
import jakarta.transaction.Status;
import jakarta.transaction.SystemException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Factory
@Requires(env = Environment.JVM)
public class DatabasePoolManagement {
    
    private static final String DEFAULT_DATASOURCE_NAME = "default";
    private static final int MAX_POOL_SIZE = 10;
    private static final int MIN_IDLE = 8;
    private static final int MAX_IDLE = 10;
    private static final long MAX_WAIT = 10000;
    private static final int TIMEOUT = 30000;
    private static final String TEST_QUERY = "SELECT 1";

    @Singleton
    public DataSource dataSource() {
        // Configuration for DataSourceFactory
        DataSourceFactory dataSourceFactory = DataSourceFactory.fromEnvironment()
                .setContextClassLoader(ClassLoader.getSystemClassLoader())
                .build();
        
        // Creating the DataSource
        return dataSourceFactory.createDataSource();
    }

    @Singleton
    public TransactionManager transactionManager() {
        return new SynchronousTransactionManager(dataSource());
    }

    @Singleton
    public ExecutorService executorService() {
        // ExecutorService for managing concurrent database operations
        return Executors.newFixedThreadPool(MAX_POOL_SIZE);
    }

    public Connection getConnection() throws SQLException, SystemException {
        // Obtaining a connection from the pool
        TransactionManager tm = transactionManager();
        tm.begin();
        Transaction tx = tm.getTransaction();
        if (tx != null && tx.getStatus() == Status.STATUS_ACTIVE) {
            DataSource ds = dataSource();
            return ds.getConnection();
        } else {
            throw new SQLException("No active transaction found");
        }
    }

    public void releaseConnection(Connection connection) throws SQLException {
        // Releasing the connection back to the pool
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Utility method to test the connection
    public boolean testConnection() {
        try (Connection connection = getConnection()) {
            // Execute a simple query to test the connection
            connection.createStatement().execute(TEST_QUERY);
            return true;
        } catch (SQLException | SystemException e) {
            e.printStackTrace();
            return false;
        }
    }
}
