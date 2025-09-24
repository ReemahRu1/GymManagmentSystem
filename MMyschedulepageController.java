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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class MMyschedulepageController implements Initializable {

    @FXML
    private TableView<Classes> MyClassesList;

    @FXML
    private TableColumn<Classes, String> room;

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
    private TableColumn<Classes, String> className;

    @FXML
    private TableColumn<Classes, String> date;

    @FXML
    private TableColumn<Classes, String> time;

    @FXML
    private TableColumn<Classes, String> trainerName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadClasses();
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
            case "btnProfile":
                GymManagementSystem.showScreen("gymmanagementsystem/MProfilepage.fxml", "Profile");
                break;
            case "btnLogout":
                GymManagementSystem.showScreen("gymmanagementsystem/login.fxml", "Login");
                break;
        }
    }

    private void loadClasses() {
        int memberId = SessionManager.getInstance().getCurrentUser().getId();
        List<Classes> classes = DatabaseHandler.loadClassesByMemberId(memberId);
        ObservableList<Classes> observableclass = FXCollections.observableArrayList(classes);
        className.setCellValueFactory(new PropertyValueFactory<Classes, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Classes, String>("day"));
        time.setCellValueFactory(new PropertyValueFactory<Classes, String>("time"));
        trainerName.setCellValueFactory(new PropertyValueFactory<Classes, String>("trainerName"));
        room.setCellValueFactory(new PropertyValueFactory<Classes, String>("room"));
        MyClassesList.setItems(observableclass);
    }

}
