/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Mejri Wajih
 */
public class Sponsor {
    private int ref_sponsor;
    private String cin;
    private String budget;
    private String message;
    private int id_user;
    private String nom_event;

    public Sponsor(int ref_sponsor, String cin, String budget, String message, int id_user, String nom_event) {
        this.ref_sponsor = ref_sponsor;
        this.cin = cin;
        this.budget = budget;
        this.message = message;
        this.id_user = id_user;
        this.nom_event = nom_event;
    }

    public Sponsor(String cin, String budget, String message, int id_user, String nom_event) {
        this.cin = cin;
        this.budget = budget;
        this.message = message;
        this.id_user = id_user;
        this.nom_event = nom_event;
    }

    public int getRef_sponsor() {
        return ref_sponsor;
    }

    public void setRef_sponsor(int ref_sponsor) {
        this.ref_sponsor = ref_sponsor;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    @Override
    public String toString() {
        return "Sponsor{" + "ref_sponsor=" + ref_sponsor + ", cin=" + cin + ", budget=" + budget + ", message=" + message + ", id_user=" + id_user + ", nom_event=" + nom_event + '}';
    }
    
    


    
}
