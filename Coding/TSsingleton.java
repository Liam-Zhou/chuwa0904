package chuwa0904.Coding;

public class TSsingleton {
    //ensure all thread sees the latest val of instance
    private static volatile TSsingleton obj;

    private TSsingleton(){}

    public TSsingleton getTSsingleton(){
        TSsingleton localFef = obj;
        if(localFef == null){
            //syn lock
            synchronized(this){

                //double lock
                if(localFef == null){
                    localFef = obj = new TSsingleton();
                }
            }
        }
        return localFef;
    }
}
