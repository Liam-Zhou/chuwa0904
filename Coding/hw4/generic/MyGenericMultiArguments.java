package generic;


public class MyGenericMultiArguments {

    public static <T1, T2, T3> void displayInfo(T1 x, T2 y, T3 z) {
        System.out.println("Value of x: " + x);
        System.out.println("Value of y: " + y);
        System.out.println("Value of z: " + z);
    }

    public static void main(String[] args) {
        displayInfo(1, "Hello", 3.14);
        displayInfo("A", true, 100L);
    }
}