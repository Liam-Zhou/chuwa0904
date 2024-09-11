class Parent {
    int number = 50;

    void display() {
        System.out.println("Parent number: " + number);
    }
}

class Child extends Parent {
    int number = 100;

    void show() {
        System.out.println("Child number: " + this.number);  // Refers to Child's instance variable
        System.out.println("Parent number: " + super.number);  // Refers to Parent's instance variable
    }

    @Override
    void display() {
        super.display();  // Calls the display method of the Parent class
        System.out.println("Child display method");
    }
}
