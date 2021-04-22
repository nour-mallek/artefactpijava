/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Service.ServiceReclamation;
import Entites.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class ReclamationbackController implements Initializable {

    @FXML
    private TableView<Reclamation> tvrecla;
    @FXML
    private Button affiche;
    @FXML
    private TableColumn<Reclamation,String> userid;
    @FXML
    private TableColumn<Reclamation,String> textrecla;
    @FXML
    private Button delete;
    private Reclamation d;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void affiche() {
        ServiceReclamation se = new ServiceReclamation();
        tvrecla.setItems(se.ListReclamation());
        userid.setCellValueFactory(new PropertyValueFactory("iduser"));
        textrecla.setCellValueFactory(new PropertyValueFactory("text_reclamation"));
        
    }

    @FXML
    private void delete(ActionEvent event) {
         System.out.println(d);
        ServiceReclamation ts = new ServiceReclamation();
        ts.remove(d);
        affiche();
    }

    @FXML
    private void select(MouseEvent event) {
        d = tvrecla.getSelectionModel().getSelectedItem();
    }

    
}
