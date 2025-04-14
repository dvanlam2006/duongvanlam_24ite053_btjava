package gui;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderHistoryPanel extends JPanel {
    private JComboBox<Customer> customerBox;
    private DefaultListModel<String> orderModel;
    private final OrderDAO orderDAO = new OrderDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    public OrderHistoryPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        customerBox = new JComboBox<>();
        JButton loadButton = new JButton("Xem lịch sử");

        topPanel.add(new JLabel("Chọn khách hàng:"));
        topPanel.add(customerBox);
        topPanel.add(loadButton);

        add(topPanel, BorderLayout.NORTH);

        orderModel = new DefaultListModel<>();
        JList<String> orderList = new JList<>(orderModel);
        add(new JScrollPane(orderList), BorderLayout.CENTER);

        for (Customer c : customerDAO.getAllCustomers()) {
            customerBox.addItem(c);
        }

        loadButton.addActionListener(e -> {
            orderModel.clear();
            Customer selected = (Customer) customerBox.getSelectedItem();
            List<Order> orders = orderDAO.getOrdersByCustomer(selected.getId());
            if (orders.isEmpty()) {
                orderModel.addElement("Không có đơn hàng nào.");
            } else {
                for (Order o : orders) {
                    orderModel.addElement("Đơn #" + o.getId() + " - Ngày: " + o.getOrderDate());
                }
            }
        });
    }
}
