/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author layan
 */
public class TfeedbackPageController implements Initializable {

    
    @FXML
    private Button FedBackSendBtn;

    @FXML
    private TextField Tmassge;

    @FXML
    private ChoiceBox<String> usernameBox;
   
    @FXML
    private void TfeedBackhandleButtonAction(ActionEvent event) throws IOException  {
        String m = usernameBox.getSelectionModel().getSelectedItem();
        String message =  Tmassge.getText();
        if(m == null || message.isEmpty()){
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
         int TrainerId = SessionManager.getInstance().getCurrentUser().getId();
        if(DatabaseHandler.insertFeedback(m, TrainerId, message)){
           AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Feedback added successfully!");

        GymManagementSystem.showScreen("gymmanagementsystem/ThomePage.fxml", "homepage");
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add the Feedback .");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> observablemember = FXCollections.observableArrayList(DatabaseHandler.loadMemberUsernamesByRole());
        usernameBox.setItems(observablemember);
    }    
    
}
