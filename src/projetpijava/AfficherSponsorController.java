/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Utils.Maconnexion;
import Entite.Evenement;
import Entite.Sponsor;
import Service.ServiceEvenement;
import Service.ServiceSponsor;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherSponsorController implements Initializable {

    @FXML
    private TableView<Sponsor> tabsponsor;
    @FXML
    private TableColumn<Sponsor, Integer> reftab;
    @FXML
    private TableColumn<Sponsor, String> cintab;
    @FXML
    private TableColumn<Sponsor, String> budgettab;
    @FXML
    private TableColumn<Sponsor, String> messagetab;
    @FXML
    private TableColumn<Sponsor, Integer> idusertab;
    @FXML
    private TableColumn<Sponsor, String> nomeventtab;
    @FXML
    private Button supp;
    @FXML
    private TextField recherche;
    ObservableList<Sponsor> cls = FXCollections.observableArrayList();
    ServiceSponsor sp = new ServiceSponsor();
    private Statement ste;
    private Connection con;
    @FXML
    private BarChart<String, Number> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        RechercheAV();
        try {
            stat();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public  void stat() throws SQLException{
        barChart.getData().clear();
        ServiceEvenement serr = new ServiceEvenement();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des Sponsors");
                List<Evenement> list = serr.readAll();
        for (Evenement aux : list)
        {
        series.getData().add(new XYChart.Data<>(aux.getNom_event(), sp.calculer(aux.getNom_event())));
        }
        barChart.getData().addAll(series);
}    
   public void Aff(){
                        try {
            con = Maconnexion.getConnection();
            ste = con.createStatement();
            cls.clear();

            ResultSet res = ste.executeQuery("select * from sponsor");
            while(res.next()){
                Sponsor f=new Sponsor(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6));
                cls.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            reftab.setCellValueFactory(new PropertyValueFactory<>("ref_sponsor"));
            cintab.setCellValueFactory(new PropertyValueFactory<>("cin"));
            budgettab.setCellValueFactory(new PropertyValueFactory<>("budget"));
            messagetab.setCellValueFactory(new PropertyValueFactory<>("message"));
            idusertab.setCellValueFactory(new PropertyValueFactory<>("id_user"));
            nomeventtab.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
            
            tabsponsor.setItems(cls);
            tabsponsor.setEditable(true);
            budgettab.setCellFactory(TextFieldTableCell.forTableColumn());
            messagetab.setCellFactory(TextFieldTableCell.forTableColumn());

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sponsor> filteredData = new FilteredList<>(cls, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(sponsor -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (sponsor.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(sponsor.getRef_sponsor()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Sponsor> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabsponsor.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabsponsor.setItems(sortedData);
    }


    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                            
             tabsponsor.setItems(cls);

             ObservableList<Sponsor> allSponsors,SingleSponsors ;
             allSponsors=tabsponsor.getItems();
             SingleSponsors=tabsponsor.getSelectionModel().getSelectedItems();
             Sponsor A = SingleSponsors.get(0);
             sp.delete(A.getRef_sponsor());
             SingleSponsors.forEach(allSponsors::remove);
             Aff();
             RechercheAV();
             stat();
    }

    @FXML
    private void Change_Budget(TableColumn.CellEditEvent event) throws SQLException {
     Sponsor tab_Sponsorselected = tabsponsor.getSelectionModel().getSelectedItem();
     tab_Sponsorselected.setBudget(event.getNewValue().toString());
     sp.update(tab_Sponsorselected,tab_Sponsorselected.getRef_sponsor());
    }

    @FXML
    private void Change_Message(TableColumn.CellEditEvent event) throws SQLException {
     Sponsor tab_Sponsorselected = tabsponsor.getSelectionModel().getSelectedItem();
     tab_Sponsorselected.setMessage(event.getNewValue().toString());
     sp.update(tab_Sponsorselected,tab_Sponsorselected.getRef_sponsor());    
    }
    
}
