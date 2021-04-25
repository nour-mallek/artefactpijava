/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Locationv;
import Services.IServiceLocationv;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;
import projetpijava.FXMLlocationvController;

/**
 *
 * @author Nour
 */
public class ServiceLocationv implements IServiceLocationv{

    Connection cnx;

    public ServiceLocationv() {
        cnx= Maconnexion.getInstance().getConnection();
    }
    @Override
    public void AddLocationv(Locationv lv) {
        try {
         Statement stm = cnx.createStatement();
         String query = "INSERT INTO `locationv` (`voiture_id`,`nom`, `prenom`, `numerodetelephone`, `startat`, `endat`, `heuredebut`, `heurefin`, `permis` )" 
            + "VALUES ('"+FXMLlocationvController.global+"','"+lv.getNom()+"','"+lv.getPrenom()+"','"+lv.getNumerodetelephone()+"','"+lv.getStartat()+"','"+lv.getEndat()+"','"+lv.getHeuredebut()+"','"+lv.getHeurefin()+"','"+lv.getPermis()+"')";
         System.out.println(query);
         stm.executeUpdate(query);
         } catch (SQLException ex) {
            Logger.getLogger(ServiceVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Locationv> AfficherLocationv() {
       
        ObservableList<Locationv> locationvs= FXCollections.observableArrayList();

        try {
            String query="select * from `locationv`";
            PreparedStatement stm = cnx.prepareStatement(query);
            ResultSet rst =stm.executeQuery(query);
            
            while (rst.next())
            {
                Locationv Lv =new Locationv ();
                Lv.setId(rst.getInt("id"));
                Lv.setNom(rst.getString("nom"));
                Lv.setPrenom(rst.getString("prenom"));
                Lv.setNumerodetelephone(rst.getInt("numerodetelephone"));
                Lv.setStartat(rst.getDate("startat"));
                Lv.setEndat(rst.getDate("endat"));
                Lv.setHeuredebut(rst.getString("heuredebut"));
                Lv.setHeurefin(rst.getString("heurefin"));
                Lv.setVoiture_id(rst.getInt("voiture_id"));
               
                locationvs.add(Lv);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return locationvs;
    }

    @Override
    public void supprimerlocation(Locationv lv) {
                int n=0;
		PreparedStatement stm;
		try {
			stm= cnx.prepareStatement("DELETE FROM `locationv` WHERE `id`=?");
			
			stm.setInt(1,lv.getId());
			n= stm.executeUpdate();
			stm.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}    }

    @Override
    public void modifierlocation(Locationv lv) {
        try {
            String query="UPDATE `locationv` SET `nom`=?,`prenom`=?,`numerodetelephone`=?,`startat`=?,`endat`=?,`heuredebut`=?,`heurefin`=? WHERE id=?";
            PreparedStatement stm = cnx.prepareStatement(query);
            stm.setString(1, lv.getNom());
            stm.setString(2, lv.getPrenom());
            stm.setInt(3, lv.getNumerodetelephone());
            stm.setDate(4,lv.getStartat());
            stm.setDate(5, lv.getEndat());
            stm.setString(6, lv.getHeuredebut());
            stm.setString(7, lv.getHeurefin());
            stm.setInt(8, lv.getId());
            stm.executeUpdate();
            System.out.println("location modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String fillcomboboxvoiture(int id) {
        String query = "select modele from Voiture where id="+id;
        String modele="";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
              modele = rs.getString("modele");
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
     return modele;   
    }
    public int select_id_bynomvoiture(String nom) {
        String query = "select id from Voiture where modele LIKE '"+nom+"'";
        String n="";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
              n = rs.getString("id");
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
     return Integer.parseInt(n);   
    }
    

   
    
}
