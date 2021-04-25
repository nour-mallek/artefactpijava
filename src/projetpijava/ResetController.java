/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class ResetController implements Initializable {

    @FXML
    private TextField passtf;
    @FXML
    private TextField confirmtf;
    @FXML
    private Button reset;
    static Maconnexion db = new Maconnexion();
    static Connection con = db.getConnection();
    ResultSet rs = null;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reset(ActionEvent event) throws SQLException {
        if(passtf.getText().equals(confirmtf.getText())){
            
            
           String updateQuery = "UPDATE `user` SET `Password`=? WHERE `Email`=?";
           PreparedStatement pst=con.prepareStatement(updateQuery);
           pst.setString(1,passtf.getText());
           pst.setString(2,SendcodeController.mail);
           pst.executeUpdate();
           System.out.println("Reset secuesss");

    }
        else
        {
            System.out.println("password not matching");
        }
    
}
}
