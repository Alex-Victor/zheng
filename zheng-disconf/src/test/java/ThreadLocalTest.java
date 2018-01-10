import org.junit.Test;

public class ThreadLocalTest {
    @Test
    public void test(){
        ThreadLocal<String> t = new ThreadLocal<>();
        t.set("hello");
        System.out.println(t.get());
    }
}
