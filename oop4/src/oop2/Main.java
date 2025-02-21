package oop2;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("John Doe", "123 Main St");
        System.out.println(person);

        Student student = new Student("Jane Doe", "456 College Rd",
                "Computer Science", 2023, 1500.0);
        System.out.println(student);

        Staff staff = new Staff("Dr. Smith", "789 School Ln",
                "XYZ High School", 2500.0);
        System.out.println(staff);
    }
}

