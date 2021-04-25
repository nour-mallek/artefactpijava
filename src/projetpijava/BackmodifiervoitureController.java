/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Voiture;
import Service.ServiceVoiture;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class BackmodifiervoitureController implements Initializable {

    @FXML
    private TextField tfMatricule;
    @FXML
    private TextField tfmodele;
    @FXML
    private TextField tfmarque;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfboite;
    @FXML
    private TextField tfville;
    @FXML
    private Button btnimage;
    @FXML
    private TextField ImgPath;
    @FXML
    private ImageView imagenews;
   private Image image;
          private File file;
           private FileChooser fileChooser;
    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button Modifier;
    @FXML
    private TextField id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       fileChooser = new FileChooser();
        new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.gif");
ServiceVoiture sn= new ServiceVoiture();
        Voiture n = new Voiture();
       

      btnimage.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        file = fileChooser.showOpenDialog(btnimage.getScene().getWindow());
        String path = file.getAbsolutePath();
        
        path= path.substring(23, path.length());
        path ="http://localhost/Images/" +path;
        ImgPath.setText(path);
        n.setImage(path);
        
       if(file!=null){
            
            try {
                System.out.println(""+file.getAbsolutePath());
                image =new Image(file.getAbsoluteFile().toURL().toString());
               image = new Image(path);
                imagenews.setImage(image);
            } catch(Exception ex)  {
                Logger.getLogger(FXMLvoitureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

    }}
    });
    }    


    public void AffichNewsDet(Voiture n){  
        tfMatricule.setText(n.getMatricule());
            tfmodele.setText(n.getModele());

            ImgPath.setText(n.getImage());
         image =new Image(n.getImage());
                imagenews.setImage(image);
                tfmarque.setText(n.getMarque());
                id.setText(String.valueOf(n.getId()));
                 tfprix.setText(String.valueOf(n.getPrix()));
            tfdescription.setText(n.getDescription());
            tfboite.setText(n.getBoite_ma());
                
                tfville.setText(n.getVille());
                
               
        

        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        ServiceVoiture sn= new ServiceVoiture();
        Voiture n = new Voiture();
        
        n.setMatricule(tfMatricule.getText());
        n.setModele(tfmodele.getText());
        n.setMarque(tfmarque.getText());
        n.setPrix((int) Float.parseFloat(tfprix.getText ()));
        n.setDescription(tfdescription.getText());
        n.setBoite_ma(tfboite.getText());
        n.setVille(tfville.getText());
                   n.setId(Integer.parseInt(id.getText()));

        System.out.println(n.getMarque());
           n.setImage(ImgPath.getText());
           
                 
        try{
                
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("voiture modifié avec succès");
            alert.show(); 
             } 
            catch (Exception e)
            {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("");
            alert.setContentText("Something went wrong:"+e.toString());
            e.printStackTrace();
            alert.show();
        }
        sn.modifier(n);
        Node node = (Node) event.getSource(); 
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        
        
        
    }
    
}
