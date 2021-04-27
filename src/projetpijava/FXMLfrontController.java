/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXMLfrontController implements Initializable {

    @FXML
    private Button btnescapade;
    @FXML
    private Button btnmaisons;
    @FXML
    private Button btnvoiture;
    @FXML
    private Button btnreclamation;
    @FXML
    private Button btncreateaccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toescapde(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/Reservationesc.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tomaison(ActionEvent event) {
        
    }

    @FXML
    private void tovoiture(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Frontlocationlist.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
                            
                           
    }

    @FXML
    private void toreclamation(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("reclamtioon.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void tosignup(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
}
