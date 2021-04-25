/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entites.Voiture;
import Services.IServiceVoiture;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Nour
 */
public class ServiceVoiture implements IServiceVoiture{
    
    Connection cnx;

    public ServiceVoiture() {
        cnx= Maconnexion.getInstance().getConnection();
    }
    
    
    @Override
    public void AddVoiture(Voiture v)  {
           try {
         Statement stm = cnx.createStatement();
         String query="INSERT INTO `voiture`(`matricule`, `modele`, `marque`, `prix`, `description`, `boite_ma`, `ville`, `image`,`disponible`) VALUES ('"+v.getMatricule()+"','"+v.getModele()+"','"+v.getMarque()+"','"+v.getPrix()+"','"+v.getDescription()+"','"+v.getBoite_ma()+"','"+v.getVille()+"','"+v.getImage()+"',"+v.isDisponible()+")";
         System.out.println(query);
         stm.executeUpdate(query);
         } catch (SQLException ex) {
            Logger.getLogger(ServiceVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     @Override
    public ObservableList<Voiture> AfficherVoiture() {
        
        ObservableList<Voiture> voitures= FXCollections.observableArrayList();

        try {
            String query="select * from `voiture`";
            PreparedStatement stm = cnx.prepareStatement(query);
            ResultSet rst =stm.executeQuery(query);
            
            while (rst.next())
            {
                Voiture V =new Voiture ();
                V.setId(rst.getInt("id"));
                V.setMatricule(rst.getString("matricule"));
                V.setModele(rst.getString("modele"));
                V.setMarque(rst.getString("marque"));
                V.setPrix(rst.getFloat("prix"));
                V.setDescription(rst.getString("description"));
                V.setBoite_ma(rst.getString("boite_ma"));
                V.setVille(rst.getString("ville"));
          V.setImage(rst.getString("image"));


               
                voitures.add(V);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return voitures;
}
    
    @Override
    public void modifier(Voiture v) {
        try {
            String query="UPDATE `voiture` SET `matricule`=?,`modele`=?,`marque`=?,`prix`=?,`description`=?,`boite_ma`=?,`ville`=?,`image`=? WHERE id=?";
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1, v.getMatricule());
            stm.setString(2, v.getModele());
            stm.setString(3, v.getMarque());
            stm.setInt(4, (int) v.getPrix());
            stm.setString(5, v.getDescription());
            stm.setString(6, v.getBoite_ma());
            stm.setString(7, v.getVille());
            stm.setString(8, v.getImage());           
            stm.setInt(9, v.getId());
            stm.executeUpdate();
            System.out.println(query);
            System.out.println("voiture modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void supprimer(Voiture v) {
         int n=0;
		PreparedStatement st;
		try {
			st= cnx.prepareStatement("DELETE FROM `voiture` WHERE `id`=?");
			
			st.setInt(1,v.getId());
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
    }
    
    
    
//    public float remise (String boite_ma){
//       
//         boite_ma="";
//         int id=0;
//        try {
//            
//            String requete=" select boite_ma from voiture where id=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1,id);
//            ResultSet rs =  pst.executeQuery();
//            if( boite_ma=="manuelle"){
//                return 0.2F;
//            } 
//            return 0;
//            
//        } catch (SQLException ex) {
//                Logger.getLogger(ServiceVoiture.class.getName()).log(Level.SEVERE, null, ex);
//                return 0;}
//       
//   }
//   
//    
}
