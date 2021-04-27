/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import Entites.Locationv;
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
public class StatlocationvController implements Initializable {

    @FXML
    private Button affichebtn;
    @FXML
    private BarChart<String, Double> LocationChart;
    @FXML
    private NumberAxis Y;
    @FXML
    private CategoryAxis X;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherstat(ActionEvent event) {
        try {
            Locationv a=new Locationv();
            String requete="SELECT voiture_id,COUNT(*) FROM locationv GROUP BY voiture_id ";
            XYChart.Series<String,Double> series=new XYChart.Series<>();
            
            //exécuter la requete et la stocker dans un resulttest
            PreparedStatement pst = Maconnexion.getInstance().getConnection().prepareStatement(requete);
            ResultSet rs =  pst.executeQuery();
           
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString("voiture_id"),rs.getDouble("COUNT(*)")));

                             LocationChart.getData().add(series);

            }
                       


        } catch (SQLException ex) {
System.out.println(ex.getMessage());        }

    }
    
}
