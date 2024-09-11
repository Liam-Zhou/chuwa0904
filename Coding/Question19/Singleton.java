package Question19;

public class Singleton {
    private static Singleton instance;

    private Singleton(){
    }

    public static Singleton getInstance(){
        if(instance == null){//first check before locking
            synchronized(Singleton.class){
                if(instance == null){ //second check after locking
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }

    public void DisplayMsg(){
        System.out.println("Msg from singletone class");
    }
}

class SingletonMain{
    public static void main(String[] args){
        Singleton s = Singleton.getInstance();
        s.DisplayMsg();

    }
}
