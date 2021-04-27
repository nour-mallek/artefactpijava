/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

/**
 *
 * @author Nour
 */
public class VoitureHolder {
    private int idvoiture;
     private final static VoitureHolder INSTANCE = new VoitureHolder();
  
  private VoitureHolder() {}
 
  
  public static VoitureHolder getInstance() {
    return INSTANCE;
  }

    public int getIdvoiture() {
        return idvoiture;
    }

    public void setIdvoiture(int idvoiture) {
        this.idvoiture = idvoiture;
    }
  
 
}
