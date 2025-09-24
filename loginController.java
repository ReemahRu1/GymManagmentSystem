/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Member;
import gymmanagementsystem.GymClasses.Trainer;
import gymmanagementsystem.GymClasses.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Ralru
 */
public class loginController  {

    @FXML
    private PasswordField TfPassword;

    @FXML
    private TextField TfUsername;

    @FXML
    private Button loginBtn;

      @FXML
    public void initialize() {
    }
    
    
    @FXML
    private void loginhandleButtonAction(ActionEvent event) throws SQLException, IOException  {
        
        String username = TfUsername.getText();
        String password = TfPassword.getText();
       if(!username.isEmpty() ||!password.isEmpty() ){
           if(DatabaseHandler.authenticateUser(username, password)==null){
              AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "Invalid username or password.");
              return;
           }
        SessionManager.getInstance().setCurrentUser( DatabaseHandler.authenticateUser(username, password));
        
        if(SessionManager.getInstance().getCurrentUser().getClass().getName().equals("gymmanagementsystem.GymClasses.Member")){
             GymManagementSystem.showScreen("gymmanagementsystem/Mhomepage.fxml", "homepage");
        }else if(SessionManager.getInstance().getCurrentUser().getClass().getName().equals("gymmanagementsystem.GymClasses.Trainer")){
               GymManagementSystem.showScreen("gymmanagementsystem/ThomePage.fxml", "homepage");
        }else if(SessionManager.getInstance().getCurrentUser().getClass().getName().equals("gymmanagementsystem.GymClasses.Admin")){
            GymManagementSystem.showScreen("gymmanagementsystem/ADhome.fxml", "homepage");
        }}else{
      
              AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "Invalid username or password.");
           
        }
 
    }
   

} 
