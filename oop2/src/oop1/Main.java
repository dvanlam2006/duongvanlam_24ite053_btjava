package oop1;

public class Main {
    public static void main(String[] args) {
        Author author = new Author("Tan Ah Teck", "ahteck@nowhere.com", 'm');
        System.out.println(author);
        Book book = new Book("Java for dummy", author, 19.95, 99);
        System.out.println(book);
        book.setPrice(12.34);
        book.setQty(22);
        System.out.println(book);
    }
}