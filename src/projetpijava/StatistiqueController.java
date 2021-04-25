/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Voiture;
import Utils.Maconnexion;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<String, Double> VoitureChart;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;
    @FXML
    private Button statid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void stat(ActionEvent event) {
        try {
            Voiture a=new Voiture();
            String requete="SELECT marque,prix from Voiture ORDER BY marque ASC ";
            XYChart.Series<String,Double> series=new XYChart.Series<>();
            
            //exécuter la requete et la stocker dans un resulttest
            PreparedStatement pst = Maconnexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs =  pst.executeQuery();
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString("marque"),rs.getDouble("prix")));
                VoitureChart.getData().add(series);
                
                
            }
        } catch (SQLException ex) {
System.out.println(ex.getMessage());        }

    }
    }
    

