package Coding;

/**
 * Singleton
 */
class Singleton {
    private volatile static Singleton instance;
    private Singleton(){

    }
    public synchronized static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}

class Th {
    
}
