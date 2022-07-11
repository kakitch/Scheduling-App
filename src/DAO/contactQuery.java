/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Appointment;
import Model.Contact;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author knfab
 */
public abstract class contactQuery {
    
    public static int insert(String contactName, String email) throws SQLException{
        String sql = "INSERT INTO Contacts (Contact_Name, Email) VALUES (?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, contactName);
        ps.setString(2, email);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
        
    }
    
    
    public static int update(String contactName, String email) throws SQLException{
        String sql = "UPDATE Contacts SET Email = ? WHERE Contact_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, contactName);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
        
    }
    
    public static int delete(int contactID) throws SQLException{
        String sql = "DELETE FROM Contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
    
    public static Contact selectContact(int contactId) throws SQLException{
        Contact result = null;
        String sql = "SELECT * FROM Contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email"); 
            result = new Contact(contactId, contactName, email);    
        }
        return result;
    }
    
    public static ObservableList<Contact> selectAllContacts() throws SQLException{
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email"); 
            Contact result = new Contact(contactId, contactName, email);  
            allContacts.add(result);
        }
        return allContacts;
    }
}