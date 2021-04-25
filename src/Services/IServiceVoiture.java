/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entites.Voiture;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author Nour
 */
public interface IServiceVoiture {
   public void AddVoiture(Voiture v);
   public ObservableList<Voiture> AfficherVoiture();
    public void supprimer(Voiture v);
   public void modifier (Voiture v);
   
    




    
}
