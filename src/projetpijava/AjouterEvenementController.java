/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entite.Evenement;
import Service.ServiceEvenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private DatePicker datedeb;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField ville;
    @FXML
    private TextField desc;
    @FXML
    private TextField affiche;
    @FXML
    private Button uploadbutton;
    @FXML
    private Button btnajout;

    ServiceEvenement se = new ServiceEvenement();
    @FXML
    private Label msg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Uploadfile(ActionEvent event) {
               
        FileChooser fc = new FileChooser();
        String path = fc.showOpenDialog(uploadbutton.getScene().getWindow()).getPath();
        affiche.setText(path);
    }

    @FXML
    private void ajouterevent(ActionEvent event) throws SQLException, IOException {
        if(Validchamp(nom) && Validchamp(ville) && Validchamp(desc) && Validchamp(affiche))
        {
                DatePicker datsort=(DatePicker) datedeb;
                String date= (String) datsort.getValue().toString();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date datedebut = new java.sql.Date(myDate.getTime());
                
                DatePicker datsortt=(DatePicker) datefin;
                String datee= (String) datsortt.getValue().toString();
                datee = datee.substring(0,4)+'/'+datee.substring(5,7)+'/'+datee.substring(8);                
                java.util.Date myDatee = new java.util.Date(date);
                java.sql.Date datefinn = new java.sql.Date(myDatee.getTime());
                                   
                File f = new File(affiche.getText());

        Evenement evennement = new Evenement(nom.getText(), datedebut, datefinn, ville.getText(), desc.getText(),f.getName(), 0);
        se.ajouter(evennement);
        
        Files.copy(Paths.get(affiche.getText()),Paths.get("E:\\xampp\\htdocs\\images\\"+f.getName()),REPLACE_EXISTING);
   
        nom.setText("");
        ville.setText("");
        desc.setText("");
        affiche.setText("");
        msg.setText("Ajouter avec succée.");
        }
        else
        {
        msg.setText("Verifier les champs.");
        }


    }
    
            
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 2;
    }

}
