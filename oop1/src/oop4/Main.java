package oop4;

public class Main {
    public static void main(String[] args) {
        Employee emp1 = new Employee(8,"peter","tan", 2500);
        System.out.println(emp1);
        emp1.setSalary(999);
        System.out.println(emp1);
        System.out.println("id is: " + emp1.getId());
        System.out.println("name is: " + emp1.getName());
        System.out.println("annualsalary is: " + emp1.getAnnualSalary());
    }
}
