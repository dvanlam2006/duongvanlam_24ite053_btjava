package dao;

import model.Customer;
import java.sql.*;
import java.util.*;

public class CustomerDAO {
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        DatabaseMetaData DBConnection = null;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                list.add(new Customer(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

