/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Customer;
/**
 *
 * @author knfab
 */
public abstract class customerQuery {
    
    /**
     *  
     * @param customerId
     * @return 
     * @throws java.sql.SQLException
     */
    public static Customer selectCustomerRecord(int customerId) throws SQLException{
        Customer customer = null;
        String sql = """
                     SELECT Customers.Customer_ID, Customers.Customer_Name, Customers.Address, Customers.Postal_Code, 
                     Customers.Phone, Customers.Division_ID, first_level_divisions.Division, countries.Country
                     From client_schedule.customers
                     INNER JOIN client_schedule.first_level_divisions
                     ON Customers.Division_ID = first_level_divisions.Division_ID
                     INNER JOIN client_schedule.countries
                     ON first_level_divisions.Country_ID = countries.Country_ID
                     WHERE Customer_ID = ?""";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            customerId = customerId;
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String Country = rs.getString("Country");
            customer = new Customer(customerId,customerName, address, postalCode, phone, divisionId, division,  Country);
            }
        return customer;
       
               
    }
    
    public static ObservableList<Customer> selectAllCustomerRecords() throws SQLException{
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        String sql = """
                     SELECT Customers.Customer_ID, Customers.Customer_Name, Customers.Address, Customers.Postal_Code, 
                     Customers.Phone, Customers.Division_ID,first_level_divisions.Division, countries.Country
                     From client_schedule.customers
                     INNER JOIN client_schedule.first_level_divisions
                     ON Customers.Division_ID = first_level_divisions.Division_ID
                     INNER JOIN client_schedule.countries
                     ON first_level_divisions.Country_ID = countries.Country_ID
                     ORDER BY Customers.Customer_ID ASC"""; 
                     
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String Country = rs.getString("Country");
            Model.Customer result = new Customer(customerId, customerName, address, postalCode, phone, divisionId, division, Country);
            allCustomers.add(result);
           
            
            }
        return allCustomers;
        
        
    }
    
    public static int insertCustomer(String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException{
        String userName = "Steve";
        String sql = "INSERT INTO Customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, (SELECT utc_timestamp()), ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setString(5, userName);
        ps.setTimestamp(6, Helper.TimeHandler.UTCtimeStamp());
        ps.setString(7, userName);
        ps.setInt(8, divisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
        
    }
    
    
    public static int updateCustomer(int customerId, String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException{
        String userId = "Steve";
        String sql = "UPDATE client_schedule.Customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?, Last_Update = (SELECT utc_timestamp()), Last_Updated_By = ?  WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);
        ps.setString(6, userId);/* This is the User*/
        ps.setInt(7, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
    
    public static void deleteCustomer(int customerID) throws SQLException{
        String sql = "DELETE FROM Customers WHERE Customer_ID = ?";
        String sql2 = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        PreparedStatement ps2 = JDBC.connection.prepareStatement(sql2);
        ps.setInt(1, customerID);
        ps2.setInt(1, customerID);
        ps.executeUpdate();
        ps2.executeUpdate();
        
               
    }
    
    public static int nextAvailableCustomerID() throws SQLException{
        int nextId = 0;
        String sql = """
                     SELECT AUTO_INCREMENT
                     FROM information_schema.TABLES
                     WHERE TABLE_SCHEMA = "client_schedule"
                     and TABLE_NAME = "customers"
                     """;
        String sql2 = """
                     analyze table client_schedule.appointments
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        PreparedStatement ps2 = JDBC.connection.prepareStatement(sql2);
        ps2.executeQuery();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            nextId = rs.getInt("AUTO_INCREMENT");
        }
        return nextId;
        
    }
    
}
            
                    
            
            
            
            
            
    

