/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.time.LocalTime;
import Helper.Timeslot;
import Model.Appointment;
import Model.Contact;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Model.Customer;
import Model.User;
import java.io.IOException;
import java.text.ParseException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class UpdateAppointmentController implements Initializable {

    
    private static Appointment selectedId;
    Stage stage;
    Parent scene;
    
      @FXML
    private TextField appIdTxt;
      
      @FXML
    private TextField titleTxt;

    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private ComboBox<Customer> customerCombo;

    @FXML
    private DatePicker datePick;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private ComboBox<User> userCombo;
    
    @FXML
    private ComboBox<Timeslot> endCombo;
    
     @FXML
    private ComboBox<Timeslot> startCombo;


    @FXML
    void cancelBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments Menu");
        stage.show();

    }

    @FXML
    @SuppressWarnings("empty-statement")
    void saveAppointBtn(ActionEvent event) throws IOException, SQLException {
        
        String title = null;
        String description = null;
        String location = null;
        String type = null;
        Timeslot startTS;
        Timeslot endTS;
        Customer customer;
        
        
        
        
        
        try{
        //Gather add appointment form inputs
        
        if(titleTxt.getText().isEmpty() == false){
            title = titleTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
                        
        if(descriptionTxt.getText().isEmpty() == false){
            description = descriptionTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
        
        if(locationTxt.getText().isEmpty() == false){
            location = locationTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
        
        if(typeCombo.getSelectionModel().isEmpty() == false){
            type = typeCombo.getSelectionModel().getSelectedItem();
        }
        else{
            throw new NullPointerException();
        }
        
        startTS = startCombo.getSelectionModel().getSelectedItem();
        LocalTime startTime = startTS.getTwentyfour();
        
        endTS = endCombo.getSelectionModel().getSelectedItem();
        LocalTime endTime = endTS.getTwentyfour();
        
        customer = customerCombo.getSelectionModel().getSelectedItem();
        int customerId = customer.getCustomerID();
        
        Contact contact = contactCombo.getSelectionModel().getSelectedItem();
        int contactId = contact.getContactID();
        
        User user = userCombo.getSelectionModel().getSelectedItem();
        int userId = user.getUserId();
        
        LocalDate date = datePick.getValue();
        
                
        
        if(startTime.isBefore(endTime) == false){
            String message = "Start time must be before end time";
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> alert = error.showAndWait();
            return;
        }
        
        boolean businessHourCheck = Helper.TimeHandler.compareToBusinessHours(startTime, endTime, date);
        
        if(businessHourCheck == false){
            String message = "Change appointment time: \n appointment time not within business hours ";
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> alert = error.showAndWait();
            return;
        }
        int appointmentsOverlap = -1;
        appointmentsOverlap = DAO.appointmentQuery.appointmentOverlapCheckUpdate(startTime, endTime, date,selectedId.getAppointmentId(),selectedId.getCustomerId());
        if(appointmentsOverlap != -1){
            String message = "Overlapping appointment: \n The appointment you are trying to schedule Overlaps with Appointment_ID: " + appointmentsOverlap;
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> alert2 = error.showAndWait();
            return;
        }
        
        //convert date and Time
        Timestamp startTs = Helper.TimeHandler.sysToUTC(startTime, date);
        Timestamp endTs = Helper.TimeHandler.sysToUTC(endTime, date);
       
        
        
        DAO.appointmentQuery.updateAppointment(title, description, location, type, startTs, endTs, customerId, userId, contactId, selectedId.getAppointmentId());
                  
        }
        
        catch(NullPointerException npe){    
            String message = "";
            if(titleTxt.getText().isBlank()){
                message = message + "Title must not be left Blank \n \n";
            }

            if(descriptionTxt.getText().isBlank()){
                message = message + "description must not be left Blank \n \n";
            }

            if(locationTxt.getText().isBlank()){
                message = message + "location must not be left Blank \n \n";
            }

            if(startCombo.getSelectionModel().isEmpty() == true){
                message = message + "choose start time \n \n";
            }

            if(endCombo.getSelectionModel().isEmpty() == true){
                message = message +"Choose end time \n \n";
            }
            
            if(datePick.getValue() == null){
                message = message +"Choose a date \n \n";
            }

            if(typeCombo.getSelectionModel().isEmpty() == true){
                message = message + "Choose an appointment type \n \n";
            }

            if(customerCombo.getSelectionModel().isEmpty() == true){
                message = message + "Choose a customer \n \n";
            }

            if(contactCombo.getSelectionModel().isEmpty() == true){
                message = message + "Choose a contact \n \n";
            }

            if(userCombo.getSelectionModel().isEmpty() == true){
                message = message + "Choose a user \n \n";
            }
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> result2 = error.showAndWait();
            return;    
            }
        
        
        
        
        
        
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments Menu");
        stage.show();

    }

    
    
    public static void getSelectedId(int input) throws SQLException{
        
        UpdateAppointmentController.selectedId = DAO.appointmentQuery.selectAppointmentRecord(input);
        
        
    }
    
    private void autoSelectContact(String contactName) {
        for(Contact contact : contactCombo.getItems()){
            if(contact.getContactName().contentEquals(contactName)){
                contactCombo.getSelectionModel().select(contact);
            }
        }
    }
    
    private void autoSelectCustomer(int customerId) {
        for(Customer customer: customerCombo.getItems()){
            if(customer.getCustomerID() == customerId){
                customerCombo.getSelectionModel().select(customer);
            }
        }
    }
    
    private void autoSelectUser(int userId) {
        for(User user: userCombo.getItems()){
            if(user.getUserId() == userId){
                userCombo.getSelectionModel().select(user);
            }
        }
    }
    
    private void autoSelectStart(String timeslot) {
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        LocalDateTime ldt = LocalDateTime.parse(timeslot, Format);
        LocalTime lt = ldt.toLocalTime();
        for(Timeslot ts: startCombo.getItems()){
            if(ts.getTwentyfour().equals(lt)){
                startCombo.getSelectionModel().select(ts);
            }
        }
    }
    
    private void autoSelectEnd(String timeslot) {
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        LocalDateTime ldt = LocalDateTime.parse(timeslot, Format);
        LocalTime lt = ldt.toLocalTime();
        for(Timeslot ts: endCombo.getItems()){
            if(ts.getTwentyfour().equals(lt)){
                endCombo.getSelectionModel().select(ts);
            }
        }
    }
    
    private void autoSelectDate(String timeslot) {
        DateTimeFormatter Format = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");
        LocalDateTime ldt = LocalDateTime.parse(timeslot, Format);
        LocalDate ld = ldt.toLocalDate();
        datePick.setValue(ld);
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
        typeCombo.setItems(Model.Appointment.getAppointmentTypes());
        try {
            customerCombo.setItems(DAO.customerQuery.selectAllCustomerRecords());
        } catch (SQLException ex) {}
        try {
            userCombo.setItems(DAO.userQuery.selectAllUsers());
        } catch (SQLException ex) {}
        try {
            contactCombo.setItems(DAO.contactQuery.selectAllContacts());
        } catch (SQLException ex) {}
        try {
            startCombo.setItems(Helper.Timeslot.getTimeslots());
        } catch (ParseException ex) {}
        try {
            endCombo.setItems(Helper.Timeslot.getTimeslots());
        } catch (ParseException ex) {}
        
        //setting fields
        
        
        appIdTxt.setText(String.valueOf(selectedId.getAppointmentId()));
        titleTxt.setText(selectedId.getTitle());
        descriptionTxt.setText(selectedId.getDescription());
        locationTxt.setText(selectedId.getLocation());
        typeCombo.setValue(selectedId.getType());
        
        autoSelectContact(selectedId.getContactName());
        autoSelectCustomer(selectedId.getCustomerId());
        autoSelectUser(selectedId.getUserId());
        autoSelectStart(selectedId.getStart());
        autoSelectEnd(selectedId.getEnd());
        autoSelectDate(selectedId.getStart());
    }    
    
}
