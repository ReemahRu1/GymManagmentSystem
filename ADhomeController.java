/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ralru
 */

public class ADhomeController implements Initializable {

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
    private Text numOfClasses;

    @FXML
    private Text numofMembers;

    @FXML
    private Text numofTrainers;

    @FXML
    private Text wolcomeMessage;
    
    
    public int getMemberCount() {
        return DatabaseHandler.getMemberCount();  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wolcomeMessage.setText("Hello "+SessionManager.getInstance().getCurrentUser().getName()+" !!");
        numofMembers.setText(valueOf(getMemberCount()));
        numofTrainers.setText(valueOf(DatabaseHandler.getTrainerCount()));
        numOfClasses.setText(valueOf(DatabaseHandler.getClassCount()));
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
    
    
}
