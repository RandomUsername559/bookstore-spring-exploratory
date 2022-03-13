package pl.rybak.dawid.springtest;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
public class AbstractTestIT {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void cleanDatabaseEach() {


        List<String> tables = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables\n" +
                        "WHERE table_schema = 'public';", String.class);
        for (String table : tables) {
            jdbcTemplate.execute("truncate table " + table + " cascade");
        }

    }
}
