/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.SQLException;
import java.util.List;
import Entite.Sponsor;
import IService.IService;
import Utils.Maconnexion;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceSponsor implements IService<Sponsor> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceSponsor() {
        con = Maconnexion.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(Sponsor a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `sponsor` (`cin`, `budget`, `message`, `id_user`, `nom_event`) VALUES (?, ?, ?, ?, ?);");
        PS.setString(1, a.getCin());
        PS.setString(2, a.getBudget());
        PS.setString(3, a.getMessage());
        PS.setInt(4, a.getId_user());
        PS.setString(5, a.getNom_event());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `sponsor` WHERE `ref_sponsor`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Sponsor a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `sponsor` SET `cin`=?,`budget`=? ,`message`=?,`id_user`=? ,`nom_event`=?  WHERE `ref_sponsor`=?");
        PS.setString(1, a.getCin());
        PS.setString(2, a.getBudget());
        PS.setString(3, a.getMessage());
        PS.setInt(4, a.getId_user());
        PS.setString(5, a.getNom_event());
        PS.setInt(6,id);
        PS.executeUpdate();
    }



    @Override
    public List<Sponsor> readAll() throws SQLException {
        List<Sponsor> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from sponsor");
        while (rs.next()) {
            Sponsor a = new Sponsor(rs.getInt(1), rs.getString(2),rs.getString(3) , rs.getString(4) ,rs.getInt(5),rs.getString(6)  );
            AL.add(a);
        }
        return AL;
    }
    public List<Sponsor> getTrier() throws SQLException {
    List<Sponsor> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from sponsor ORDER BY cin DESC");
        while (rs.next()) {
            Sponsor a = new Sponsor(rs.getInt(1), rs.getString(2),rs.getString(3) , rs.getString(4) ,rs.getInt(5),rs.getString(6)  );
            arr.add(a);
     }
    return arr;
    }
  public Sponsor getById(int id) {
          Sponsor a = null;
         String requete = " select* from sponsor where ref_sponsor='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Sponsor(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Sponsor getByCin(String n) {
          Sponsor a = null;
         String requete = " select* from sponsor  where (cin like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Sponsor(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  

            public int calculer(String nom) {
          int l = 0 ;
         String requete = "SELECT COUNT(*) FROM sponsor WHERE nom_event='"+nom+"'" ;
        try {
           
           Statement st =con.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
    
}
