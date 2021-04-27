/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entite.Evenement;
import Entite.Sponsor;
import Service.ServiceEvenement;
import Service.ServiceSponsor;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterSponsorController implements Initializable {

    @FXML
    private TextField cin;
    @FXML
    private TextField budget;
    @FXML
    private TextField message;
    @FXML
    private ComboBox<String> comboevent;
    @FXML
    private Label msg;
    ObservableList<String> listnom = FXCollections.observableArrayList();
    ServiceSponsor sp = new ServiceSponsor();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void fillcombo() throws SQLException{
                
        ServiceEvenement ser = new ServiceEvenement();
        List<Evenement> list = ser.readAll();
        for (Evenement aux : list)
        {
          listnom.add(aux.getNom_event());
        }
        comboevent.setItems(listnom);
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
     if(Validchamp(cin)&&Validchamp(budget)&&Validchamp(message)) 
     {
        Sponsor spon = new Sponsor(cin.getText(), budget.getText(), message.getText(), 1, comboevent.getValue());
        sp.ajouter(spon);
        cin.setText("");
        budget.setText("");
        message.setText("");
            String title = "succes ";
        String message = "Ajouter avec succée.";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait(); 
     }
     else
     {
            String title = "Error ";
        String message = "Verifier les champs.";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.ERROR);
        tray.showAndWait(); 
     }
    }
       
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 2;
    }
}
