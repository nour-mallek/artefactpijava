/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;
import Service.ServiceReclamation;
import Entites.Reclamation;
import Utils.Maconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class ReclamtioonController implements Initializable {

    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextArea tfrecla;
    @FXML
    private Button submitbtn;
    public static int gl=0;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResultSet rs1 = null;
          try {
                Connection cnx = Maconnexion.getInstance().getCnx();
                rs1 = cnx.createStatement().executeQuery("SELECT Username FROM user");
            } catch (SQLException ex) {
                Logger.getLogger(ReservationescController.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            while (rs1.next()) {  try {
                // loop
                
                // Now add the comboBox addAll statement
                combo.getItems().addAll(rs1.getString("Username"));
            } catch (SQLException ex) {
                Logger.getLogger(ReclamtioonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamtioonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void addrecla(ActionEvent event) throws IOException {
        String text_recla = tfrecla.getText();
        String u=combo.getSelectionModel().getSelectedItem();
        gl= new ServiceReclamation().select_id_bynom(u);
        System.out.println(gl);
        Reclamation ur = new Reclamation();
        ur.setIduser(gl);
        ur.setText_reclamation(text_recla);
        ServiceReclamation recla = new ServiceReclamation();
       recla.AddReclamation(ur);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Success");
             alert.setHeaderText("");
             alert.setContentText("Reclamation envoyer");
             alert.show();
             Parent root = FXMLLoader.load(getClass().getResource("reclamationback.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
