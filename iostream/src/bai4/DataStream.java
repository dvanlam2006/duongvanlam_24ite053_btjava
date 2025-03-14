package bai4;

import java.io.*;

public class DataStream {
    public static void main(String[] args) {
        String filename = "numbers.dat";

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            int[] numbers = {10, 20, 30, 40, 50};
            for (int num : numbers) {
                dos.writeInt(num);
            }
            System.out.println("Đã ghi danh sách số nguyên vào file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            while (dis.available() > 0) {
                System.out.println("Số đọc được: " + dis.readInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

