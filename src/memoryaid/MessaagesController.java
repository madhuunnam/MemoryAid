/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import com.mysql.jdbc.Messages;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yatish
 */
public class MessaagesController implements Initializable {
@FXML
    private void HandleMessageLink (ActionEvent event) throws IOException {
       System.out.println("You clicked me!");
       Parent Messages = FXMLLoader.load(getClass().getResource("FamilySpecHome.fxml"));       
       Scene Messages_scene = new Scene(Messages);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       app_stage.hide();
       app_stage.setScene(Messages_scene);
       app_stage.show();
    }
    
    @FXML
    private TextArea msgTextBox2;
    @FXML
    private TextArea msgInsertText;
    @FXML
    private ImageView msgInsertImage;
    
    @FXML
    private TextArea  BuildMessagesTable;
    
    
   @FXML
    private void HandleAddEventAction(ActionEvent event) throws IOException, SQLException {
          String timestamp = 
    new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
                 System.out.println(" Time stamp"+timestamp);
        System.out.println("You clicked AddEvent!");
        System.out.println(msgTextBox2.getText());
                    DbConnection db = new DbConnection();
                Connection conn = db.connect();
                PreparedStatement preparedStmt = null;
                String insertSql = "INSERT into Messages(Name, Text) values (?,?)";
                        //+ "values(?)";
                preparedStmt = conn.prepareStatement(insertSql); 
                preparedStmt.setString(1,"Divya");
                preparedStmt.setString(2,msgTextBox2.getText());
                preparedStmt.executeUpdate();
                System.out.println("message has sent  Successfully  ");
                //Set up text in the empty text area
                msgInsertText.setText(( msgTextBox2.getText()) + "\n"+ timestamp);
               // msgInsertText.setText(timestamp);
                //Make image view visible
                msgInsertImage.setVisible(true);
                //Clear msgTextBox2
                msgTextBox2.clear();
              
    }
    
    @FXML
    private void handleMessageDeleteButtonAction(ActionEvent event) throws SQLException, IOException {
        System.out.println("Delete button selected");   
        
        //BuildMessagesTable.clear();
        msgInsertText.clear();
        
  
    }
    
      
    
    @FXML
    public void BuildMessagesTableAction() throws SQLException {
        System.out.println("In builddata");
         String timestamp = 
    new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
                 System.out.println(" Time stamp"+timestamp);

        DbConnection db = new DbConnection();
        Connection conn = db.connect();
        String SQL = "select Name, Text from messages";
        ResultSet rs = conn.createStatement().executeQuery(SQL);

                  
//        TableColumn eventCol = new TableColumn("Event");
//        eventCol.setCellValueFactory(new PropertyValueFactory<>("EventId"));
// 
//        TableColumn EventDateCol = new TableColumn("EventDate");
//        EventDateCol.setCellValueFactory(new PropertyValueFactory<>("EventDateId"));
//     
//        CalendarTableId.getColumns().add(eventCol);
//        CalendarTableId.getColumns().add(EventDateCol);
//
//        
//        ObservableList<Object> data = FXCollections.observableArrayList();
//                
        while(rs.next()){
                     
           // data.add(new CalendarIndex( rs.getString("Event"), rs.getString("EventDate")));
            BuildMessagesTable.setText(rs.getString("NAME")+":"+ rs.getString("TEXT") +"\n"+ timestamp);
         }
         //CalendarTableId.setItems(data);
         
       
      
         rs.close();
         conn.close();
        
    } 
        
        
       
    /**
     * Initializes the controller class.
     */
            // TODO
         
         
           
            
        
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        BuildMessagesTableAction();
        // TODO
    } catch (SQLException ex) {
        Logger.getLogger(MessaagesController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
    
}