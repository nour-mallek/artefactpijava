/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Locationv;
import javafx.collections.ObservableList;

/**
 *
 * @author Nour
 */
public interface IServiceLocationv {
    public void AddLocationv(Locationv lv);
    public ObservableList<Locationv> AfficherLocationv();
    public void supprimerlocation(Locationv lv);
    public void modifierlocation (Locationv lv);
    public String fillcomboboxvoiture(int id);
    
}
