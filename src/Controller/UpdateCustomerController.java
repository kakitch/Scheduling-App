/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Country;
import Model.Customer;
import Model.first_level_divisions;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class UpdateCustomerController implements Initializable {
    
    Stage stage;
    Parent scene;
    static Customer loadedCustomer;
    
    @FXML
    private TextField addressTxt;

    @FXML
    private ComboBox<Country> countryCombo;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private ComboBox<first_level_divisions> divisionCombo;

    @FXML
    private TextField phoneNumberTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    void addCustomer(ActionEvent event) throws SQLException, IOException {
        try{
        String name;
        String address;
        String phone;
        String postal;
        int divisionId;
        int customerId = loadedCustomer.getCustomerID();
                
        if(customerNameTxt.getText().isEmpty() == false){
            name = customerNameTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
        
        if(addressTxt.getText().isEmpty() == false){
            address = addressTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
        
        if(phoneNumberTxt.getText().isEmpty() == false){
            phone = phoneNumberTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
        
        if(postalCodeTxt.getText().isEmpty() == false){
            postal =  postalCodeTxt.getText();
        }
        else{
            throw new NullPointerException();
        }
        
        divisionId = divisionCombo.getSelectionModel().getSelectedItem().getDivisionID();
        
        
        
        DAO.customerQuery.updateCustomer(customerId, name, address, postal, phone, divisionId);
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customer Menu");
        stage.show();
        }
        catch(NullPointerException npe){
        String message = "";
            if(addressTxt.getText().isBlank()){
                message = message + "address name must not be left Blank \n \n";
            }
        
            if(customerNameTxt.getText().isBlank()){
                message = message + "customer name must not be left Blank \n \n";
            }
        
            if(phoneNumberTxt.getText().isBlank()){
                message = message + "phone number must not be left Blank \n \n";
            }
            
            if(postalCodeTxt.getText().isBlank()){
                message = message + "postal code must not be left Blank \n \n";
            }
            
            if(countryCombo.getSelectionModel().isEmpty() == true){
                message = message + "country must not be left blank \n \n";
            }
            
            if(divisionCombo.getSelectionModel().isEmpty() == true){
                message = message + "state/division must not be left blank \n \n";
            }
            
            Alert error = new Alert(Alert.AlertType.ERROR,message);
        
            Optional<ButtonType> result2 = error.showAndWait();
            
        }
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Customer Menu");
        stage.show();

    }
    
    @FXML
    void countryAction(ActionEvent event) throws SQLException {
        
        if(countryCombo.getSelectionModel().isEmpty()){
            return;
        }
        else{
            int id = countryCombo.getSelectionModel().getSelectedItem().getCountryID();
            divisionCombo.setItems(DAO.divisionQuery.selectAllCountryDivisions());
        }
        
    }

    
    private void nextId(){
        String nextId = null;
        try {
              nextId = String.valueOf(DAO.customerQuery.nextAvailableCustomerID());
              
                        
          } catch (SQLException ex) {}
        customerIdTxt.setText(nextId);
    }
    
    public static void setCustomerUpdate(int id) throws SQLException{
        loadedCustomer = DAO.customerQuery.selectCustomerRecord(id);
        
    }
    
    private void autoSelectDivision(int divisionId) {
        for(first_level_divisions division : divisionCombo.getItems()){
            if(division.getDivisionID() == divisionId){
                divisionCombo.getSelectionModel().select(division);
                int countryId = division.getCountryID();
                for(Country country : countryCombo.getItems()){
                    if(country.getCountryID() == countryId){
                        countryCombo.getSelectionModel().select(country);
                    
                
                    }
                }
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        nextId();
        try {
            countryCombo.setItems(DAO.countryQuery.selectAllCountries());
            divisionCombo.setItems(DAO.divisionQuery.selectAllCountryDivisions());
        } 
        catch (SQLException ex) {}    
        
        customerIdTxt.setText(String.valueOf(loadedCustomer.getCustomerID()));
        customerNameTxt.setText(loadedCustomer.getCustomerName());
        addressTxt.setText(loadedCustomer.getAddress());
        phoneNumberTxt.setText(loadedCustomer.getPhone());
        postalCodeTxt.setText(loadedCustomer.getPostalCode());
        
        autoSelectDivision(loadedCustomer.getDivisionID());
        
        
    
        
    }    
    
}
