package Coding.Question2;

public interface DIML {


    static final String BLOG = "This is a blog from fiona gu";

    //abs method
    //before java 8, interface can only have abs method
    abstract int add(int a,int b);

    //default method
    //a method in interface that has body
    default int sub(int a,int b){
        return a-b;
    }

    //static mehtod
    //usually fields in interface are static, final and public 
    static String blogName(){
        return BLOG; //not using this becuz this is a static method, meaning it belongs to this dmil interface, not any objects 
    }
}
