/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class CustomerMenuController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private TableView<Customer> customerTableView;
    
    @FXML
    private TableColumn<Customer, String> addressCol;

    @FXML
    private TableColumn<Customer, String> countryCol;

    @FXML
    private TableColumn<Customer, Integer> customerIdCol;

    @FXML
    private TableColumn<Customer, String> customerNameCol;

    @FXML
    private TableColumn<Customer, String> divisionCol;

    @FXML
    private TableColumn<Customer, String> phoneNumberCol;

    @FXML
    private TableColumn<Customer, String> postalCode;

    @FXML
    void addCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Add Customer");
        stage.show();

    }

    @FXML
    void appointmentsMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointments Menu");
        stage.show();

    }

    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException {
        try{
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();
        int customerId = 0;
    
        
    
        customerId = customer.getCustomerID();
            
        
        String message = "You are about to delete CustomerId ID: " + customerId + "\n Are you sure you want to continue?";
        String message2 =  "CustomerId ID: " + customerId + " has been deleted." ;    
                       
            Alert error = new Alert(Alert.AlertType.CONFIRMATION,message);
        
            Optional<ButtonType> result = error.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
            
                DAO.customerQuery.deleteCustomer(customerId);
                refreshTable();
                
                Alert error2 = new Alert(Alert.AlertType.INFORMATION,message2);
        
                Optional<ButtonType> result2 = error2.showAndWait();
            
                if(result2.isPresent() && result2.get() == ButtonType.OK){
            
                }
            }
        }
        catch(NullPointerException npe){
            String message = "Select a Customer to Delete";
            
                       
            Alert error = new Alert(Alert.AlertType.ERROR, message);
            
            Optional<ButtonType> result = error.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
            
                
            }
        
        }
        

    }

    @FXML
    void updateCustomer(ActionEvent event) throws IOException, SQLException {
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();
        int id = customer.getCustomerID();
        Controller.UpdateCustomerController.setCustomerUpdate(id);
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Update Customer Menu");
        stage.show();

    }
    
    private void refreshTable() throws SQLException {
        customerTableView.setItems(DAO.customerQuery.selectAllCustomerRecords());
            
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            refreshTable();
        } catch (SQLException ex) {
        }
        
        
    }    
    
}
