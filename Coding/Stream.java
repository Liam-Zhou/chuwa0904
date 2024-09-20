import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;

class MyStream<T> {
    private final List<T> source;
    
    public MyStream(List<T> source){
        this.source = source;
    }

    public MyStream<T> filter(Predicate<T> predicate){
        List<T> res = new ArrayList<>();
        for(T element : source){
            if(predicate.test(element)){
                res.add(element);
            }
        }
        return new MyStream<>(res);
    }

    public <R> MyStream<R> map(Function<T, R> mapper){
        List<R> res = new ArrayList<>();
        for(T element : source){
            res.add(mapper.apply(element));
        }
        return new MyStream(res);
    }

    public List<T> collect() {
        return source;
    }
    public void forEach(Consumer<T> consumer) {
        for (T element : source) {
            consumer.accept(element);
        }
    }

    public static <T> MyStream<T> of(List<T> source) {
        return new MyStream<>(source);
    }

    public class Main {
        public static void main(String[] args) {
            List<String> names = List.of("Emile", "Bob", "Jack", "David", "Nick");
    
            MyStream.of(names)
                    .filter(name -> name.length() > 3)    // Filter names longer than 3 characters
                    .map(name -> name.toUpperCase())      // Convert names to uppercase
                    .forEach(System.out::println);        // Print result
        }
    }
}