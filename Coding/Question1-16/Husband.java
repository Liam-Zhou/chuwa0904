public class Husband extends Person {  
      
    /* 
     * Encapsulation:
     * 
     */  
    private int age ;  

      
    /* 
     * setter()、getter() to access data
     */  
     

    public int getAge() {  
        return age;  
    }  
  
    public void setAge(int age) {  
        this.age = age;  
    }

    @Override
    public void display(){
        System.out.println("Display husband..");
    }
    
    
  
}  
