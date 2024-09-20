package Coding.Question1;

public class GenericMethod {
    public static void main(String[] args){
        System.out.printf("Max of %d,%d,%d is %d \n\n",6,7,8,MaxGenericTest.maxGeneric(6,7,8));
    }
}

class MaxGenericTest{
    public static <T extends Comparable<T>> T maxGeneric(T t1, T t2,T t3){
        T max =t1;
        if(t2.compareTo(max)>0){
            max = t2;
        }

        if(t3.compareTo(max)>0){
            max = t3;
        }

        return max;
    }
}
