import gui.AddOrderPanel;
import gui.OrderHistoryPanel;
import gui.TotalAmountPanel;

import javax.swing.*;
import java.awt.*;

public class OrderManagementApp extends JFrame {

    public OrderManagementApp() {
        setTitle("Quản lý đơn hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("➕ Thêm đơn hàng", new AddOrderPanel());
        tabbedPane.addTab("📜 Lịch sử đơn hàng", new OrderHistoryPanel());
        tabbedPane.addTab("💰 Tính tổng tiền", new TotalAmountPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new OrderManagementApp().setVisible(true);
        });
    }
}

