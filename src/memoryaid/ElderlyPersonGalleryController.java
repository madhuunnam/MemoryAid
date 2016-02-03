/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.awt.CardLayout;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static memoryaid.CalendarProgram.pnlCalendar;

/**
 * FXML Controller class
 *
 * @author madhaviunnam
 */
public class ElderlyPersonGalleryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
     @FXML
    private void handleViewAllPicturesLink (ActionEvent event) throws IOException {
      
       Parent setReminder = FXMLLoader.load(getClass().getResource("ViewAllPicturesPage.fxml"));
       Scene set_reminder_scene = new Scene(setReminder);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(set_reminder_scene);
       app_stage.show();
       
//        AnchorPane anchorPane = new AnchorPane();
//        pnlCalendar.add(new CalendarProgram().getCalendar());
//        ListView list = new ListView();
//        AnchorPane.setTopAnchor(list, 10.0);
//     AnchorPane.setLeftAnchor(list, 10.0);
//     AnchorPane.setRightAnchor(list, 65.0);
     
     
     
//     CardLayout card = (CardLayout)firstJPanel.getLayout();
//        pnlCalendar.add(new CalendarProgram().getCalendar());
//        card.show(firstJPanel, "calenderCard");
     // Button will float on right edge
     //Button button = new Button("Add");
//     AnchorPane.setTopAnchor(button, 10.0);
//     AnchorPane.setRightAnchor(button, 10.0);
//     anchorPane.getChildren().addAll(list, button);
    }
    
     @FXML
    private void handleViewCalenderLink (ActionEvent event) throws IOException {
     
//        CardLayout card = (CardLayout)firstJPanel.getLayout();
//        pnlCalendar.add(new CalendarProgram().getCalendar());
//        card.show(firstJPanel, "calenderCard");
        
       Parent setReminder = FXMLLoader.load(getClass().getResource("EPCalendar.fxml"));
       Scene set_reminder_scene = new Scene(setReminder);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(set_reminder_scene);
       app_stage.show();
    }
    
    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
    
        Parent ePHome = FXMLLoader.load(getClass().getResource("ElderlyPerson.fxml"));
        Scene back_to_Homepage_scene = new Scene(ePHome);
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
