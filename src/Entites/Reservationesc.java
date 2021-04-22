/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.time.LocalDate;
import Service.ServiceReservationesc;

/**
 *
 * @author ASUS
 */
public class Reservationesc {
    private int ref;
    private int escapade_id;
    private int nbrepers;
    private Date dateres;
    private String commentaire;
    private int tel;
    private String title;

    public String getTitle() {
        title = new ServiceReservationesc().fillcombobox(this.ref);
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
    public Reservationesc(){
    }
/*
    public Reservationesc(int ref, int escapade_id, int nbrepers, Date dateres, String commentaire, int tel) {
        this.ref = ref;
        this.escapade_id = escapade_id;
        this.nbrepers = nbrepers;
        this.dateres = dateres;
        this.commentaire = commentaire;
        this.tel = tel;
    }
*/
    /*
    public Reservationesc(int escapade_id, int nbrepers, Date dateres, String commentaire, int tel, String title) {
        this.escapade_id = escapade_id;
        this.nbrepers = nbrepers;
        this.dateres = dateres;
        this.commentaire = commentaire;
        this.tel = tel;
        this.title = title;
    }

    */

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getEscapade_id() {
        
        return escapade_id;
    }

    public void setEscapade_id(int escapade_id) {
        this.escapade_id = escapade_id;
    }

    public int getNbrepers() {
        return nbrepers;
    }

    public void setNbrepers(int nbrepers) {
        this.nbrepers = nbrepers;
    }

    public Date getDateres() {
        return dateres;
    }

    public void setDateres(Date dateres) {
        this.dateres = dateres;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    
    
    
    
}
