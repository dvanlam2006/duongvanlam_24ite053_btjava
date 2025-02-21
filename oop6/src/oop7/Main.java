package oop7;

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("KiKi");
        dog1.greets();
        Cat cat1 = new Cat("Mimi");
        cat1.greets();

        Animal animal1 = new Dog("BiBi");
        animal1.greets();
        Animal animal2 = new BigDog("Meme");
        animal2.greets();
        Animal animal3 = new Cat("Lili");

        Dog dog2 = (Dog)animal2;
        dog2.greets(dog1);
        Cat cat2 = (Cat)animal3;
        cat2.greets();
    }
}
