/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Member;
import gymmanagementsystem.GymClasses.Trainer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class ADtrainersController implements Initializable {

    @FXML
    private TableView<Trainer> trainetsList;

    @FXML
    private TableColumn<Trainer, String> Cemail;

    @FXML
    private TableColumn<Trainer, String> Cname;

    @FXML
    private TableColumn<Trainer, String> Cpassword;

    @FXML
    private TableColumn<Trainer, String> Cphone;

    @FXML
    private TableColumn<Trainer, String> Cshift;

    @FXML
    private TableColumn<Trainer, String> Cusername;

    @FXML
    private ToggleGroup ShiftGroup;

    @FXML
    private Button btnAddTrainer;

    @FXML
    private Button btnClass;

    @FXML
    private Button btnDelete;

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
    private Button btnupdate;

    @FXML
    private RadioButton shift1;

    @FXML
    private RadioButton shift2;

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

    private int index = -1;

    private String trainerShift = "7-3";
    int id = 0;

    @FXML
    private TableColumn<Trainer, Integer> Cid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTrainer();
    }

    public void getshift(ActionEvent event) {
        if (shift1.isSelected()) {
            trainerShift = "7-3";
        } else if (shift2.isSelected()) {
            trainerShift = "3-11";
        }
    }

    @FXML
    public void rowSelected(MouseEvent event) {
        index = trainetsList.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfName.setText(Cname.getCellData(index).toString());
        tfName.setText(Cname.getCellData(index).toString());
        tfPhonenum.setText(Cphone.getCellData(index).toString());
        tfEmail.setText(Cemail.getCellData(index).toString());
        tfUsername.setText(Cusername.getCellData(index).toString());
        tfPassword.setText(Cpassword.getCellData(index).toString());
        id = Cid.getCellData(index);
        trainerShift = Cshift.getCellData(index);
        
        if (trainerShift.equals("7-3")) {
            shift1.setSelected(true);
        } else if (trainerShift.equals("3-11")) {
            shift2.setSelected(true);
        
        }
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
    public void addTrainer(ActionEvent event) {
        getshift(event);
        // Get the values from the form fields
        String name = tfName.getText();
        String phone = tfPhonenum.getText();
        String username = tfUsername.getText();
        String pass = tfPassword.getText();
        String email = tfEmail.getText();

        if (name.isEmpty() || phone.isEmpty() || username.isEmpty() || pass.isEmpty() || email.isEmpty()) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
        Trainer t = new Trainer(0, name, phone, username, pass, email, trainerShift);

        if (DatabaseHandler.insertTrainer(t)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Trainer added successfully!");
            loadTrainer();
            clearTrainerFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add trainer to database.");
        }
    }

    @FXML
    public void updateTrainer(ActionEvent event) {
        getshift(event);
        String name = tfName.getText();
        String phone = tfPhonenum.getText();
        String username = tfUsername.getText();
        String pass = tfPassword.getText();
        String email = tfEmail.getText();

        if (name.isEmpty() || phone.isEmpty() || username.isEmpty() || pass.isEmpty() || email.isEmpty()) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
        Trainer t = new Trainer(id, name, phone, username, pass, email, trainerShift);

        if (DatabaseHandler.updateTrainer(t)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Trainer has been updated successfully!");
            loadTrainer();
            clearTrainerFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update the trainer.");
        }
    }

    @FXML
    public void deleteTrainer(ActionEvent event) {
        if (id == 0) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "You must select member.");
            return;
        }
        if (DatabaseHandler.deleteTrainer(id)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Trainer has been deleted successfully!");
            loadTrainer();
            clearTrainerFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete the trainer.");
        }
    }

    private void loadTrainer() {
        List<Trainer> trainers = DatabaseHandler.loadAll("Trainer");
        ObservableList<Trainer> observableTrainer = FXCollections.observableArrayList(trainers);
        Cid.setCellValueFactory(new PropertyValueFactory<Trainer, Integer>("id"));
        Cname.setCellValueFactory(new PropertyValueFactory<Trainer, String>("name"));
        Cphone.setCellValueFactory(new PropertyValueFactory<Trainer, String>("phone"));
        Cemail.setCellValueFactory(new PropertyValueFactory<Trainer, String>("Email"));
        Cusername.setCellValueFactory(new PropertyValueFactory<Trainer, String>("Username"));
        Cpassword.setCellValueFactory(new PropertyValueFactory<Trainer, String>("password"));
        Cshift.setCellValueFactory(new PropertyValueFactory<Trainer, String>("trainershift"));
        trainetsList.setItems(observableTrainer);
    }

    private void clearTrainerFields() {
        tfName.clear();
        tfPhonenum.clear();
        tfEmail.clear();
        tfUsername.clear();
        tfPassword.clear();
        ShiftGroup.selectToggle(null); 
        shift1.setSelected(true);
        trainerShift = "7-3";
        id = 0;
    }

}
