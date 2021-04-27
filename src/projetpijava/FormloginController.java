/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Entites.user;
import Service.ServiceUser;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class FormloginController implements Initializable {

    @FXML
    private TextField usernametf;
    @FXML
    private TextField passwordtf;
    @FXML
    private Label frogot;
    @FXML
    private Button btnlogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        
      ServiceUser su=new ServiceUser();
      String Username = usernametf.getText();
      String Password = passwordtf.getText();
      user u=new user(Username,Password);
        int log = su.login(u);
        if (log == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("Connected");
            alert.show();

            Parent root = FXMLLoader.load(getClass().getResource("tvuser.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } else {
              System.out.println("Wrong username or password");
               Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Failed");
             alert.setHeaderText("");
             alert.setContentText("Wrong username or password");

             alert.show();
           

        }


     
        
    }

    @FXML
    private void forgot(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("sendcode.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
}
