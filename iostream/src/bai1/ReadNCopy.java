package bai1;

import java.io.*;

public class ReadNCopy {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("input.txt");
             FileOutputStream fos = new FileOutputStream("output.txt")) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                fos.write(byteData);
            }
            System.out.println("Sao chép file thành công!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
