## 1. Write up Example code to demonstrate the three foundmental concepts of OOP.
### 1. Encapsulation
```
public class Encap{
    private int id;
    private String email;
    private String name;

    public int getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }

    public void setId(int newId){
        id = newId;
    }

    public void setEmail(String newEmail){
        email = newEmail;
    }

    public void setName(String newName){
        name = newName;
    }
}
public class RunEncap{
    public static void main(String args[]){
        Encap en = new Encap();
        en.setId(001);
        en.setName("Alex");
        en.setEmail("alexakame@gmail.com");

        System.out.println("user_id: "+en.getId());
        System.out.println("user_name: "+en.getName());
        System.out.println("user_email: "+en.getEmail());
    }
}
```