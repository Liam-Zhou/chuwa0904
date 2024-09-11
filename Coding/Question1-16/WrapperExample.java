import java.util.ArrayList;

public class WrapperExample {
     public static void main(String[] args) {
        // Autoboxing: Converting int to Integer
        int primitiveInt = 5;
        Integer wrapperInt = primitiveInt;  // Autoboxing

        // Unboxing: Converting Integer back to int
        int newPrimitiveInt = wrapperInt;  // Unboxing

        // Using wrapper class in a collection
        ArrayList<Integer> list = new ArrayList<>();
        list.add(wrapperInt);  // Adding Integer object to ArrayList

        System.out.println("Primitive int: " + primitiveInt);
        System.out.println("Wrapper Integer: " + wrapperInt);
        System.out.println("ArrayList value: " + list.get(0));
    }
}
