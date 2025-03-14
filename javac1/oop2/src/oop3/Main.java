package oop3;

public class Main {
    public static void main(String[] args) {
        Author au1 = new Author("Tan Ah Teck", "ahteck@nowhere.com");
        System.out.println(au1);

        au1.setEmail("ahteck@somewhere.com");
        System.out.println(au1);
        System.out.println(au1.getEmail());

        Book book = new Book("12345", "Java for dummies", au1, 8.8, 88);
        System.out.println(book);
    }
}
