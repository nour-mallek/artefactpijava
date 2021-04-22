/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author ASUS
 */
public class Escapade {
    private int id_esc;
    private int price;
    private String histoire;
    private String ville;
    private String title;
    private String image;
    public Escapade(){
    }

    public Escapade(int id_esc, int price, String histoire, String ville,String title, String image) {
        this.id_esc = id_esc;
        this.price = price;
        this.histoire = histoire;
        this.ville = ville;
        this.title = title;
        this.image = image;
    }

    public int getId_esc() {
        return id_esc;
    }

    public void setId_esc(int id_esc) {
        this.id_esc = id_esc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getHistoire() {
        return histoire;
    }

    public void setHistoire(String histoire) {
        this.histoire = histoire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Escapade{" + "id_esc=" + id_esc + ", price=" + price + ", histoire=" + histoire + ", title=" + title + ", ville=" + ville + ", image=" + image + '}';
    }
    
}
