public class MockDao {

    public String get(String key){
        System.out.println("read from redis");
        return "world";
    }
}
