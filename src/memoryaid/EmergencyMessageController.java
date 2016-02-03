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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author madhaviunnam
 */

public class EmergencyMessageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void handleBackButtonAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent emergencyMsg = FXMLLoader.load(getClass().getResource("Caregiver.fxml"));
       Scene emergency_back_scene = new Scene(emergencyMsg);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(emergency_back_scene);
       app_stage.show();
    }
    
    @FXML
    private void handleOkButtonAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent emergencyMsg = FXMLLoader.load(getClass().getResource("Caregiver.fxml"));
       Scene emergency_back_scene = new Scene(emergencyMsg);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(emergency_back_scene);
       app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
