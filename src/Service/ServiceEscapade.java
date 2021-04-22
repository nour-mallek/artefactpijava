/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
//import Services.IServiceEscapade;
import Entites.Escapade;
import Services.IServiceEscapade;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServiceEscapade implements IServiceEscapade{
    static Maconnexion db = new Maconnexion();
    static Connection con = db.getConnection(); 
    @Override
    public void addEscapade(Escapade esc) {
           // String requete = "INSERT INTO `escapade` (`id_esc`, `price`, `histoire`, `title`, `ville`, `image`)" 
            //+ "VALUES ('"+esc.getId_esc()+"','"+esc.getPrice()+"','"+esc.getHistoire()+"','"+esc.getTitle()+"','"+esc.getVille()+"','"+esc.getImage()+"')";
            String requete = "INSERT INTO `escapade` (`price`, `histoire`, `title`, `ville`, `image`)" 
            + "VALUES ('"+esc.getPrice()+"','"+esc.getHistoire()+"','"+esc.getTitle()+"','"+esc.getVille()+"','"+esc.getImage()+"')";
                  try{
            Statement st =con.createStatement();
            st.executeUpdate(requete);
                System.out.println("personne ajouté");
            } catch (SQLException ex){
                System.out.println("erreur d'insertion");
            }
           
    }
    
    @Override
    public ObservableList<Escapade> ListEscapade() {
         ObservableList<Escapade> os = FXCollections.observableArrayList();
        try {
            String query = "select * from Escapade";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               // if(!"archived".equals(rs.getString("status"))) {
                    Escapade esc = new Escapade();
                    esc.setId_esc(rs.getInt("id_esc"));
                    esc.setTitle(rs.getString("title"));
                    esc.setHistoire(rs.getString("histoire"));
                    esc.setPrice(rs.getInt("price"));
                    esc.setVille(rs.getString("ville"));
                    os.addAll(esc);
               // }
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os;
    }

    @Override
    public void remove(Escapade esc) {
         int n=0;
		PreparedStatement st;
		try {
			st= con.prepareStatement("DELETE FROM `escapade` WHERE `id_esc`=?");
			
			st.setInt(1,esc.getId_esc());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
    } 
    
    
  
    
    
    
    @Override
    public void updateEsc(Escapade esc) {
        try {
            String query="UPDATE `escapade` SET `price`=?,`histoire`=?,`title`=?,`ville`=? WHERE id_esc=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(3, esc.getTitle());
            pst.setString(2, esc.getHistoire());
            pst.setInt(1, (int) esc.getPrice());
            pst.setString(4, esc.getVille());
            //pst.setString(5, esc.getImage());
            pst.setInt(5, esc.getId_esc());
            pst.executeUpdate();
            System.out.println("escapade updated succesfully");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEscapade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
      public void fillcombobox() {
        final ObservableList options = FXCollections.observableArrayList();
        try {
            String query = "select title from Escapade";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              options.add(rs.getString("title"));
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
    }
    
 }

    
   
    

