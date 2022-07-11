/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.time.LocalTime;
import Helper.Timeslot;
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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class AddAppointmentController implements Initializable {
    
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
        appointmentsOverlap = DAO.appointmentQuery.appointmentOverlapCheck(startTime, endTime, date, customerId);
        if(appointmentsOverlap != -1){
            String message = "Overlapping appointment: \n The appointment you are trying to schedule Overlaps with Appointment_ID: " + appointmentsOverlap;
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> alert2 = error.showAndWait();
            return;
        }
        
        //convert date and Time
        Timestamp startTs = Helper.TimeHandler.sysToUTC(startTime, date);
        Timestamp endTs = Helper.TimeHandler.sysToUTC(endTime, date);
       
        
        
        DAO.appointmentQuery.insertAppointment(title, description, location, type, startTs, endTs, customerId, userId, contactId);
                  
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

    private void nextId(){
        String nextId = null;
        try {
              nextId = String.valueOf(DAO.appointmentQuery.nextAvailableAppointmentID());
              appIdTxt.setText(nextId);
                        
          } catch (SQLException ex) {}
        appIdTxt.setText(nextId);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
          
        nextId();
         
        
        
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
        
    }    
    
}
