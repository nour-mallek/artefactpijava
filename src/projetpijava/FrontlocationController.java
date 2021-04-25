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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import static projetpijava.FXMLlocationvController.global;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class FrontlocationController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnumdetel;
    @FXML
    private DatePicker tfstartat;
    @FXML
    private DatePicker tfendat;
    @FXML
    private ComboBox<Voiture> idcombo;
    @FXML
    private TextField tfheured;
    @FXML
    private TextField tfheurefin;
    @FXML
    private Button btnlouer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
//       public static final  String ACCOUNT_SID = "AC5855f26f1d059fcf73911900db0f5647";
//    public static final String  AUTH_TOKEN = "6db5b9f5a4fd8d7471fdc4e58f25e042";

       public static final  String ACCOUNT_SID = "AC2de4fee957884a2a787f10dec0b55303";
       public static final String  AUTH_TOKEN = "c1a71968e4a7a6d88978e30ee0f6509c";

    @FXML
    private void louer(ActionEvent event) {
         ServiceLocationv sl = new ServiceLocationv();
       
        Locationv v = new Locationv () ;
            v.setNom(tfnom.getText ());
            v.setPrenom(tfprenom.getText ());
            v.setNumerodetelephone((int) Float.parseFloat(tfnumdetel.getText ()));
            v.setStartat(new java.sql.Date(new java.util.Date().getTime()));
            v.setEndat(new java.sql.Date(new java.util.Date().getTime()));
            v.setHeuredebut(tfheured.getText());
            v.setHeurefin(tfheurefin.getText());
             try{
                
            Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
            String msg="Votre demande a été effectué avec succès ,votre voiture de location vous attend! ";
            Message message = Message.creator(new PhoneNumber("+216"+(tfnumdetel.getText ())),
                    
            new PhoneNumber("+17207073337"), msg).create();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("SMS Send Successfully");
            alert.show(); } catch (Exception e){

    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("");
            alert.setContentText("Something went wrong:"+e.toString());
            e.printStackTrace();
            alert.show();
        }
             
            sl.AddLocationv(v);  
            String val= idcombo.getSelectionModel().getSelectedItem().getModele();
           
            System.out.println(global);
            
         

    }

    @FXML
    private void comboaffiche(ActionEvent event) {
        ServiceLocationv se = new ServiceLocationv();
        String val= idcombo.getSelectionModel().getSelectedItem().getModele();
        
       int ve=  se.select_id_bynomvoiture(val);
        System.out.println("------> "+ve);
        global=ve;
        System.out.println("global"+global);
    }

//    @FXML
//    private void afficherprix(ActionEvent event) {
//         ServiceVoiture lv = new ServiceVoiture();
//         ServiceLocationv sv = new ServiceLocationv();
//           float remise=sv.remise(global);
//
//          Float prix = Float.valueOf(tfprixremise.getText() );
//          //calcul remise  
//                  Voiture v = new Voiture () ;
//
//          v.setPrix(Float.valueOf (prix)-(Float.valueOf (prix)*remise));
//    }

   
    }
    

