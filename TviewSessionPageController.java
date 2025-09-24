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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TviewSessionPageController implements Initializable {
 @FXML
    private TableView<Classes> classesList;
     @FXML
    private TableColumn<Classes, String> Cname;

    @FXML
    private TableColumn<Classes, String> Ctime;

    @FXML
    private Button backviewbtn;

  @FXML
    private TableColumn<Classes,String> Cday;


      @FXML
    private void BackmanageVAction(ActionEvent event) throws IOException  {
        GymManagementSystem.showScreen("gymmanagementsystem/TmanageSesstionPage.fxml", "manage page");
       
    }  
       
       
       
       
     private void loadClasses() {
          int TrainerId = SessionManager.getInstance().getCurrentUser().getId();
        List<Classes> classes = DatabaseHandler.loadClassesByTrainer(TrainerId);
        ObservableList<Classes> observableclass = FXCollections.observableArrayList(classes);
        Cname.setCellValueFactory(new PropertyValueFactory<Classes, String>("name"));
        Ctime.setCellValueFactory(new PropertyValueFactory<Classes, String>("time"));
        Cday.setCellValueFactory(new PropertyValueFactory<Classes, String>("day"));
        classesList.setItems(observableclass);
    }  
       

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       loadClasses();
    }    
    
}
