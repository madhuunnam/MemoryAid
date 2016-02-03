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
public class EPCalendarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException {
    
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
