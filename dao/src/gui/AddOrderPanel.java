package gui;

import dao.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.*;
import java.util.List;

public class AddOrderPanel extends JPanel {
    private JComboBox<Customer> customerBox;
    private JComboBox<Product> productBox;
    private JSpinner quantitySpinner;
    private final DefaultListModel<String> cartModel;
    private List<OrderItem> cartItems = new ArrayList<>();

    private ProductDAO productDAO = new ProductDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private OrderDAO orderDAO = new OrderDAO();

    public AddOrderPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout());
        customerBox = new JComboBox<>();
        productBox = new JComboBox<>();
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton addButton = new JButton("Thêm vào giỏ");

        topPanel.add(new JLabel("Khách hàng:"));
        topPanel.add(customerBox);
        topPanel.add(new JLabel("Sản phẩm:"));
        topPanel.add(productBox);
        topPanel.add(new JLabel("Số lượng:"));
        topPanel.add(quantitySpinner);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.NORTH);

        cartModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartModel);
        add(new JScrollPane(cartList), BorderLayout.CENTER);

        JButton orderButton = new JButton("Tạo đơn hàng");
        add(orderButton, BorderLayout.SOUTH);

        // Load dữ liệu ban đầu
        for (Customer c : customerDAO.getAllCustomers()) {
            customerBox.addItem(c);
        }

        for (Product p : productDAO.getAllProducts()) {
            productBox.addItem(p);
        }

        // Thêm vào giỏ hàng
        addButton.addActionListener(e -> {
            Product p = (Product) productBox.getSelectedItem();
            int quantity = (int) quantitySpinner.getValue();
            cartItems.add(new OrderItem(p.getId(), quantity));
            cartModel.addElement(p.getName() + " x" + quantity);
        });

        // Tạo đơn hàng
        orderButton.addActionListener(e -> {
            if (cartItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giỏ hàng trống!");
                return;
            }

            Customer c = (Customer) customerBox.getSelectedItem();
            Order o = new Order();
            o.setCustomerId(c.getId());
            o.setOrderDate(new Date(System.currentTimeMillis()));
            orderDAO.addOrder(o, cartItems);

            JOptionPane.showMessageDialog(this, "Tạo đơn hàng thành công!");

            cartModel.clear();
            cartItems.clear();
        });
    }
}
