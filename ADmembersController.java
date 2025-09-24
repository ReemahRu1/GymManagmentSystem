/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.DB.DatabaseHandler;
import gymmanagementsystem.GymClasses.Member;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author Ralru
 */
public class ADmembersController implements Initializable {

    @FXML
    private TableView<Member> memberList;

    @FXML
    private TableColumn<Member, String> Cemail;


    @FXML
    private TableColumn<Member, String> Cmembership;

    @FXML
    private TableColumn<Member, String> Cname;

    @FXML
    private TableColumn<Member, String> Cpassword;

    @FXML
    private TableColumn<Member, String> Cphone;

    @FXML
    private TableColumn<Member, String> Cusername;

    @FXML
    private Text Tamount;

    @FXML
    private Button btnAddMember;

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
    private Button btnUpdate;

    @FXML
    private ToggleGroup membershipGroup;
    @FXML
    private RadioButton ch12month;

    @FXML
    private RadioButton ch3month;

    @FXML
    private RadioButton ch6month;

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
    @FXML
    private TableColumn<Member, Integer> Cid;

    private int index = -1;
    private String membershipDuration = "3 month";
    private int id = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMember();
    }

    public void getMembership(ActionEvent event) {
        if (ch3month.isSelected()) {
            membershipDuration = "3 month";
           Tamount.setText("1200 sr");
        } else if (ch6month.isSelected()) {
            membershipDuration = "6 month";
            Tamount.setText("2100 sr");
        } else if (ch12month.isSelected()) {
            membershipDuration = "12 month";
           Tamount.setText("3600 sr");
        }
    }
    
    @FXML
    public void getAmount(MouseEvent event) {
        if (ch3month.isSelected()) {
            membershipDuration = "3 month";
           Tamount.setText("1200 sr");
        } else if (ch6month.isSelected()) {
            membershipDuration = "6 month";
            Tamount.setText("2100 sr");
        } else if (ch12month.isSelected()) {
            membershipDuration = "12 month";
           Tamount.setText("3600 sr");
        }
    }

    @FXML
    public void rowSelected(MouseEvent event) {
        index = memberList.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }

        // Set text fields based on selected row
        tfName.setText(Cname.getCellData(index));
        tfPhonenum.setText(Cphone.getCellData(index));
        tfEmail.setText(Cemail.getCellData(index));
        tfUsername.setText(Cusername.getCellData(index));
        tfPassword.setText(Cpassword.getCellData(index));
        id = Cid.getCellData(index);
        String membership = Cmembership.getCellData(index);

        // Select appropriate membership radio button
        if (membership.equals("3 month")) {
            ch3month.setSelected(true);
             Tamount.setText("1200 sr");
        } else if (membership.equals("6 month")) {
            ch6month.setSelected(true);
             Tamount.setText("2100 sr");
        } else if (membership.equals("12 month")) {
            ch12month.setSelected(true);
            Tamount.setText("3600 sr");
            
        }
    }

    private void loadMember() {
        List<Member> members = DatabaseHandler.loadAll("Member");
        ObservableList<Member> observableMember = FXCollections.observableArrayList(members);
        Cid.setCellValueFactory(new PropertyValueFactory<Member, Integer>("id"));
        Cname.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        Cphone.setCellValueFactory(new PropertyValueFactory<Member, String>("phone"));
        Cemail.setCellValueFactory(new PropertyValueFactory<Member, String>("Email"));
        Cusername.setCellValueFactory(new PropertyValueFactory<Member, String>("Username"));
        Cpassword.setCellValueFactory(new PropertyValueFactory<Member, String>("password"));
        Cmembership.setCellValueFactory(new PropertyValueFactory<Member, String>("Membership"));
        memberList.setItems(observableMember);
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
    private void addMember(ActionEvent event) throws IOException {
        getMembership(event);

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
        Member m = new Member(0, name, phone, username, pass, email, membershipDuration);

        if (DatabaseHandler.insertMember(m)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Member added successfully!");
            loadMember();
            clearMemberFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to add member to database.");
          
        }
    }

    @FXML
    private void updateMember(ActionEvent event) {
        getMembership(event);
        // Get the values from the form fields
        String name = tfName.getText();
        String phone = tfPhonenum.getText();
        String username = tfUsername.getText();
        String pass = tfPassword.getText();
        String email = tfEmail.getText();

        if (name.isEmpty() || phone.isEmpty() || username.isEmpty() || pass.isEmpty() || email.isEmpty()||membershipDuration == "" ) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }
        Member m = new Member(id, name, phone, username, pass, email, membershipDuration);

        if (DatabaseHandler.updateMember(m)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Member has been updated successfully!");
            loadMember();
            clearMemberFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to update the member .");
        }
    }

    @FXML
    private void deleteMember(ActionEvent event) {
        if (id == 0) {
            AlertHandler.showAlert(Alert.AlertType.WARNING, "Validation Error", "You must select member.");
            return;
        }
        if (DatabaseHandler.deleteMember(id)) {
            AlertHandler.showAlert(Alert.AlertType.INFORMATION, "Success", "Member has been deleted successfully!");
            loadMember();
            clearMemberFields();
        } else {
            AlertHandler.showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete member.");
        }
    }

    private void clearMemberFields() {
        tfName.clear();
        tfPhonenum.clear();
        tfEmail.clear();
        tfUsername.clear();
        tfPassword.clear();
        membershipGroup.selectToggle(null);  // Deselect membership radio buttons
        membershipDuration = "";
        id = 0;
    }

}
