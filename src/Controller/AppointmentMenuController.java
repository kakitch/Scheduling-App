/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class AppointmentMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    
    /* all appointments table components*/
    @FXML
    private TableView<Appointment> appointTbleA;
    
    @FXML
    private TableColumn<Appointment, Integer> appIDA;
    
    @FXML
    private TableColumn<Appointment, String> titleA;
    
    @FXML
    private TableColumn<Appointment, String> descA;
    
    @FXML
    private TableColumn<Appointment, String> LocationA;
    
    @FXML
    private TableColumn<Appointment, String> contactA;
    
    @FXML
    private TableColumn<Appointment, String> typeA;
    
    @FXML
    private TableColumn<Appointment, String> startA;
    
    @FXML
    private TableColumn<Appointment, String> EndA;
    
    @FXML
    private TableColumn<Appointment, Integer> cIDA;
    
    @FXML
    private TableColumn<Appointment, Integer> uIDA;
    
    
    
    
    /* This Month Table Components*/
    @FXML
    private TableView<Appointment> appointTbleM;
    
    @FXML
    private TableColumn<Appointment, Integer> appIDM;
    
    @FXML
    private TableColumn<Appointment, String> titleM;
    
    @FXML
    private TableColumn<Appointment, String> descM;
    
    @FXML
    private TableColumn<Appointment, String> LocationM;
    
    @FXML
    private TableColumn<Appointment, String> ContactM;
    
    @FXML
    private TableColumn<Appointment, String> typeM;
    
    @FXML
    private TableColumn<Appointment, String> StartM;
    
    @FXML
    private TableColumn<Appointment, LocalDateTime> EndM;
    
    @FXML
    private TableColumn<Appointment, Integer> cIDM;
    
    @FXML
    private TableColumn<Appointment, Integer> uIDM;
    
    
    
    /* This Week Table Components*/
    @FXML
    private TableView<Appointment> appointTbleW;
    
    @FXML
    private TableColumn<Appointment, Integer> appIDW;
    
    @FXML
    private TableColumn<Appointment, String> titleW;
    
    @FXML
    private TableColumn<Appointment, String> descW;
    
    @FXML
    private TableColumn<Appointment, String> locationW;
    
    @FXML
    private TableColumn<Appointment, String> contactW;
    
    @FXML
    private TableColumn<Appointment, String> typeW;
    
    @FXML
    private TableColumn<Appointment, Timestamp> startW;
    
    @FXML
    private TableColumn<Appointment, Timestamp> endW;
    
    @FXML
    private TableColumn<Appointment, Integer> cIDW;
    
    @FXML
    private TableColumn<Appointment, Integer> uIDW;
    
    

    
    /* all/month/week tabs*/    
     @FXML
    private TabPane tabPane;
     
   

    

    @FXML
    void CustomersBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customers Menu");
        stage.show();

    }

    @FXML
    void DeleteAppointmentBtn(ActionEvent event) throws SQLException {
        int tabIndex = tabPane.getSelectionModel().getSelectedIndex();
    int appointmentId = 0;
    Appointment appointment = null;
    
    try{
    switch (tabIndex) {
        case 0:
            appointment = appointTbleA.getSelectionModel().getSelectedItem();
            appointmentId = appointment.getAppointmentId();
            break;
        case 1:
            appointment = appointTbleM.getSelectionModel().getSelectedItem();
            appointmentId = appointment.getAppointmentId();
            break;
        case 2:
            appointment = appointTbleW.getSelectionModel().getSelectedItem();
            appointmentId = appointment.getAppointmentId();
            break;
    }
        
        String message = "You are about to delete Appointment ID: " + appointmentId + "\n Are you sure you want to continue?";
            
                       
            Alert error = new Alert(Alert.AlertType.CONFIRMATION,message);
        
            Optional<ButtonType> result = error.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
            
                DAO.appointmentQuery.deleteAppointment(appointmentId);
                refreshTables();
            }
    }
    catch(NullPointerException npe){
        String message = "Select an appointment to Delete";
                 
            Alert error = new Alert(Alert.AlertType.ERROR, message);
            
            Optional<ButtonType> result = error.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
            
                
            }
    
        }
        

    }

    @FXML
    void addAppointmentBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Appointment Menu");
        stage.show();

    }

    

    @FXML
    void reportsBtn(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Reports Menu");
        stage.show();
        
    }

    @FXML
    void updateAppointmentBtn(ActionEvent event) throws IOException, SQLException {
    int tabIndex = tabPane.getSelectionModel().getSelectedIndex();
    int appointmentId = 0;
    Appointment appointment = null;
    
    try{
    switch (tabIndex) {
        case 0:
            appointment = appointTbleA.getSelectionModel().getSelectedItem();
            appointmentId = appointment.getAppointmentId();
            break;
        case 1:
            appointment = appointTbleM.getSelectionModel().getSelectedItem();
            appointmentId = appointment.getAppointmentId();
            break;
        case 2:
            appointment = appointTbleW.getSelectionModel().getSelectedItem();
            appointmentId = appointment.getAppointmentId();
            break;
    }
    
    
       
        
    UpdateAppointmentController.getSelectedId(appointmentId);    
        
        
        
        
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Appointment Menu");
        stage.show();
    }
    catch(NullPointerException npe){
        String message = "Select an appointment to Update";
                 
            Alert error = new Alert(Alert.AlertType.ERROR, message);
            
            Optional<ButtonType> result = error.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
            
                
            }   
            
        }    

    }
    
    private void setAllAppointments() throws SQLException{
        appointTbleA.setItems(DAO.appointmentQuery.selectAllAppointments());
            
        appIDA.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleA.setCellValueFactory(new PropertyValueFactory<>("title"));
        descA.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationA.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactA.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeA.setCellValueFactory(new PropertyValueFactory<>("type"));
        startA.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndA.setCellValueFactory(new PropertyValueFactory<>("end"));
        cIDA.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        uIDA.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    private void setWeekAppointments() throws SQLException{
        appointTbleW.setItems(DAO.appointmentQuery.selectThisWeeksAppointments());
            
        appIDW.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleW.setCellValueFactory(new PropertyValueFactory<>("title"));
        descW.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationW.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactW.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeW.setCellValueFactory(new PropertyValueFactory<>("type"));
        startW.setCellValueFactory(new PropertyValueFactory<>("start"));
        endW.setCellValueFactory(new PropertyValueFactory<>("end"));
        cIDW.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        uIDW.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    
    private void setMonthAppointments() throws SQLException{
        appointTbleM.setItems(DAO.appointmentQuery.selectThisMonthsAppointments());
        
        appIDM.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleM.setCellValueFactory(new PropertyValueFactory<>("title"));
        descM.setCellValueFactory(new PropertyValueFactory<>("description"));
        LocationM.setCellValueFactory(new PropertyValueFactory<>("location"));
        ContactM.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeM.setCellValueFactory(new PropertyValueFactory<>("type"));
        StartM.setCellValueFactory(new PropertyValueFactory<>("start"));
        EndM.setCellValueFactory(new PropertyValueFactory<>("end"));
        cIDM.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        uIDM.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }
    
    
    
    private void refreshTables() throws SQLException{
        
        setAllAppointments();
        setWeekAppointments();
        setMonthAppointments();
        
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            setAllAppointments();
            setMonthAppointments();
            setWeekAppointments();
            
        } catch (SQLException ex) {
        }
        
        
    }    
    
}
