/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLException;
import java.util.List;
import Entite.Evenement;
import IService.IService;
import Utils.Maconnexion;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceEvenement implements IService<Evenement> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceEvenement() {
        con = Maconnexion.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Evenement a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `evenement` (`nom_event`, `start_at`, `end_at`, `ville`, `description`, `image`, `rating`) VALUES (?, ?, ?, ?, ?, ?, ?);");
        PS.setString(1, a.getNom_event());
        PS.setDate(2, a.getStart_at());
        PS.setDate(3, a.getEnd_at());
        PS.setString(4, a.getVille());
        PS.setString(5, a.getDescription());
        PS.setString(6, a.getImage());
        PS.setFloat(7, a.getRating());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `evenement` WHERE `id_event`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Evenement a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `evenement` SET `nom_event`=?,`start_at`=? ,`end_at`=?,`ville`=? ,`description`=? ,`image`=? ,`rating`=? WHERE `id_event`=?");
        PS.setString(1, a.getNom_event());
        PS.setDate(2, a.getStart_at());
        PS.setDate(3, a.getEnd_at());
        PS.setString(4, a.getVille());
        PS.setString(5, a.getDescription());
        PS.setString(6, a.getImage());
        PS.setFloat(7, a.getRating());
        PS.setInt(8,id);
        PS.executeUpdate();
    }



    @Override
    public List<Evenement> readAll() throws SQLException {
        List<Evenement> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from evenement");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            java.sql.Date datedeb = rs.getDate(3);
            java.sql.Date datefin  = rs.getDate(4);
            String ville = rs.getString(5);
            String description = rs.getString(6);
            String image = rs.getString(7);
            float rating = rs.getFloat(8);
            Evenement a = new Evenement(id, nom,datedeb , datedeb ,ville,description,image,rating  );
            AL.add(a);
        }
        return AL;
    }
    public List<Evenement> getTrier() throws SQLException {
    List<Evenement> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from evenement ORDER BY Nom_Groupe DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            java.sql.Date datedeb = rs.getDate(3);
            java.sql.Date datefin  = rs.getDate(4);
            String ville = rs.getString(5);
            String description = rs.getString(6);
            String image = rs.getString(7);
            float rating = rs.getFloat(8);
            Evenement a = new Evenement(id, nom,datedeb , datedeb ,ville,description,image,rating  );
            arr.add(a);
     }
    return arr;
    }
  public Evenement getById(int id) {
          Evenement a = null;
         String requete = " select* from evenement where Id_Groupe='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Evenement(res.getInt(1),res.getString(2),res.getDate(3),res.getDate(4),res.getString(5),res.getString(6),res.getString(7),res.getFloat(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Evenement getByName(String n) {
          Evenement a = null;
         String requete = " select* from evenement  where (Nom_Groupe like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Evenement(res.getInt(1),res.getString(2),res.getDate(3),res.getDate(4),res.getString(5),res.getString(6),res.getString(7),res.getFloat(8));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
    
}
