/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Classes;
import gymmanagementsystem.GymClasses.Trainer;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author layan
 */
public class TaddSesstionController implements Initializable {



 

     @FXML
    private Button addbackbtn;


    @FXML
    private TextField descreptionTextFeild;

    @FXML
    private TextField namesessiom;

    @FXML
    private TextField persons;

    @FXML
    private ComboBox<String> rooms;
    
    @FXML
    private ComboBox<String> days;

    @FXML
    private TextField times;
    @FXML
    private void BackmanageAAction(ActionEvent event) throws IOException {
        addClass();
        GymManagementSystem.showScreen("gymmanagementsystem/TmanageSesstionPage.fxml", "manage page");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getDays();
        gerRooms();
    }

    private void getDays() {
        ObservableList<String> observableDays = FXCollections.observableArrayList("Sunday","Monday","Tuesday","Wednsday","Thursday","Saturday");
        days.setItems(observableDays);
    }
    
    private void gerRooms(){
        ObservableList<String> observableRooms = FXCollections.observableArrayList("A102","B104","A203","B203","A201");
        rooms.setItems(observableRooms);
    }
    
    
    private void addClass() throws IOException {
        String name = namesessiom.getText();
        String des = descreptionTextFeild.getText();
        String time = times.getText();
        int spots = Integer.parseInt(persons.getText());
        String room = rooms.getSelectionModel().getSelectedItem();
        String day = days.getSelectionModel().getSelectedItem();
        if (name.isEmpty() || des.isEmpty() || time.isEmpty() || room == null|| day == null) {
             AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }

        Trainer t = (Trainer) SessionManager.getInstance().getCurrentUser();
         //Trainer t = DatabaseHandler.getTrainerById(trainerId);
        Classes c = new Classes(0,day,time,name,des,spots,t,room);

        if (DatabaseHandler.insertClass(c)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Class added successfully!");
            clearClassFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add Class to database.");
        }
    }
        private void clearClassFields() {
        namesessiom.clear();
        descreptionTextFeild.clear();
        times.clear();
        persons.clear();
        rooms.getSelectionModel().clearSelection();
        days.getSelectionModel().clearSelection();
        
    }
    
}
