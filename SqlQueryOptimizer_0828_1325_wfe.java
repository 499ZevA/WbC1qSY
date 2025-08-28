// 代码生成时间: 2025-08-28 13:25:47
 * and is intended to be a starting point for more complex implementations.
 */

package com.example.sqloptimizer;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Factory
@Requires(env = "prod")
public class SqlQueryOptimizer {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public SqlQueryOptimizer(@Value("") String dbUrl,
                         @Value("") String dbUser,
                         @Value("") String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    @Bean
    public Runnable optimizeQuery() {
        return () -> Flowable.unsafeCreate(emitter -> {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    emitter.onNext(resultSet.getString("name"));
                }
            } catch (SQLException e) {
                emitter.onError(e);
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        emitter.onError(e);
                    }
                }
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.from(executor))
        .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Done"));
    }

    /**
     * Closes the executor service when the application is shutting down.
     */
    public void close() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
