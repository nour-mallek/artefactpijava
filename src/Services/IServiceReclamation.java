/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Reclamation;
import javafx.collections.ObservableList;

/**
 *
 * @author Rayen Ben Driaa
 */
public interface IServiceReclamation {
     public void AddReclamation (Reclamation user);
     public ObservableList<Reclamation> ListReclamation();
     public void remove(Reclamation Reclamation);
     public void updateReclamation (Reclamation user);
    
}
