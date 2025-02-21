package oop2;

public class Main {
    public static void main(String[] args) {
        Author author1 = new Author("Lam", "lamzxc", 'm');
        Author author2 = new Author("Lam2", "lamzxc2", 'm');
        System.out.println(author1);
        System.out.println(author2);
        Author[] authors = {author1, author2};
        Book book = new Book("Java for Dummies", authors, 19.95, 99);
        System.out.println(book);
    }
}
