package generic;

public class MyGenericClass<T> {
    private T value;

    public MyGenericClass(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
}