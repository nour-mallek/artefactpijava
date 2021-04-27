/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class BackController implements Initializable {

    @FXML
    private Button btnescapade;
    @FXML
    private Button btnmaisons;
    @FXML
    private Button btnvoiture;
    @FXML
    private Button btnrecla;
    @FXML
    private Button btnsponsor;
    @FXML
    private Button btnmaisonres;
    @FXML
    private Button bntlocationvoi;
    @FXML
    private Button btnuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toescapade(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/Escapade.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    private void tomaison(ActionEvent event) {
    }

    @FXML
    private void tovoiture(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLvoiture.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void toreclamation(ActionEvent event) {
    }

    @FXML
    private void tosponsor(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void tomaisonres(ActionEvent event) {
    }

    @FXML
    private void tolocation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLlocationv.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void touser(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/tvuser.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}
