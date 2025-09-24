/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Admin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class ADprofileController implements Initializable {

    @FXML
    private Button btnClass;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMembers;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnService;

    @FXML
    private Button btnTrainers;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfPhonenum;

    @FXML
    private TextField tfUsername;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadAdminInfo();
    }   
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String btn = ((Node) event.getSource()).getId();
             switch (btn) {
            case "btnClass":
                GymManagementSystem.showScreen("gymmanagementsystem/ADclasses.fxml", "Classes");
                break;
            case "btnHome":
                GymManagementSystem.showScreen("gymmanagementsystem/ADhome.fxml", "Home Page");
                break;
            case "btnMembers":
                GymManagementSystem.showScreen("gymmanagementsystem/ADmembers.fxml", "Member");
                break;
            case "btnLogout":
                GymManagementSystem.showScreen("gymmanagementsystem/login.fxml", "Login");
                break;
            case "btnTrainers":
                GymManagementSystem.showScreen("gymmanagementsystem/ADtrainers.fxml", "Trainers");
                break;
            case "btnService":
                GymManagementSystem.showScreen("gymmanagementsystem/ADserviceOffers.fxml", "Service ");
                break;
            case "btnProfile":
                GymManagementSystem.showScreen("gymmanagementsystem/ADprofile.fxml", "Profile");
                break;
        }
    }
    
    
     
      @FXML
     private void UpdateProfileInfo(ActionEvent event) throws IOException {
         
        String name = tfName.getText();
        String phone = tfPhonenum.getText();
        String username = tfUsername.getText();
        String pass = tfPassword.getText();
        String email = tfEmail.getText();
        int AdminId = SessionManager.getInstance().getCurrentUser().getId();
        

        if (name.isEmpty() || phone.isEmpty() || username.isEmpty() || pass.isEmpty() || email.isEmpty() ) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
        Admin d = new Admin(AdminId, name, phone, username, pass, email);

        if (DatabaseHandler.updateAdmin(d)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Info has been updated successfully!");
            clearAdminFields();
            SessionManager.getInstance().setCurrentUser(d);
            loadAdminInfo();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update the info.");
        }
     }
    private void clearAdminFields() {
        tfName.clear();
        tfPhonenum.clear();
        tfEmail.clear();
        tfUsername.clear();
        tfPassword.clear();
        
    }
    
    private void loadAdminInfo(){
        int AdminId = SessionManager.getInstance().getCurrentUser().getId();
         Admin d = DatabaseHandler.getAdminById(AdminId);
        tfName.setText(d.getName());
         tfPhonenum.setText(d.getPhone());
        tfUsername.setText(d.getUsername());
        tfPassword.setText(d.getPassword());
        tfEmail.setText(d.getEmail());
    }
    
}
