/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author madhaviunnam
 */
public class MonitorStatusController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView reportTable;

    @FXML
    private DatePicker reportStartDateId;

    @FXML
    private DatePicker reportEndDateId;

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent monitorStatus = FXMLLoader.load(getClass().getResource("Caregiver.fxml"));
        Scene monitor_status_back_scene = new Scene(monitorStatus);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(monitor_status_back_scene);
        app_stage.show();
    }

    @FXML
    private void handleShowReminderBtnAction(ActionEvent event) {
        
        System.out.println("In Show All Reminder!");
        LocalDate reportStDt = reportStartDateId.getValue();
        LocalDate reportEnDt = reportEndDateId.getValue();
        
        if(reportEnDt == null || reportStDt == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oops, an Error Dialog");
            alert.setContentText("Start date and End date cannot be blank!");
            alert.showAndWait();
            System.out.println("End date should be greater than start date!");
        }
        
        else if (reportStDt.isAfter(reportEnDt) == true) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oops, an Error Dialog");
            alert.setContentText("End date cannot be before the Start date!");
            alert.showAndWait();
            System.out.println("End date should be greater than start date!");
        } else {
                String stdt = reportStDt.toString();
                String endt = reportEnDt.toString();
            try {
                String reportSQL = "select R.RId, R.ReminderName, R.ReminderType, RD.ReminderDate, R.ReminderTime, RD.ReminderStatus as Status from Reminders as R, ReminderDetails as RD where"
                        + " R.RId = RD.RId and RD.ReminderDate BETWEEN '"+stdt+"' AND  '"+endt+"'";
                System.out.println(reportSQL);
                buildReportTableData(reportSQL);
            } catch (SQLException ex) {
                Logger.getLogger(MonitorStatusController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleMissedReminderBtnAction(ActionEvent event) {
        System.out.println("In Missed Reminder Report button action!");
        
        LocalDate reportStDt = reportStartDateId.getValue();
        LocalDate reportEnDt = reportEndDateId.getValue();
        
        if (reportStDt == null || reportEnDt == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oops, an Error Dialog");
            alert.setContentText("Start date and End date cannot be blank!");
            alert.showAndWait();
            System.out.println("End date should be greater than start date!");
        }
        else if (reportStDt.isAfter(reportEnDt) == true) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oops, an Error Dialog");
            alert.setContentText("End date cannot be before the Start date!");
            alert.showAndWait();
            System.out.println("End date should be greater than start date!");
        } else {
            String stdt = reportStDt.toString();
            String endt = reportEnDt.toString();
            try {
              String missedReportSQL = "select R.RId, R.ReminderName, R.ReminderType, RD.ReminderDate, R.ReminderTime, RD.ReminderStatus as Status from Reminders as R, ReminderDetails as RD where"
                        + " R.RId = RD.RId and RD.ReminderStatus = 'Missed' and RD.ReminderDate BETWEEN '"+stdt+"' AND  '"+endt+"'";
              
                buildReportTableData(missedReportSQL);
            } catch (SQLException ex) {
                Logger.getLogger(MonitorStatusController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void buildReportTableData(String sql) throws SQLException {

        System.out.println("In buildReportTableData!");

        ObservableList<Object> reportData = FXCollections.observableArrayList();
        DbConnection db = new DbConnection();
        Connection conn = db.connect();

        ResultSet rs = conn.createStatement().executeQuery(sql);

        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });
            reportTable.getColumns().addAll(col);
            System.out.println("Column [" + i + "] ");
        }
        while (rs.next()) {
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                //Iterate Column
                row.add(rs.getString(i));
            }
            System.out.println("Row [1] added " + row);
            reportData.add(row);
        }
        reportTable.setItems(reportData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
