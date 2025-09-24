/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Service;
import java.io.IOException;
import static java.lang.String.valueOf;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class ADserviceOffersController implements Initializable {

    @FXML
    private TableView<Service> serviceList;
    @FXML
    private ComboBox<String> ChMember;

    @FXML
    private ComboBox<Integer> ChService;

    @FXML
    private TableColumn<Service, Double> CserviceAmount;

    @FXML
    private TableColumn<Service, Integer> Cid;

    @FXML
    private TableColumn<Service, String> CserviceName;

 

    @FXML
    private Button btnAddService;

    @FXML
    private Button btnBookService;

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
    private TextField tfServiceName;

    @FXML
    private TextField trServiceAmount;

    Double amountOfService = 0.0;

    int index = -1;

    int id = 0;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadService();
        getUsernameMember();

    }

    @FXML
    public void rowSelected(MouseEvent event) {
        index = serviceList.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfServiceName.setText(CserviceName.getCellData(index).toString());
        trServiceAmount.setText(CserviceAmount.getCellData(index).toString());
        id = Cid.getCellData(index);
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
                GymManagementSystem.showScreen("gymmanagementsystem/ADserviceOffers.fxml", "Service And Offers");
                break;
            case "btnProfile":
                GymManagementSystem.showScreen("gymmanagementsystem/ADprofile.fxml", "Profile");
                break;
        }
    }

    @FXML
    private void AddService(ActionEvent event) {
        String name = tfServiceName.getText();
        String m = trServiceAmount.getText();
        Double amount = Double.parseDouble(m);
        if (name.isEmpty() || m.isEmpty()) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }

        Service s = new Service(0, name, amount);
        if (DatabaseHandler.insertService(s)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Service added successfully!");
            loadService();
            clearServiceFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add service to database.");
        }
    }

    @FXML
    private void BookService(ActionEvent event) {
        int s = 0;
        String m = ChMember.getSelectionModel().getSelectedItem();
        s = ChService.getSelectionModel().getSelectedItem();
        
        if (m == null || s == 0) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
        if (DatabaseHandler.bookServiceByUsername(m, s)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Booking Confirmed!");

        }
    }

    @FXML
    private void DeleteService(ActionEvent event) {

        if (id == 0) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "You must select service.");
            return;
        }
        if (DatabaseHandler.deleteServiceById(id)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Service has been deleted!");
            loadService();
            clearServiceFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete Service.");
        }
    }

    private void clearServiceFields() {
        tfServiceName.clear();
        trServiceAmount.clear();

    }

    private void loadService() {
        List<Service> service = DatabaseHandler.loadAllServices();
        ObservableList<Service> observableService = FXCollections.observableArrayList(service);
        CserviceAmount.setCellValueFactory(new PropertyValueFactory<Service, Double>("amount"));
        CserviceName.setCellValueFactory(new PropertyValueFactory<Service, String>("name"));
        Cid.setCellValueFactory(new PropertyValueFactory<Service, Integer>("id"));
        serviceList.setItems(observableService);
        ObservableList<Integer> observableS = FXCollections.observableArrayList(DatabaseHandler.getAllServiceIds());
        ChService.setItems(observableS);
    }

    private void getUsernameMember() {
        ObservableList<String> observableMembers = FXCollections.observableArrayList(DatabaseHandler.loadMemberUsernamesByRole());
        ChMember.setItems(observableMembers);

    }

    

}
