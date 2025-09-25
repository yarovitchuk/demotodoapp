package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TodoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Todo> todoRowMapper = (rs, num) ->
            new Todo(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("description"),
                    rs.getBoolean("is_completed")
            );

    public TodoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void save(Todo todo) {
        //language=sql
        var sql = """
                INSERT INTO todo(id, description, is_completed)
                VALUES (:id, :description, :is_completed)
                ON CONFLICT (id)
                DO UPDATE SET
                    description = :description,
                    is_completed = :is_completed
                """;

        Map<String, Object> parameters = Map.of(
                "id", todo.getId(),
                "description", todo.getDescription(),
                "is_completed", todo.isCompleted()
        );

        jdbcTemplate.update(sql, parameters);
    }

    public Optional<Todo> findById(UUID id) {
        //language=sql
        String sql = "SELECT t.id, t.description, t.is_completed FROM todo t WHERE t.id = :id";

        Map<String, Object> parameters = Map.of("id", id);

        var result = jdbcTemplate.query(
                sql,
                parameters,
                todoRowMapper
        );

        if (result.size() > 1) {
            throw new RuntimeException("More than one rows found by one id");
        }

        return result.stream().findFirst();
    }

    public void deleteById(UUID id) {
        //language=sql
        var sql = "DELETE FROM todo WHERE id = :id";
        var params = Map.of("id", id);
        jdbcTemplate.update(sql, params);
    }

    public List<Todo> getAll() {
        //language=sql
        var sql = "SELECT t.id, t.description, t.is_completed FROM todo t";

        return jdbcTemplate.query(
                sql,
                Map.of(),
                todoRowMapper
        );
    }

    public List<Todo> findByDescription(String description) {
        //language=sql
        var sql = """
                    SELECT t.id, t.description, t.is_completed FROM todo t
                    WHERE t.description LIKE :description;
                """;
        var params = Map.of("description", "%" + description + "%");
        return jdbcTemplate.query(
                sql,
                params,
                todoRowMapper
        );
    }
}
