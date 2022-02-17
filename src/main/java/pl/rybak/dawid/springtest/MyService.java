//package spring;
//
//import org.springframework.web.context.support.GenericWebApplicationContext;
//
//import java.util.Random;
//
//import static org.junit.Assert.assertTrue;
//
//public class MyService {
//    public int getRandomNumber() {
//        GenericWebApplicationContext context;
//        return new Random().nextInt(10);
//
//        context.registerBean(MyService.class, () -> new MyService());
//
//        MyService myService = (MyService) context.getBean("com.baeldung.functional.MyService");
//
//        assertTrue(myService.getRandomNumber() < 10);
//        context.registerBean("mySecondService", MyService.class, () -> new MyService());
//    }
//}
