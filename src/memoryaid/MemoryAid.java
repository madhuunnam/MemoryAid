/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author madhaviunnam
 */
public class MemoryAid extends Application {
    
    public static Stage pStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
       setPrimaryStage(stage);
        
        Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    private void setPrimaryStage(Stage pStage) {
        MemoryAid.pStage = pStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
           AlarmTimer timer = new AlarmTimer();
        launch(args);
    }
    
    
    
}
