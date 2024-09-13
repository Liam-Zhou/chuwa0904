package Coding.Question20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

class MyStream<T>{
    private List<T> lists;
    //constructor
    public MyStream(List<T> lists){
        this.lists = lists;
    }

    //filter method for mystream class
    public MyStream<T> filter(Predicate<T> pre){
        List<T> filterList = new ArrayList<>();
        for(T item:lists){
            if(pre.test(item)){
                filterList.add(item);
            }
        }
        return new MyStream<>(filterList);
    }

    //map method for mystream class
    //R: generoc type declaration 
    //MyStream<R>: return type for method
    public <R> MyStream<R> map(Function<T,R> mp){
        List<R> mapList = new ArrayList<>();
        for(T item : lists){
            mapList.add(mp.apply(item));
        }
        return new MyStream<>(mapList);
    }

    //terminal method
    public List<T> collList(){
        return lists;
    }



}

public class StreamAPI {
    public static void main(String[] args) {
        List<Integer> numbList = List.of(1,2,3,4,5);
        //stream api process
        List<Integer> result = new MyStream<>(numbList)//stream object
                            .filter(n->n>2)
                            .map(n->n*n)
                            .collList();

        //print out
        System.out.println(result);
    }
}
