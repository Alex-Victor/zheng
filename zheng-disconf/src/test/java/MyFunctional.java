@FunctionalInterface
public interface MyFunctional {
    int test();

    static int test2(){
        return 1;
    }

    default  int test3(){
        return 3;
    }

}
