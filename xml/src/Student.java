

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Scanner;

public class Student {
    private static final String FILE_NAME = "students.xml";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option: \n1. Add Student\n2. Delete Student by ID\n3. Update Student by ID");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter student ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter student age: ");
            String age = scanner.nextLine();
            System.out.print("Enter student major: ");
            String major = scanner.nextLine();

            addStudentToXML(id, name, age, major);
        } else if (choice == 2) {
            System.out.print("Enter student ID to delete: ");
            String idToDelete = scanner.nextLine();
            deleteStudentFromXML(idToDelete);
        } else if (choice == 3) {
            System.out.print("Enter student ID to update: ");
            String idToUpdate = scanner.nextLine();
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new age: ");
            String newAge = scanner.nextLine();
            System.out.print("Enter new major: ");
            String newMajor = scanner.nextLine();

            updateStudentInXML(idToUpdate, newName, newAge, newMajor);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void writeXMLToFile(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(FILE_NAME));
        transformer.transform(source, result);
    }

    private static void addStudentToXML(String id, String name, String age, String major) {
        try {
            File file = new File(FILE_NAME);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc;

            if (file.exists()) {
                doc = dBuilder.parse(file);
                doc.getDocumentElement().normalize();
            } else {
                doc = dBuilder.newDocument();
                Element rootElement = doc.createElement("Students");
                doc.appendChild(rootElement);
            }

            Element student = doc.createElement("Student");
            student.setAttribute("id", id);

            Element nameElement = doc.createElement("Name");
            nameElement.appendChild(doc.createTextNode(name));
            student.appendChild(nameElement);

            Element ageElement = doc.createElement("Age");
            ageElement.appendChild(doc.createTextNode(age));
            student.appendChild(ageElement);

            Element majorElement = doc.createElement("Major");
            majorElement.appendChild(doc.createTextNode(major));
            student.appendChild(majorElement);

            doc.getDocumentElement().appendChild(student);

            writeXMLToFile(doc);
            System.out.println("Student data has been successfully added to " + FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudentFromXML(String id) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList studentList = doc.getElementsByTagName("Student");
            boolean found = false;

            for (int i = 0; i < studentList.getLength(); i++) {
                Element student = (Element) studentList.item(i);
                if (student.getAttribute("id").equals(id)) {
                    student.getParentNode().removeChild(student);
                    found = true;
                    break;
                }
            }

            if (found) {
                writeXMLToFile(doc);
                System.out.println("Student with ID " + id + " has been deleted.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateStudentInXML(String id, String newName, String newAge, String newMajor) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File not found.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList studentList = doc.getElementsByTagName("Student");
            boolean found = false;

            for (int i = 0; i < studentList.getLength(); i++) {
                Element student = (Element) studentList.item(i);
                if (student.getAttribute("id").equals(id)) {
                    student.getElementsByTagName("Name").item(0).setTextContent(newName);
                    student.getElementsByTagName("Age").item(0).setTextContent(newAge);
                    student.getElementsByTagName("Major").item(0).setTextContent(newMajor);
                    found = true;
                    break;
                }
            }

            if (found) {
                writeXMLToFile(doc);
                System.out.println("Student with ID " + id + " has been updated.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

