import org.junit.Test;

import java.time.Clock;

public class JodaTimeTest {
    @Test
    public void test(){
       Clock clock=  Clock.systemUTC();
        System.out.println(clock.instant());
    }
}
