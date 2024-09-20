// import java.util.*;
package Coding;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Arrays;
/**
 * IntStreamSum
 */
public class IntStreamSum {
    public static void main(String[] args) {
        int[] nums = {20, 3, 78, 9, 6, 53, 73, 99, 24, 32};
        int ans = Arrays.stream(nums).sum();
        System.out.println(ans);
    }
}
