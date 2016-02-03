/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Yatish
 */
public class FamilySpecHomeController implements Initializable {
    
    @FXML
    private Label label;
    
     @FXML
    private void handleHomeLinkAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent familyHome = FXMLLoader.load(getClass().getResource("Homepage.fxml"));       
       Scene familyHome_scene = new Scene(familyHome);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(familyHome_scene);
       app_stage.show();
    }
    
    
    @FXML
    private void HandleCalendarLinkAction(ActionEvent event) throws IOException {
         System.out.println("You clicked me!");
       Parent calendar = FXMLLoader.load(getClass().getResource("CalendarIndex.fxml"));       
       Scene calendar_scene = new Scene(calendar);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(calendar_scene);
       app_stage.show();
      
    }
     @FXML
    private void HandleHomeGalleryLink (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent GalleryHome = FXMLLoader.load(getClass().getResource("Gallery.fxml"));       
       Scene GalleryHome_scene = new Scene(GalleryHome);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(GalleryHome_scene);
       app_stage.show();
    }
     @FXML
    private void HandleHomeMessagesLink (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent MessagesHome = FXMLLoader.load(getClass().getResource("Messaages.fxml"));       
       Scene MessagesHome_scene = new Scene(MessagesHome);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(MessagesHome_scene);
       app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
