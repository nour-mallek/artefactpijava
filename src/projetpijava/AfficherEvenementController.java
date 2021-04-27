/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Utils.Maconnexion;
import Entite.Evenement;
import Service.ServiceEvenement;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private TableView<Evenement> tabevent;
    @FXML
    private TableColumn<Evenement, Integer> idtab;
    @FXML
    private TableColumn<Evenement, String> nomtab;
    @FXML
    private TableColumn<Evenement, java.sql.Date> datedebtab;
    @FXML
    private TableColumn<Evenement, java.sql.Date> datefintab;
    @FXML
    private TableColumn<Evenement, String> villetab;
    @FXML
    private TableColumn<Evenement, String> desctab;
    @FXML
    private TableColumn<Evenement, Float> ratingtab;
    @FXML
    private TableColumn<Evenement, ImageView> imagetab;
    @FXML
    private Button supp;
    @FXML
    private TextField recherche;

    private Statement ste;
    private Connection con;
    ObservableList<Evenement> cls = FXCollections.observableArrayList();
    ServiceEvenement se = new ServiceEvenement();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        RechercheAV();

    }    

   public void Aff(){
                        try {
            con = Maconnexion.getInstance().getConnection();
            ste = con.createStatement();
            cls.clear();

            ResultSet res = ste.executeQuery("select * from evenement");
            while(res.next()){
                Evenement f= new Evenement(res.getInt(1),res.getString(2),res.getDate(3),res.getDate(4),res.getString(5),res.getString(6),res.getString(7),res.getFloat(8));

                File file = new File("E:\\xampp\\htdocs\\images\\"+res.getString(7));
                Image image = new Image(file.toURI().toString());
                
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                f.setImg(imageView);                
                cls.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            idtab.setCellValueFactory(new PropertyValueFactory<>("id_event"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
            datedebtab.setCellValueFactory(new PropertyValueFactory<>("start_at"));
            datefintab.setCellValueFactory(new PropertyValueFactory<>("end_at"));
            villetab.setCellValueFactory(new PropertyValueFactory<>("ville"));
            desctab.setCellValueFactory(new PropertyValueFactory<>("description"));
            ratingtab.setCellValueFactory(new PropertyValueFactory<>("rating"));
            imagetab.setCellValueFactory(new PropertyValueFactory<>("img"));
            
            tabevent.setItems(cls);
            tabevent.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            desctab.setCellFactory(TextFieldTableCell.forTableColumn());
            villetab.setCellFactory(TextFieldTableCell.forTableColumn());

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Evenement> filteredData = new FilteredList<>(cls, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(evenement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (evenement.getNom_event().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(evenement.getId_event()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Evenement> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabevent.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabevent.setItems(sortedData);
    }
      
    @FXML
    private void Change_Nom(TableColumn.CellEditEvent event) throws SQLException {
     Evenement tab_Eventelected = tabevent.getSelectionModel().getSelectedItem();
     tab_Eventelected.setNom_event(event.getNewValue().toString());
     se.update(tab_Eventelected,tab_Eventelected.getId_event());
    }

    @FXML
    private void Change_Ville(TableColumn.CellEditEvent event) throws SQLException {
     Evenement tab_Eventelected = tabevent.getSelectionModel().getSelectedItem();
     tab_Eventelected.setVille(event.getNewValue().toString());
     se.update(tab_Eventelected,tab_Eventelected.getId_event());
    }

    @FXML
    private void Change_Description(TableColumn.CellEditEvent event) throws SQLException {
     Evenement tab_Eventelected = tabevent.getSelectionModel().getSelectedItem();
     tab_Eventelected.setDescription(event.getNewValue().toString());
     se.update(tab_Eventelected,tab_Eventelected.getId_event());
    }

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                     
        tabevent.setItems(cls);

             ObservableList<Evenement> allevents,Singleevents ;
             allevents=tabevent.getItems();
             Singleevents=tabevent.getSelectionModel().getSelectedItems();
             Evenement A = Singleevents.get(0);
             se.delete(A.getId_event());
             Singleevents.forEach(allevents::remove);
             Aff();
             RechercheAV();
    }
    
}
