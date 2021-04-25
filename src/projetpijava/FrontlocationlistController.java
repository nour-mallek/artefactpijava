/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Voiture;
import Service.ServiceVoiture;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class FrontlocationlistController implements Initializable {

    @FXML
    private ListView<Voiture> listView;
    @FXML
    private VBox vBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AffichList();
        
    }    
    public void AffichList() {
        
        ServiceVoiture sn = new ServiceVoiture();
        
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);

        ObservableList<Voiture> NewsList = sn.AfficherVoiture();
        listView.setStyle("  -fx-selection-bar: #ffc100 ; -fx-border-color:#ffc100");
        listView.setCellFactory((Callback<ListView<Voiture>, ListCell<Voiture>>) param -> {
            return new ListCell<Voiture>() {
                @Override
                protected void updateItem(Voiture n, boolean empty) {
                    super.updateItem(n, empty);

                    if (n == null || empty) {
                        setText(null);
                    } else {
                       
                    HBox Hbx = new HBox(60);
                        Hbx.setAlignment(Pos.CENTER_LEFT);
                        Hbx.setPadding(new Insets(5, 10, 5, 10));

                        //imageNews
                       String path =n.getImage();
                       System.out.println(path);
                                        BufferedImage BfImg = null;
                                        
                         try {

                                URL url= new URL(path);
                                BfImg= ImageIO.read(url);
                                
                         } catch (Exception ex) {
                            System.out.println("Failed to load image");
                            System.out.println(ex);
                         }
                         WritableImage wr = null;
                         if (BfImg != null) {
                             wr = new WritableImage(BfImg.getWidth(), BfImg.getHeight());
                             PixelWriter pw = wr.getPixelWriter();
                             for (int x = 0; x < BfImg.getWidth(); x++) {
                                 for (int y = 0; y < BfImg.getHeight(); y++) {
                                     pw.setArgb(x, y, BfImg.getRGB(x, y));
                                 }
                             }
                         }
                         ImageView NewsImage = new ImageView();
                      NewsImage.setFitHeight(100);
                      NewsImage.setFitWidth(200);
                         NewsImage.setImage(wr);
                                             Hbx.getChildren().addAll(NewsImage);

                         Label ts= new Label(n.getMatricule());
                         ts.setMinWidth(20);
                        ts.setMinHeight(20);
                        Hbx.getChildren().add(ts);
                        ts.setCursor(Cursor.HAND);
                        //imageNews

                       Label NewsLabel = new Label(n.getModele());
                         NewsLabel.setMinWidth(20);
                        NewsLabel.setMinHeight(20);
                        Hbx.getChildren().add(NewsLabel);
                        NewsLabel.setCursor(Cursor.HAND);

                          Label Contenu= new Label(n.getMarque());
                         Contenu.setMinWidth(20);
                        Contenu.setMinHeight(20);
                        Hbx.getChildren().add(Contenu);
                        Contenu.setCursor(Cursor.HAND);
                         Hbx.setAlignment(Pos.CENTER_LEFT);
                        Hbx.setPadding(new Insets(2, 2, 2, 2));
                        Label reacts= new Label(String.valueOf(n.getPrix()));
                         reacts.setMinWidth(20);
                        reacts.setMinHeight(20);
                        Hbx.getChildren().add(reacts);
                        reacts.setCursor(Cursor.HAND);
                        
                        Label description= new Label(n.getDescription());
                         description.setMinWidth(20);
                        description.setMinHeight(20);
                        Hbx.getChildren().add(description);
                        description.setCursor(Cursor.HAND);
                        
                         Label boitema= new Label(n.getBoite_ma());
                        boitema.setMinWidth(20);
                        boitema.setMinHeight(20);
                        Hbx.getChildren().add(boitema);
                        boitema.setCursor(Cursor.HAND);
                        
                        Label ville= new Label(n.getVille());
                        ville.setMinWidth(20);
                        ville.setMinHeight(20);
                        Hbx.getChildren().add(ville);
                        ville.setCursor(Cursor.HAND);
                        
                        
//                        Contenu.setStyle("-fx-text-fill: #90ee90 ;-fx-font-weight: bold;");
//
                     
                      Region region = new Region();
                        HBox.setHgrow(region, Priority.ALWAYS);
                        Hbx.getChildren().add(region);
                        Button Détails = new Button("Louer");
                        Détails.setStyle("-fx-background-color:  #ffc100; ");
                        
                        Détails.setOnAction(event -> {
                           
                            FXMLLoader loader = new FXMLLoader ();
                            
                            loader.setLocation(getClass().getResource("Frontlocation.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(FXMLvoitureController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                     
                        System.out.println(n.getId());

            
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                           
                        });
       

                            Hbx.getChildren().addAll(Détails);

                        setText(null);
                        setGraphic(Hbx);
                    }

                }
            };

        });

        listView.setItems(NewsList);

        vBox.getChildren().add(listView);
        

       
    }
    
}
