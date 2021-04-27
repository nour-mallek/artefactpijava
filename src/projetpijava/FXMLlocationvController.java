/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Locationv;
import Entites.Voiture;

import Service.ServiceLocationv;
import Service.ServiceVoiture;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class FXMLlocationvController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnumdetel;
    //@FXML
    //private TextField tfheurefin;
    @FXML
    private DatePicker tfstartat;
    @FXML
    private DatePicker tfendat;
    //@FXML
    //private TextField tfheuredebut;
    @FXML
    private TableView<Locationv> tvlocationv;
    private TableColumn<Locationv, Integer> cid;
    @FXML
    private TableColumn<Locationv, String> cnom;
    @FXML
    private TableColumn<Locationv, String> cprenom;
    @FXML
    private TableColumn<Locationv, String> cnumdetel;
    @FXML
    private TableColumn<Locationv, Date> cstartat;
    @FXML
    private TableColumn<Locationv, Date> cendat;
    @FXML
    private TableColumn<Locationv, Time> cheuredebut;
    @FXML
    private TableColumn<Locationv, Time> cheurefin;
    @FXML
    private Button btnaffiche;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsuppr;
    @FXML
    private Button btnlocation;
    @FXML
    private ComboBox<Voiture> idcombo;
    @FXML
    private TextField tfheured;
    @FXML
    private TextField tfheurefin;
    @FXML
    private TableColumn<Locationv, Integer> colvoiture;

    /**
     * Initializes the controller class.
     */
    public static int global=0;
    @FXML
    private Button statbtn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showlocation();
        ServiceVoiture sv = new ServiceVoiture();
        idcombo.getItems().addAll(sv.AfficherVoiture());
          idcombo.setConverter(new StringConverter<Voiture>() {
            @Override
            public String toString(Voiture object) {
                return object.getModele();
            }

            @Override
            public Voiture fromString(String string) {
                return null;
            }
        });      
    }    

    @FXML
    private void showlocation() {
        ServiceLocationv sl = new ServiceLocationv();
        tvlocationv.setItems(sl.AfficherLocationv());
        //cid.setCellValueFactory(new PropertyValueFactory("id"));
        cnom.setCellValueFactory(new PropertyValueFactory("nom"));
        cprenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        cnumdetel.setCellValueFactory(new PropertyValueFactory("numerodetelephone"));
        cstartat.setCellValueFactory(new PropertyValueFactory("startat"));
        cendat.setCellValueFactory(new PropertyValueFactory("endat"));
        cheuredebut.setCellValueFactory(new PropertyValueFactory("heuredebut"));
        cheurefin.setCellValueFactory(new PropertyValueFactory("heurefin"));
        colvoiture.setCellValueFactory(new PropertyValueFactory("voiture_id"));
        
        //colvoiture.setCellValueFactory(c-> new SimpleStringProperty(idcombo.getValue().getModele()));
        
        
    }

    @FXML
    private void modifierlocation(ActionEvent event) {
        Locationv lv = tvlocationv.getSelectionModel().getSelectedItem();
        ServiceLocationv vl = new ServiceLocationv();
        
        lv.setNom(tfnom.getText());
        lv.setPrenom(tfprenom.getText());
        lv.setNumerodetelephone(Integer.parseInt(tfnumdetel.getText()));
        lv.setHeuredebut(tfheured.getText ());
        lv.setHeurefin(tfheurefin.getText ());
        lv.setStartat(new java.sql.Date(new java.util.Date().getTime()));
        lv.setEndat(new java.sql.Date(new java.util.Date().getTime()));
        //lv.setStartat(tfstartat.getDate()));
        //lv.setEndat(tfendat.getText());
        //lv.setHeuredebut(tfheuredebut.getText());
        //lv.setHeurefin(tfheurefin.getText());
        System.out.println(lv.toString());
        vl.modifierlocation(lv);
         showlocation();
    }

    @FXML
    private void supprimerlocation(ActionEvent event) {
        Locationv lv = tvlocationv.getSelectionModel().getSelectedItem();
        ServiceLocationv sl = new ServiceLocationv();
        sl.supprimerlocation(lv);
        showlocation();
    }

    @FXML
    private void Ajouterlocation(ActionEvent event) {
        ServiceLocationv sl = new ServiceLocationv();
       
        Locationv v = new Locationv () ;
            v.setNom(tfnom.getText ());
            v.setPrenom(tfprenom.getText ());
            v.setNumerodetelephone((int) Float.parseFloat(tfnumdetel.getText ()));
            v.setStartat(new java.sql.Date(new java.util.Date().getTime()));
            v.setEndat(new java.sql.Date(new java.util.Date().getTime()));
            v.setHeuredebut(tfheured.getText());
            v.setHeurefin(tfheurefin.getText());
             
           sl.AddLocationv(v);  
           String val= idcombo.getSelectionModel().getSelectedItem().getModele();
           
            System.out.println(global);
            showlocation();
            
            
            

  
         

    }

    @FXML
    private void deselect(KeyEvent event) {
        if (event.getCode()==KeyCode.ESCAPE) {
            tvlocationv.getSelectionModel().clearSelection();
            btnmodifier.setDisable(true);
            btnsuppr.setDisable(true);
            tfnom.clear();
            tfprenom.clear();
            tfnumdetel.clear();
            tfheured.clear();
            tfheurefin.clear();
            
    }}

    @FXML
    private void select(MouseEvent event) {
        Locationv d = tvlocationv.getSelectionModel().getSelectedItem();

            tfnom.setText(d.getNom());
            tfprenom.setText(d.getPrenom());
            tfnumdetel.setText(String.valueOf(d.getNumerodetelephone()));
            tfheured.setText(d.getHeuredebut());
            tfheurefin.setText(d.getHeurefin());
            btnsuppr.setDisable(false);
            btnmodifier.setDisable(false);
    }

    @FXML
    private void affichercombo(ActionEvent event) {
        ServiceLocationv se = new ServiceLocationv();
        String val= idcombo.getSelectionModel().getSelectedItem().getModele();
        
        int ve=  se.select_id_bynomvoiture(val);
        System.out.println("------> "+ve);
        global=ve;
        System.out.println("global"+global);
        
       // global=new ServiceLocationv().select_id_bynomvoiture(val);
        //System.out.println("------> "+val);
    }

    @FXML
    private void statlocationv(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader ();
                            
                            loader.setLocation(getClass().getResource("Statlocationv.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(FXMLvoitureController.class.getName()).log(Level.SEVERE, null, ex);
    }
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
}
    }

    
    
    

