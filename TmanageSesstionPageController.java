package gymmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class TmanageSesstionPageController implements Initializable {

  

    @FXML
    private Button addsessionBtn;
     @FXML
    private Button vbtn;
    @FXML
    private Button backmangebtn;
     
         @FXML
      private void ThandleButtonActionTmanage(ActionEvent event) throws IOException {
        String btn = ((Node) event.getSource()).getId();
        switch (btn) {
            case "addsessionBtn":
                GymManagementSystem.showScreen("gymmanagementsystem/TaddSesstion.fxml", "add Sesstion");
                break;
            case "vbtn":
                GymManagementSystem.showScreen("gymmanagementsystem/TviewSessionPage.fxml", "view session");
                break;
            case "backmangebtn":
                GymManagementSystem.showScreen("gymmanagementsystem/ThomePage.fxml", "home ");
                break;

        }

    }
     
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
    }

}
