package Coding.Question1;

public class GenericClass<T>{
    //attribute t of type T 
    T obj;

    //constructor
    public GenericClass(T obj){
         this.obj = obj;
    }

    //method
    public T getObj(){
        return this.obj;
    }

}