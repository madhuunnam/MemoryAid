/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author Yatish
 */


public class GalleryController implements Initializable {
    @FXML
    private TextField imagePathText;
    
    @FXML
    private void HandleGalleryLinkAction (ActionEvent event) throws IOException {
            System.out.println("You clicked me!");
       Parent Gallery = FXMLLoader.load(getClass().getResource("FamilySpecHome.fxml"));       
       Scene Gallery_scene = new Scene(Gallery);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(Gallery_scene);
       app_stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void handleUploadButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedImage = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        imagePathText.setText(selectedImage.getAbsolutePath());

    }
    @FXML
    private void uploadImageAction(ActionEvent event) throws IOException {
    String imgPath = imagePathText.getText();
        String baseName = FilenameUtils.getBaseName(imgPath);
   
        String destinationPath = "/Users/madhaviunnam/NetBeansProjects/MemoryAid/src/GalleryImages";
	File destinationPathObject = new File(destinationPath + "/" + baseName+  ".png");
        File sourceFilePathObject = new File(imgPath);
        FileUtils.copyFile(sourceFilePathObject, destinationPathObject);
       Parent Gallery = FXMLLoader.load(getClass().getResource("Gallery1.fxml"));       
       Scene Gallery_scene = new Scene(Gallery);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(Gallery_scene);
       app_stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
