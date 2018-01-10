import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class FunctionTest2 {

    @Test
    public void test(){
        Car car =Car.create(Car::new);//no-arg constructor
        List<Car> cars = Arrays.asList(car);

        cars.forEach(Car::collide);//one arg
        cars.forEach(Car::repair);//no arg

        cars.forEach(car::follow);//instance method, one arg
    }

}
