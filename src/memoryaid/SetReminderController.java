/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryaid;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author madhaviunnam
 */
public class SetReminderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> reminderTypeList = FXCollections.observableArrayList("Medicine", "Doctor Appointment");

    ObservableList<String> reminderHourList = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
    ObservableList<String> reminderMinuteList = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");
    ObservableList<String> reminderSecondList = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60");

    @FXML
    private ComboBox reminderTypeBox;

    @FXML
    private ComboBox repeatOnBox;

    @FXML
    private ComboBox reminderHhBox;

    @FXML
    private ComboBox reminderMmBox;

    @FXML
    private ComboBox reminderSsBox;

    @FXML
    private Button uploadButton;

    @FXML
    private TextField imagePathText;

    @FXML
    private TextField reminderNameText;

    @FXML
    private DatePicker startDateId;

    @FXML
    private DatePicker endDateId;

    @FXML
    private TableView reminderTable;

    @FXML
    private CheckComboBox<String> multiSelectComboBox;

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked back/cancel!");
        Parent setReminder = FXMLLoader.load(getClass().getResource("Caregiver.fxml"));
        Scene set_reminder_back_scene = new Scene(setReminder);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(set_reminder_back_scene);
        app_stage.show();
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked cancel!");
        reminderNameText.clear();
        reminderTypeBox.setValue(null);
        repeatOnBox.setValue(null);
        reminderHhBox.setValue(null);
        reminderMmBox.setValue(null);
        reminderSsBox.setValue(null);
        startDateId.setValue(null);
        endDateId.setValue(null);
        imagePathText.clear();

    }

    @FXML
    private void handleUploadImageAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedImage = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        imagePathText.setText(selectedImage.getAbsolutePath());

    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) throws SQLException, IOException {
        System.out.println("Delete button selected");
        
        ReminderModel selectedItem =(ReminderModel)reminderTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String RIdToDelete = selectedItem.getReminderId();

        Statement stmt = null;
        DbConnection db = new DbConnection();
        Connection conn = db.connect();
        stmt = conn.createStatement();
        String sql1 = "DELETE FROM Reminders "
                + "WHERE RId = '" + RIdToDelete + "'";
        stmt.executeUpdate(sql1);

        String sql2 = "DELETE FROM ReminderDetails "
                + "WHERE RId = '" + RIdToDelete + "'";
        stmt.executeUpdate(sql2);
        System.out.println("Delete Successfull");
        Parent setReminder = FXMLLoader.load(getClass().getResource("SetReminder.fxml"));
        Scene set_reminder_refresh_scene = new Scene(setReminder);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(set_reminder_refresh_scene);
        app_stage.show();
    }



    @FXML
    private void handleSetReminderBtnAction(ActionEvent event) throws Exception {

        System.out.println("You clicked Add Reminder!");

        String rType = (String) reminderTypeBox.getValue();
        String rName = reminderNameText.getText();
        LocalDate rSDate = startDateId.getValue();
        LocalDate rEDate = endDateId.getValue();
        List<LocalDate> reminderDates = new ArrayList<>();

        String hours = (String) reminderHhBox.getValue();
        String minutes = (String) reminderMmBox.getValue();
        String seconds = (String) reminderSsBox.getValue();
        ObservableList<String> repeat = multiSelectComboBox.getCheckModel().getCheckedItems();

        String imgPath = imagePathText.getText();
        
        if(rType == null || rName == null || rSDate == null || rEDate == null || hours == null 
                || minutes == null || seconds == null ||repeat.size() == 0 || imgPath == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oops, an Error Dialog");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            System.out.println("Please fill all the fields");
        }
        else if (rSDate.isAfter(rEDate) == true) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Oops, an Error Dialog");
            alert.setContentText("End date cannot be before the Start date");
            alert.showAndWait();
            System.out.println("End date should be greater than start date");
        } else {
            String rTime = hours + ":" + minutes + ":" + seconds;
            if (rType != null && rName != null && !rName.isEmpty()
                    && rEDate != null && hours != null
                    && minutes != null && seconds != null && repeat != null && imgPath != null) {
             
                
                String destinationPath = "/Users/madhaviunnam/NetBeansProjects/MemoryAid/src/Reminders";
		File destinationPathObject = new File(destinationPath + "/" + rName+  ".png");
            	File sourceFilePathObject = new File(imgPath);
                FileUtils.copyFile(sourceFilePathObject, destinationPathObject);
                
                System.out.println("You clicked Add Reminder!" + rType + rName + rSDate + rEDate + rTime + repeat + imgPath);
                System.out.println("****Inside if");
                Reminder reminderToSet = new Reminder();
                reminderToSet.setReminderName(rName);
                reminderToSet.setReminderType(rType);
                reminderToSet.setStartDate(rSDate);
                reminderToSet.setEndDate(rEDate);
                
                //endDateToCheck = rEDate.plusDays(1);
                
                if (repeat.contains("Every Monday")) {
                    reminderToSet.setRepeatMon("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.MONDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                    rSDate = startDateId.getValue();
                } else {
                    reminderToSet.setRepeatMon("false");
                }
                if (repeat.contains("Every Tuesday")) {
                    reminderToSet.setRepeatTue("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.TUESDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                    rSDate = startDateId.getValue();
                } else {
                    reminderToSet.setRepeatTue("false");
                }
                if (repeat.contains("Every Wednesday")) {
                    reminderToSet.setRepeatWed("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                    rSDate = startDateId.getValue();
                } else {
                    reminderToSet.setRepeatWed("false");
                }
                if (repeat.contains("Every Thursday")) {
                    reminderToSet.setRepeatThu("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.THURSDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                    rSDate = startDateId.getValue();
                } else {
                    reminderToSet.setRepeatThu("false");
                }
                if (repeat.contains("Every Friday")) {
                    reminderToSet.setRepeatFri("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                    rSDate = startDateId.getValue();
                } else {
                    reminderToSet.setRepeatFri("false");
                }
                if (repeat.contains("Every Saturday")) {
                    reminderToSet.setRepeatSat("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                    rSDate = startDateId.getValue();
                } else {
                    reminderToSet.setRepeatSat("false");
                }
                if (repeat.contains("Every Sunday")) {
                    reminderToSet.setRepeatSun("true");
                    while (rSDate.isBefore(rEDate)) {
                        if (rSDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            reminderDates.add(rSDate);
                        }
                        rSDate = rSDate.plusDays(1);
                    }
                } else {
                    reminderToSet.setRepeatSun("false");
                }
                if (repeat.contains("None")) {
                    reminderToSet.setRepeatNone("true");
                    reminderDates.add(rSDate);
                }
                 else {
                    reminderToSet.setRepeatNone("false");
                }
                
                System.out.println("%%%%Reminder DATes" + reminderDates);
                reminderToSet.setReminderTime(rTime);
                reminderToSet.setReminderStatus("New");

                DbConnection db = new DbConnection();
                Connection conn = db.connect();
                PreparedStatement preparedStmt = null;
                String insertSql = "INSERT into Reminders(ReminderType, ReminderName, StartDate, EndDate, ReminderTime, RepeatM, RepeatT, RepeatW, RepeatTH, RepeatF, RepeatSat, RepeatSun)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
                preparedStmt = conn.prepareStatement(insertSql);
                preparedStmt.setString(1, reminderToSet.getReminderType());
                preparedStmt.setString(2, reminderToSet.getReminderName());
                preparedStmt.setString(3, reminderToSet.getStartDate().toString());
                preparedStmt.setString(4, reminderToSet.getEndDate().toString());
                preparedStmt.setString(5, reminderToSet.getReminderTime());
                preparedStmt.setString(6, reminderToSet.getRepeatMon());
                preparedStmt.setString(7, reminderToSet.getRepeatTue());
                preparedStmt.setString(8, reminderToSet.getRepeatWed());
                preparedStmt.setString(9, reminderToSet.getRepeatThu());
                preparedStmt.setString(10, reminderToSet.getRepeatFri());
                preparedStmt.setString(11, reminderToSet.getRepeatSat());
                preparedStmt.setString(12, reminderToSet.getRepeatSun());

                preparedStmt.executeUpdate();
                System.out.println("Reminder Inserted Successfully  ");
                //Get the inserted reminders RId
                String getRIdsql = "Select max(RId) as RId from Reminders";
                ResultSet rSet = conn.createStatement().executeQuery(getRIdsql);
                int newRId = 0;
                while (rSet.next()) {
                    newRId = rSet.getInt(1);

                }
                System.out.println("RID ^^^^^^^^" + newRId);
                PreparedStatement preparedStmtRDetails = null;
                for (int i = 0; i < reminderDates.size(); i++) {
                    String rDetailsSql = "Insert into ReminderDetails(RId,DetailNum,ReminderDate,ReminderStatus)"
                            + " values(?,?,?,?)";
                    preparedStmtRDetails = conn.prepareStatement(rDetailsSql);
                    preparedStmtRDetails.setInt(1, newRId);
                    preparedStmtRDetails.setInt(2, i);
                    preparedStmtRDetails.setString(3, reminderDates.get(i).toString());
                    preparedStmtRDetails.setString(4, "New");
                    preparedStmtRDetails.executeUpdate();
                    System.out.println("ReminderDetails Table Insert Successfull  ");
                }
                Parent setReminder = FXMLLoader.load(getClass().getResource("SetReminder.fxml"));
                Scene set_reminder_refresh_scene = new Scene(setReminder);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(set_reminder_refresh_scene);
                app_stage.show();

            } else {
                System.out.println("Inside else");
                //show alert
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Oops, an Error Dialog");
                alert.setContentText("Please fill all the fields");
                alert.showAndWait();

            }
        }

    }

    @FXML
    public void buildReminderTableData() throws SQLException {
        System.out.println("In builddata");

        DbConnection db = new DbConnection();
        Connection conn = db.connect();
        String SQL = "select RId,ReminderName,ReminderType,StartDate,EndDate,ReminderTime from reminders";
        ResultSet rs = conn.createStatement().executeQuery(SQL);

        TableColumn rIdCol = new TableColumn("RId");
        rIdCol.setCellValueFactory(new PropertyValueFactory<>("reminderId"));

        TableColumn reminderNameCol = new TableColumn("ReminderName");
        reminderNameCol.setCellValueFactory(new PropertyValueFactory<>("reminderName"));

        TableColumn reminderTypeCol = new TableColumn("ReminderType");
        reminderTypeCol.setCellValueFactory(new PropertyValueFactory<>("reminderType"));

        TableColumn startDateCol = new TableColumn("StartDate");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn endDateCol = new TableColumn("EndDate");
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn reminderTimeCol = new TableColumn("ReminderTime");
        reminderTimeCol.setCellValueFactory(new PropertyValueFactory<>("reminderTime"));

        reminderTable.getColumns().add(rIdCol);
        reminderTable.getColumns().add(reminderNameCol);
        reminderTable.getColumns().add(reminderTypeCol);
        reminderTable.getColumns().add(startDateCol);
        reminderTable.getColumns().add(endDateCol);
        reminderTable.getColumns().add(reminderTimeCol);

        ObservableList<Object> data = FXCollections.observableArrayList();
                
        while(rs.next()){
            String rId = String.valueOf(rs.getInt("RId"));
           
            data.add(new ReminderModel(rId, rs.getString("ReminderName"), rs.getString("ReminderType"), rs.getString("StartDate"), rs.getString("EndDate"), rs.getString("ReminderTime")));
         }
         rs.close();
         conn.close();
       
        
        
        Callback<TableColumn<ReminderModel, String>, 
            TableCell<ReminderModel, String>> cellFactory
                = (TableColumn<ReminderModel, String> p) -> new EditingCell();
        
        //Code For Reminder Name update
        
        reminderNameCol.setCellValueFactory(
            new PropertyValueFactory<>("reminderName"));
        reminderNameCol.setCellFactory(cellFactory);
        reminderNameCol.setOnEditCommit(new EventHandler<CellEditEvent<ReminderModel, String>>() {
            
            
            @Override
            public void handle(CellEditEvent<ReminderModel, String> t) {
                ((ReminderModel) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setReminderName(t.getNewValue());
                       
                System.out.println("*******" + t.getNewValue());
                ReminderModel selectedItem =(ReminderModel)reminderTable.getSelectionModel().getSelectedItem();
                System.out.println(selectedItem);
                String rIdToUpdate = selectedItem.getReminderId();
                Statement stmt = null;
                DbConnection db = new DbConnection();
                Connection conn = db.connect();

                try {
                    stmt = conn.createStatement();

                    String updateSql = "UPDATE Reminders set ReminderName = '" + t.getNewValue() + "'"
                            + "WHERE RId = '"+rIdToUpdate+"'" ;

                    stmt.executeUpdate(updateSql);
                } catch (SQLException ex) {
                    Logger.getLogger(SetReminderController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
       
        });
        
        //For ReminderType Column Update
        
        reminderTypeCol.setCellValueFactory(
            new PropertyValueFactory<>("reminderType"));
        reminderTypeCol.setCellFactory(cellFactory);
        reminderTypeCol.setOnEditCommit(new EventHandler<CellEditEvent<ReminderModel, String>>() {
            
            
            @Override
            public void handle(CellEditEvent<ReminderModel, String> t) {
                ((ReminderModel) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setReminderType(t.getNewValue());
                       
                System.out.println("*******" + t.getNewValue());
                ReminderModel selectedItem =(ReminderModel)reminderTable.getSelectionModel().getSelectedItem();
                System.out.println(selectedItem);
                String rIdToUpdate = selectedItem.getReminderId();
                Statement stmt = null;
                DbConnection db = new DbConnection();
                Connection conn = db.connect();

                try {
                    stmt = conn.createStatement();

                    String updateSql = "UPDATE Reminders set ReminderType = '" + t.getNewValue() + "'"
                            + "WHERE RId = '"+rIdToUpdate+"'" ;

                    stmt.executeUpdate(updateSql);
                } catch (SQLException ex) {
                    Logger.getLogger(SetReminderController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
       
        });
        
        //For reminderTime Column Update
        
        reminderTimeCol.setCellValueFactory(
            new PropertyValueFactory<>("reminderTime"));
        reminderTimeCol.setCellFactory(cellFactory);
        reminderTimeCol.setOnEditCommit(new EventHandler<CellEditEvent<ReminderModel, String>>() {
            
            
            @Override
            public void handle(CellEditEvent<ReminderModel, String> t) {
                ((ReminderModel) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setReminderTime(t.getNewValue());
                       
                System.out.println("*******" + t.getNewValue());
                ReminderModel selectedItem =(ReminderModel)reminderTable.getSelectionModel().getSelectedItem();
                System.out.println(selectedItem);
                String rIdToUpdate = selectedItem.getReminderId();
                Statement stmt = null;
                DbConnection db = new DbConnection();
                Connection conn = db.connect();

                try {
                    stmt = conn.createStatement();

                    String updateSql = "UPDATE Reminders set ReminderTime = '" + t.getNewValue() + "'"
                            + "WHERE RId = '"+rIdToUpdate+"'" ;

                    stmt.executeUpdate(updateSql);
                } catch (SQLException ex) {
                    Logger.getLogger(SetReminderController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
       
        });
        
        reminderTable.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reminderTypeBox.setItems(reminderTypeList);
        multiSelectComboBox.getItems().addAll("None", "Every Monday", "Every Tuesday", "Every Wednesday", "Every Thursday", "Every Friday", "Every Saturday", "Every Sunday");
        reminderHhBox.setItems(reminderHourList);
        reminderMmBox.setItems(reminderMinuteList);
        reminderSsBox.setItems(reminderSecondList);

        try {

            buildReminderTableData();
        } catch (SQLException ex) {
            Logger.getLogger(SetReminderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
