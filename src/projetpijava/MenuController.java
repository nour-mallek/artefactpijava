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
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wajih
 */
public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane AP;
    @FXML
    private Button ajoutevent;
    @FXML
    private Button interevent;
    @FXML
    private Button ajoutsonsor;
    @FXML
    private Button affsponsor;
    @FXML
    private Button intereventclient;
    
    public void changetoAjouterScreen(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjouterChambre.fxml"));
        Scene tabbleViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tabbleViewScene);
        window.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPage("AjouterEvenement");
    }    
    


    private void loadPage(String page){
                           
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setCenter(root);
    }


    @FXML
    private void ajoutevent(MouseEvent event) {
                loadPage("AjouterEvenement");
 }

    @FXML
    private void interevent(MouseEvent event) {
                loadPage("AfficherEvenement");

    }

    @FXML
    private void ajoutsponsor(MouseEvent event) {
                loadPage("AjouterSponsor");
    }

    @FXML
    private void affsponsor(MouseEvent event) {
                loadPage("AfficherSponsor");
    }

    @FXML
    private void intereventclient(MouseEvent event) {
                loadPage("AfficherEvenement_Client");
    }

  


}
