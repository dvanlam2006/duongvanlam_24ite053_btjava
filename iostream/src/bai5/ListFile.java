package bai5;

import java.io.File;

public class ListFile {
    public static void main(String[] args) {
        File directory = new File("C:\\Users\\Public");
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            System.out.println("Danh sách file trong thư mục:");
            if (files != null) {
                for (File file : files) {
                    System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("Thư mục không tồn tại hoặc không hợp lệ.");
        }
    }
}

