/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;
import Entites.Reservationesc;
import Service.ServiceReservationesc;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class NjarabController implements Initializable {

    @FXML
    private TableView<Reservationesc> tv;
    @FXML
    private TableColumn<Reservationesc, Integer> colnbre;
    @FXML
    private TableColumn<Reservationesc, Date> coldate;
    @FXML
    private TableColumn<Reservationesc, String> colcommentaire;
    @FXML
    private TableColumn<Reservationesc, Integer> colnum;
    @FXML
    private TableColumn<Reservationesc, Integer> colescapade;
    @FXML
    private TextField tfnbre;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextArea tacomment;
    @FXML
    private TextField tftel;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsup;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affich();
    }    

   

    @FXML
    private void select(MouseEvent event) {
         Reservationesc d = tv.getSelectionModel().getSelectedItem();

            tacomment.setText(d.getCommentaire());
            tftel.setText(String.valueOf(d.getTel()));
            tfnbre.setText(String.valueOf(d.getNbrepers()));
            btnsup.setDisable(false);
            btnmodifier.setDisable(false);
    }
    
    
    
    private void affich() {
        ServiceReservationesc s = new ServiceReservationesc();
        tv.setItems(s.ListResrvation());
        colnbre.setCellValueFactory(new PropertyValueFactory("nbrepers"));
        colcommentaire.setCellValueFactory(new PropertyValueFactory("commentaire"));
        colnum.setCellValueFactory(new PropertyValueFactory("tel"));
        coldate.setCellValueFactory(new PropertyValueFactory("dateres"));
        colescapade.setCellValueFactory(new PropertyValueFactory("escapade_id"));  
    }



    @FXML
    private void modifierres(ActionEvent event) {
        
         Reservationesc esc = tv.getSelectionModel().getSelectedItem();
        ServiceReservationesc ts = new ServiceReservationesc();
        
        
        esc.setNbrepers(Integer.parseInt(tfnbre.getText()));
         esc.setTel(Integer.parseInt(tftel.getText()));
        esc.setCommentaire(tacomment.getText());
        esc.setDateres(new java.sql.Date(new java.util.Date().getTime()));
        
        System.out.println(esc.toString());
        ts.updateRes(esc);
        affich();
        
    }
    
    

    @FXML
    private void supres(ActionEvent event) {
        Reservationesc res = tv.getSelectionModel().getSelectedItem();
        ServiceReservationesc sl = new ServiceReservationesc();
        sl.remove(res);
        affich();
    }
      @FXML
    private void deselect(KeyEvent event) {
       if (event.getCode()==KeyCode.ESCAPE) {
            tv.getSelectionModel().clearSelection();
            btnmodifier.setDisable(true);
            btnsup.setDisable(true);
            tfnbre.clear();
            tacomment.clear();
          //tfdate.c
            tftel.clear(); 
        
    }
}

    @FXML
    private void goback(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/Escapade.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
}