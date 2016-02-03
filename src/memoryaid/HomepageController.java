/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 * FXML Controller class
 *
 * @author madhaviunnam
 */
public class HomepageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleElderlyPersonLink (ActionEvent event) throws IOException {
         
//        JFrame frame = new JFrame("Stephanie");
//        frame.setVisible(true);
//        frame.setResizable(false);
//        frame.setLocationRelativeTo(null);
//        JPanel panel = new stephanie.mainPanel();
//        frame.add(panel);
//        frame.pack();
//        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);

       Parent homepage = FXMLLoader.load(getClass().getResource("ElderlyPerson.fxml"));
       Scene elderly_scene = new Scene(homepage);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(elderly_scene);
       app_stage.show();
        
    }
    
    @FXML
    private void handleCaregiverLink (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent homepage = FXMLLoader.load(getClass().getResource("Caregiver.fxml"));
       Scene caregiver_scene = new Scene(homepage);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(caregiver_scene);
       app_stage.show();
    }
    
     @FXML
    private void handleFamilyMemberLink (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent homepage = FXMLLoader.load(getClass().getResource("FamilySpecHome.fxml"));
       Scene family_member_scene = new Scene(homepage);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(family_member_scene);
       app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //AlarmTimer alarmTimer = new AlarmTimer();
       
    }    
    
}
