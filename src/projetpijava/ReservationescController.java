/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Escapade;
import Entites.Reservationesc;
import Service.ServiceEscapade;
import Service.ServiceReservationesc;
import static Service.ServiceReservationesc.getRandomStr;
import Utils.Maconnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationescController implements Initializable {

    @FXML
    private ComboBox<String> rescombobox;
    @FXML
    private TextField tfnbre;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tftel;
    private TableView<Escapade> tvescapade;
    private TableColumn<Escapade, String> colnom;
    private TableColumn<Escapade, String> coldescription;
    private TableColumn<Escapade, String> colville;
    private TableColumn<Escapade, Integer> colprix;
    @FXML
    private TextArea tacomment;
    @FXML
    private TableView<Reservationesc> tvreservation;
    @FXML
    private TableColumn<Reservationesc, Integer> colnbre;
    @FXML
    private TableColumn<Reservationesc, Date> coldate;
    @FXML
    private TableColumn<Reservationesc, String> colcommentaire;
    @FXML
    private TableColumn<Reservationesc, Integer> colnum;
    @FXML
    private TableColumn<Reservationesc, String> colescapade;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsup;

    /**
     * Initializes the controller class.
     */
    
    
    public static int global=0;
    private Label label;
    @FXML
    private Label labelreduction;
    @FXML
    private Button btnreserver;
    @FXML
    private Label labelnbre;
    @FXML
    private Label labeldate;
    @FXML
    private Label labelcode;
    @FXML
    private Label labeltel;
    @FXML
    private Label labescapade;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //afficherescapade();
      tfnbre.setText("on accepte deux personnes minimum");
      tacomment.setText("vous pouver entrer un code promo ou laissez vide");
      
      tftel.setText("entrer8 chiffres");
      btnsup.setDisable(true);
      btnmodifier.setDisable(true);
      afficherres();
      
      ResultSet rs1 = null;
      
      try {
                Connection cnx = Maconnexion.getInstance().getCnx();
                rs1 = cnx.createStatement().executeQuery("SELECT title FROM escapade");
            } catch (SQLException ex) {
                Logger.getLogger(ReservationescController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while (rs1.next()) {  // loop
                    
                    // Now add the comboBox addAll statement
                    rescombobox.getItems().addAll(rs1.getString("title"));
                    
                }} catch (SQLException ex) {
                    Logger.getLogger(ReservationescController.class.getName()).log(Level.SEVERE, null, ex);
                }
      
      
      
      
    }    
//****************afficher les donnée dans le combobox****************
    @FXML
    private void afficher(ActionEvent event) {
       ServiceReservationesc se = new ServiceReservationesc();
        String val=rescombobox.getSelectionModel().getSelectedItem();
        global=new ServiceReservationesc().select_id_bynom(val);
        System.out.println(global);
           // label.setText(rescombobox.getValue());
       
     
    }
/*
    private void afficherescapade() {
        ServiceReservationesc se = new ServiceReservationesc();
        tvescapade.setItems(se.List());
        colnom.setCellValueFactory(new PropertyValueFactory("title"));
        coldescription.setCellValueFactory(new PropertyValueFactory("histoire"));
        colprix.setCellValueFactory(new PropertyValueFactory("price"));
        colville.setCellValueFactory(new PropertyValueFactory("ville"));
    }
*/
    @FXML
    private void ajouterreservation(ActionEvent event) throws IOException {
       
    /*
   ServiceReservationesc  sl = new ServiceReservationesc();
       
        Reservationesc v = new Reservationesc() ;
            v.setCommentaire(tacomment.getText());
            v.setTel((int) Float.parseFloat(tftel.getText ()));
            v.setNbrepers((int) Float.parseFloat(tfnbre.getText ()));
            v.setDateres(new java.sql.Date(new java.util.Date().getTime()));
            sl.addResrvation(v);
            String val= rescombobox.getSelectionModel().getSelectedItem();
            System.out.println(global);
            
            //Image img = new Image("/projetpijava.Icons/success.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("success")
                    .text("votre reservation a bien été prise en charge")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.showConfirm();
                    
            //colescapade.setCellValueFactory(new PropertyValueFactory("title"));
            afficherres();
            btnreserver.setDisable(true);
           
        */       
    
    ServiceReservationesc  sl = new ServiceReservationesc();
       
        Reservationesc v = new Reservationesc() ;
    
   
    if (tftel.getText().length()!=8)
    {
    tftel.setText("entrer8 chiffres");
    tftel.setVisible(true);
    labelreduction.setVisible(false);
    labescapade.setVisible(false);
    rescombobox.setVisible(false);
    }
    else 
        {
            tftel.setVisible(false);
            labeltel.setVisible(false);
        } 
    
    
    
    if ((tacomment.getText().length() < 6 && !tacomment.getText().equals("") )) {
            tacomment.setText("vous pouver entrer un code promo ou laissez vide");
            tacomment.setVisible(true);
            // warning.setVisible(true);
            return;
    }
         else {
            tacomment.setVisible(false);
            labelcode.setVisible(false);
        }
    
    
      
    if ((Date.valueOf(tfdate.getValue())).before(sl.selectDate()))
            {
                
              System.out.println(tfdate.getEditor().getText());
            System.out.println(sl.selectDate());
            Alert alert = new Alert(AlertType.INFORMATION);
             alert.setTitle("Attention");
                            alert.setHeaderText("Echec");
                            alert.setContentText("Veillez verifier que  la date de votre demmande est superieur à la date actuelle");
                            alert.show();
                            tfdate.requestFocus();
                            
        }
    
    else {
    tfdate.setVisible(false);
    labeldate.setVisible(false);
    
    }
    
    
    
    int i = Integer.parseInt(tfnbre.getText());
    if (i < 2)
    {
    tfnbre.setText("on accepte deux personnes minimum");
    tfnbre.setVisible(true); 
    labelreduction.setVisible(false);
    labescapade.setVisible(false);
    rescombobox.setVisible(false);
    }
    else 
        {
            tfnbre.setVisible(false);
            labelnbre.setVisible(false);
        } 
    
    
    
    if (!tfnbre.isVisible() &&!tfdate.isVisible() && !tacomment.isVisible()&& !tftel.isVisible())
    {
    
       // ServiceReservationesc  sl = new ServiceReservationesc();
       
        //Reservationesc v = new Reservationesc() ;
            v.setCommentaire(tacomment.getText());
            v.setTel((int) Float.parseFloat(tftel.getText ()));
            v.setNbrepers((int) Float.parseFloat(tfnbre.getText ()));
            v.setDateres(new java.sql.Date(new java.util.Date().getTime()));
            sl.addResrvation(v);
            String val= rescombobox.getSelectionModel().getSelectedItem();
            System.out.println(global);
            
            //Image img = new Image("/projetpijava.Icons/success.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("success")
                    .text("votre reservation a bien été prise en charge")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationBuilder.darkStyle();
            notificationBuilder.showConfirm();
                    
            //colescapade.setCellValueFactory(new PropertyValueFactory("title"));
            afficherres();
          //btnreserver.setDisable(true);
         btnreserver.setVisible(false);
         //btnmodifier.setVisible(false);
         //btnsup.setVisible(false);
            labelreduction.setVisible(false);
            rescombobox.setVisible(false);
            labescapade.setVisible(false);
            /*
            Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/njarab.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();*/
            
            
    
    }
    
    
    
    }
    
    
   
    
    
    
    
    

    private void afficherres() {
        ServiceReservationesc sl = new ServiceReservationesc();
        tvreservation.setItems(sl.ListResrvation());
        //colref.setCellValueFactory(new PropertyValueFactory("ref"));
        colnbre.setCellValueFactory(new PropertyValueFactory("nbrepers"));
        colcommentaire.setCellValueFactory(new PropertyValueFactory("commentaire"));
        colnum.setCellValueFactory(new PropertyValueFactory("tel"));
        coldate.setCellValueFactory(new PropertyValueFactory("dateres"));
        colescapade.setCellValueFactory(new PropertyValueFactory("escapade_id"));
        //colescapade.setCellValueFactory(c-> new SimpleStringProperty(rescombobox.getSelectionModel().getSelectedItem()));
        //colescapade =(TableColumn<Reservationesc, Integer>) rescombobox.getItems();
        //String val= rescombobox.getSelectionModel().getSelectedItem();
        //global=val;
        //System.out.println(val+"thouraya");
        
        //colescapade.setCellFactory(val);
        
        
        
        
    }

    @FXML
    private void modifierres(ActionEvent event) {
        Reservationesc esc = tvreservation.getSelectionModel().getSelectedItem();
        ServiceReservationesc ts = new ServiceReservationesc();
        
        
        esc.setNbrepers(Integer.parseInt(tfnbre.getText()));
         esc.setTel(Integer.parseInt(tftel.getText()));
         
        //esc.setCommentaire(tacomment.getText());
        esc.setDateres(new java.sql.Date(new java.util.Date().getTime()));
        
        System.out.println(esc.toString());
        ts.updateRes(esc);
        afficherres();
    }

    @FXML
    private void supres(ActionEvent event) {
        
        Reservationesc res = tvreservation.getSelectionModel().getSelectedItem();
        ServiceReservationesc sl = new ServiceReservationesc();
        sl.remove(res);
        afficherres();
    }

    @FXML
    private void deselect(KeyEvent event) {
         if (event.getCode()==KeyCode.ESCAPE) {
            tvreservation.getSelectionModel().clearSelection();
            btnmodifier.setDisable(true);
            btnsup.setDisable(true);
            tfnbre.clear();
            tacomment.clear();
          //tfdate.c
            tftel.clear();
        } 
    }

    @FXML
    private void select(MouseEvent event) {
        Reservationesc d = tvreservation.getSelectionModel().getSelectedItem();
        btnsup.setDisable(true);
            btnmodifier.setDisable(true);
labelreduction.setVisible(false);
tacomment.setVisible(false);
labelcode.setVisible(false);
            //tacomment.setText(d.getCommentaire());
            tftel.setText(String.valueOf(d.getTel()));
            tfnbre.setText(String.valueOf(d.getNbrepers()));
            btnsup.setDisable(false);
            btnmodifier.setDisable(false);
            btnreserver.setDisable(true);
    }

    @FXML
    private void redirectionescapade(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/Escapade.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void handlecontroledesaisie(MouseEvent event) {
       
    
        
    }

     
    
    @FXML
    private void reduction(MouseEvent event) {
         int len = 6; 
        System.out.println(getRandomStr(len)); 
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("votre code promo est les suivant");
        alert.setHeaderText("copier ce bout de code dans le champ code promo afin de beneficier de 10% de reduction");
        alert.setContentText(getRandomStr(len));
        alert.showAndWait();
        labelreduction.setDisable(true);
        //labelreduction.setText(getRandomStr(len));
    }

    @FXML
    private void refresh(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/Reservationesc.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }
    
    
    
}
