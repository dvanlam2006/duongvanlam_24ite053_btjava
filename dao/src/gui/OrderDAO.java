package gui;

import model.Order;
import model.OrderItem;

import java.util.List;

public class OrderDAO {
    public void addOrder(Order o, List<OrderItem> cartItems) {
    }

    public List<Order> getOrdersByCustomer(int id) {
        return List.of();
    }

    public double calculateTotalAmount(int id) {
        return 0;
    }
}
