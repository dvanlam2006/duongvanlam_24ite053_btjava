package oop4;

public class Main {
    public static void main(String[] args) {
        Customer c1 = new Customer(88, "Tan Ah Teck", 10);
        System.out.println(c1);

        c1.setDiscount(8);
        System.out.println(c1);

        Invoice inv1 = new Invoice(101, c1, 888.8);
        System.out.println(inv1);

        inv1.setAmount(999.9);
        System.out.println(inv1);

        System.out.println("customer is: " + inv1.getCustomer());


    }

}
