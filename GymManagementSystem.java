/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package gymmanagementsystem;

import gymmanagementsystem.GymClasses.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ralru
 */
public class GymManagementSystem extends Application {
      private static Stage primaryStage;
      
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage ;
       showScreen("gymmanagementsystem/login.fxml","Login");
            
    }
    
    public static void showScreen( String fxmlpath,String title) throws IOException{
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource(fxmlpath));
         Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
           primaryStage.show();
         
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
