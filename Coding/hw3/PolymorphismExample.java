// Parent class
class Animal {
   public void makeSound() {
      System.out.println("Animal makes a sound");
   }
}

// Child Classes
class Dog extends Animal {
   @Override
   public void makeSound() {
      System.out.println("Dog barks");
   }
}

class Cat extends Animal {
   @Override
   public void makeSound() {
      System.out.println("Cat meows");
   }
}

// Usage
public class PolymorphismExample {
   public static void main(String[] args) {
      Animal myAnimal = new Animal();  // Animal reference and object
      Animal myDog = new Dog();        // Animal reference but Dog object
      Animal myCat = new Cat();        // Animal reference but Cat object

      myAnimal.makeSound();  // Outputs: Animal makes a sound
      myDog.makeSound();     // Outputs: Dog barks
      myCat.makeSound();     // Outputs: Cat meows
   }
}