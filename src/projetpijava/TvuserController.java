/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import Service.ServiceUser;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class TvuserController implements Initializable {

    @FXML
     private TableColumn<user,String> Username;
    @FXML
     private TableColumn<user,String> nom;
    @FXML
     private TableColumn<user,String> prenom;
    @FXML
    private TableColumn<user,String> Email;
    @FXML
     private TableColumn<user,String> Role;
    @FXML
    private TableView<user> tvusers;
    @FXML
    private Button afficher;
    @FXML
    private Button editbtn;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button removebtn;
    private user d;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    } 
       @FXML
    private void afficher() {
         ServiceUser se = new ServiceUser();
        tvusers.setItems(se.ListUser());
        Username.setCellValueFactory(new PropertyValueFactory("Username"));
        nom.setCellValueFactory(new PropertyValueFactory("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        Email.setCellValueFactory(new PropertyValueFactory("Email"));
       Role.setCellValueFactory(new PropertyValueFactory("Role"));
    }
    

   @FXML
    private void delete(ActionEvent event) {
        System.out.println(d);
        ServiceUser ts = new ServiceUser();
        ts.remove(d);
        afficher();
    }

    @FXML
    private void modifier(ActionEvent event) {
       user u = tvusers.getSelectionModel().getSelectedItem();
        ServiceUser ts = new ServiceUser();
        u.setId_user(458);
        u.setNom(tfnom.getText());
        u.setUsername(tfusername.getText());
        u.setPrenom(tfprenom.getText());
        u.setPassword(tfpassword.getText());
        u.setEmail(tfemail.getText());
        System.out.println(u.toString());
        ts.updateUser(u);
        afficher();
    }
    @FXML
    private void select(MouseEvent event) {
         d = tvusers.getSelectionModel().getSelectedItem();

            tfnom.setText(d.getNom());
            tfprenom.setText(d.getPrenom());
            tfusername.setText(d.getUsername());
            tfemail.setText(d.getEmail());
            tfpassword.setText(d.getPassword());
            removebtn.setDisable(false);
            editbtn.setDisable(false);
    }

   

 
   
}
