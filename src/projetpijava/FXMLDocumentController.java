/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;
import Entites.user;
import Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



/**
 *
 * @author Nour
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField nomtf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField passwordtf;
    @FXML
    private Button addbutton;
    @FXML
    private PasswordField confirmtf;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void addUser(ActionEvent event) throws IOException {
         if(passwordtf.getText().equals(confirmtf.getText())){
        String Username = usernametf.getText();
        String prenom = prenomtf.getText();
        String nom = nomtf.getText();
        String Email = emailtf.getText();
        String Password = passwordtf.getText();
        
                  user u = new user(Username, prenom, nom, Email, Password);
             ServiceUser users = new ServiceUser();
             users.AddUser(u);
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Success");
             alert.setHeaderText("");
             alert.setContentText("utlilisateur ajouté");
             alert.show();
             Parent root = FXMLLoader.load(getClass().getResource("formlogin.fxml"));
            
        
             Stage stage = new Stage();
             Scene scene = new Scene(root);

             stage.setScene(scene);
             stage.show();
         }
         else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Failed");
             alert.setHeaderText("");
             alert.setContentText("Password not matching");

             alert.show();
          
           
          
        }
         }
        
    }
    

