package bai2;

import java.io.*;

public class InputFromKeyB {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             FileWriter fw = new FileWriter("outputt.txt")) {
            System.out.println("Nhập nội dung (gõ 'exit' để thoát):");
            String line;
            while (!(line = br.readLine()).equalsIgnoreCase("exit")) {
                fw.write(line + "\n");
            }
            System.out.println("Đã lưu nội dung vào file output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

