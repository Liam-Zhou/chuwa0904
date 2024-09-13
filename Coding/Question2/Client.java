package Coding.Question2;

public class Client {
    public static void main(String[] args) {
        DIMLimpl d1 = new DIMLimpl();
        //override method add 
        System.out.println("\n This is override method calculating 1 + 2 in DMILimpl: "+d1.add(1, 2));

        //default method
        System.out.println("\n This is default method sub calculating 2 - 1 : "+d1.sub(2, 1));

        //printout the static method from class
        System.out.println("\n Printing out the blog name: "+DIML.blogName());
    }
}
