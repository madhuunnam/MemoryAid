/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yatish
 */
public class CalendarCreateController implements Initializable {
     @FXML  
private void HandleCalendarCreateAction (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent CalendarAction = FXMLLoader.load(getClass().getResource("CalendarIndex.fxml"));       
       Scene CalendarCreateAction_scene = new Scene(CalendarAction);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(CalendarCreateAction_scene);
       app_stage.show();
    }

  @FXML
    private TextField EventInsertText;
  
 @FXML
    private DatePicker EventDateInsertText;
 
 @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked cancel!");
        EventInsertText.clear();
        EventDateInsertText.setValue(null);
        //imagePathText.clear();

    }
 
@FXML
    private void HandleCalendarSubmitAction(ActionEvent event) throws IOException, SQLException {        
        System.out.println("You clicked AddEvent!");
       // System.out.println(msgTextBox2.getText());
          String timestamp = 
    new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
                 System.out.println(" Time stamp"+timestamp);
                    DbConnection db = new DbConnection();
                Connection conn = db.connect();
                PreparedStatement preparedStmt = null;
                String insertSql = "INSERT into Calendar(Event, EventDate) values (?,?)";
                        //+ "values(?)";
                preparedStmt = conn.prepareStatement(insertSql); 
                preparedStmt.setString(1,EventInsertText.getText());
                      
                preparedStmt.setString(2,EventDateInsertText.getValue().toString()+"\t"+timestamp);
                // System.out.println(eventDateField);
               // preparedStmt.setString(3, reminderToSet.getStartDate().toString());
                preparedStmt.executeUpdate();
                System.out.println("message has sent  Successfully  ");
                //Set up text in the empty text area
                //msgInsertText.setText(msgTextBox2.getText());
                //Make image view visible
               // msgInsertImage.setVisible(true);
                //Clear msgTextBox2
            
           // EventDateInsertText.setValue(null);   
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
