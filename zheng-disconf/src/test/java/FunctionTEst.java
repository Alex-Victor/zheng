import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionTEst {

    @Test
    public void test(){
//        this.testSupplier(()->"hello world");
//
//        this.testConsumer(s-> System.out.println(s));
//        this.testPredicate(Integer.MAX_VALUE, i->i>1000000);
//        this.testPredicate(Integer.MIN_VALUE, j->j>1000000);
//
//        List<Integer> data = Arrays.asList(1,3,2,8,5,8);
//        System.out.println(this.evenSum(data, i->i%2==0));

        Arrays.asList(1,3,2).sort((i,j)->i-j);
    }

    private void testSupplier( Supplier<String> supplier){
        System.out.println(supplier.get());
    }

    private void testConsumer(Consumer<String> consumer){
        consumer.accept("你好");
    }

    private void testPredicate(int i, Predicate<Integer> predicate){
        if(predicate.test(i)){
            System.out.println("too big");
        }else{
            System.out.println("too small");
        }
    }

    private Integer evenSum(List<Integer> data, Predicate<Integer> predicate){
        int sum=0;
        for(Integer i : data){
            if(predicate.test(i)){
                sum+=i;
            }
        }
        return sum;
    }
}
