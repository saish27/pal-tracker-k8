package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;

public class JdbcTimeEntryRepository implements TimeEntryRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = jdbcTemplate.queryForObject(
            "insert into time_entries (project_id, user_id, date, hours) values (?, ?, ?, ?) returning id",
            new Object[]{
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                Date.valueOf(timeEntry.getDate()),
                timeEntry.getHours()
            },
            Integer.class
        );

        return find(id);
    }

    @Override
    public TimeEntry find(long id) {
        return jdbcTemplate.query(
            "select id, project_id, user_id, date, hours from time_entries where id = ?",
            new Object[]{id},
                rsExtractor);
    }

    @Override
    public List<TimeEntry> list() {
        return jdbcTemplate.query("select id, project_id, user_id, date, hours from time_entries order by id", rowMapper);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        jdbcTemplate.update("update time_entries " +
                "set project_id = ?, user_id = ?, date = ?,  hours = ? " +
                "where id = ?",
            timeEntry.getProjectId(),
            timeEntry.getUserId(),
            Date.valueOf(timeEntry.getDate()),
            timeEntry.getHours(),
            id);

        return find(id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM time_entries WHERE id = ?", id);
    }

    private final RowMapper<TimeEntry> rowMapper = (rs, rowNum) -> new TimeEntry(
        rs.getLong("id"),
        rs.getLong("project_id"),
        rs.getLong("user_id"),
        rs.getDate("date").toLocalDate(),
        rs.getInt("hours")
    );

    private final ResultSetExtractor<TimeEntry> rsExtractor =
        (rs) -> rs.next() ? rowMapper.mapRow(rs, 1) : null;
}