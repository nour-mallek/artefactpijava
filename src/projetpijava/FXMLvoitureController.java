/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Voiture;
import Service.ServiceLocationv;
import Service.ServiceVoiture;
import Utils.Maconnexion;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.runtime.Debug.id;


/**
 * FXML Controller class
 *
 * @author Nour
 */
public class FXMLvoitureController implements Initializable {

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
    private Button btnajouter;
    private TableView<Voiture> tvoiture;
    private TableColumn<Voiture, String> cmatricule;
    private TableColumn<Voiture, String> cmodele;
    private TableColumn<Voiture, String> cmarque;
    private TableColumn<Voiture, Float> cprix;
    private TableColumn<Voiture, String> cdescription;
    private TableColumn<Voiture, String> cboite;
    private TableColumn<Voiture, String> cville;
    @FXML
    private Button btnaffiche;
    private Button btnmodif;
    private Button btnsupp;
    @FXML
    private Button btnimage;
    @FXML
    private ListView<Voiture> listView;
    @FXML
    private VBox vBox;
    private Image image;
    private File file;
    private FileChooser fileChooser;
    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private TextField ImgPath;
    @FXML
    private ImageView imagenews;
    @FXML
    private Button voirstat;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AffichList();
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
//        showvoiture();
        

        
        
       
    }    

    @FXML
    private void AjouterVoiture(ActionEvent event) {
        
        ServiceVoiture sv = new ServiceVoiture();
        Voiture v = new Voiture () ;
        
                v.setMatricule(tfMatricule.getText ());
                v.setModele(tfmodele.getText ());
                v.setMarque(tfmarque.getText ());
                v.setPrix(Float.parseFloat (tfprix.getText ()) );
                v.setDescription(tfdescription.getText ());
                v.setBoite_ma(tfboite.getText ());
                v.setVille(tfville.getText ());
                v.setImage(ImgPath.getText ());
                
             try{
                
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setHeaderText("");
            alert.setContentText("Voiture ajouté");
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
                sv.AddVoiture(v);  
                AffichList  ();
                
                 
                         
    }
//    @FXML
//    private void showvoiture() {
//    ServiceVoiture sv = new ServiceVoiture();
//        tvoiture.setItems(sv.AfficherVoiture());
//        //cid.setCellValueFactory(new PropertyValueFactory("id"));
//        cmatricule.setCellValueFactory(new PropertyValueFactory("matricule"));
//        cmodele.setCellValueFactory(new PropertyValueFactory("modele"));
//        cmarque.setCellValueFactory(new PropertyValueFactory("marque"));
//        cprix.setCellValueFactory(new PropertyValueFactory("prix"));
//        cdescription.setCellValueFactory(new PropertyValueFactory("description"));
//        cboite.setCellValueFactory(new PropertyValueFactory("boite_ma"));
//        cville.setCellValueFactory(new PropertyValueFactory("ville"));
//    
//}

    private void modifiervoiture(ActionEvent event) {
        Voiture v = tvoiture.getSelectionModel().getSelectedItem();
        ServiceVoiture vs = new ServiceVoiture();
        
        v.setMatricule(tfMatricule.getText());
        v.setModele(tfmodele.getText());
        v.setMarque(tfmarque.getText());
        v.setPrix(Integer.parseInt(tfprix.getText()));
        v.setDescription(tfdescription.getText());
        v.setBoite_ma(tfboite.getText());
        v.setVille(tfville.getText());
  
        System.out.println(v.toString());
        
        
        vs.modifier(v);
//        showvoiture();
    }

    private void supprimervoiture(ActionEvent event) {
        Voiture v = tvoiture.getSelectionModel().getSelectedItem();
        ServiceVoiture sv = new ServiceVoiture();
        sv.supprimer(v);
//        showvoiture();
    }

    private void deselect(KeyEvent event) {
        if (event.getCode()==KeyCode.ESCAPE) {
            tvoiture.getSelectionModel().clearSelection();
            btnmodif.setDisable(true);
            btnsupp.setDisable(true);
            tfMatricule.clear();
            tfmodele.clear();
            tfmarque.clear();
            tfprix.clear();
            tfdescription.clear();
            tfboite.clear();
            tfville.clear();
            
    } }
    private void select(MouseEvent event) {
        Voiture d = listView.getSelectionModel().getSelectedItem();

            tfMatricule.setText(d.getMatricule());
            tfmodele.setText(d.getModele());           
            tfmarque.setText(d.getModele());  
            tfprix.setText(String.valueOf(d.getPrix()));
            tfdescription.setText(d.getDescription());
            tfboite.setText(d.getBoite_ma());
            tfville.setText(d.getVille());
            btnsupp.setDisable(false);
            btnmodif.setDisable(false);
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
                       
                    HBox Hbx = new HBox(50);
                        Hbx.setAlignment(Pos.CENTER_LEFT);
                        Hbx.setPadding(new Insets(2, 2, 2, 2));

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
                      NewsImage.setFitWidth(150);
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
                        Hbx.setPadding(new Insets(5, 10, 5, 10));
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
                        Button Détails = new Button("Modifier");
                        Détails.setStyle("-fx-background-color:  #ffc100; ");
                        
                        Détails.setOnAction(event -> {
                           
                            FXMLLoader loader = new FXMLLoader ();
                            
                            loader.setLocation(getClass().getResource("backmodifiervoiture.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(FXMLvoitureController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        BackmodifiervoitureController cc = loader.getController();
                        cc.AffichNewsDet(n);    
                        System.out.println(n.getId());

            
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
                           
                        });
                        

                        Button Supp = new Button("Supprimer");
                        Supp.setStyle("-fx-background-color:  #ffc100; ");

                        Supp.setOnAction(event -> {  
                                                     
                        sn.supprimer(n);
                                                     
                        AffichList();
                        
                        } );

                        Hbx.getChildren().addAll(Détails,Supp);
                            
                        setText(null);
                        setGraphic(Hbx);
                    }

                }
            };

        });
        
        

        listView.setItems(NewsList);

        vBox.getChildren().add(listView);
        

       
    }

    @FXML
    private void showupdate(ActionEvent event) {
        AffichList();
    }

//    @FXML
//    private void stat(ActionEvent event) {
//        
//          try {
//            Voiture a=new Voiture();
//            String requete="SELECT marque,prix from Voiture ORDER BY marque ASC ";
//            XYChart.Series<String,Double> series=new XYChart.Series<>();
//            
//            //exécuter la requete et la stocker dans un resulttest
//            PreparedStatement pst = Maconnexion.getInstance().getConnection().prepareStatement(requete);
//            ResultSet rs =  pst.executeQuery();
//            while(rs.next()){
//                series.getData().add(new XYChart.Data<>(rs.getString("marque"),rs.getDouble("prix")));
//                VoitureChart.getData().add(series);
//                
//                
//            }
//        } catch (SQLException ex) {
//System.out.println(ex.getMessage());        }
//
//    }

    @FXML
    private void voirstat(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader ();
                            
                            loader.setLocation(getClass().getResource("Statistique.fxml"));
                        try {
                            loader.load();
                           
                              } catch (IOException ex) {
                            Logger.getLogger(FXMLvoitureController.class.getName()).log(Level.SEVERE, null, ex);
    }
                        Parent parent = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();
}}
    
    


