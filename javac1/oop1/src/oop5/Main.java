package oop5;

public class Main {
    public static void main(String[] args) {
        InvoiceItem inv = new InvoiceItem("A101", "Pen red", 888, 0.08);
        System.out.println(inv);
        inv.setQty(999);
        inv.setUnitPrice(0.99);
        System.out.println(inv);
        System.out.println("id is: " + inv.getId());
        System.out.println("the total is: " + inv.getTotal());
    }
}
