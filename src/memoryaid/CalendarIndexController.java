/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import java.io.IOException;
import com.mysql.jdbc.Connection;
import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Yatish
 */
public class CalendarIndexController implements Initializable {

    @FXML
    private TableView CalendarTableId;

    @FXML
    private void HandleIndexLinkAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent CalendarIndex = FXMLLoader.load(getClass().getResource("FamilySpecHome.fxml"));
        Scene CalendarIndex_scene = new Scene(CalendarIndex);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(CalendarIndex_scene);
        app_stage.show();
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) throws SQLException, IOException {
        System.out.println("Delete button selected");

        CalendarIndex selectedItem = (CalendarIndex) CalendarTableId.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String EventRIdToDelete = selectedItem.getEventId();

        Statement stmt = null;
        DbConnection db = new DbConnection();
        com.mysql.jdbc.Connection conn = (com.mysql.jdbc.Connection) db.connect();
        stmt = conn.createStatement();
        String sql1 = "DELETE FROM calendar "
                + "WHERE Event = '" + EventRIdToDelete + "'";
        stmt.executeUpdate(sql1);

//        System.out.println("Delete Successfull");
//        Parent setReminder = FXMLLoader.load(getClass().getResource("CalendarIndex.fxml"));
//        Scene set_reminder_refresh_scene = new Scene(setReminder);
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app_stage.hide();
//        app_stage.setScene(set_reminder_refresh_scene);
//        app_stage.show();
    }

    @FXML
    private void HandleCalendarAddAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent CalendarCreate = FXMLLoader.load(getClass().getResource("CalendarCreate.fxml"));
        Scene CalendarCreate_scene = new Scene(CalendarCreate);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(CalendarCreate_scene);
        app_stage.show();
    }

    @FXML
    public void buildCalendarTableData() throws SQLException {
        System.out.println("In builddata");

        DbConnection db = new DbConnection();
        Connection conn = db.connect();
        String SQL = "select Event,EventDate from calendar";
        ResultSet rs = conn.createStatement().executeQuery(SQL);

        TableColumn eventCol = new TableColumn("Event");
        eventCol.setCellValueFactory(new PropertyValueFactory<>("EventId"));

        TableColumn EventDateCol = new TableColumn("EventDate");
        EventDateCol.setCellValueFactory(new PropertyValueFactory<>("EventDateId"));

        CalendarTableId.getColumns().add(eventCol);
        CalendarTableId.getColumns().add(EventDateCol);

        ObservableList<Object> data = FXCollections.observableArrayList();

        while (rs.next()) {

            data.add(new CalendarIndex(rs.getString("Event"), rs.getString("EventDate")));
        }
        //Code for columns update
        
        Callback<TableColumn<CalendarIndex, String>, 
            TableCell<CalendarIndex, String>> cellFactory
                = (TableColumn<CalendarIndex, String> p) -> new EditingCellCalendar();
       
        //Code For Event update
        eventCol.setCellValueFactory(
            new PropertyValueFactory<>("EventId"));
        eventCol.setCellFactory(cellFactory);
        eventCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<CalendarIndex, String>>() {
            
            
            @Override
            public void handle(TableColumn.CellEditEvent<CalendarIndex, String> t) {
                ((CalendarIndex) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setEventId(t.getNewValue());
                       
                System.out.println("*******" + t.getNewValue());
                CalendarIndex selectedItem =(CalendarIndex)CalendarTableId.getSelectionModel().getSelectedItem();
                System.out.println(selectedItem);
                String eventToUpdate = selectedItem.getEventId();
                Statement stmt = null;
                DbConnection db = new DbConnection();
                Connection conn = db.connect();

                try {
                    stmt = conn.createStatement();

                    String updateSql = "UPDATE calendar set EVENT = '" + t.getNewValue() + "'"
                            + "WHERE EVENT = '"+eventToUpdate+"'" ;

                    stmt.executeUpdate(updateSql);
                } catch (SQLException ex) {
                    Logger.getLogger(SetReminderController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
       
        });
        
        
        CalendarTableId.setItems(data);

        rs.close();
        conn.close();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            buildCalendarTableData();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarIndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
