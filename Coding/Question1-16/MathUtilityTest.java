class MathUtility {
    static int square(int number) {
        return number * number;
    }
}

public class MathUtilityTest {
    public static void main(String[] args) {
        int result = MathUtility.square(5);  // No object creation needed
        System.out.println("Square of 5: " + result);  // Output: 25
    }
}
