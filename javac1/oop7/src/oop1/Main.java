package oop1;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("John Doe");
        customer.setMember(true);
        customer.setMemberType("Gold");

        Visit visit = new Visit(customer.getName(), new Date());
        visit.setServiceExpense(100);
        visit.setProductExpense(50);

        System.out.println(visit);
        System.out.println("Total Expense: " + visit.getTotalExpense());
    }
}
