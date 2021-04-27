/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpijava;


import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author Rayen Ben Driaa
 */
public class SendcodeController implements Initializable {

    @FXML
    private Button btnmail;
    @FXML
    private TextField email;
    @FXML
    private Button verifbtn;
    
    
    int randomCode;
    @FXML
    private TextField tfcode;
    public static String mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sendmail(ActionEvent event) throws MessagingException {
        
            Random rand = new Random();
        randomCode = rand.nextInt(999999);
        String host = "smtp.gmail.com";
        String user = "rayenbd63s@gmail.com";
        String pass = "kalkoff24180742";
        String to = email.getText();
        String subject = "Reseting Code";
        String message = "Your reset code is " + randomCode;
        boolean sessionDebug = false;
        Properties pros = System.getProperties();
        pros.put("mail.smtp.host", "smtp.gmail.com");
        pros.put("mail.smtp.socketFactory.port", "465");
        pros.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        pros.put("mail.smtp.auth", "true");
        pros.put("mail.smtp.prot", "465");

        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailSession = Session.getDefaultInstance(pros, null);
        mailSession.setDebug(sessionDebug);
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(user));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject);
        msg.setText(message);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        System.out.println("sent sucess");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Success");
             alert.setHeaderText("");
             alert.setContentText("code sent, check your Email");
             alert.show();
        
            
           

    }

    @FXML
    private void verify(ActionEvent event) throws IOException {
        if (Integer.valueOf(tfcode.getText()) == randomCode) {
        System.out.println("the code is right ");
        mail=email.getText();
        Parent root = FXMLLoader.load(getClass().getResource("reset.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();

        } else {
            System.out.println("the code is wrong ");
        }
    }

}
