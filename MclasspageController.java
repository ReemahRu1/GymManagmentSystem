/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Classes;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class MclasspageController implements Initializable {

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
    private Button btnRegister;

    @FXML
    private TableView<Classes> classsesList;
    @FXML
    private TableColumn<Classes, String> className;
       @FXML
    private TableColumn<Classes, Integer> Cid;

    @FXML
    private TableColumn<Classes, String> classRomm;

    @FXML
    private TableColumn<Classes, String> date;

    @FXML
    private TableColumn<Classes, String> description;

    @FXML
    private TableColumn<Classes, Integer> spot;

    @FXML
    private TableColumn<Classes, String> time;

    @FXML
    private TableColumn<Classes, String> trainerName;

    int index = -1;
    int classId = 0 ;

    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
        String btn = ((Node) event.getSource()).getId();
        switch (btn) {
            case "btnMyschedule":
                GymManagementSystem.showScreen("gymmanagementsystem/MMyschedulepage.fxml", "My Schedule");
                break;
            case "btnHome":
                GymManagementSystem.showScreen("gymmanagementsystem/Mhomepage.fxml", "Home Page");
                break;
            case "btnProfile":
                GymManagementSystem.showScreen("gymmanagementsystem/MProfilepage.fxml", "Profile");
                break;
            case "btnLogout":
                GymManagementSystem.showScreen("gymmanagementsystem/login.fxml", "Login");
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadClasses();
    }

    @FXML
    private void registerForClass(ActionEvent event) throws IOException {
        int memberId = SessionManager.getInstance().getCurrentUser().getId();
        if(classId == 0){
             AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "You must select class.");
            return;
        }
        if (DatabaseHandler.insertMemberClasses(memberId, classId)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Booking Confirmed!\nWe can't wait to see you there!");
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Oops! Booking failed. \nPlease try again.");
        }
        

    }

    @FXML
    public void rowSelected(MouseEvent event) {
        index = classsesList.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        classId = Cid.getCellData(index);

    }
    
    private void loadClasses() {
        List<Classes> classes = DatabaseHandler.loadClasses();
        ObservableList<Classes> observableclass = FXCollections.observableArrayList(classes);
        Cid.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("id"));
        className.setCellValueFactory(new PropertyValueFactory<Classes, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Classes, String>("day"));
        time.setCellValueFactory(new PropertyValueFactory<Classes, String>("time"));
        trainerName.setCellValueFactory(new PropertyValueFactory<Classes, String>("trainerName"));
        classRomm.setCellValueFactory(new PropertyValueFactory<Classes, String>("room"));
        description.setCellValueFactory(new PropertyValueFactory<Classes, String>("description"));
        spot.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("capacity"));
        classsesList.setItems(observableclass);
    }
    
    
}
