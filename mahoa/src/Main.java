import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            // Cố gắng sử dụng look and feel của hệ thống
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Trường hợp không thiết lập được, sử dụng mặc định
            System.out.println("Không thể thiết lập giao diện hệ thống: " + e.getMessage());
        }

        // Chạy ứng dụng trong Event Dispatch Thread của Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    EncryptionApp app = new EncryptionApp();
                    app.setVisible(true);
                    System.out.println("Ứng dụng mã hóa đã khởi động thành công!");
                } catch (Exception e) {
                    System.err.println("Lỗi khởi động ứng dụng: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}