/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Entites.user;
import Service.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class UsersController implements Initializable {

    @FXML
    private TableView<user> tvusers;
    @FXML
    private TableColumn<user,String> Username;
    @FXML
    private TableColumn<user,String> nom;
    @FXML
    private TableColumn<user,String> prenom;
    @FXML
    private TableColumn<user,String> email;
    @FXML
    private TableColumn<user,String> role;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    /* private void afficher() {
        ServiceUser se = new ServiceUser();
        tvusers.setItems(se.ListUser());
        Username.setCellValueFactory(new PropertyValueFactory("Username"));
        nom.setCellValueFactory(new PropertyValueFactory("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        email.setCellValueFactory(new PropertyValueFactory("Email"));
       role.setCellValueFactory(new PropertyValueFactory("Role"));
        
    }*/
}
