/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;

/**
 *
 * @author Nour
 */
public class Commentaire {
    private int id;
    private String Contenu;
    private Date date_dajout;
    private int idVoiture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDate_dajout() {
        return date_dajout;
    }

    public void setDate_dajout(Date date_dajout) {
        this.date_dajout = date_dajout;
    }

    public int getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(int idVoiture) {
        this.idVoiture = idVoiture;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", Contenu=" + Contenu + ", date_dajout=" + date_dajout + '}';
    }
   
    

  
    
}
