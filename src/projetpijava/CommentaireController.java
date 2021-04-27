/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Commentaire;
import Entites.Voiture;
import Service.ServiceCommentaire;
import Service.ServiceVoiture;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class CommentaireController implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private TextField ContenuCommentaire;
    @FXML
    private ListView<Commentaire> listView;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

         

//news.setMouseTransparent(true);
//news.setFocusTraversable(false);
       ServiceCommentaire sc = new ServiceCommentaire();
       Commentaire c = new Commentaire();
           ShowCommentaires();            
 
    }   
 
    @FXML
    private void ValiderCommentaire(ActionEvent event) {
        validate();
        ServiceCommentaire sc = new ServiceCommentaire();
        Commentaire c = new Commentaire();
        Voiture ne = new Voiture();

         java.util.Date d1 = new java.util.Date();
            Date dateToday = new java.sql.Date(d1.getTime());
         c.setDate_dajout(dateToday);
     
         
          // filtrage 
        String str = ContenuCommentaire.getText();


         str = CensoredComment(str);
        
        
        c.setContenu(str);
        sc.AjouterCommentaire(c, getVoitureId());
        ShowCommentaires();
        }

        
         
    
   

public String CensoredComment(String comment) {
try {
	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\badwords.txt"));
	String s ; 
	List<String> words = new ArrayList<String>() ;
	while (( s = br.readLine()) != null )  {
	words.add(s) ;
	}
	
	for (String word : words ) {
		Pattern rx = Pattern.compile("\\b" + word + "\\b", Pattern.CASE_INSENSITIVE);
		comment = rx.matcher(comment ).replaceAll(new String(new char[word.length()]).replace('\0', '*'));
	}
	return comment ;

}
catch (Exception ex) {

 System.out.println("failed to read txt") ; 
 System.out.println(ex);
}
return comment ; 
}
//    public String CensoredComment(String comment) {
//
////	BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\badwords.txt"));
//	String s="AAA"; 
//	
//	
//	
//	
//		Pattern rx = Pattern.compile("\\b" + s + "\\b", Pattern.CASE_INSENSITIVE);
//		comment = rx.matcher(comment ).replaceAll(new String(new char[s.length()]).replace('\0', '*'));
//	
//
//
//
//return comment ; 
//}
    

       

    @FXML
    private void AnnulerCommentaire(ActionEvent event) {
       Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

   
    private void AfficherCommentaires(ActionEvent event) throws IOException {
                ServiceCommentaire sc = new ServiceCommentaire();
                ShowCommentaires();
                
    }
                 
        

    private void SupprimerCommentaire(ActionEvent event) {
        ServiceCommentaire sc = new ServiceCommentaire();
            Commentaire c = new Commentaire();
       int IDValue = Integer.parseInt(id.getText());
        c.setId(IDValue);
        sc.SupprimerCommentaire(c);
        ShowCommentaires();
        
    }
     private void ModifierCommentaire(ActionEvent event) {

    ServiceCommentaire sc = new ServiceCommentaire();
        Commentaire c = new Commentaire();
        c.setContenu(ContenuCommentaire.getText());
         java.util.Date d1 = new java.util.Date();
            Date dateToday = new java.sql.Date(d1.getTime());
         c.setDate_dajout(dateToday);
         int IDValue = Integer.parseInt(id.getText());
        c.setId(IDValue);
        sc.ModifierCommentaire(c);
        ShowCommentaires();
   
        
    }
     public void ShowCommentaires(){
        ServiceCommentaire sc = new ServiceCommentaire();
      
        vBox.setPadding(new Insets(10));
        vBox.setAlignment(Pos.CENTER);

        ObservableList<Commentaire> NewsList = sc.AfficherCommentaire(getVoitureId());
          listView.setStyle("  -fx-selection-bar: #ffc100 ; -fx-border-color:#ffc100");

        listView.setCellFactory((Callback<ListView<Commentaire>, ListCell<Commentaire>>) param -> {
            return new ListCell<Commentaire>() {
                @Override
                protected void updateItem(Commentaire c, boolean empty) {
                    super.updateItem(c, empty);

                    if (c == null || empty) {
                        setText(null);
                    } else {
                       
                        HBox Hbx = new HBox(100);
                        Hbx.setAlignment(Pos.TOP_CENTER);
                        Hbx.setPadding(new Insets(5, 10, 5, 10));
 Label Contenu= new Label(String.valueOf(c.getContenu())+"\n"+"Ajouté le : "+c.getDate_dajout());
                         Contenu.setMinWidth(100);
                        Contenu.setMinHeight(100);
                        Contenu.setCursor(Cursor.HAND);
                           Label Supp=new Label("Supprimer");
                                                    Supp.setStyle("-fx-background-color:  #ffc100; ");
                        Supp.setCursor(Cursor.HAND);

                                                   Supp.setOnMouseClicked(event -> {
                
            int IDValue = Integer.parseInt(id.getText());
            c.setId(IDValue);
            sc.SupprimerCommentaire(c);
            ShowCommentaires();
   
 }); 
                                                    Label Modifier=new Label("Modifier");
                                                       Modifier.setStyle("-fx-background-color:  #ffc100; ");

                        Modifier.setCursor(Cursor.HAND);

                                                   Modifier.setOnMouseClicked(e -> {
                                                      
                                                    
        c.setContenu(ContenuCommentaire.getText());
        java.util.Date d1 = new java.util.Date();
      
        int IDValue = Integer.parseInt(id.getText());
        c.setId(IDValue);
        sc.ModifierCommentaire(c);
      
                
      
                
        });
      
Hbx.getChildren().addAll(Contenu,Supp,Modifier);
                        setText(null);
                        setGraphic(Hbx);
                    }

                }
            };

        });

        listView.setItems(NewsList);

        vBox.getChildren().add(listView);

       
    }
    
     

     
     public int getVoitureId() {
        VoitureHolder holder = VoitureHolder.getInstance();
        int t = holder.getIdvoiture();
        return t;
    }

    @FXML
    private void AffiageTextArea(MouseEvent event) {
         Commentaire c =listView.getSelectionModel().getSelectedItem();
         ContenuCommentaire.setText(c.getContenu());
          String s = String.valueOf(c.getId());
            id.setText(s); 
      ShowCommentaires();
    }
       public boolean validate(){
         if(ContenuCommentaire.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please enter into the fields");
            alert.showAndWait();
         return false   ;
        }
         return true;
    }

            
        }
    
  
    

    


  
    
    

