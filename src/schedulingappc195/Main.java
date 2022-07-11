/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package schedulingappc195;

import DAO.JDBC;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author knfab
 */
public class Main extends Application{

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException, Exception {
        // TODO code application logic here
        JDBC.openConnection();
            
//        DAO.contactQuery.insert("Jim Stevens", "jim.stevens@dell.com");
//        DAO.contactQuery.update("Jim Stevens", "acosta@andagain.com");
//        DAO.contactQuery.delete(5);
//        DAO.userQuery.select("test");
        
          /*Customers Table Queries*/
//        Customer ted = DAO.customerQuery.selectCustomerRecord(1); /* Tested Successful */
//        System.out.println(ted.getCustomerID());
//        System.out.println(ted.getCustomerName());
//        System.out.println(ted.getAddress());
//        System.out.println(ted.getPostalCode());
//        System.out.println(ted.getPhone());
//        System.out.println(ted.getDivisionID());
//        System.out.println(ted.getCountry());
//        System.out.println(DAO.customerQuery.selectAllCustomerRecords().size());/* Returns Customers Observable Array List  */
//        DAO.customerQuery.updateCustomer(2, "norm wallis", "406", "adfec","555-666-8888", 12); /*  updates Customer Record -- Tested Successful -- stores in DB UTC */
//        DAO.customerQuery.insertCustomer("norm wallis", "406", "adfec","555-666-8888", 12); /*  Inserts Customer Object  -- missing timestamps */
//        DAO.customerQuery.deleteCustomer(4);  /* Deletes Customer record - Tested Successful*/
        
          /*Appointments Queries*/
//        Appointment test = DAO.appointmentQuery.selectAppointmentRecord(50);/*test Successful -- need to validate timestamp method*/
//        System.out.println(test.getAppointmentId());
//        System.out.println(test.getTitle());
//        System.out.println(test.getDescription());
//        System.out.println(test.getLocation());
//        System.out.println(test.getContactName());
//        System.out.println(test.getType());
//        System.out.println(test.getStart());
//        System.out.println(test.getEnd());
//        System.out.println(test.getCustomerId());
//        System.out.println(test.getUserId());
//        DAO.appointmentQuery.selectAllAppointments();
//        DAO.appointmentQuery.insertAppointment("thing", "thing", "thing", "thing", 1, 1, 1);/* Test Successful, need to validate DB timestamp method*/
//        DAO.appointmentQuery.deleteAppointment(5);/* Test Successful*/
//        System.out.println(DAO.appointmentQuery.nextAvailableAppointmentID());
        
        /* time handler methods tests*/
        //Helper.TimeHandler.UTCtimeStamp();
        
        //System.out.println(DAO.appointmentQuery.nextAvailableAppointmentID());
        //Helper.TimeHandler.sysToUTC(LocalDate.MAX, LocalTime.MIN);
        
        
        launch(args);
        
        JDBC.closeConnection();
        
        


    //(ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);)
//    System.out.println(TimeZone.getDefault().getID());
    }
    
      
    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("schedulingappc195/Nat", Locale.getDefault());
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(rb.getString("label"));
        stage.show();
    }
    
}
