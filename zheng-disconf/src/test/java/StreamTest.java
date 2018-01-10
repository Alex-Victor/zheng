import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void test(){
        List<Double> doubleList = Arrays.asList(1.2d, 2d,3.1415d, 8d);
        int sum = doubleList.stream().filter(d->d>2.0d).mapToInt(d->d.intValue()).sum();
        System.out.println(sum);

        String str = "hello world";
        Stream.of(str).onClose(()-> System.out.println("done")).forEach(ch-> System.out.println(ch + "xxx"));
    }
}
