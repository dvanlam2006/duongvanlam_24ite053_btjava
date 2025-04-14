package gui;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TotalAmountPanel extends JPanel {
    private JComboBox<Customer> customerBox;
    private JComboBox<Order> orderBox;
    private JLabel resultLabel;

    private OrderDAO orderDAO = new OrderDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    public TotalAmountPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        customerBox = new JComboBox<>();
        orderBox = new JComboBox<>();
        JButton calcButton = new JButton("Tính tổng tiền");

        topPanel.add(new JLabel("Khách hàng:"));
        topPanel.add(customerBox);
        topPanel.add(new JLabel("Đơn hàng:"));
        topPanel.add(orderBox);
        topPanel.add(calcButton);

        add(topPanel, BorderLayout.NORTH);

        resultLabel = new JLabel("Tổng tiền: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.CENTER);

        for (Customer c : customerDAO.getAllCustomers()) {
            customerBox.addItem(c);
        }

        customerBox.addActionListener(e -> {
            orderBox.removeAllItems();
            Customer selected = (Customer) customerBox.getSelectedItem();
            if (selected != null) {
                List<Order> orders = orderDAO.getOrdersByCustomer(selected.getId());
                for (Order o : orders) {
                    orderBox.addItem(o);
                }
            }
        });

        calcButton.addActionListener(e -> {
            Order selected = (Order) orderBox.getSelectedItem();
            if (selected != null) {
                double total = orderDAO.calculateTotalAmount(selected.getId());
                resultLabel.setText("Tổng tiền: " + total + " VNĐ");
            }
        });
    }
}
