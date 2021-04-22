/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entites.Escapade;
import Entites.Reservationesc;
import Entites.Reservationesc;
import static Service.ServiceEscapade.con;
import static Service.ServiceReservationesc.con;
import Services.ISservicereservationesc;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projetpijava.ReservationescController;

/**
 *
 * @author ASUS
 */
public class ServiceReservationesc implements ISservicereservationesc {
    static Maconnexion db = new Maconnexion();
    static Connection con = db.getConnection(); 

    @Override
    public void addResrvation(Reservationesc res) {
      try {
         Statement stm = con.createStatement();
          System.out.println(ReservationescController.global);
          
         String requete = "INSERT INTO `reservationesc` (`escapade_id`,`nbrepers`, `dateres`, `commentaire`, `tel`)" 
            + "VALUES ('"+ReservationescController.global+"','"+res.getNbrepers()+"','"+res.getDateres()+"','"+res.getCommentaire()+"','"+res.getTel()+"')";
         System.out.println(requete);
         stm.executeUpdate(requete);
         } catch (SQLException ex) {
            Logger.getLogger(ServiceVoiture.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    }

    @Override
    public ObservableList<Reservationesc> ListResrvation() {
        ObservableList<Reservationesc> os = FXCollections.observableArrayList();
    /*    try {
            String query = "select * from reservationesc";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Reservationesc res = new Reservationesc();
                    res.setRef(rs.getInt("ref"));
                    res.setEscapade_id(rs.getInt("escapade_id"));
                    res.setNbrepers(rs.getInt("nbrepers"));
                    res.setTel(rs.getInt("tel"));
                    res.setCommentaire(rs.getString("Commentaire"));
                    
                  
                    
                    os.addAll(res);
               // }
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os; */
    
    
   
        try {
            String query="select * from reservationesc";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rst =stm.executeQuery(query);
            
            while (rst.next())
            {
                Reservationesc Lv =new Reservationesc();
                Lv.setRef(rst.getInt("ref"));
                Lv.setEscapade_id(rst.getInt("escapade_id"));
                Lv.setNbrepers(rst.getInt("nbrepers"));
                //Lv.setEscapade_id(rst.getString("nom"));
                Lv.setCommentaire(rst.getString("commentaire"));
                Lv.setTel(rst.getInt("tel"));
                Lv.setDateres(rst.getDate("dateres"));
                
               
               os.add(Lv);
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return os;
    
    
    }

    @Override
    public void remove(Reservationesc res) {
       int n=0;
		PreparedStatement st;
		try {
			st= con.prepareStatement("DELETE FROM `reservationesc` WHERE `ref`=?");
			
			st.setInt(1,res.getRef());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
    }

    @Override
    public void updateRes(Reservationesc res) {
      try { 
            String query="UPDATE `reservationesc` SET `nbrepers`=?,`dateres`=?,`commentaire`=?,`tel`=? WHERE ref=?";
            PreparedStatement pst = con.prepareStatement(query);
        
            
            pst.setInt(1, (int) res.getNbrepers());
            pst.setDate(2,res.getDateres());
            pst.setString(3, res.getCommentaire());            
            pst.setInt(4, res.getTel());
            pst.setInt(5, res.getRef());
           
            
            pst.executeUpdate();
            System.out.println("reservation updated succesfully");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEscapade.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
//***********************fonction pour essayer d'extrarire les donneée au combobox**********
    
    public String fillcombobox(int ref) {
        String query = "select title from Escapade where id_esc="+ref;
        String title="";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
              title = rs.getString("title");
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
     return title;   
    }
//***********************fonction pour essayer d'extrarire les donneée au combobox**********
    @Override
    public ObservableList<Escapade> titreescapade() {
       ObservableList<Escapade> os = FXCollections.observableArrayList();
        try {
            String query = "select * from Escapade";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              
                    Escapade esc = new Escapade();
                    
                    esc.setTitle(rs.getString("title"));
                   
                    os.add(esc);
               
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os;
    }
//******************pour afficher le tableau des escapades durant la reservation****************
    @Override
    public ObservableList<Escapade> List() {
        ObservableList<Escapade> os = FXCollections.observableArrayList();
        try {
            String query = "select * from Escapade";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Escapade esc = new Escapade();
                    //esc.setId_esc(rs.getInt("id_esc"));
                    esc.setTitle(rs.getString("title"));
                    esc.setHistoire(rs.getString("histoire"));
                    esc.setPrice(rs.getInt("price"));
                    esc.setVille(rs.getString("ville"));
                    os.addAll(esc);
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os;
    }
    
      
    public int select_id_bynom(String nom) {
        String query = "select id_esc from escapade where title= '"+nom+"'";
        //SELECT * FROM `reservationesc` INNER JOIN escapade on escapade.id_esc= reservationesc.escapade_id 
        //String query = "SELECT title FROM `reservationesc` INNER JOIN escapade on escapade.id_esc= reservationesc.escapade_id";
        String n="";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
              n = rs.getString("id_esc");
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
       
     return Integer.parseInt(n);   
    }
    
   public static String getRandomStr(int n) 
    {
        //choisissez un caractére au hasard à partir de cette chaîne
        String str = " "+"abcdefghijklmnopqrstuvxyz"
                    +"ABCDEFGHIJKLMNOPQRSTUVXYZ"; 
  
        StringBuilder s = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index = (int)(str.length() * Math.random()); 
            s.append(str.charAt(index)); 
        } 
        return s.toString(); 
    } 
  
     public Date selectDate()
             
     {
         Date datee=null;
         try {       
         Statement ste = Maconnexion.getInstance().getCnx().createStatement();
         String req = "select sysdate()";
         ResultSet rs =ste.executeQuery(req);
           while (rs.next()) {
              Date datenow = rs.getDate(1);
              
             datee=datenow;
               
           }
            } catch (SQLException ex) {
             System.out.println(ex.toString());
        }
       
       return  datee;
       
       }
    
}
