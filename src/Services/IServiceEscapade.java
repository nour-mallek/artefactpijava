/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Escapade;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public interface IServiceEscapade {
    public void addEscapade(Escapade esc);
    public ObservableList<Escapade> ListEscapade();
    public void remove(Escapade esc);
    public void fillcombobox();
   // public void removeEsc (Escapade esc);
    public void updateEsc (Escapade esc);
    //public List<Escapade> getEscapades();
   
}
