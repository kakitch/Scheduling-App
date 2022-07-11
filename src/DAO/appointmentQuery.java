/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Appointment;
import Model.CurrentUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author knfab
 */
public abstract class appointmentQuery {
    
    /**
     *  
     * @param appointmentId
     * @return 
     * @throws java.sql.SQLException
     */
    public static Appointment selectAppointmentRecord(int appointmentId) throws SQLException{
        Appointment appointment = null;
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.END, appointments.Customer_ID, appointments.User_ID, contacts.Contact_Name 
                     FROM client_schedule.appointments
                     INNER JOIN client_schedule.contacts
                     ON appointments.Contact_ID = contacts.Contact_ID
                     WHERE appointments.Appointment_ID = ? 
                     ORDER BY appointments.Start ASC, appointments.Appointment_ID ASC
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String title = rs.getString("Title");
            String disc = rs.getString("Description");
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
            appointment = new Appointment(appointmentId, title, disc, location, type, startString, endString, customerId, userId, contactName);
            }
        return appointment; 
       
               
    }
    
    
    public static ObservableList<Appointment> selectAllAppointments() throws SQLException{
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.END, appointments.Customer_ID, appointments.User_ID, contacts.Contact_Name 
                     FROM client_schedule.appointments
                     INNER JOIN client_schedule.contacts
                     ON appointments.Contact_ID = contacts.Contact_ID
                     ORDER BY appointments.Appointment_ID ASC
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
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
    
    
    public static ObservableList<Appointment> selectThisMonthsAppointments() throws SQLException{
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.END, appointments.Customer_ID, appointments.User_ID, contacts.Contact_Name
                     FROM client_schedule.appointments
                     INNER JOIN client_schedule.contacts
                     ON appointments.Contact_ID = contacts.Contact_ID
                     WHERE (appointments.Start >= ? AND (appointments.End < ? + INTERVAL 1 MONTH))
                     ORDER BY appointments.Appointment_ID ASC;
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, Helper.TimeHandler.UTCtimeStamp());
        ps.setTimestamp(2, Helper.TimeHandler.UTCtimeStamp());
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
    
    
    
    public static ObservableList<Appointment> selectThisWeeksAppointments() throws SQLException{
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Title, appointments.Description, appointments.Location, appointments.Type, appointments.Start, appointments.END, appointments.Customer_ID, appointments.User_ID, contacts.Contact_Name
                     FROM client_schedule.appointments
                     INNER JOIN client_schedule.contacts
                     ON appointments.Contact_ID = contacts.Contact_ID
                     WHERE (appointments.Start >= ? AND (appointments.End < ? + INTERVAL 1 WEEK))
                     ORDER BY appointments.Appointment_ID ASC;
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, Helper.TimeHandler.UTCtimeStamp());
        ps.setTimestamp(2, Helper.TimeHandler.UTCtimeStamp());
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
    
    /**
     *
     * @param title
     * @param description
     * @param location
     * @param type
     * @param Start
     * @param End
     * @param customerId
     * @param userId
     * @param contactId
     * @return
     * @throws SQLException
     */
    public static int insertAppointment(String title, String description, String location, String type, Timestamp Start, Timestamp End, int customerId, int userId, int contactId) throws SQLException{
        String currentUser = CurrentUser.getUserName();
        Timestamp ts = Helper.TimeHandler.UTCtimeStamp();
        String sql = """
                     INSERT INTO client_schedule.appointments (Title, Description, Location, Type, Start, End,  Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);""";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Start);
        ps.setTimestamp(6, End);
        ps.setTimestamp(7, ts);
        ps.setString(8, currentUser);
        ps.setTimestamp(9, ts);
        ps.setString(10, currentUser);
        ps.setInt(11, customerId);
        ps.setInt(12, userId);
        ps.setInt(13, contactId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
        
    }
    
    
    public static int updateAppointment(String title, String description, String location, String type, Timestamp Start, Timestamp End, int customerId, int userId, int contactId, int appointmentId) throws SQLException{
        String currentUser = CurrentUser.getUserName();
        Timestamp ts = Helper.TimeHandler.UTCtimeStamp();
        String sql = "UPDATE Appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?,  Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Start);
        ps.setTimestamp(6, End);
        ps.setTimestamp(7, ts);
        ps.setString(8, currentUser);
        ps.setInt(9, customerId);
        ps.setInt(10, userId);
        ps.setInt(11, contactId);
        ps.setInt(12, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
    
    public static int deleteAppointment(int appointmentID) throws SQLException{
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
    
    public static int nextAvailableAppointmentID() throws SQLException{
        int nextId = 0;
        String sql = """
                     SELECT AUTO_INCREMENT
                     FROM information_schema.TABLES
                     WHERE TABLE_SCHEMA = "client_schedule"
                     and TABLE_NAME = "appointments"
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
    
    public static int appointmentOverlapCheck(LocalTime start, LocalTime end,LocalDate date,int id) throws SQLException{
        int overlappingId = -1;
        Timestamp aStart = Helper.TimeHandler.sysToUTC(start, date);
        Timestamp aEnd = Helper.TimeHandler.sysToUTC(end, date);
        
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Start, appointments.END
                     FROM client_schedule.appointments
                     where appointments.Customer_ID = ?
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            Timestamp compStart = rs.getTimestamp("Start");
            Timestamp compEnd = rs.getTimestamp("END");
            if((compStart.before(aStart) && (compEnd.before(aStart) || compEnd.equals(aStart))) || ((compStart.after(aEnd) || compStart.equals(aEnd)) && compEnd.after(aEnd)) == true ){
                //System.out.println(appointmentId + ": pass");
            }   
           else{
                
                //System.out.println(appointmentId + ": fail 1 " + aStart +  aEnd  + compStart + compEnd );
                overlappingId = appointmentId;
            }
        }
        return overlappingId; 
    }
    
    
    public static int appointmentOverlapCheckUpdate(LocalTime start, LocalTime end,LocalDate date,int appointId,int customerId) throws SQLException{
        int overlappingId = -1;
        Timestamp aStart = Helper.TimeHandler.sysToUTC(start, date);
        Timestamp aEnd = Helper.TimeHandler.sysToUTC(end, date);
        
        String sql = """
                     SELECT appointments.Appointment_ID, appointments.Start, appointments.END
                     FROM client_schedule.appointments
                     WHERE appointments.Appointment_ID != ? AND appointments.Customer_ID = ?
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointId);
        ps.setInt(2, customerId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int appointmentId = rs.getInt("Appointment_ID");
            Timestamp compStart = rs.getTimestamp("Start");
            Timestamp compEnd = rs.getTimestamp("END");
            if((compStart.before(aStart) && (compEnd.before(aStart) || compEnd.equals(aStart))) || ((compStart.after(aEnd) || compStart.equals(aEnd)) && compEnd.after(aEnd)) == true ){
                //System.out.println(appointmentId + ": pass");
            }   
           else{
                
                //System.out.println(appointmentId + ": fail 1 " + aStart +  aEnd  + compStart + compEnd );
                overlappingId = appointmentId;
            }
        }
        return overlappingId; 
    }
    
    public static int appointmentNext15Min() throws SQLException{
        //Timestamp test = Timestamp.valueOf(LocalDateTime.of(2022, 6, 13, 19, 40));
        //System.out.println(test);
        Timestamp ts = Helper.TimeHandler.UTCtimeStamp();
        int appointmentId = -1;
        String sql = """
                     SELECT appointments.Appointment_ID
                     FROM client_schedule.appointments
                     WHERE (appointments.Start >= ? AND (appointments.Start < ? + INTERVAL 15 MINUTE))
                     """;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, ts);
        ps.setTimestamp(2, ts);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            appointmentId = rs.getInt("Appointment_ID");
                       
        }
        return appointmentId; 
    }
    
}
