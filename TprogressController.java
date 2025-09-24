/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Classes;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author layan
 */
public class TprogressController implements Initializable {

     
     @FXML
    private Button back;

    @FXML
    private Text regestedSession;

    @FXML
    private ComboBox<String> session;

    @FXML
    private Button trackingbtn;

    @FXML
    private ComboBox<String> username;

    
     @FXML
    private void BackmanageTPAction(ActionEvent event) throws IOException  {
        GymManagementSystem.showScreen("gymmanagementsystem/ThomePage.fxml", "manage page");
       
    }  
    
    
     @FXML
    private void track(ActionEvent event) throws IOException  {
        String m = username.getSelectionModel().getSelectedItem();
        if(m == null){
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "You must select member.");
            return;
        }
       regestedSession.setText(valueOf(DatabaseHandler.countMemberClassesByUsername(m)));
       ObservableList<String> observableDays = FXCollections.observableArrayList(DatabaseHandler.getClassNamesByUsername(m));
       if(observableDays!=null){
        session.setItems(observableDays);
    }  }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> observableDays = FXCollections.observableArrayList(DatabaseHandler.loadMemberUsernamesByRole());
        username.setItems(observableDays);
    }    
    
}
