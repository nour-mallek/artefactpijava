/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Service.ServiceUser;
import Entites.user;
import java.io.IOException;

/**
 *
 * @author Nour
 */
public class Projetpijava extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("formlogin.fxml"));
        
       /* Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
   
            // Parent root = FXMLLoader.load(getClass().getResource("njarab.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("formlogin.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Artefact");
            stage.setScene(scene);
            stage.show();
       
            
        
    
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
