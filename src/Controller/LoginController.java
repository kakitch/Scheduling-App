/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.scene.control.PasswordField;
import java.net.URL;
import java.security.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author knfab
 */
public class LoginController implements Initializable {
    Stage stage;
    Parent scene;
    String  error;
    
     @FXML
    private Label LogPw;

    @FXML
    private Button logButton;

    @FXML
    private Label logInMessage;

    @FXML
    private Label logTitle;

    @FXML
    private Label logUn;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField userNameTxt;
    
    Helper.ioHandler logInAppend = (String a) -> { try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("login_activity.txt",true)))){out.println(a);} catch (IOException e){}};
    Helper.timeConvert time = () -> {String t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Helper.TimeHandler.UTCtimeStamp()); return t;};
            
    @FXML
    void logInOnClick(ActionEvent event) throws IOException, SQLException {
        String userName;
        String passwordInput;
        String passwordStored;
        userName = userNameTxt.getText();
        passwordInput = passwordTxt.getText();
        passwordStored = DAO.userQuery.selectUsernamePassword(userName);

        
        if(passwordInput.equals(passwordStored)){
            
            //Sets Current User
            DAO.userQuery.setCurrentUserQuery(userName);
            
            
            logInAppend.output("User " + userName + " successfully logged in at " + time.time() + " UTC");
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointments Menu");
            stage.show();
            
            String message = "";
            int upcoming = DAO.appointmentQuery.appointmentNext15Min();
            //System.out.println(upcoming);
            if(upcoming != -1){
                message = "There is an upcoming appointment of ID: " + upcoming + "\n within the next 15 minutes";
            }
            else{
                message = "there are no upcoming appointments";
            }
            Alert error = new Alert(Alert.AlertType.INFORMATION, message);
            
            Optional<ButtonType> result = error.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
            }
            
        }
        else {
            logInMessage.setTextFill(Color.web("#FF0000"));
            logInMessage.setText(error);
            logInAppend.output("User " + userName + " gave invalid log-in at " + time.time() + " UTC");
        }

    } 
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        rb = ResourceBundle.getBundle("schedulingappc195/Nat", Locale.getDefault());
        
        
            
            logTitle.setText(rb.getString("title")); 
            logUn.setText(rb.getString("un"));
            LogPw.setText(rb.getString("pw")); 
            logInMessage.setText(rb.getString("prompt")); 
            logButton.setText(rb.getString("button"));
            error = rb.getString("error");
        
        if(rb.getLocale().getLanguage().equals("fr")){
            logUn.setLayoutX(100.0);
            LogPw.setLayoutX(100.0);
        }
        
        logInMessage.setTextFill(Color.web("#000000"));
        // TODO
    }    
    
}
