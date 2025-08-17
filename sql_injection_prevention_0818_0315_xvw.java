// 代码生成时间: 2025-08-18 03:15:48
package com.example.security;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.web.router.Router;
import io.micronaut.core.async.annotation.SingleResult;
import io.micronaut.core.type.Argument;
import io.micronaut.core.util.StringUtils;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller("/api")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class SqlInjectionPreventionController {

    private final DataSource dataSource;

    public SqlInjectionPreventionController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Get("/users")
    public SingleResult<HttpResponse<Page<User>>> getUsers(
            @QueryValue(value = "name", required = false) String name,
            @QueryValue(value = "page", defaultValue = "0") int page,
            @QueryValue(value = "size", defaultValue = "10") int size) throws SQLException {

        String query = "SELECT * FROM users WHERE name LIKE ?";
        if (StringUtils.isEmpty(name)) {
            query = "SELECT * FROM users";
        }

        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(query)) {

            if (StringUtils.isNotEmpty(name)) {
                statement.setString(1, name + "%");
            }

            var resultSet = statement.executeQuery();

            // Process the result set and create a list of users
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }

            return HttpResponse.ok(buildPage(users, page, size));
        }
    }

    private Page<User> buildPage(List<User> users, int page, int size) {
        int totalUsers = users.size();
        int totalPages = (int) Math.ceil((double) totalUsers / size);
        int offset = page * size;

        List<User> pagedUsers = users.subList(offset, Math.min(offset + size, totalUsers));

        return new Page<>(pagedUsers, page, size, totalUsers, totalPages);
    }

    // Inner User class
    public static class User {
        private int id;
        private String name;
        private String email;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    // Inner Page class for pagination
    public static class Page<T> {
        private List<T> content;
        private int number;
        private int size;
        private long totalElements;
        private int totalPages;

        public Page(List<T> content, int number, int size, long totalElements, int totalPages) {
            this.content = content;
            this.number = number;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
        }

        public List<T> getContent() { return content; }
        public int getNumber() { return number; }
        public int getSize() { return size; }
        public long getTotalElements() { return totalElements; }
        public int getTotalPages() { return totalPages; }
    }
}
