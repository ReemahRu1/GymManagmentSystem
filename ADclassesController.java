/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Classes;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class ADclassesController implements Initializable {

    @FXML
    private TableView<Classes> classsesList;
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
    private TableColumn<Classes, Integer> capacity;

    @FXML
    private TableColumn<Classes, String> className;

    @FXML
    private TableColumn<Classes, String> classRoom;

    @FXML
    private TableColumn<Classes, String> date;

    @FXML
    private TableColumn<Classes, String> description;

    @FXML
    private TableColumn<Classes, String> time;

    @FXML
    private TableColumn<Classes, String> trainerName;
    
    int id =0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadClasses();
    }

private void loadClasses() {
        List<Classes> classes = DatabaseHandler.loadClasses();
        ObservableList<Classes> observableclass = FXCollections.observableArrayList(classes);
        //Cid.setCellValueFactory(new PropertyValueFactory<Member, Integer>("id"));
        className.setCellValueFactory(new PropertyValueFactory<Classes, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Classes, String>("day"));
        time.setCellValueFactory(new PropertyValueFactory<Classes, String>("time"));
        trainerName.setCellValueFactory(new PropertyValueFactory<Classes, String>("trainerName"));
        classRoom.setCellValueFactory(new PropertyValueFactory<Classes, String>("room"));
        description.setCellValueFactory(new PropertyValueFactory<Classes, String>("description"));
        capacity.setCellValueFactory(new PropertyValueFactory<Classes, Integer>("capacity"));
        classsesList.setItems(observableclass);
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

