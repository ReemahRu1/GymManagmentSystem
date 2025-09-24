/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Member;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class MProfilepageController implements Initializable {

    @FXML
    private Button btnClass;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnMyschedule;

    @FXML
    private Button btnProfile;

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
    
    @FXML
    private Text membership;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMemberInfo();
    }    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String btn = ((Node) event.getSource()).getId();
            switch (btn) {
                case "btnClass":
                    GymManagementSystem.showScreen("gymmanagementsystem/Mclasspage.fxml", "Classes"); 
                    break;
                case "btnHome":
                   GymManagementSystem.showScreen("gymmanagementsystem/Mhomepage.fxml", "Home Page"); 
                    break;
                case "btnMyschedule":
                   GymManagementSystem.showScreen("gymmanagementsystem/MMyschedulepage.fxml", "My Schedule"); 
                    break;
                case "btnLogout":
                   GymManagementSystem.showScreen("gymmanagementsystem/login.fxml", "Login"); 
                    break;
            }
    }
    
    @FXML
     private void updateProfileInfo(ActionEvent event) throws IOException {
         
        String name = tfName.getText();
        String phone = tfPhonenum.getText();
        String username = tfUsername.getText();
        String pass = tfPassword.getText();
        String email = tfEmail.getText();
        int memberId = SessionManager.getInstance().getCurrentUser().getId();
        String membership = DatabaseHandler.getMemberById(memberId).getMembership();

        if (name.isEmpty() || phone.isEmpty() || username.isEmpty() || pass.isEmpty() || email.isEmpty() ) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
        Member m = new Member(memberId, name, phone, username, pass, email, membership);

        if (DatabaseHandler.updateMember(m)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Member has been updated successfully!");
            clearMemberFields();
            SessionManager.getInstance().setCurrentUser(m);
            loadMemberInfo();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update member to database.");
        }
     }
    private void clearMemberFields() {
        tfName.clear();
        tfPhonenum.clear();
        tfEmail.clear();
        tfUsername.clear();
        tfPassword.clear();
        
    }
    
    private void loadMemberInfo(){
        int memberId = SessionManager.getInstance().getCurrentUser().getId();
         Member m = DatabaseHandler.getMemberById(memberId);
        tfName.setText(m.getName());
         tfPhonenum.setText(m.getPhone());
        tfUsername.setText(m.getUsername());
        tfPassword.setText(m.getPassword());
        tfEmail.setText(m.getEmail());
        membership.setText(m.getMembership());
    }
}
