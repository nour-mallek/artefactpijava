/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Commentaire;
import Services.IServiceCommentaire;
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
 * @author Nour
 */
public class ServiceCommentaire implements IServiceCommentaire{
      Connection cnx;

    public ServiceCommentaire() {
        cnx= Maconnexion.getInstance().getConnection();
    }
    @Override
    public void AjouterCommentaire(Commentaire c,int idVoiture) {
        try {
            Statement stm =cnx.createStatement();
            String query="INSERT INTO `commentaire`(`idCommentaire`, `Contenu`, `dateCommentaire`,`idVoiture`) VALUES ("+'"'+c.getId()+'"'+","+'"'+c.getContenu()+'"'+","+'"'+c.getDate_dajout()+'"'
                  +","+'"'+idVoiture+'"'+")";
           
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    }
    @Override
    public ObservableList<Commentaire> AfficherCommentaire(int idN) {
       
            ObservableList<Commentaire> Commentaires = FXCollections.observableArrayList();

        try {
            Statement stm =cnx.createStatement();
//           
            String query="SELECT * FROM commentaire where idVoiture="+idN;
            System.out.println(query);
            ResultSet rst =stm.executeQuery(query);
            while (rst.next())
            {
                Commentaire c = new Commentaire();
                c.setId(rst.getInt("idCommentaire"));
                c.setContenu(rst.getString("Contenu"));
                c.setDate_dajout(rst.getDate("dateCommentaire"));
                
                Commentaires.add(c);
                
              
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
            return Commentaires;
    }
    

    @Override
    public void SupprimerCommentaire(Commentaire c) {
             try {
                 String query = "delete from `commentaire` where `idCommentaire`="+c.getId();
                 PreparedStatement pst = cnx.prepareStatement(query);
                 pst.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    @Override
    public void ModifierCommentaire(Commentaire c) {
             try {
                 String query = "UPDATE `commentaire` SET `Contenu`= '"+c.getContenu()+"' WHERE idCommentaire="+c.getId();
                 
                 PreparedStatement pst = cnx.prepareStatement(query);
                 pst.execute();
             } catch (SQLException ex) {
                 Logger.getLogger(ServiceCommentaire.class.getName()).log(Level.SEVERE, null, ex);
             }
    }

    
}
