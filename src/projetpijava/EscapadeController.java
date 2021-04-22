/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Escapade;
import Service.ServiceEscapade;
import Utils.Maconnexion;
import static Utils.Maconnexion.ConnectDb;
import com.sun.javafx.scene.control.skin.Utils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class EscapadeController implements Initializable {
ArrayList name = new ArrayList();
private Stage primaryStage;
private ObservableList<Escapade> listR = FXCollections.observableArrayList();
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfville;
    @FXML
    private TextField tfprix;
    @FXML
    private ImageView tfimage;
      @FXML
    private TableView<Escapade> tvescapade;
    private TableColumn<Escapade, Integer> colid;
    @FXML
    private TableColumn<Escapade, String> colnom;
    @FXML
    private TableColumn<Escapade, String> coldescription;
    @FXML
    private TableColumn<Escapade, Integer> colprix;
    @FXML
    private TableColumn<Escapade, String> colville;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    @FXML
    private TextField ftR;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
        
        
        Connection cnx = Maconnexion.getInstance().getCnx();
        try {
            ResultSet rs8 = cnx.createStatement().executeQuery("SELECT ville FROM escapade");
            while(rs8.next()){
                name.add(rs8.getString("ville"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(EscapadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextFields.bindAutoCompletion(ftR,name);
        
        
    }    

    @FXML
    private void ajouterescapade(ActionEvent event) {
        String title = tfnom.getText();
        String histoire = tfdescription.getText();
        String ville = tfville.getText();
        int price = Integer.parseInt(tfprix.getText());
        //String image = tfimage.getImageView();
        //Escapade esc = new Escapade(price, price, histoire, ville, ville);
        Escapade esc = new Escapade(price, price, histoire, ville, title,title);
        ServiceEscapade esccrud = new ServiceEscapade();
        esccrud.addEscapade(esc);
        afficher();
    }

    private void ajouterimage(ActionEvent event) {
        Image image = new Image("/projetpijava.Icons/imagesss.jpg");
        tfimage.setImage(image);
        
    }

    private void afficher() {
        ServiceEscapade se = new ServiceEscapade();
        tvescapade.setItems(se.ListEscapade());
       // colid.setCellValueFactory(new PropertyValueFactory("id_esc"));
        colnom.setCellValueFactory(new PropertyValueFactory("title"));
        coldescription.setCellValueFactory(new PropertyValueFactory("histoire"));
        colprix.setCellValueFactory(new PropertyValueFactory("price"));
        colville.setCellValueFactory(new PropertyValueFactory("ville"));
        
    }

    @FXML
    private void supprimeresc(ActionEvent event) {
        Escapade esc = tvescapade.getSelectionModel().getSelectedItem();
        ServiceEscapade ts = new ServiceEscapade();
        ts.remove(esc);
        afficher();
    }

    @FXML
    private void modifier(ActionEvent event) {
        Escapade esc = tvescapade.getSelectionModel().getSelectedItem();
        ServiceEscapade ts = new ServiceEscapade();
        //esc.setId_esc(1811);
        esc.setTitle(tfnom.getText());
        esc.setHistoire(tfdescription.getText());
        esc.setVille(tfville.getText());
        esc.setPrice(Integer.parseInt(tfprix.getText()));
        System.out.println(esc.toString());
        ts.updateEsc(esc);
        afficher();
        
    }

    @FXML
    private void deselect(KeyEvent event) {
        if (event.getCode()==KeyCode.ESCAPE) {
            tvescapade.getSelectionModel().clearSelection();
            btnupdate.setDisable(true);
            btndelete.setDisable(true);
            tfnom.clear();
            tfdescription.clear();
            tfville.clear();
            tfprix.clear();
        } 
    }

    @FXML
    private void select(MouseEvent event) {
        Escapade d = tvescapade.getSelectionModel().getSelectedItem();

            tfnom.setText(d.getTitle());
            tfdescription.setText(d.getHistoire());
            tfville.setText(d.getVille());
            tfprix.setText(String.valueOf(d.getPrice()));
            btndelete.setDisable(false);
            btnupdate.setDisable(false);
    }

    @FXML
    private void imprimer(ActionEvent event) {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(this.primaryStage); 
            
    Node root = this.tvescapade;
           job.printPage(root);
           job.endJob();



    }
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
        Connection conn = ConnectDb();
      
        String value9 = ftR.getText();
      
            PreparedStatement ps = conn.prepareStatement("select * from escapade where ville Like'"+value9+"'");
            
            ResultSet rs5 = ps.executeQuery();
            
            while (rs5.next()){   
                
                listR.add(new Escapade( rs5.getInt(1),rs5.getInt(2), rs5.getString(3), rs5.getString(4), rs5.getString(5), rs5.getString(6)));               
            }
        //colid.setCellValueFactory(new PropertyValueFactory("id_esc"));
        colnom.setCellValueFactory(new PropertyValueFactory("title"));
        coldescription.setCellValueFactory(new PropertyValueFactory("histoire"));
        colprix.setCellValueFactory(new PropertyValueFactory("price"));
        colville.setCellValueFactory(new PropertyValueFactory("ville"));

        tvescapade.setItems(listR);
    }

    @FXML
    private void redirectionres(MouseEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/Reservationesc.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void redirect(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/projetpijava/njarab.fxml"));
     
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }

    
    
}
