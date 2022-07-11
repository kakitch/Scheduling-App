/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author knfab
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private int customerId;
    private int userId;
    private String contactName;
    private String start;
    private String end;
    

    public Appointment(int appointmentId, String title, String description, String location, String type, String Start, String End, int customerId, int userId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = Start;
        this.end = End;
        this.customerId = customerId;
        this.userId = userId;
        this.contactName = contactName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentID) {
        this.appointmentId = appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String Start) {
        this.start = Start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String End) {
        this.end = End;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerID) {
        this.customerId = customerID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userID) {
        this.userId = userID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public static ObservableList<String> getAppointmentTypes(){
        ObservableList<String> types = FXCollections.observableArrayList();
        types.add("Lunch");
        types.add("Coffee");
        types.add("Business Meeting");
        types.add("Sales Meeting");
        return types;
    }
   
    
 
}
