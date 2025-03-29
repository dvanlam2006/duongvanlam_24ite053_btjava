

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class Readfile {
    private static final String FILE_NAME = "company.xml";

    public static void main(String[] args) {
        try {
            File file = new File(FILE_NAME);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList employeeList = doc.getElementsByTagName("employee");

            for (int i = 0; i < employeeList.getLength(); i++) {
                Element employee = (Element) employeeList.item(i);
                String id = employee.getAttribute("id");
                String name = employee.getElementsByTagName("name").item(0).getTextContent();

                Element contact = (Element) employee.getElementsByTagName("contact").item(0);
                String email = contact.getElementsByTagName("email").item(0).getTextContent();
                String phone = contact.getElementsByTagName("phone").item(0).getTextContent();

                Element department = (Element) employee.getElementsByTagName("department").item(0);
                String deptName = department.getElementsByTagName("name").item(0).getTextContent();
                String location = department.getElementsByTagName("location").item(0).getTextContent();

                System.out.println("Employee ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Department: " + deptName);
                System.out.println("Location: " + location);
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
