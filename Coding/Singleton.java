package chuwa0904.Coding;

/**
 * Singleton
 */
public class Singleton {
    //ensure only one instance of the class, avoid multiple instances in system
    //global access instance

    //create private static instance of the class
    //static means the instance belong to the class 
    //1. w/o static when created a new obj of class it creates a new instance of the class which defeating the purpose of singleton
    //2. also static instance shared among the class
    private static Singleton s = null;

    //provide private constructor to avoid any new object creation
    private Singleton(){}

    //provide public static method that returns the instance
    //static method allow to be accessed w/o creating a obj of the class

    //use synchronized locked inside the forloop: thread safe guaranteed, lazy initialzation
    public static Singleton getInstance(){
        //non thread safe becuz multi thread try to create the obj at the same time
        if(s ==  null){
            s = new Singleton();
        }
        return s;
    }
}