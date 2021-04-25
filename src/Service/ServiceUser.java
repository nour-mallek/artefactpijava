/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.user;
import Services.IServiceUser;
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
 * @author Rayen Ben Driaa
 */
public class ServiceUser implements IServiceUser {
    static Maconnexion db = new Maconnexion();
    static Connection con = db.getConnection();

    @Override
    public void AddUser(user user) {
         String requete = "INSERT INTO `user` ( `Username`, `nom`, `prenom`, `Email`, `Password`)" 
                    + "VALUES ('"+user.getUsername()+"','"+user.getNom()+"','"+user.getPrenom()+"','"+user.getEmail()+"','"+user.getPassword()+"')";
                  try{
            Statement st =con.createStatement();
            st.executeUpdate(requete);
                System.out.println("personne ajouté");
            } catch (SQLException ex){
                System.out.println("erreur d'insertion");
            }
           
        
    }

    @Override
    public ObservableList<user> ListUser() {
         ObservableList<user> os = FXCollections.observableArrayList();
        try {
            String query = "select * from user";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                    user    user = new user();
                   
                 user.setNom(rs.getString("nom"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setEmail(rs.getString("Email"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getNString("Role"));
                    os.addAll(user);
              
            } 
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return os;
    }

    @Override
    public void remove(user user) {
         int n=0;
		PreparedStatement st;
		try {
			st= con.prepareStatement("DELETE FROM `user` WHERE `Username`=?");
			
			st.setString(1, user.getUsername());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
       
    }

    @Override
    public void updateUser(user user) {
            try {
            String query="UPDATE `user` SET `Username`=?,`nom`=?,`prenom`=?,`Email`=?,`Password`=?,`Role`=?,`image`=?,`reset_token`=? WHERE id_user=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(3, user.getUsername());
            pst.setString(2, user.getNom());
            pst.setString(1, user.getPrenom());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPassword());
            pst.setString(6, user.getRole());
            pst.setString(7, user.getImage());
            pst.setString(8, user.getReset_token());
            pst.setInt(9, user.getId_user());
            
            //pst.setString(5, esc.getImage());
            pst.executeUpdate();
            System.out.println("utlisateur modifié");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login(user user) {
        String query="select * FROM `user` WHERE `Username`=? and `Password`=? ";
        try {
            PreparedStatement pst=con.prepareStatement(query);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();  
            if(rs.next()){
                System.out.println("login sucess");
            }else
            { System.out.println("login failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
        
    }
    

