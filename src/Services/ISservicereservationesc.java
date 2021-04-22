/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Escapade;
import Entites.Reservationesc;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public interface ISservicereservationesc {
     public void addResrvation(Reservationesc res);
    public ObservableList<Reservationesc> ListResrvation();
    public ObservableList<Escapade> List();
    public void remove(Reservationesc res);
   // public void removeEsc (Escapade esc);
    public void updateRes (Reservationesc res);
    public String fillcombobox(int ref);
    public ObservableList<Escapade> titreescapade();
    //public List<Escapade> getEscapades();
}
