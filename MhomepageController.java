/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class MhomepageController implements Initializable {

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
    private Label feedback;

    @FXML
    private Text feedbackMessage;

    @FXML
    private Text numOfClasses;

    @FXML
    private Text state;

    @FXML
    private Label totalclasses;

    @FXML
    private Text welcomeMessage;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         welcomeMessage.setText("Hello "+SessionManager.getInstance().getCurrentUser().getName()+" !!");
         getFeedback();
         numOfClasses.setText(valueOf(DatabaseHandler.countClassesForMember(SessionManager.getInstance().getCurrentUser().getId())));
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String btn = ((Node) event.getSource()).getId();
            switch (btn) {
                case "btnClass":
                    GymManagementSystem.showScreen("gymmanagementsystem/Mclasspage.fxml", "Classes"); 
                    break;
                case "btnMyschedule":
                   GymManagementSystem.showScreen("gymmanagementsystem/MMyschedulepage.fxml", "My Schedule"); 
                    break;
                case "btnProfile":
                    GymManagementSystem.showScreen("gymmanagementsystem/MProfilepage.fxml", "Profile"); 
                    break;
                case "btnLogout":
                   GymManagementSystem.showScreen("gymmanagementsystem/login.fxml", "Login"); 
                    break;
            }
      
    }
    private void getFeedback(){
        String fmessage = "" ;
        List<String> feedbackList = DatabaseHandler.getFeedbackForMemberById(SessionManager.getInstance().getCurrentUser().getId()); 
      for (String f : feedbackList) {
       fmessage += f;
       fmessage += "\n";
    }
      
      if(fmessage.equals("")){
          feedbackMessage.setText("There is no feedback message yet!");
      }else{
          feedbackMessage.setText(fmessage);
      }
    }

}