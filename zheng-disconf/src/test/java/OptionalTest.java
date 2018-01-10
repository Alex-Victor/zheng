import org.junit.Test;

import java.util.Optional;

public class OptionalTest {
    @Test
    public void test(){
        Optional<String> optional = Optional.ofNullable(null);
        System.out.println(optional.orElse("none"));
        System.out.println(optional.orElseGet(()->"I am none"));
        System.out.println(optional.map(s->"hello" + s).orElse("hello none"));
        System.out.println(optional.orElseThrow(()->new IllegalArgumentException("must not null")));
    }
}
