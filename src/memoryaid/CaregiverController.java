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
 *
 * @author madhaviunnam
 */
public class CaregiverController implements Initializable {
    
    @FXML
    private void handleLinkSetReminderAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent setReminder = FXMLLoader.load(getClass().getResource("SetReminder.fxml"));
       Scene set_reminder_scene = new Scene(setReminder);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(set_reminder_scene);
       app_stage.show();
    }
    @FXML
    private void handleLinkMonitorStatusAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent monitorStatus = FXMLLoader.load(getClass().getResource("MonitorStatus.fxml"));
       Scene monitor_status_scene = new Scene(monitorStatus);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(monitor_status_scene);
       app_stage.show();
    }
    @FXML
    private void handleLinkEmergencyMessageAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent emergencyMsg = FXMLLoader.load(getClass().getResource("EmergencyMessage.fxml"));
       Scene emergency_msg_scene = new Scene(emergencyMsg);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(emergency_msg_scene);
       app_stage.show();
    }
    
 
    @FXML
    private void handleBackToHomepageButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked back/cancel!");
        Parent caregiveHome = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene back_to_Homepage_scene = new Scene(caregiveHome);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(back_to_Homepage_scene);
        app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
