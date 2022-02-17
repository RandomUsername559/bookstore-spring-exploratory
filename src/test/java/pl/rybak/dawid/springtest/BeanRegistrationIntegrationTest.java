package pl.rybak.dawid.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;


@SpringBootTest(classes = Main.class)
public class BeanRegistrationIntegrationTest {
    @Autowired
    private GenericWebApplicationContext context;
}

