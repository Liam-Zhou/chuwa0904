package Coding.Question1;
public class GenericClassTests{
    public static void main(String[] args) {
        //
        GenericClass<Integer> obj1 = new GenericClass<>(1);
        System.err.println("This is a integer obj : "+obj1.getObj());

        //
        GenericClass<String> obj2 = new GenericClass<>("Hello");
        System.err.println("This is a string obj : "+obj2.getObj());

        //
        GenericClass<Double> obj3 = new GenericClass<>(2.22);
        System.err.println("This is a double obj : "+obj3.getObj());
    }
}