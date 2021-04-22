/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entites.Reclamation;
import Entites.user;

import Services.IServiceReclamation;
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
/**
 *
 * @author Rayen Ben Driaa
 */
public class ServiceReclamation implements IServiceReclamation {
     static Maconnexion db = new Maconnexion();
    static Connection con = db.getConnection();
   public int select_id_bynom(String nom) {
       System.out.println(nom);
        String query = "select id_user from user where Username= '"+nom+"'";
        //SELECT * FROM `reservationesc` INNER JOIN escapade on escapade.id_esc= reservationesc.escapade_id 
        //String query = "SELECT title FROM `reservationesc` INNER JOIN escapade on escapade.id_esc= reservationesc.escapade_id";
        String n="";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
              n = rs.getString("id_user");
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
     return Integer.parseInt(n);   
    }
    @Override
    public void AddReclamation(Reclamation rec) {
        
        String requete = "INSERT INTO `reclamation` ( `text_reclamation`, `iduser`)" 
                    + "VALUES ('"+rec.getText_reclamation()+"','"+rec.getIduser()+"')";
                  try{
            Statement st =con.createStatement();
            st.executeUpdate(requete);
                System.out.println("reclamation ajouté");
            } catch (SQLException ex){
                System.out.println("erreur d'insertion");
            }
           
        
    }

    @Override
    public ObservableList<Reclamation> ListReclamation() {
        
      ObservableList<Reclamation> os = FXCollections.observableArrayList();
        try {
            String query = "select * from reclamation";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                    Reclamation    user = new Reclamation();
                   
                user.setText_reclamation(rs.getString("text_reclamation"));
                user.setIduser(rs.getInt("iduser"));
                
                    os.addAll(user);
              
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os;
    }
      public String fillcombobox(int ref) {
        String query = "select Username from User where id_user="+ref;
        String title="";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
              title = rs.getString("Username");
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
     return title;   
    }

          public ObservableList<user> titreescapade() {
       ObservableList<user> os = FXCollections.observableArrayList();
        try {
            String query = "select * from user";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
                   user u = new user();
                    
                    u.setUsername("Username");
                   
                    os.add(u);
               
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os;
    }
    @Override
    public void remove(Reclamation Reclamation) {
        int n=0;
		PreparedStatement st;
		try {
			st= con.prepareStatement("DELETE FROM `Reclamation` WHERE `text_reclamation`=?");
			
			st.setString(1, Reclamation.getText_reclamation());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
        
        
    }

    @Override
    public void updateReclamation(Reclamation rec) {
          try {
            String query="UPDATE `reclamation` SET `text_reclamation`=? WHERE id_reclamation=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(0, rec.getText_reclamation());
           pst.setInt(1, rec.getIduser());
           pst.setInt(2, rec.getId_reclamation());
            
            //pst.setString(5, esc.getImage());
            pst.executeUpdate();
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
}
