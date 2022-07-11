/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointment;
import Model.Contact;
import Model.Country;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class ReportMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    //report 1 controls
    
    @FXML
    private ComboBox<String> typeCombo;
    
    @FXML
    private ComboBox<String> MonthCombo;

    @FXML
    private Label reportOneOutput;

    

    
   
    //report 2 controls
    @FXML
    private ComboBox<Contact> contactCombo;
    
    @FXML
    private TableView<Appointment> reportTwoTV;
    
    @FXML
    private TableColumn<Appointment, Integer> AppointmentIdCol;

    @FXML
    private TableColumn<Appointment, String> titleCol;

    @FXML
    private TableColumn<Appointment, String> typeCol;
    
    @FXML
    private TableColumn<Appointment, String> startCol;
    
    @FXML
    private TableColumn<Appointment, String> endCol;
    
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;
    
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    
    
    
    
    
    
    //report 3 controls
    @FXML
    private ComboBox<Country> countryCombo;
    
    @FXML
    private Label reportThreeOutput;
    
   

    

    

    
      @FXML
    void cancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment Menu");
        stage.show();

    }

     @FXML
    void reportOneRun(ActionEvent event) throws SQLException {
        
        try{
        String type = typeCombo.getSelectionModel().getSelectedItem();
        String month = MonthCombo.getSelectionModel().getSelectedItem();
        
        int monthInt = 4;
            switch (month) {
                case "January":
                    monthInt = 1;
                    break;
                case "Febuary":
                    monthInt = 2;
                    break;    
                case "March":
                    monthInt = 3;
                    break;    
                case "April":
                    monthInt = 4;
                    break;    
                case "May":
                    monthInt = 5;
                    break;    
                case "June":
                    monthInt = 6;
                    break;     
                case "July":
                    monthInt = 7;
                    break;
                case "August":
                    monthInt = 8;
                    break;    
                case "September":
                    monthInt = 9;
                    break;    
                case "October":
                    monthInt = 10;
                    break;    
                case "November":
                    monthInt = 11;
                    break;    
                case "December":
                    monthInt = 12;
                    break;         
  
            }
            
        int total = DAO.ReportQuery.reportOneQuery(monthInt, type);
        
        reportOneOutput.setText("Number of Appoingments : " + total);
        }
        catch(NullPointerException npe){
            String message = "";
            if(MonthCombo.getSelectionModel().isEmpty() == true){
                message = message + "month must not be left blank \n \n";
            }
            
            if(typeCombo.getSelectionModel().isEmpty() == true){
                message = message + "type must not be left blank \n \n";
            }
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> result2 = error.showAndWait();
        
        }
    }

    @FXML
    void reportThreeRun(ActionEvent event) throws SQLException {
        try{
        int ID = countryCombo.getSelectionModel().getSelectedItem().getCountryID();
        int total = DAO.ReportQuery.reportThreeQuery(ID);
        reportThreeOutput.setText("Number of Customers : " + total);
        }
        catch(NullPointerException npe){
            String message = "";
            if(countryCombo.getSelectionModel().isEmpty() == true){
                message = message + "Country must not be left blank \n \n";
            }
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> result2 = error.showAndWait();
        }
    }

    @FXML
    void reportTwoRun(ActionEvent event) throws SQLException {
       try{
       ObservableList<Appointment> appointments = DAO.ReportQuery.reportTwoQuery(contactCombo.getSelectionModel().getSelectedItem().getContactID());
       
       reportTwoTV.setItems(appointments);
       
       AppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
       titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
       typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
       startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
       endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
       customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
       descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
       }
       catch(NullPointerException npe){
           String message = "";
           if(contactCombo.getSelectionModel().isEmpty() == true){
                message = message + "Contact must not be left blank \n \n";
            }
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> result2 = error.showAndWait();
       }
       
    }
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            try {

                
                
                contactCombo.setItems(DAO.contactQuery.selectAllContacts());
                typeCombo.setItems(Appointment.getAppointmentTypes());
                try {
                    countryCombo.setItems(DAO.countryQuery.selectAllCountries());
                } catch (SQLException ex) {
                    Logger.getLogger(ReportMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ReportMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        ObservableList<String> months = FXCollections.observableArrayList();
        months.add("January");
        months.add("Febuary");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        
        MonthCombo.setItems(months);
    }
}
    