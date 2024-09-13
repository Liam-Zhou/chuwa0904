package Coding.CodeSnippet;

import java.lang.classfile.ClassFile.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {
    public void checkNull(String data){
        if(data != null){
            System.out.println(data.toUpperCase());
        }else{
            System.out.println("data is null");
        }
    }

    public Optional<String> getData(boolean data2){
        if(data2){
            return Optional.of("valid data");
        }else{
            return Optional.empty();
        }
    }

    public void presentData(boolean data2){
        Optional<String> data3  = getData(data2);
        data3.ifPresent(System.out::println);
    }

    public void processData(String data){
        Objects.requireNonNull(data,"Data cannot be null");
    }

    public static void main(String[] args) {
        //new Test().processData(null);

        // Consumer<String> con = s -> System.out.println("Displaying "+s);
        // con.accept("mesg 1");

        // Supplier<String> sup = () -> "msg from supplier";
        // System.out.println(sup.get());

        // BiFunction<Integer,Integer,Integer> bi = (a,b) -> a+b;
        
        // System.out.println("The sum of 1 and 2 is "+ bi.apply(1, 2));

        // // Predicate to check if a number is greater than 10
        // Predicate<Integer> isGreaterThanTen = number -> number > 10;

        // // Applying the predicate
        // System.out.println(isGreaterThanTen.test(15));  // true
        // System.out.println(isGreaterThanTen.test(5));   // false
    
        // List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // // Filtering numbers greater than 5 and printing them
        // numbers.stream()
        //        .filter(num -> num > 5)
        //        .forEach(System.out::println);

        // final int number = 5;  // Explicitly marked as final

        // // Lambda expression using the final variable
        // Runnable runnable = () -> System.out.println("Number: " + number);

        // runnable.run();  // Output: Number: 5

        
            List<Integer> salary = Arrays.asList(10,100,1000);
    
            // Sort names alphabetically
            salary.stream()
                 .sorted()
                 .forEach(System.out::println);  // Output: 
        
    }
}


