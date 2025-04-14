package model;

import java.sql.Date;

public class Customer {
    private int id;
    private String name;

    public Customer() {}
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public String toString() {
        return name;
    }

    public static class Order {
        private int id;
        private int customerId;
        private Date orderDate;

        public Order() {}

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getCustomerId() { return customerId; }
        public void setCustomerId(int customerId) { this.customerId = customerId; }

        public Date getOrderDate() { return orderDate; }
        public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    }

    public static class OrderItem {
        private int productId;
        private int quantity;

        public OrderItem(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() { return productId; }
        public int getQuantity() { return quantity; }
    }

    public static class Product {
        private int id;
        private String name;
        private double price;

        public Product() {}
        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }
    }
}

