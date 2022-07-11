/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author knfab
 */
public abstract class ReportQuery {
    
    public static ObservableList<Appointment> reportTwoQuery(int contactId) throws SQLException{
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.END, appointments.Customer_ID, appointments.User_ID, contacts.Contact_Name
                     FROM client_schedule.appointments
                     INNER JOIN client_schedule.contacts
                     ON appointments.Contact_ID = contacts.Contact_ID
                     WHERE appointments.Contact_ID = ?
                     ORDER BY appointments.Appointment_ID ASC;
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("END");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            String contactName = rs.getString("Contact_Name");
            start = Helper.TimeHandler.UTCtoSys(start);
            end = Helper.TimeHandler.UTCtoSys(end);
            String startString = Helper.TimeHandler.reformatTimestamp(start);
            String endString = Helper.TimeHandler.reformatTimestamp(end);
            Appointment result = new Appointment(appointmentId, title, description, location, type, startString, endString, customerId, userId, contactName);
            allAppointments.add(result);
        }
        
        return allAppointments; 
    }
    
    
    
    public static int reportOneQuery(int month, String type) throws SQLException{
        int total = 0;
        String sql = """
                     SELECT count(Appointment_ID) FROM client_schedule.appointments
                     WHERE ((SELECT EXTRACT(MONTH FROM appointments.start)) = ?) AND Type = ?
                     ;
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, month);
        ps.setString(2, type);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            total = rs.getInt("count(Appointment_ID)");
        }
        
        return total; 
    }
    
    public static int reportThreeQuery(int id) throws SQLException{
        int total = 0;
        String sql = """
                     SELECT count(Customer_ID) FROM client_schedule.customers
                     join client_schedule.first_level_divisions on customers.Division_ID = first_level_divisions.Division_ID
                     join client_schedule.countries on countries.Country_ID = first_level_divisions.Country_ID
                     WHERE countries.Country_ID = ?
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            total = rs.getInt("count(Customer_ID)");
        }
        
        return total; 
    }
}
