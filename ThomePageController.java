/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ThomePageController implements Initializable {

    @FXML
    private Button btnSesstion;

    @FXML
    private Button btnTrack;

    @FXML
    private Button btnFeedback;
    
    @FXML
    private Text welcomemessage;

    @FXML
    private void ThandleButtonActionThome(ActionEvent event) throws IOException {
        String btn = ((Node) event.getSource()).getId();
        switch (btn) {
            case "btnSesstion":
                GymManagementSystem.showScreen("gymmanagementsystem/TmanageSesstionPage.fxml", "manage Sesstion");
                break;
            case "btnTrack":
                GymManagementSystem.showScreen("gymmanagementsystem/Tprogress.fxml", "Tracking");
                break;
            case "btnFeedback":
                GymManagementSystem.showScreen("gymmanagementsystem/TfeedbackPage.fxml", "FeedBack");
                break;

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomemessage.setText("Wellcome Coach "+SessionManager.getInstance().getCurrentUser().getName());
    }

}
